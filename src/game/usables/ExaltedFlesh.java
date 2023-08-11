package game.usables;

import engine_code.actors.Actor;
import engine_code.items.Item;
import engine_code.positions.Exit;
import engine_code.positions.Location;
import game.entityactions.ConsumeExaltedFleshAction;
import game.entityactions.Purchasable;
import game.entityactions.SellItemAction;
import game.utils.Status;

/**
 * The Exalted Flesh increases the outgoing damage of the Player by 30% for 5 turns.
 * Created by:
 * @author Hussain Sadheedh Shaam
 * Modified by:
 */
public class ExaltedFlesh extends Item implements Consumables, Purchasable {
    /**
     * Player can sell Exalted Flesh for 8000 runes
     */
    private final int EXALTED_PRICE = 8000;

    private ConsumeExaltedFleshAction consumeAction = new ConsumeExaltedFleshAction(this);
    private final SellItemAction sellFlesh = new SellItemAction(this, EXALTED_PRICE);

    /**
     * Variable to check whether if there are any Exalted Flesh left in the inventory.
     */
    private int count = 1;

    /***
     * Constructor for Exalted Flesh.
     */
    public ExaltedFlesh() {
        super("Exalted Flesh", 'z', false);
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


    @Override
    public void tick(Location currentLocation, Actor actor) {
        offerSale(currentLocation);
    }

    private void offerSale(Location currentLocation){
        if (checkTraderAdjacent(currentLocation) && !this.getAllowableActions().contains(this.sellFlesh)){
            this.addAction(this.sellFlesh);
        } else if (!checkTraderAdjacent(currentLocation) && this.getAllowableActions().contains(this.sellFlesh)){
            this.removeAction(this.sellFlesh);
        }
    }

    private boolean checkTraderAdjacent(Location currentLocation){
        for (Exit e: currentLocation.getExits()){
            Location adjacent = e.getDestination();
            if (adjacent.containsAnActor() && adjacent.getActor().hasCapability(Status.CAN_BUY)
                    && adjacent.getActor().hasCapability(Status.CAN_BUY_CONSUMABLES)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int buyFor() {
        return EXALTED_PRICE;
    }
}
