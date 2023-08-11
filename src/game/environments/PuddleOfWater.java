package game.environments;

import engine_code.positions.Location;
import game.enemies.EnemyFactory;
import game.enemies.eastenemy.EastEnemies;
import game.enemies.enemytype.Crustacean;
import game.enemies.westenemy.WestEnemies;

/**
 * Class for the Puddle of Water
 *
 * Created by:
 * @author Zhi Yong Lee
 * Modified by:
 *
 */
public class PuddleOfWater extends SpawningGround{

    private EnemyFactory enemyFactory;

    private final int MID_POINT = 38;

    /**
     * Constructor for Puddle of Water
     *
     */
    public PuddleOfWater() {
        super('~');
    }

    /**
     * For every tick(turn), the Puddle of Water entity will attempt to spawn a
     * Crustacean-type enemy
     *
     * If on the west side, spawn the GiantCrab.
     * If on the east side, spawn the GiantCrayfish.
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
            Crustacean cs = spawnEnemy();
            if (cs != null){
                location.addActor(cs);
            }
        }
    }

    /**
     * Creates and returns a Crustacean-type enemy
     *
     * @return a Crustacean-type enemy
     */
    public Crustacean spawnEnemy() {
        return enemyFactory.createCrustacean();
    }

}
