package game.summons;

import engine_code.actions.Action;
import engine_code.actions.ActionList;
import engine_code.actions.DoNothingAction;
import engine_code.actors.Actor;
import engine_code.displays.Display;
import engine_code.positions.GameMap;
import engine_code.weapons.WeaponItem;
import game.behaviours.*;
import game.entityactions.AttackAction;
import game.entityactions.DeathAction;
import game.entityactions.DespawnAction;
import game.resets.Resettable;
import game.utils.Status;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract GeneralAlly is created so that this class can be extended to create additional types of allies.
 *
 * Created by:
 * @author Hussain Sadheedh Shaam
 * Modified by: Zhi Yong Lee
 */
public abstract class GeneralAlly extends Actor implements Resettable{
    private Map<Integer, Behaviour> behaviours = new HashMap<>();
    /**
     * Constructor.
     *
     * @param name        the name of the Ally
     * @param displayChar the character that will represent the Summon in the display
     * @param hitPoints   the Ally's starting hit points
     */
    public GeneralAlly(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.behaviours.put(1, new AOEBehaviour());
        this.behaviours.put(2, new AttackBehaviour());
        this.behaviours.put(999, new WanderBehaviour());
    }

    /**
     * At each turn, select a valid action to perform.
     * If the Ally has died, it will despawn from the map.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return           the list of actions being performed on the Summon.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Similar to GeneralEnemy Class but removed the despawn chances.
        // Drop weapon of the Ally when Ally dies during battle.
        if (!this.isConscious()){
            new DeathAction(this);
        }

        // Check if Player is dead, then despawn the ALLIES
        if (this.hasCapability(Status.DESPAWN)){
            new DespawnAction().execute(this, map);
        }

        for (Behaviour behaviour : behaviours.values()){
            Action action = behaviour.getAction(this, map);
            if(action != null){
                return action;
            }
        }
        return new DoNothingAction();
    }

    /**
     * The Ally can be attacked by any actor that does not have IS_PLAYER, ALLY, FRIENDLY status
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return           the list of actions being performed on it
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // Allows any actors that are hostile to Ally to attack
        if(!(otherActor.hasCapability(Status.CAN_REST) || otherActor.hasCapability(Status.ALLY)) ){
            // Allows the Ally to follow the enemy
            this.behaviours.put(3, new FollowBehaviour(otherActor));
            this.addCapability(Status.IS_FOLLOWING);
            // If the actor has a weapon
            if (!otherActor.getWeaponInventory().isEmpty()){
                // Add skill check here
                for (WeaponItem w : otherActor.getWeaponInventory()){
                    actions.add(new AttackAction(this, direction, w));
                }
                // Add SkillAction of the weapon
                actions.add(otherActor.getWeaponInventory().get(0).getSkill(this, direction));
            }
            // If the actor does not have a weapon
            else {
                actions.add(new AttackAction(this, direction));
            }
        }
        return actions;
    }
}
