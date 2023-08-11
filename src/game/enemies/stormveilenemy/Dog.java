package game.enemies.stormveilenemy;

import engine_code.weapons.IntrinsicWeapon;
import game.enemies.enemytype.Canine;
import game.resets.ResetManager;
import game.utils.Status;

public class Dog extends Canine {
    /**
     * Constructor for Dog
     *
     */
    public Dog() {
        super("Dog", 'a', 104);
        ResetManager.getInstance().registerResettable(this);
        this.addCapability(Status.IS_STORMVEILMOB);
    }

    /**
     * Creates and returns an intrinsic weapon that overrides the default IntrinsicWeapon.
     *
     * The Dog 'bites' for 101 damage with a 93% hit rate
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(101, "bites", 93);
    }

    @Override
    public void reset() {
        this.addCapability(Status.DESPAWN);
    }
}
