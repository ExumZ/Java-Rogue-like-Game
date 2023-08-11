package game.enemies.westenemy;

import engine_code.weapons.IntrinsicWeapon;
import game.enemies.enemytype.Crustacean;
import game.resets.ResetManager;
import game.utils.Status;

/**
 * Class for Giant Crab
 *
 * Created by:
 * @author Zhi Yong Lee
 * Modified by:
 *
 */
public class GiantCrab extends Crustacean {

    /**
     * Constructor for GiantCrab
     *
     */
    public GiantCrab() {
        super("Giant Crab", 'C', 407);
        this.addCapability(Status.CAN_AOE);
        ResetManager.getInstance().registerResettable(this);
    }

    /**
     * Creates and returns an intrinsic weapon that overrides the default IntrinsicWeapon.
     *
     * The GiantCrab 'slams' for 208 damage with a 90% hit rate
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(208, "slams", 90);
    }

    @Override
    public void reset() {
        this.addCapability(Status.DESPAWN);
    }

}
