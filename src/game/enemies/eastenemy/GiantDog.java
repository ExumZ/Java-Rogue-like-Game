package game.enemies.eastenemy;

import engine_code.weapons.IntrinsicWeapon;
import game.enemies.enemytype.Canine;
import game.resets.ResetManager;
import game.utils.Status;

/**
 * Class for Giant Dog
 *
 * Created by:
 * @author Zhi Yong Lee
 * Modified by:
 *
 */
public class GiantDog extends Canine {

    /**
     * Constructor for GiantDog
     *
     */
    public GiantDog() {
        super("Giant Dog", 'G', 693);
        this.addCapability(Status.CAN_AOE);
        ResetManager.getInstance().registerResettable(this);
    }

    /**
     * Creates and returns an intrinsic weapon that overrides the default IntrinsicWeapon.
     *
     * The GiantDog 'slams' for 314 damage with a 90% hit rate
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(314, "slams", 90);
    }

    @Override
    public void reset() {
        this.addCapability(Status.DESPAWN);
    }

}
