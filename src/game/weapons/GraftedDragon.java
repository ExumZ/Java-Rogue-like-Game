package game.weapons;

import engine_code.weapons.WeaponItem;
import game.entityactions.Sellable;

/**
 * A weapon that does 89 damage with 90% accuracy
 * @author Taraaf
 */
public class GraftedDragon extends WeaponItem implements Sellable {


    /**
     * Constructor .
     */
    public GraftedDragon() {
        super("Grafted Dragon", 'N', 89, "slashes", 90);
    }

    @Override
    public int sellFor() {
        return 0;
    }
}
