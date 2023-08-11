package game.entityactions;

import engine_code.actions.Action;
import engine_code.actors.Actor;
import engine_code.positions.GameMap;
import game.utils.Map;
import game.utils.Status;

/**
 * An action executed whenever a player travels between the maps via the Golden Fog Doors
 * Created by:
 * @author Zhi Yong Lee
 * Modified by:
 *
 */
public class TravelAction extends Action {

    /**
     * Where does the Golden Fog Door lead to?
     */
    private String travelLocation;

    /**
     * Coordinates for the Golden Fog Doors
     */
    private final int RTH_LIM_X = 6;
    private final int RTH_LIM_Y = 22;
    private final int SVC_LIM_X = 29;
    private final int SVC_LIM_Y = 1;
    private final int LIM_SVC_X = 38;
    private final int LIM_SVC_Y = 22;
    private final int LIM_RTH_X = 9;
    private final int LIM_RTH_Y = 0;

    /**
     * Constructor for TravelAction
     */
    public TravelAction(String travelLocation) {
        this.travelLocation = travelLocation;
    }

    /**
     * When executed, the player will travel from one map to another via the Golden Fog Doors
     *
     * @param actor The actor performing the travel action.
     * @param map The map the actor is on.
     * @return A string indicating where the player traveled to.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        if (actor.hasCapability(Status.IN_LIMGRAVE) && travelLocation.equals("Stormveil Castle")){
            map.moveActor(actor, Map.stormveil.at(LIM_SVC_X, LIM_SVC_Y));
            actor.removeCapability(Status.IN_LIMGRAVE);
            actor.addCapability(Status.IN_STORMVEIL);
        }
        else if (actor.hasCapability(Status.IN_LIMGRAVE) && travelLocation.equals("Roundtable Hold")){
            map.moveActor(actor, Map.roundtable.at(LIM_RTH_X, LIM_RTH_Y));
            actor.removeCapability(Status.IN_LIMGRAVE);
            actor.addCapability(Status.IN_ROUNDTABLE);
        }
        else if (actor.hasCapability(Status.IN_ROUNDTABLE)){
            map.moveActor(actor, Map.limgrave.at(RTH_LIM_X, RTH_LIM_Y));
            actor.removeCapability(Status.IN_ROUNDTABLE);
            actor.addCapability(Status.IN_LIMGRAVE);
        }
        else if (actor.hasCapability(Status.IN_STORMVEIL)){
            map.moveActor(actor, Map.limgrave.at(SVC_LIM_X, SVC_LIM_Y));
            actor.removeCapability(Status.IN_STORMVEIL);
            actor.addCapability(Status.IN_LIMGRAVE);
        }

        return menuDescription(actor);
    }

    /**
     * Describes where the player traveled to.
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " travels to " + travelLocation;
    }

}
