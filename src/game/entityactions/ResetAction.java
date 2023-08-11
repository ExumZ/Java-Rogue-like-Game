package game.entityactions;

import engine_code.actions.Action;
import engine_code.actors.Actor;
import engine_code.displays.Display;
import engine_code.positions.GameMap;
import game.resets.ResetManager;
import game.utils.FancyMessage;
import game.utils.Status;

/**
 * An action executed if the game resets (Player dies/rests at Site of Lost Grace)
 * Created by:
 * @author Zhi Yong Lee
 * Modified by:
 *
 */
public class ResetAction extends Action{

    private final Actor actor;
    private String siteLocation;

    public ResetAction(Actor actor, String siteLocation) {
        this.actor = actor;
        this.siteLocation = siteLocation;
    }

    /**
     * Action that resets the game state and sets the spawn point of the Player
     *
     * @param target The actor being reset
     * @param map The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor target, GameMap map) {

        if (siteLocation.equals("The First Step")){
            if (actor.hasCapability(Status.SP_STORMVEIL)){
                actor.removeCapability(Status.SP_STORMVEIL);
            }
            else if (actor.hasCapability(Status.SP_ROUNDTABLE)){
                actor.removeCapability(Status.SP_ROUNDTABLE);
            }
            actor.addCapability(Status.SP_LIMGRAVE);
        }
        else if (siteLocation.equals("Stormveil Main Gate")){
            if (actor.hasCapability(Status.SP_LIMGRAVE)){
                actor.removeCapability(Status.SP_LIMGRAVE);
            }
            else if (actor.hasCapability(Status.SP_ROUNDTABLE)){
                actor.removeCapability(Status.SP_ROUNDTABLE);
            }
            actor.addCapability(Status.SP_STORMVEIL);

            if (!actor.hasCapability(Status.SOLG_STORMVEIL_DISC)){
                // Lost Grace Discovered
                for (String line : FancyMessage.LOST_GRACE_DISCOVERED.split("\n")) {
                    new Display().println(line);
                    try {
                        Thread.sleep(400);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
            actor.addCapability(Status.SOLG_STORMVEIL_DISC);
        }
        else if (siteLocation.equals("Table of Lost Grace")){
            if (actor.hasCapability(Status.SP_LIMGRAVE)){
                actor.removeCapability(Status.SP_LIMGRAVE);
            }
            else if (actor.hasCapability(Status.SP_STORMVEIL)){
                actor.removeCapability(Status.SP_STORMVEIL);
            }
            actor.addCapability(Status.SP_ROUNDTABLE);

            if (!actor.hasCapability(Status.SOLG_ROUNDTABLE_DISC)){
                // Lost Grace Discovered
                for (String line : FancyMessage.LOST_GRACE_DISCOVERED.split("\n")) {
                    new Display().println(line);
                    try {
                        Thread.sleep(400);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
            actor.addCapability(Status.SOLG_ROUNDTABLE_DISC);
        }

        ResetManager.getInstance().run();
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " rests at the " + siteLocation + ".";
    }

}
