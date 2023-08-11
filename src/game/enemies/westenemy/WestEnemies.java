package game.enemies.westenemy;

import game.enemies.EnemyFactory;
import game.enemies.eastenemy.GiantCrayfish;
import game.enemies.eastenemy.GiantDog;
import game.enemies.eastenemy.SkeletalBandit;
import game.enemies.enemytype.Canine;
import game.enemies.enemytype.Crustacean;
import game.enemies.enemytype.Humanoid;
import game.enemies.enemytype.Skeleton;
import game.utils.RandomNumberGenerator;

/**
 * Contains methods that spawns the types of enemies on the west side of the map
 * Created by:
 * @author Zhi Yong Lee
 * Modified by:
 *
 */
public class WestEnemies implements EnemyFactory {

    /**
     * Has a 2% chance of returning a GiantCrab
     *
     * @return a GiantCrab, or null depend on the number generated
     */
    @Override
    public Crustacean createCrustacean(){
        // 2% spawn rate
        if ((RandomNumberGenerator.getRandomInt(100) <= 2)) {
            return new GiantCrab();
        }
        return null;
    }

    /**
     * Has a 33% chance of returning a LoneWolf
     *
     * @return a LoneWolf, or null depend on the number generated
     */
    @Override
    public Canine createCanine(){
        // 33% spawn rate
        if ((RandomNumberGenerator.getRandomInt(100) <= 33)) {
           return new LoneWolf();
        }
        return null;
    }

    /**
     * Has a 27% chance of returning a HeavySkeletalSwordsman
     *
     * @return a HeavySkeletalSwordsman, or null depend on the number generated
     */
    @Override
    public Skeleton createSkeleton(){
        // 27% spawn rate
        if ((RandomNumberGenerator.getRandomInt(100) <= 27)) {
            return new HeavySkeletalSwordsman();
        }
        return null;
    }

    @Override
    public Humanoid createHumanoid(){
        return null;
    }

}
