package game.weapons;

import engine_code.weapons.WeaponItem;
import game.entityactions.Sellable;

/**
 * An Axe that deals 142 damage with 84% accuracy
 * @author Taraaf
 */
public class AxeOfGodrick extends WeaponItem implements Sellable {

    /**
     * Constructor
     */
    public AxeOfGodrick() {
        super("Axe of Godrick", 'T', 142, "", 84);
    }

    @Override
    public int sellFor() {
        return 0;
    }
}
