package game.environments;

import engine_code.actions.ActionList;
import engine_code.actors.Actor;
import engine_code.positions.Ground;
import engine_code.positions.Location;
import game.entityactions.TravelAction;
import game.utils.Status;

/**
 * Class for Golden Fog Door
 * Created by:
 * @author Zhi Yong Lee
 * Modified by:
 *
 */
public class GoldenFogDoor extends Ground {


    private final int Y_MID = 12;

    public GoldenFogDoor() {
        super('D');
        this.addCapability(Status.IN_LIMGRAVE);
    }

    /**
     * Returns a travel action performed by the player
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a collection of travel actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();
        if (actor.hasCapability(Status.IN_LIMGRAVE)){
            if (location.y() <= Y_MID){
                actions.add(new TravelAction("Stormveil Castle"));
            }
            else if ((location.y() > Y_MID)) {
                actions.add(new TravelAction("Roundtable Hold"));
            }
        }
        else if (actor.hasCapability(Status.IN_ROUNDTABLE)){
            actions.add(new TravelAction("Limgrave"));
        }
        else if (actor.hasCapability(Status.IN_STORMVEIL)){
            actions.add(new TravelAction("Limgrave"));
        }
        return actions;
    }
}
