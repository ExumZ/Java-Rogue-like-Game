package game.enemies.enemytype;

import game.enemies.GeneralEnemy;
import game.utils.Status;

/**
 * Abstract class for Canine-type enemies
 *
 * Created by:
 * @author Zhi Yong Lee
 * Modified by:
 *
 */
public abstract class Canine extends GeneralEnemy {

    /**
     * Constructor.
     * @param name the name of the Canine
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints the Canine's starting hit points
     */
    public Canine(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.IS_CANINE);

    }

}
