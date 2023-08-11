package game.weapons;

import engine_code.actions.Action;
import engine_code.actors.Actor;
import engine_code.positions.Exit;
import engine_code.positions.Location;
import engine_code.weapons.WeaponItem;
import game.entityactions.Purchasable;
import game.entityactions.SellWeaponAction;
import game.entityactions.Sellable;
import game.entityactions.UnsheatheAction;
import game.utils.Status;

/**
 * A katana weapon that can be used to attack the enemy.
 * It deals 115 damage with 80% hit rate
 * It has a special skill "Unsheathe" that does 2x damage with 60% hit rate.
 * Created by:
 * @author Hussain Sadheedh Shaam
 * Modified by:
 *
 */
public class Uchigatana extends WeaponItem implements Purchasable, Sellable {
    private int buyForAmt = 5000;
    private int sellForAmt = 500;
    private final SellWeaponAction uchitgatanaSellWeaponAction;

    /**
     * Constructor
     */
    public Uchigatana() {
        super("Uchigatana", ')', 115, "Slashes", 80);
        this.uchitgatanaSellWeaponAction = new SellWeaponAction(this, sellForAmt);
    }

    @Override
    public Action getSkill(Actor target, String direction) {
        return new UnsheatheAction(target, this);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        offerSale(currentLocation);
    }

    private void offerSale(Location currentLocation){
        if (checkTraderAdjacent(currentLocation) && !this.getAllowableActions().contains(this.uchitgatanaSellWeaponAction)){
            this.addAction(this.uchitgatanaSellWeaponAction);
        } else if (!checkTraderAdjacent(currentLocation) && this.getAllowableActions().contains(this.uchitgatanaSellWeaponAction)){
            this.removeAction(this.uchitgatanaSellWeaponAction);
        }
    }

    private boolean checkTraderAdjacent(Location currentLocation){
        for (Exit e: currentLocation.getExits()){
            Location adjacent = e.getDestination();
            if (adjacent.containsAnActor() && adjacent.getActor().hasCapability(Status.CAN_BUY)){
                return true;
            }
        }
        return false;
    }


    @Override
    public int buyFor() {
        return buyForAmt;
    }

    @Override
    public int sellFor() {
        return sellForAmt;
    }
}