package game.enemies.eastenemy;

import game.enemies.enemytype.Skeleton;
import game.resets.ResetManager;
import game.utils.Status;
import game.weapons.Scimitar;

/**
 * Class for Skeletal Bandit
 *
 * Created by:
 * @author Zhi Yong Lee
 * Modified by:
 *
 */
public class SkeletalBandit extends Skeleton {

    /**
     * Constructor for SkeletalBandit
     *
     */
    public SkeletalBandit() {
        super("Skeletal Bandit", 'b', 184);
        addWeaponToInventory(new Scimitar());
        ResetManager.getInstance().registerResettable(this);
    }

    @Override
    public void reset() {
        this.addCapability(Status.DESPAWN);
    }
}
