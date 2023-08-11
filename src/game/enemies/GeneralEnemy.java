package game.enemies;

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
import game.rune.RuneManager;
import game.utils.RandomNumberGenerator;
import game.utils.Status;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract Class for the General Enemies
 *
 * Created by:
 * @author Zhi Yong Lee
 * Modified by:
 *
 */
public abstract class GeneralEnemy extends Actor implements Resettable {

    private Map<Integer, Behaviour> behaviours = new HashMap<>();
    private RuneManager runeManager = RuneManager.getInstance();

    /**
     * Constructor.
     * @param name the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints the Actor's starting hit points
     */
    public GeneralEnemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.behaviours.put(1, new AOEBehaviour());
        this.behaviours.put(2, new AttackBehaviour());
        this.behaviours.put(999, new WanderBehaviour());
        this.addCapability(Status.HOSTILE_TO_PLAYER);
    }

    /**
     * At each turn, select a valid action to perform. If the enemy is not following anyone, it has 10% chance of despawning
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if(!this.hasCapability(Status.INVADER)){
            // 10% Despawn Chance
            if ((RandomNumberGenerator.getRandomInt(100) <= 10) && !(this.hasCapability(Status.IS_FOLLOWING))){
                map.removeActor(this);
            }
        }

        // Check if Player is dead, then despawn the INVADER
        if (this.hasCapability(Status.INVADER) && DeathAction.playerDeath){
            new DespawnAction().execute(this, map);
        }

        if (this.hasCapability(Status.DESPAWN)){
            new DespawnAction().execute(this, map);
        }
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * The enemies can be attacked by any actor that has the HOSTILE_TO_ENEMY status
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return           the list of actions being performed on it
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // Allows any actors that are hostile to enemy to attack this actor
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            // Allows the enemy to follow the player
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

    @Override
    // Removes the actor from the map
    public void reset() {

    }

}
