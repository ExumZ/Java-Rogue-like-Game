package game.specialItems;

import engine_code.actors.Actor;
import engine_code.items.Item;
import engine_code.positions.Exit;
import engine_code.positions.Location;
import game.entityactions.ExchangeAction;
import game.entityactions.SellItemAction;
import game.utils.Status;
import game.weapons.AxeOfGodrick;
import game.weapons.GraftedDragon;

/**
 * Item dropped by Godrick that can be exchanged with Enia for weapons
 */
public class RemembranceOfTheGrafted extends Item {

    private final SellItemAction sellItemAction;
    private final ExchangeAction exchangeAxeAction;
    private final ExchangeAction exchangeGdAction;
    private AxeOfGodrick axeOfGodrick = new AxeOfGodrick();
    private GraftedDragon graftedDragon = new GraftedDragon();
    /**
     * Constructor.
     */
    public RemembranceOfTheGrafted() {
        super("Remembrance of the Grafted", 'O', false);
        this.addCapability(Status.CAN_BE_EXCHANGED);
        this.sellItemAction = new SellItemAction(this, 20000);
        this.exchangeAxeAction = new ExchangeAction(axeOfGodrick);
        this.exchangeGdAction = new ExchangeAction(graftedDragon);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        offerSale(currentLocation);
    }

    private void offerSale(Location currentLocation){
        if (checkTraderAdjacent(currentLocation) &&
                !this.getAllowableActions().contains(this.sellItemAction) &&
                !this.getAllowableActions().contains(this.exchangeAxeAction) &&
                !this.getAllowableActions().contains(this.exchangeGdAction)){
            this.addAction(this.sellItemAction);
            this.addAction(this.exchangeAxeAction);
            this.addAction(this.exchangeGdAction);
        } else if (!checkTraderAdjacent(currentLocation) &&
                this.getAllowableActions().contains(this.sellItemAction) &&
                this.getAllowableActions().contains(this.exchangeAxeAction) &&
                this.getAllowableActions().contains(this.exchangeGdAction)){
            this.removeAction(this.sellItemAction);
            this.removeAction(this.exchangeAxeAction);
            this.removeAction(this.exchangeGdAction);
        }
    }

    private boolean checkTraderAdjacent(Location currentLocation){
        for (Exit e: currentLocation.getExits()){
            Location adjacent = e.getDestination();
            if (adjacent.containsAnActor() && adjacent.getActor().hasCapability(Status.CAN_DO_EXCHANGE)){
                return true;
            }
        }
        return false;
    }
}
