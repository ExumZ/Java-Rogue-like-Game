package game.behaviours;

import java.util.ArrayList;
import java.util.Random;

import engine_code.actions.Action;
import engine_code.actors.Actor;
import engine_code.positions.Exit;
import engine_code.positions.GameMap;
import engine_code.positions.Location;

/**
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */
public class WanderBehaviour implements Behaviour {
	
	private final Random random = new Random();

	/**
	 * Returns a MoveAction to wander to a random location, if possible.  
	 * If no movement is possible, returns null.
	 * 
	 * @param actor the Actor enacting the behaviour
	 * @param map the map that actor is currently on
	 * @return an Action, or null if no MoveAction is possible
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		ArrayList<Action> actions = new ArrayList<>();

		if (map.locationOf(actor) == null){
			return null;
		}
		
		for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
            	actions.add(exit.getDestination().getMoveAction(actor, "around", exit.getHotKey()));
            }
        }
		
		if (!actions.isEmpty()) {
			return actions.get(random.nextInt(actions.size()));
		}
		else {
			return null;
		}

	}
}
