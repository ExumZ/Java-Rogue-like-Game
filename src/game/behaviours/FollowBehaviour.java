package game.behaviours;

import engine_code.actors.Actor;
import engine_code.actions.Action;
import engine_code.positions.Exit;
import engine_code.positions.GameMap;
import engine_code.positions.Location;
import engine_code.actions.MoveActorAction;
import game.utils.Status;

/**
 * A class that figures out a MoveAction that will move the actor one step 
 * closer to a target Actor.
 *
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by: Zhi Yong Lee
 *
 */
public class FollowBehaviour implements Behaviour {

	private final Actor target;

	/**
	 * Constructor.
	 *
	 * @param subject the Actor to follow
	 */
	public FollowBehaviour(Actor subject) {
		this.target = subject;
	}

	/**
	 * Returns a MoveAction to follow the player if the enemy detects a player
	 * If there are no players around it, returns null
	 *
	 * @param actor the Actor enacting the behaviour
	 * @param map the map that actor is currently on
	 * @return an Action, or null if no MoveAction is possible
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		if(!map.contains(target) || !map.contains(actor))
			return null;

		Location here = map.locationOf(actor);
		Location there = map.locationOf(target);

		int currentDistance = distance(here, there);
		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();
			if (destination.canActorEnter(actor)) {
				int newDistance = distance(destination, there);
				if (newDistance < currentDistance) {
					actor.addCapability(Status.IS_FOLLOWING);
					return new MoveActorAction(destination, exit.getName());
				}
			}
		}
		return null;
	}

	/**
	 * Compute the Manhattan distance between two locations.
	 * 
	 * @param a the first location
	 * @param b the first location
	 * @return the number of steps between a and b if you only move in the four cardinal directions.
	 */
	private int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}
}