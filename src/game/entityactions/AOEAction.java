package game.entityactions;

import engine_code.actions.Action;
import engine_code.actors.Actor;
import engine_code.positions.Exit;
import engine_code.positions.GameMap;
import engine_code.positions.Location;
import engine_code.weapons.Weapon;
import game.utils.Status;

import java.util.Random;

/**
 * An Action to attack any enemies within a single block of its vicinity
 *
 * Created by:
 * @author Zhi Yong Lee
 * Modified by:
 *
 */

public class AOEAction extends Action {
    /**
     * The Actor that is to be attacked
     */
    private Actor target;

    /**
     * The direction of incoming attack.
     */
    private String direction;

    /**
     * Random number generator
     */
    private Random rand = new Random();

    /**
     * Weapon used for the attack
     */
    private Weapon weapon;

    /**
     * Destination
     */
    private Location destination;

    /**
     * Constructor for AOEAction
     */
    public AOEAction() {
    }

    /**
     * When executed, the chance to hit of the weapon that the Actor used is computed to determine whether
     * the actor will hit the target. If so, deal damage to all the targets around the actor.
     *
     * @param actor The actor performing the attack action.
     * @param map The map the actor is on.
     * @return the result of the attack, e.g. whether the target is killed, etc.
     * @see DeathAction
     */

    @Override
    public String execute(Actor actor, GameMap map) {
        if (weapon == null) {
            weapon = actor.getIntrinsicWeapon();
        }

        int damage = weapon.damage();

        Location here = map.locationOf(actor);
        String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
        // check all exits that contains an actor, then attack
        for (Exit exit : here.getExits()){
            destination = exit.getDestination();
            if (exit.getDestination().containsAnActor()){
                if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
                    return actor + " misses " + target + ".";
                }

                // Increase outgoing damage the Player by 30% when Exalted Flesh is consumed.
                if(actor.hasCapability(Status.CONSUME_EXALTED_FLESH)){
                    damage = (int)Math.ceil(damage * 1.3);
                    destination.getActor().hurt(damage);
                    result = actor + " " + weapon.verb() + " " + destination.getActor() + " for " + damage + " AOE damage (30%)";

                    // Halve incoming damage to the Player when Boiled Crab is consumed.
                } else if (destination.getActor().hasCapability(Status.CONSUME_BOILED_CRAB)) {
                    damage = Math.floorDiv(damage, 2);
                    destination.getActor().hurt(damage);
                    result = actor + " " + weapon.verb() + " " + destination.getActor() + " for " + damage + " AOE damage (Half)";
                } else {
                    destination.getActor().hurt(damage);
                    result = actor + " " + weapon.verb() + " " + destination.getActor() + " for " + damage + " AOE damage";
                }

                if (!destination.getActor().isConscious()) {
                    result += new DeathAction(actor).execute(destination.getActor(), map);
                }

            }
        }
        return result;
    }

    /**
     * Describes which target the actor is attacking with which weapon
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " at " + direction + " with " + (weapon != null ? weapon : "Intrinsic Weapon");
    }
}
