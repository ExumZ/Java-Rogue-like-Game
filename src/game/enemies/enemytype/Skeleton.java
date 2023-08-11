package game.enemies.enemytype;

import game.enemies.GeneralEnemy;
import game.utils.Status;

/**
 * Abstract class for Skeleton-type enemies
 *
 * Created by:
 * @author Zhi Yong Lee
 * Modified by:
 *
 */
public abstract class Skeleton extends GeneralEnemy {

    /**
     * Constructor.
     * @param name the name of the Skeleton
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints the Skeleton's starting hit points
     */
    public Skeleton(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.IS_SKELETON);
    }

}
