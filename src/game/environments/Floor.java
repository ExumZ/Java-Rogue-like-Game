package game.environments;

import engine_code.actors.Actor;
import engine_code.positions.Ground;
import game.utils.Status;

/**
 * A class that represents the floor inside a building.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */
public class Floor extends Ground {
	public Floor() {
		super('_');
	}

	@Override
	public boolean canActorEnter(Actor actor) {
		return (actor.hasCapability(Status.HOSTILE_TO_ENEMY) && !actor.hasCapability(Status.INVADER));
	}
}
