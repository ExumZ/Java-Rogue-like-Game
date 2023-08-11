package game.entityactions;

import engine_code.actions.Action;
import engine_code.actors.Actor;
import engine_code.items.Item;
import engine_code.positions.GameMap;
import game.rune.RuneManager;
import game.utils.Status;

/**
 * Action executed when the player sells an item
 */
public class SellItemAction extends Action {

    private Item item;
    private int amount;

    public SellItemAction(Item item, int amount){
        this.item = item;
        this.amount = amount;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        // if actor is carrying a remembrance of the grafted
        if(actor.hasCapability(Status.CAN_BE_EXCHANGED)){
            actor.removeItemFromInventory(this.item);
            RuneManager.getInstance().addRune(this.amount);
        }
        result += System.lineSeparator() + menuDescription(actor);
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " sells " + this.item + " for " + this.amount + " runes.";
    }
}
