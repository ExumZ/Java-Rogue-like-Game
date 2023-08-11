package game.summons;

import engine_code.actions.Action;
import engine_code.actions.ActionList;
import engine_code.actors.Actor;
import engine_code.displays.Display;
import engine_code.positions.GameMap;
import game.enemies.GeneralEnemy;
import game.entityactions.DeathAction;
import game.jobs.Job;
import game.resets.ResetManager;
import game.utils.Status;

/**
 * Invader class extends from GeneralEnemy. They are created with a random job and has capability INVADER to make sure that
 * they do not attack other Invaders.
 *
 * Created by:
 * @author Hussain Sadheedh Shaam
 * Modified by: Zhi Yong Lee
 */
public class Invader extends GeneralEnemy {
    /**
     * Constructor.
     */
    public Invader(Job job) {
        super(job.getJob() + " Invader", 'ඞ', 1);
        this.resetMaxHp(job.getHp());
        this.addWeaponToInventory(job.startingWeapon());
        this.addCapability(Status.INVADER);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Status.HOSTILE_TO_PLAYER);
        ResetManager.getInstance().registerResettable(this);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Drop weapon when Invader dies during battle.
        if (!this.isConscious()){
            new DeathAction(this);
        }
        return super.playTurn(actions, lastAction, map, display);
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        return super.allowableActions(otherActor, direction, map);
    }

    @Override
    // Removes the actor from the map
    public void reset() {
        if (DeathAction.playerDeath){
            this.addCapability(Status.DESPAWN);
        }
    }
}
