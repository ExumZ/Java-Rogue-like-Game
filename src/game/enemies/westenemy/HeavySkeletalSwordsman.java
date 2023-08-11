package game.enemies.westenemy;

import game.enemies.enemytype.Skeleton;
import game.resets.ResetManager;
import game.utils.Status;
import game.weapons.Grossmesser;

/**
 * Class for Heavy Skeletal Swordsman
 *
 * Created by:
 * @author Zhi Yong Lee
 * Modified by:
 *
 */
public class HeavySkeletalSwordsman extends Skeleton {

    /**
     * Constructor for HeavySkeletalSwordsman
     *
     */
    public HeavySkeletalSwordsman() {
        super("Heavy Skeletal Swordsman", 'q', 153);
        addWeaponToInventory(new Grossmesser());
        this.addCapability(Status.IS_HSS);
        ResetManager.getInstance().registerResettable(this);
    }

    @Override
    public void reset() {
        this.addCapability(Status.DESPAWN);
    }
}
