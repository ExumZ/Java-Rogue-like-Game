package game.enemies.stormveilenemy;

import game.enemies.EnemyFactory;
import game.enemies.enemytype.Canine;
import game.enemies.enemytype.Crustacean;
import game.enemies.enemytype.Humanoid;
import game.enemies.enemytype.Skeleton;
import game.enemies.westenemy.GiantCrab;
import game.enemies.westenemy.HeavySkeletalSwordsman;
import game.enemies.westenemy.LoneWolf;
import game.utils.RandomNumberGenerator;

public class StormveilEnemies implements EnemyFactory {

    @Override
    public Crustacean createCrustacean(){
        return null;
    }

    /**
     * Has a 37% chance of returning a Dog
     *
     * @return a Dog, or null depend on the number generated
     */
    @Override
    public Canine createCanine(){
        // 37% spawn rate
        if ((RandomNumberGenerator.getRandomInt(100) <= 37)) {
            return new Dog();
        }
        return null;
    }

    @Override
    public Skeleton createSkeleton(){

        return null;
    }

    /**
     * Has a 45% chance of returning a GodrickSoldier
     *
     * @return a GodrickSoldier, or null depend on the number generated
     */
    @Override
    public Humanoid createHumanoid(){
        // 45% spawn rate
        if ((RandomNumberGenerator.getRandomInt(100) <= 45)) {
            return new GodrickSoldier();
        }
        return null;
    }

}
