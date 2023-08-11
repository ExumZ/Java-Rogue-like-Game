package game.behaviours;

import engine_code.actions.Action;
import engine_code.actors.Actor;
import engine_code.positions.Exit;
import engine_code.positions.GameMap;
import engine_code.positions.Location;
import game.entityactions.AOEAction;
import game.utils.RandomNumberGenerator;
import game.utils.Status;

/**
 * A class that serves as the logic for AOEAction
 * Checks whether there are any actors within a single block of its vicinity
 *
 * Created by:
 * @author Zhi Yong Lee
 * Modified by:
 *
 */

public class AOEBehaviour implements Behaviour {
    private Actor target;
    private String direction;
    private Location destination;


    /**
     * Logic for the AOE attacks. If the actor can perform an AOE and the exits around the actor
     * contains another actor of a different enemy-type, perform the AOE Attack.
     *
     * @param actor The actor performing the attack action.
     * @param map The map the actor is on.
     * @return the AOEAction, or null if conditions don't meet
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {

        if(!map.contains(actor))
            return null;

        Location here = map.locationOf(actor);
        int counter = 0;
        for (Exit exit : here.getExits()) {
            destination = exit.getDestination();
            if (destination.containsAnActor() && actor.hasCapability(Status.CAN_AOE)) {
                target = destination.getActor();
                if (actor.hasCapability(Status.IS_CANINE) && !target.hasCapability(Status.IS_CANINE)){
                    if (RandomNumberGenerator.getRandomInt(1, 100) <= 50) {
                        return new AOEAction();
                    }
                }
                else if (actor.hasCapability(Status.IS_CRUSTACEAN) && !target.hasCapability(Status.IS_CRUSTACEAN)){
                    if (RandomNumberGenerator.getRandomInt(1, 100) <= 50) {
                        return new AOEAction();
                    }
                }
                else if (actor.hasCapability(Status.IS_SKELETON) && !target.hasCapability(Status.IS_SKELETON)){
                    if (RandomNumberGenerator.getRandomInt(1, 100) <= 50) {
                        return new AOEAction();
                    }
                }
            }
        }
        return null;
    }
}
