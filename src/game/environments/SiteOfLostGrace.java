package game.environments;

import engine_code.actions.ActionList;
import engine_code.actors.Actor;
import engine_code.positions.Ground;
import engine_code.positions.Location;
import game.entityactions.FastTravelAction;
import game.entityactions.ResetAction;
import game.utils.Status;

/**
 * Class for the Site of Lost Grace
 *
 * Created by:
 * @author Zhi Yong Lee
 * Modified by:
 *
 */
public class SiteOfLostGrace extends Ground {

    /**
     * Constructor for Site of Lost Grace
     *
     */
    public SiteOfLostGrace() {
        super('U');
    }

    /**
     * Returns action that allows Player to reset the game or set their spawn point at
     * that particular Site of Lost Grace
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new, empty collection of Actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        // If actor can rest and is in Limgrave, we know the SOLF is The First Step
        if (actor.hasCapability(Status.CAN_REST) && actor.hasCapability(Status.IN_LIMGRAVE)){
            // Can fast travel to Stormveil Main Gate if it has been discovered prior
            if (actor.hasCapability(Status.SOLG_STORMVEIL_DISC)){
                actions.add(new FastTravelAction("Stormveil Main Gate"));
            }
            // Can fast travel to Table of Lost Grace if it has been discovered prior
            if (actor.hasCapability(Status.SOLG_ROUNDTABLE_DISC)){
                actions.add(new FastTravelAction("Table of Lost Grace"));
            }
            // Can rest at The First Step
            actions.add(new ResetAction(actor, "The First Step"));
        }
        else if (actor.hasCapability(Status.CAN_REST) && actor.hasCapability(Status.IN_STORMVEIL)){
            if (actor.hasCapability(Status.SOLG_LIMGRAVE_DISC)){
                actions.add(new FastTravelAction("The First Step"));
            }
            if (actor.hasCapability(Status.SOLG_ROUNDTABLE_DISC)){
                actions.add(new FastTravelAction("Table of Lost Grace"));
            }
            actions.add(new ResetAction(actor, "Stormveil Main Gate"));
        }
        else if (actor.hasCapability(Status.CAN_REST) && actor.hasCapability(Status.IN_ROUNDTABLE)){
            if (actor.hasCapability(Status.SOLG_STORMVEIL_DISC)){
                actions.add(new FastTravelAction("Stormveil Main Gate"));
            }
            if (actor.hasCapability(Status.SOLG_LIMGRAVE_DISC)){
                actions.add(new FastTravelAction("The First Step"));
            }
            actions.add(new ResetAction(actor, "Table of Lost Grace"));
        }
        return actions;
    }

}
