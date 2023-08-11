package game.usables;

import engine_code.actors.Actor;
import engine_code.items.Item;
import engine_code.positions.Exit;
import engine_code.positions.Location;
import game.entityactions.ConsumeGoldenSeedAction;
import game.entityactions.Purchasable;
import game.entityactions.SellItemAction;
import game.utils.Status;
/**
 * The Golden Seed will increase the number of Flask of Crimson Tears the Player can carry by 1
 * when it is consumed by the Player.
 *
 * Created by:
 * @author Hussain Sadheedh Shaam
 * Modified by:
 */
public class GoldenSeed extends Item implements Consumables, Purchasable {
    /**
     * Player can sell Golden Seed for 20,000 runes
     */
    private final int SEED_PRICE = 20000;

    private ConsumeGoldenSeedAction consumeAction = new ConsumeGoldenSeedAction(this);
    private SellItemAction sellSeed = new SellItemAction(this, SEED_PRICE);
    /**
     * Variable to check whether if there are no more Golden Seeds left in the inventory.
     */
    private int count = 1;


    /***
     * Constructor for Golden Seed.
     */
    public GoldenSeed() {
        super("Golden Seed", '^', false);
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
        if (checkTraderAdjacent(currentLocation) && !this.getAllowableActions().contains(this.sellSeed)){
            this.addAction(this.sellSeed);
        } else if (!checkTraderAdjacent(currentLocation) && this.getAllowableActions().contains(this.sellSeed)){
            this.removeAction(this.sellSeed);
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
        return SEED_PRICE;
    }
}
