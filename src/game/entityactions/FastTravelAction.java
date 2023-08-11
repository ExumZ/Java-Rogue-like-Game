package game.entityactions;

import engine_code.actions.Action;
import engine_code.actors.Actor;
import engine_code.positions.GameMap;
import game.utils.Map;
import game.utils.Status;

/**
 * An action executed whenever a player fast travels between maps via any discovered Sites of Lost Grace
 * Created by:
 * @author Zhi Yong Lee
 * Modified by:
 *
 */
public class FastTravelAction extends Action {

    /**
     * Which Site of Lost Grace?
     */
    private String siteOfLostGrace;

    /**
     * Coordinates for the Sites of Lost Grace
     */
    private final int SP_LIM_X = 38;
    private final int SP_LIM_Y = 11;
    private final int SP_SVC_X = 32;
    private final int SP_SVC_Y = 21;
    private final int SP_RTH_X = 3;
    private final int SP_RTH_Y = 2;

    /**
     * Constructor for TravelAction
     */
    public FastTravelAction(String siteOfLostGrace) {
        this.siteOfLostGrace = siteOfLostGrace;
    }

    /**
     * When executed, the player will travel from one map to another via the Sites of Lost Grace
     *
     * @param actor The actor performing the travel action.
     * @param map The map the actor is on.
     * @return A string indicating where the player traveled to.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (siteOfLostGrace.equals("The First Step")){
            map.moveActor(actor, Map.limgrave.at(SP_LIM_X,SP_LIM_Y));
            if (actor.hasCapability(Status.IN_STORMVEIL)){
                actor.removeCapability(Status.IN_STORMVEIL);
            }
            else if (actor.hasCapability(Status.IN_ROUNDTABLE)){
                actor.removeCapability(Status.IN_ROUNDTABLE);
            }
            actor.addCapability(Status.IN_LIMGRAVE);
        }
        else if (siteOfLostGrace.equals("Stormveil Main Gate")){
            map.moveActor(actor, Map.stormveil.at(SP_SVC_X,SP_SVC_Y));
            if (actor.hasCapability(Status.IN_LIMGRAVE)){
                actor.removeCapability(Status.IN_LIMGRAVE);
            }
            else if (actor.hasCapability(Status.IN_ROUNDTABLE)){
                actor.removeCapability(Status.IN_ROUNDTABLE);
            }
            actor.addCapability(Status.IN_STORMVEIL);
        }
        else if (siteOfLostGrace.equals("Table of Lost Grace")){
            map.moveActor(actor, Map.roundtable.at(SP_RTH_X,SP_RTH_Y));
            if (actor.hasCapability(Status.IN_STORMVEIL)){
                actor.removeCapability(Status.IN_STORMVEIL);
            }
            else if (actor.hasCapability(Status.IN_LIMGRAVE)){
                actor.removeCapability(Status.IN_LIMGRAVE);
            }
            actor.addCapability(Status.IN_ROUNDTABLE);
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
        return actor + " fast travels to " + siteOfLostGrace;
    }
}
