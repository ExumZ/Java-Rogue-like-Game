package game.enemies.enemytype;

import game.enemies.GeneralEnemy;
import game.utils.Status;

public abstract class Humanoid extends GeneralEnemy {
    /**
     * Constructor.
     * @param name the name of the Stormveilmob
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints the Stormveilmob's starting hit points
     */
    public Humanoid(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.IS_HUMANOID);
    }
}
