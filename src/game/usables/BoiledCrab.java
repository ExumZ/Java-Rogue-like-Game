package game.usables;

import engine_code.actors.Actor;
import engine_code.items.Item;
import engine_code.positions.Exit;
import engine_code.positions.Location;
import game.entityactions.ConsumeBoiledCrabAction;
import game.entityactions.Purchasable;
import game.entityactions.SellItemAction;
import game.utils.Status;

/**
 * The Boiled Crab halves the incoming damage of the Player for 5 turns
 * Created by:
 * @author Hussain Sadheedh Shaam
 * Modified by:
 */
public class BoiledCrab extends Item implements Consumables, Purchasable {

    /**
     * Player can sell Boiled Crab for 8000 runes
     */
    private final int CRAB_PRICE = 8000;

    private ConsumeBoiledCrabAction consumeAction = new ConsumeBoiledCrabAction(this);
    private SellItemAction sellCrab = new SellItemAction(this, CRAB_PRICE);

    /**
     * Variable to check whether if there are any Boiled Crab left in the inventory.
     */
    private int count = 1;

    /***
     * Constructor for Boiled Crab.
     *
     */
    public BoiledCrab() {
        super("Boiled Crab", '%', false);
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
        if (checkTraderAdjacent(currentLocation) && !this.getAllowableActions().contains(this.sellCrab)){
            this.addAction(this.sellCrab);
        } else if (!checkTraderAdjacent(currentLocation) && this.getAllowableActions().contains(this.sellCrab)){
            this.removeAction(this.sellCrab);
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
        return CRAB_PRICE;
    }
}
