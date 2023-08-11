package game.enemies;

import engine_code.actions.Action;
import engine_code.actions.ActionList;
import engine_code.actions.DoNothingAction;
import engine_code.actors.Actor;
import engine_code.displays.Display;
import engine_code.positions.GameMap;
import engine_code.positions.Location;
import engine_code.weapons.WeaponItem;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.entityactions.AttackAction;
import game.entityactions.DespawnAction;
import game.entityactions.ReviveAction;
import game.resets.ResetManager;
import game.resets.Resettable;
import game.utils.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class for Pile of Bones
 * Created by:
 * @author Zhi Yong Lee
 * Modified by:
 *
 */
public class PileOfBones extends Actor implements Resettable {

    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    private int count;

    private Location loc;

    /**
     * A weapon inventory, making it possible to have more than two active weapons
     * (not limited to right-hand and left-hand weapons)
     */
    private List<WeaponItem> weaponInventory = new ArrayList<>();

    public PileOfBones() {
        super("Pile Of Bones", 'x', 1);
        this.count = 0;
        ResetManager.getInstance().registerResettable(this);
    }

    /**
     * At each turn, count increases by 1. When count reaches 3, Pile of Bones will revive the fallen Skeleton
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (this.hasCapability(Status.DESPAWN)){
            new DespawnAction().execute(this, map);
        }
        if (count >= 3){
            return onRevive(this);
        }
        count++;
        return new DoNothingAction();
    }

    /**
     * The Pile of Bones can be attacked by any actor that has the HOSTILE_TO_ENEMY status
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return the list of actions being performed on it
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // Allows any actors that are hostile to enemy to attack this actor
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            // Allows the enemy to follow the player
            this.behaviours.put(2, new FollowBehaviour(otherActor));
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

    public int numberOfRunesDropped(int high, int low) {
        return game.utils.RandomNumberGenerator.getRandomInt(high, low);
    }


    /**
     * The enemies can be attacked by any actor that has the HOSTILE_TO_ENEMY status
     *
     * @param actor the actor being revived
     *
     * @return a ReviveAction that revives that skeleton
     */
    public ReviveAction onRevive(Actor actor) {
        return new ReviveAction(this);
    }

    // Removes the actor from the map
    public void reset() { this.addCapability(Status.DESPAWN); }
}
