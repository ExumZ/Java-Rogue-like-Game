package game.entityactions;

import engine_code.actions.Action;
import engine_code.actors.Actor;
import engine_code.positions.GameMap;
import game.usables.Consumables;
import game.utils.Status;

/**
 * Action class that executes if the player is conscious and has a consumable in their inventory.
 * When executed, the Player consumes the Boiled Crab. The Player will now have CONSUME_EXALTED_FLESH
 * capability for 5 turns.
 *
 * Created by:
 * @author Hussain Sadheedh Shaam
 * Modified by:
 */
public class ConsumeExaltedFleshAction extends Action {
    private Consumables consumable;

    /**
     * Constructor for ConsumeExaltedFlesh
     * @param consumable Boiled Crab
     */
    public ConsumeExaltedFleshAction(Consumables consumable){
        this.consumable = consumable;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        // && actor.hasCapability(Status.CAN_CONSUME_RUNE
        if (actor.isConscious()){
            if (consumable.consume(actor)){
                actor.addCapability(Status.CONSUME_EXALTED_FLESH);
            }
        }
        result += System.lineSeparator() + menuDescription(actor);
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes an Exalted Flesh.";
    }
}
