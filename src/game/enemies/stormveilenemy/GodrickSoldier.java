package game.enemies.stormveilenemy;

import game.enemies.enemytype.Humanoid;
import game.resets.ResetManager;
import game.utils.Status;
import game.weapons.Club;

public class GodrickSoldier extends Humanoid {
    /**
     * Constructor for Godrick Soldier
     * Will be using a Club as a replacement for the crossbow
     *
     */
    public GodrickSoldier() {
        super("Godrick Soldier", 'p', 198);
        addWeaponToInventory(new Club());
        ResetManager.getInstance().registerResettable(this);
        this.addCapability(Status.IS_STORMVEILMOB);
    }

    @Override
    public void reset() {
        this.addCapability(Status.DESPAWN);
    }
}
