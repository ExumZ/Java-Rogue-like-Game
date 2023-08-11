package game.enemies.eastenemy;

import engine_code.weapons.IntrinsicWeapon;
import game.enemies.enemytype.Crustacean;
import game.resets.ResetManager;
import game.utils.Status;

/**
 * Class for Giant Crayfish
 *
 * Created by:
 * @author Zhi Yong Lee
 * Modified by:
 *
 */
public class GiantCrayfish extends Crustacean {

    /**
     * Constructor for GiantCrayfish
     *
     */
    public GiantCrayfish() {
        super("Giant Crayfish", 'R', 4803);
        this.addCapability(Status.CAN_AOE);
        ResetManager.getInstance().registerResettable(this);
    }

    /**
     * Creates and returns an intrinsic weapon that overrides the default IntrinsicWeapon.
     *
     * The GiantCrayfish 'slams' for 527 damage with a 100% hit rate
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(527, "slams", 100);
    }

    @Override
    public void reset() {
        this.addCapability(Status.DESPAWN);
    }

}
