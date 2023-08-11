package game.enemies.eastenemy;

import game.enemies.EnemyFactory;
import game.enemies.enemytype.Canine;
import game.enemies.enemytype.Crustacean;
import game.enemies.enemytype.Humanoid;
import game.enemies.enemytype.Skeleton;
import game.utils.RandomNumberGenerator;

/**
 * Contains methods that spawns the types of enemies on the east side of the map
 *
 * Created by:
 * @author Zhi Yong Lee
 * Modified by:
 *
 */
public class EastEnemies implements EnemyFactory {

    /**
     * Has a 1% chance of returning a GiantCrayfish
     *
     * @return a GiantCrayfish, or null depend on the number generated
     */
    @Override
    public Crustacean createCrustacean(){
        // 1% spawn rate
        if ((RandomNumberGenerator.getRandomInt(100) <= 1)) {
            return new GiantCrayfish();
        }
        return null;
    }

    /**
     * Has a 4% chance of returning a GiantDog
     *
     * @return a GiantDog, or null depend on the number generated
     */
    @Override
    public Canine createCanine(){
        // 4% spawn rate
        if ((RandomNumberGenerator.getRandomInt(100) <= 4)) {
            return new GiantDog();
        }
        return null;
    }

    /**
     * Has a 27% chance of returning a SkeletalBandit
     *
     * @return a SkeletalBandit, or null depend on the number generated
     */
    @Override
    public Skeleton createSkeleton(){
        // 27% spawn rate
        if ((RandomNumberGenerator.getRandomInt(100) <= 27)) {
            return new SkeletalBandit();
        }
        return null;
    }

    @Override
    public Humanoid createHumanoid(){
        return null;
    }

}
