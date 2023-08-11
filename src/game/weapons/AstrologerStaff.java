package game.weapons;

import engine_code.actors.Actor;
import engine_code.positions.Location;
import engine_code.weapons.WeaponItem;
import game.entityactions.Purchasable;
import game.entityactions.Sellable;


/**
 * A staff weapon that can be used to attack the enemy.
 * It can be sold for 100 runes and be purchased for 800 runes.
 * It deals 274 damage with 50% hit rate
 * Created by:
 * @author Hussain Sadheedh Shaam
 * Modified by:
 *
 */
public class AstrologerStaff extends WeaponItem implements Purchasable, Sellable {
    private int buyForAmt = 800;
    private int sellForAmt = 100;

    /**
     * Constructor .
     *
     */
    public AstrologerStaff() {
        super("Astrologer's Staff", 'f', 274, "attack", 50);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {}

    @Override
    public int buyFor() {
        return buyForAmt;
    }

    @Override
    public int sellFor() {
        return sellForAmt;
    }
}
