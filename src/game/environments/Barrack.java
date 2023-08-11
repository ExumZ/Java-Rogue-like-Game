package game.environments;

import engine_code.positions.Location;
import game.enemies.EnemyFactory;
import game.enemies.enemytype.Humanoid;
import game.enemies.stormveilenemy.StormveilEnemies;

/**
 * Class for Barrack spawning ground
 * Created by:
 * @author Zhi Yong Lee
 * Modified by:
 *
 */
public class Barrack extends SpawningGround{
    private EnemyFactory enemyFactory;

    /**
     * Constructor for Barrack
     *
     */
    public Barrack() {
        super('B');
    }

    /**
     * For every tick(turn), the Barrack entity will attempt to spawn a
     * Humanoid-type enemy
     *
     * Will spawn Godrick Soldiers
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        enemyFactory = new StormveilEnemies();
        if(!location.containsAnActor()){
            Humanoid hm = spawnEnemy();
            if (hm != null){
                location.addActor(hm);
            }
        }
    }

    /**
     * Creates and returns a Humanoid-type enemy
     *
     * @return a Humanoid-type enemy
     */
    public Humanoid spawnEnemy() { return enemyFactory.createHumanoid(); }
}
