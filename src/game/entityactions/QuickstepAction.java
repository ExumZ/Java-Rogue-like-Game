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
 * Special skill for the GreatKnife.
 *
 * Created by:
 * @author Hussain Sadheedh Shaam
 * Modified by:
 *
 */
public class QuickstepAction extends Action {
    /**
     * The Actor that is to be attacked
     */
    private Actor target;

    /**
     * Random number generator
     */
    private Random rand = new Random();

    /**
     * Weapon used for the attack
     */
    private Weapon weapon;

    /**
     * The direction of incoming attack.
     */
    private String direction;

    /**
     * Location where the Player moves after performing Quickstep
     */
    private Location newDestination;

    /**
     * Weapon verb
     */
    private String verb = "Quickstep";

    /**
     * Constructor for QuickstepAction.
     *
     * @param target the Actor to attack
     * @param weapon the weapon that uses the special skill for attacking.
     */
    public QuickstepAction(Actor target, Weapon weapon){
        this.target = target;
        this.weapon = weapon;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";

        Location here = map.locationOf(actor);
        for (Exit exit : here.getExits()) {
            if (exit.getDestination().getActor() == null || exit.getDestination().getGround() == null){
                newDestination = exit.getDestination();
                direction = exit.getName();
            }
        }

        if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
            map.moveActor(actor, newDestination);
            return actor + " misses " + target + " and moves " + direction + ".";
        }

        int damage = weapon.damage();
        if (newDestination == null){
            return verb + " could not be performed.";
        } else {
            map.moveActor(actor, newDestination);

            // Increase outgoing damage the Player by 30% when Exalted Flesh is consumed.
            if(actor.hasCapability(Status.CONSUME_EXALTED_FLESH)){
                damage = (int)Math.ceil(damage * 1.3);
                target.hurt(damage);
                result = actor + " performs " + verb + " on " + target + " for " + damage + " damage (30%) and moves " + direction;

                // Halve incoming damage to the Player when Boiled Crab is consumed.
            } else if (target.hasCapability(Status.CONSUME_BOILED_CRAB)) {
                damage = Math.floorDiv(damage, 2);
                target.hurt(damage);
                result = actor + " performs " + verb + " on " + target + " for " + damage + " damage (Half) and moves " + direction;
            } else {
                target.hurt(damage);
                result = actor + " performs " + verb + " on " + target + " for " + damage + " damage and moves " + direction;
            }
            if (!target.isConscious()) {
                result += new DeathAction(actor).execute(target, map);
            }

        }

        return result;
    }

    /**
     * Describes which target the actor is attacking with which weapon's special skill
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " performs " + verb + " with the " + weapon + " on " + target;
    }
}