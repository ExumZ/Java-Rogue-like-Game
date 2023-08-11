package game.summons;

import game.entityactions.DeathAction;
import game.jobs.Job;
import game.resets.ResetManager;
import game.utils.Status;

/**
 * Ally class extends from GeneralAlly. They are created with a random job and has capability ALLY to make sure that
 * they do not attack the Player or other Allies.
 *
 * Created by:
 * @author Hussain Sadheedh Shaam
 * Modified by: Zhi Yong Lee
 */
public class Ally extends GeneralAlly {
    /**
     * Constructor for Ally.
     */
    public Ally(Job job) {
        super(job.getJob() + " Ally", 'A', 1);
        this.resetMaxHp(job.getHp());
        this.addWeaponToInventory(job.startingWeapon());
        this.addCapability(Status.ALLY);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        ResetManager.getInstance().registerResettable(this);
    }

    @Override
    // Removes the actor from the map
    public void reset() {
        if (DeathAction.playerDeath){
            this.addCapability(Status.DESPAWN);
        }
    }
}
