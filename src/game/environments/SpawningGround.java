package game.environments;

import engine_code.positions.Ground;

/**
 * Abstract class for Spawning Grounds
 *
 * Created by:
 * @author Zhi Yong Lee
 * Modified by:
 *
 */
public abstract class SpawningGround extends Ground {

    /**
     * Constructor General Spawning Ground
     *
     * @param displayChar character to display for this type of terrain
     */
    public SpawningGround(char displayChar) {
        super(displayChar);
    }

}
