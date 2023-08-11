package game.enemies;

import game.enemies.enemytype.Canine;
import game.enemies.enemytype.Crustacean;
import game.enemies.enemytype.Skeleton;
import game.enemies.enemytype.Humanoid;

/**
 * Interface that contains the skeleton methods for spawning the types of enemies
 *
 * Created by:
 * @author Zhi Yong Lee
 * Modified by:
 *
 */
public interface EnemyFactory {

    Canine createCanine();
    Crustacean createCrustacean();
    Skeleton createSkeleton();
    Humanoid createHumanoid();

}
