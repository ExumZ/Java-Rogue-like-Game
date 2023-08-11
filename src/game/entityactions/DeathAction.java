package game.entityactions;

import engine_code.actions.Action;
import engine_code.actions.ActionList;
import engine_code.actors.Actor;
import engine_code.displays.Display;
import engine_code.items.Item;
import engine_code.positions.GameMap;
import engine_code.positions.Location;
import engine_code.weapons.WeaponItem;
import game.Player;
import game.enemies.PileOfBones;
import game.resets.ResetManager;
import game.rune.Rune;
import game.rune.RuneManager;
import game.utils.FancyMessage;
import game.utils.Map;
import game.utils.Status;

/**
 * An action executed if an actor is killed.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Zhi Yong Lee and Taraaf Tazeez Khalidi
 *
 */
public class DeathAction extends Action{
    private Actor actor;
    private Location location;
    private Rune droppedRune;
    private static Location previousDeathLocation;
    public static boolean playerDeath = false;

    /**
     * Coordinates for the Sites of Lost Grace
     */
    private final int SP_LIM_X = 38;
    private final int SP_LIM_Y = 11;
    private final int SP_SVC_X = 32;
    private final int SP_SVC_Y = 21;
    private final int SP_RTH_X = 3;
    private final int SP_RTH_Y = 2;


    public DeathAction(Actor actor) {
        this.actor = actor;
    }

    /**
     * When the target is killed, the items and weapons carried by target
     * will be dropped to the location in the game map where the target was
     *
     * @param target The actor performing the action.
     * @param map The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor target, GameMap map) {
        String result = "";

        // Death for Skeletons
        if (target.hasCapability(Status.IS_SKELETON)){
            // If skeleton dies, spawn pile of bones
            location = map.locationOf(target);
            PileOfBones pb = new PileOfBones();
            if (target.hasCapability(Status.IS_HSS)){
                pb.addCapability(Status.IS_HSS);
            }
            pb.addWeaponToInventory(target.getWeaponInventory().get(0));
            map.removeActor(target);
            map.at(location.x(), location.y()).addActor(pb);
            result += System.lineSeparator() + target + " is killed and turned into a pile of bones.";
        }
        // Death for Player
        else if (target.hasCapability(Status.CAN_REST)){
            playerDeath = true;
            Location here = Player.previousLocation;
            if (previousDeathLocation != null && previousDeathLocation.getItems().size() > 0){
                if (previousDeathLocation.getItems().get(0).hasCapability(Status.DROPPED_RUNE)){
                    previousDeathLocation.removeItem(previousDeathLocation.getItems().get(0));
                }
            }

            previousDeathLocation = here;
            if (RuneManager.getInstance().getTotalAmountOfRunes() > 0){
                // Drops player runes at location of the previous turn before they die
                droppedRune = RuneManager.getInstance().dropRune();
                droppedRune.addCapability(Status.DROPPED_RUNE);
                here.addItem(droppedRune);
                RuneManager.getInstance().deductRunes();
            }

            if (target.hasCapability(Status.SP_LIMGRAVE)){
                map.moveActor(target, Map.limgrave.at(SP_LIM_X,SP_LIM_Y));
                if (actor.hasCapability(Status.IN_STORMVEIL)){
                    actor.removeCapability(Status.IN_STORMVEIL);
                }
                else if (actor.hasCapability(Status.IN_ROUNDTABLE)){
                    actor.removeCapability(Status.IN_ROUNDTABLE);
                }
                actor.addCapability(Status.IN_ROUNDTABLE);
            }
            else if (target.hasCapability(Status.SP_STORMVEIL)){
                map.moveActor(target, Map.stormveil.at(SP_SVC_X,SP_SVC_Y));
                if (actor.hasCapability(Status.IN_LIMGRAVE)){
                    actor.removeCapability(Status.IN_LIMGRAVE);
                }
                else if (actor.hasCapability(Status.IN_ROUNDTABLE)){
                    actor.removeCapability(Status.IN_ROUNDTABLE);
                }
                actor.addCapability(Status.IN_STORMVEIL);
            }
            else if (target.hasCapability(Status.SP_ROUNDTABLE)){
                map.moveActor(target, Map.roundtable.at(SP_RTH_X,SP_RTH_Y));
                if (actor.hasCapability(Status.IN_LIMGRAVE)){
                    actor.removeCapability(Status.IN_LIMGRAVE);
                }
                else if (actor.hasCapability(Status.IN_STORMVEIL)){
                    actor.removeCapability(Status.IN_STORMVEIL);
                }
                actor.addCapability(Status.IN_ROUNDTABLE);
            }
            ResetManager.getInstance().run();
            playerDeath = false;

            // YOU DIED
            for (String line : FancyMessage.YOU_DIED.split("\n")) {
                new Display().println(line);
                try {
                    Thread.sleep(400);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
        // Death for Anything else
        else {
            ActionList dropActions = new ActionList();
            // drop all items
            for (Item item : target.getItemInventory())
                dropActions.add(item.getDropAction(target));
            for (WeaponItem weapon : target.getWeaponInventory())
                dropActions.add(weapon.getDropAction(target));
            for (Action drop : dropActions)
                drop.execute(target, map);
            // remove actor
            map.removeActor(target);
            result += System.lineSeparator() + menuDescription(target);
            if (target.hasCapability(Status.HOSTILE_TO_PLAYER)){
                RuneManager.getInstance().transferRunes(target.getDisplayChar());
            }
        }

        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " is killed.";
    }
}
