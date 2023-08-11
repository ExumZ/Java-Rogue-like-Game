package game.environments;

import engine_code.positions.Location;
import game.enemies.EnemyFactory;
import game.enemies.eastenemy.EastEnemies;
import game.enemies.enemytype.Canine;
import game.enemies.westenemy.WestEnemies;

/**
 * Class for the Gust of Wind
 * Created by:
 * @author Zhi Yong Lee
 * Modified by:
 *
 */
public class GustOfWind extends SpawningGround{

    private EnemyFactory enemyFactory;

    private final int MID_POINT = 38;

    /**
     * Constructor for Gust of Wind
     *
     */
    public GustOfWind() {
        super('&');
    }

    /**
     * For every tick(turn), the Gust of Wind entity will attempt to spawn a
     * Canine-type enemy
     *
     * If on the west side, spawn the LoneWolf.
     * If on the east side, spawn the GiantDog.
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
            Canine cn = spawnEnemy();
            if (cn != null){
                location.addActor(cn);
            }
        }
    }

    /**
     * Creates and returns a Canine-type enemy
     *
     * @return a Canine-type enemy
     */
    public Canine spawnEnemy() { return enemyFactory.createCanine(); }
}
