package game.enemies.enemytype;

import game.enemies.GeneralEnemy;
import game.utils.Status;

/**
 * Abstract class for Crustacean-type enemies
 *
 * Created by:
 * @author Zhi Yong Lee
 * Modified by:
 *
 */
public abstract class Crustacean extends GeneralEnemy {

    /**
     * Constructor.
     * @param name the name of the Crustacean
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints the Crustacean's starting hit points
     */
    public Crustacean(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.IS_CRUSTACEAN);
    }


}
