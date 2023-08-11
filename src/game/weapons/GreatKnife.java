package game.weapons;

import engine_code.actions.Action;
import engine_code.actors.Actor;
import engine_code.positions.Exit;
import engine_code.positions.Location;
import engine_code.weapons.WeaponItem;
import game.entityactions.QuickstepAction;
import game.entityactions.SellWeaponAction;
import game.utils.Status;

/**
 * A dagger weapon that can be used to attack the enemy.
 * It deals 75 damage with 70% hit rate
 * It has a special skill "Quickstep" that does normal damage and then moves away from the enemy, evading their attacks.
 * Created by:
 * @author Hussain Sadheedh Shaam
 * Modified by:
 *
 */
public class GreatKnife extends WeaponItem {

    private final int sellForAmt = 600;
    private SellWeaponAction greatKnifeSellWeaponAction;
    /**
     * Constructor
     */
    public GreatKnife() {
        super("Great Knife", '/', 75, "Slashes / Pierces", 70);
        this.greatKnifeSellWeaponAction = new SellWeaponAction(this, sellForAmt);
    }

    @Override
    public Action getSkill(Actor target, String direction) {
        return new QuickstepAction(target, this);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        offerSale(currentLocation);
    }

    private void offerSale(Location currentLocation){
        if (checkTraderAdjacent(currentLocation) && !this.getAllowableActions().contains(this.greatKnifeSellWeaponAction)){
            this.addAction(this.greatKnifeSellWeaponAction);
        } else if (!checkTraderAdjacent(currentLocation) && this.getAllowableActions().contains(this.greatKnifeSellWeaponAction)){
            this.removeAction(this.greatKnifeSellWeaponAction);
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