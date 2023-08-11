package game.entityactions;

import java.util.Random;

import engine_code.actions.Action;
import engine_code.actors.Actor;
import engine_code.positions.GameMap;
import engine_code.weapons.Weapon;
import game.utils.Status;

/**
 * An Action to attack another Actor.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Zhi Yong Lee
 *
 */
public class AttackAction extends Action {

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
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 * @param direction the direction where the attack should be performed (only used for display purposes)
	 */
	public AttackAction(Actor target, String direction, Weapon weapon) {
		this.target = target;
		this.direction = direction;
		this.weapon = weapon;
	}

	/**
	 * Constructor with intrinsic weapon as default
	 *
	 * @param target the actor to attack
	 * @param direction the direction where the attack should be performed (only used for display purposes)
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	/**
	 * When executed, the chance to hit of the weapon that the Actor used is computed to determine whether
	 * the actor will hit the target. If so, deal damage to the target and determine whether the target is killed.
	 *
	 * @param actor The actor performing the attack action.
	 * @param map The map the actor is on.
	 * @return the result of the attack, e.g. whether the target is killed, etc.
	 * @see DeathAction
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		String result = "";
		if (weapon == null) {
			weapon = actor.getIntrinsicWeapon();
		}

		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}

		int damage = weapon.damage();

		// Increase outgoing damage the Player by 30% when Exalted Flesh is consumed.
		if(actor.hasCapability(Status.CONSUME_EXALTED_FLESH)){
			damage = (int)Math.ceil(damage * 1.3);
			result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage. (30%)";

			// Halve incoming damage to the Player when Boiled Crab is consumed.
		} else if (target.hasCapability(Status.CONSUME_BOILED_CRAB)) {
			damage = Math.floorDiv(damage, 2);
			result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage. (Half)";
		} else {
			result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		}

		target.hurt(damage);

		if (!target.isConscious()) {
			result += new DeathAction(actor).execute(target, map);
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
