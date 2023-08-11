package game.entityactions;

import engine_code.actions.Action;
import engine_code.actors.Actor;
import engine_code.positions.GameMap;
import engine_code.weapons.Weapon;
import game.utils.Status;

import java.util.Random;
/**
 * Special skill for the Uchigatana. Doubles the damage.
 *
 * Created by:
 * @author Hussain Sadheedh Shaam
 * Modified by:
 *
 */
public class UnsheatheAction extends Action {
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
     * Damage when performing UnsheatheAction
     */
    private int damage;

    /**
     * hitRate when performing UnsheatheAction
     */
    private int hitRate;

    /**
     * Weapon verb
     */
    private String verb = "Unsheathe";

    /**
     * Constructor for UnsheatheAction.
     *
     * @param target the Actor to attack
     * @param weapon the weapon that uses the special skill for attacking.
     */
    public UnsheatheAction(Actor target, Weapon weapon){
        this.target = target;
        this.weapon = weapon;

        damage = weapon.damage() * 2;
        hitRate = 60;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (!(rand.nextInt(100) <= hitRate)) {
            return actor + " misses " + target + ".";
        }

        String result = "";
        // Increase outgoing damage the Player by 30% when Exalted Flesh is consumed.
        if(actor.hasCapability(Status.CONSUME_EXALTED_FLESH)){
            damage = (int)Math.ceil(damage * 1.3);
            target.hurt(damage);
            result = actor + " " + verb + " " + target + " for " + damage + " damage. (30%)";

            // Halve incoming damage to the Player when Boiled Crab is consumed.
        } else if (target.hasCapability(Status.CONSUME_BOILED_CRAB)) {
            damage = Math.floorDiv(damage, 2);
            target.hurt(damage);
            result = actor + " " + verb + " " + target + " for " + damage + " damage. (Half)";
        } else {
            target.hurt(damage);
            result = actor + " " + verb + " " + target + " for " + damage + " damage.";
        }

        if (!target.isConscious()) {
            result += new DeathAction(actor).execute(target, map);
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