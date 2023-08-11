package game.weapons;

import engine_code.actors.Actor;
import engine_code.positions.Exit;
import engine_code.positions.Location;
import engine_code.weapons.WeaponItem;
import game.entityactions.SellWeaponAction;
import game.utils.Status;

/**
 * A simple weapon that can be used to attack the enemy.
 * It deals 103 damage with 80% hit rate
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Club extends WeaponItem {
    private int sellForAmt = 100;
    private final SellWeaponAction clubSellWeaponAction;

    /**
     * Constructor
     */
    public Club() {
        super("Club", '!', 103, "bonks", 80);
        this.clubSellWeaponAction = new SellWeaponAction(this, sellForAmt);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        offerSale(currentLocation);
    }

    private void offerSale(Location currentLocation){
        if (checkTraderAdjacent(currentLocation) && !this.getAllowableActions().contains(this.clubSellWeaponAction)){
            this.addAction(this.clubSellWeaponAction);
        } else if (!checkTraderAdjacent(currentLocation) && this.getAllowableActions().contains(this.clubSellWeaponAction)){
            this.removeAction(this.clubSellWeaponAction);
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

}
