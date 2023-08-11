package game.rune;

import engine_code.actors.Actor;
import engine_code.items.Item;
import game.entityactions.ConsumeGoldenRuneAction;
import game.usables.Consumables;
import game.utils.Status;

/**
 * A special item that is spread around the map and can be consumed to grant runes to the player
 * @author Taraaf
 */
public class GoldenRune extends Item implements Consumables {

    private ConsumeGoldenRuneAction consumeAction = new ConsumeGoldenRuneAction(this);
    private int count = 1;

    /***
     * Constructor.
     */
    public GoldenRune() {
        super("Golden Rune", '*', true);
        this.addAction(consumeAction);
        this.addCapability(Status.CAN_CONSUME_RUNE);
    }

    @Override

    public boolean consume(Actor actor) {
        if (this.count != 0){
            count -=1;
            if (this.count == 0){
                this.removeAction(consumeAction);
                actor.removeItemFromInventory(this);
            } return true;
        } else return false;
    }


}
