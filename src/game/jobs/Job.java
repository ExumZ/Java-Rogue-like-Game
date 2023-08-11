package game.jobs;

import engine_code.weapons.WeaponItem;

/**
 * Base class for jobs which includes all the base stat points for the starting class for a player.
 * @author Sadeedh
 * Modified by: Taraaf Tazeez Khalidi
 */

public abstract class Job{
    /**
     * The weapon that is assigned to the player based on the selection made by the player
     * @return the job-specific weapon the player will have equipped
     */
    public abstract WeaponItem startingWeapon();

    /**
     * The hp of the player based on player selection
     * @return the job-specific hp the player will spawn with
     */
    public abstract int getHp();

    /**
     * Return the name of the job
     */
    public abstract String getJob();
}