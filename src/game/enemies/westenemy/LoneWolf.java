package game.enemies.westenemy;

import engine_code.weapons.IntrinsicWeapon;
import game.enemies.enemytype.Canine;
import game.resets.ResetManager;
import game.utils.Status;

/**
 * Class for Lone Wolf
 *
 * Created by:
 * @author Adrian Kryistanto
 * Modified by: Zhi Yong Lee
 *
 */
public class LoneWolf extends Canine {

    /**
     * Constructor for LoneWolf
     *
     */
    public LoneWolf() {
        super("Lone Wolf", 'h', 102);
        ResetManager.getInstance().registerResettable(this);
    }

    /**
     * Creates and returns an intrinsic weapon that overrides the default IntrinsicWeapon.
     *
     * The LoneWolf 'bites' for 97 damage with a 95% hit rate
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(97, "bites", 95);
    }

    @Override
    public void reset() {
        this.addCapability(Status.DESPAWN);
    }
}
