package game.environments;

import engine_code.positions.Location;
import game.enemies.EnemyFactory;
import game.enemies.enemytype.Canine;
import game.enemies.stormveilenemy.StormveilEnemies;

/**
 * Class for Cage spawning ground
 * Created by:
 * @author Zhi Yong Lee
 * Modified by:
 *
 */
public class Cage extends SpawningGround{
    private EnemyFactory enemyFactory;

    /**
     * Constructor for Cage
     *
     */
    public Cage() {
        super('<');
    }

    /**
     * For every tick(turn), the Cage entity will attempt to spawn a
     * Canine-type enemy
     *
     * Will spawn Dogs
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        enemyFactory = new StormveilEnemies();
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
