package game.environments;

import engine_code.positions.Location;
import game.enemies.EnemyFactory;
import game.enemies.eastenemy.EastEnemies;
import game.enemies.enemytype.Skeleton;
import game.enemies.westenemy.WestEnemies;

/**
 * Class for the Graveyard
 *
 * Created by:
 * @author Zhi Yong Lee
 * Modified by:
 *
 */
public class Graveyard extends SpawningGround{

    private EnemyFactory enemyFactory;

    private final int MID_POINT = 38;

    /**
     * Constructor for Graveyard
     *
     */
    public Graveyard() {
        super('n');
    }

    /**
     * For every tick(turn), the Puddle of Water entity will attempt to spawn a
     * Skeleton-type enemy
     *
     * If on the west side, spawn the HeavySkeletalSwordsman.
     * If on the east side, spawn the SkeletalBandit.
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (location.x() < MID_POINT){
            enemyFactory = new WestEnemies();
        }
        else {
            enemyFactory = new EastEnemies();
        }
        if(!location.containsAnActor()){
            Skeleton sk = spawnEnemy();
            if (sk != null){
                location.addActor(sk);
            }
        }
    }

    /**
     * Creates and returns a Skeleton-type enemy
     *
     * @return a Skeleton-type enemy
     */
    public Skeleton spawnEnemy() {
        return enemyFactory.createSkeleton();
    }

}
