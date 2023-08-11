package game.jobs;

import engine_code.weapons.WeaponItem;
import game.weapons.GreatKnife;

/**
 * Bandit class has starting hp 414 and Great Knife in their inventory.
 * @author Sadeedh
 * Modified by: Taraaf
 */

public class Bandit extends Job{

    private WeaponItem greatKnife = new GreatKnife();

    @Override
    public WeaponItem startingWeapon(){
        return greatKnife;
    }

    @Override
    public int getHp() {
        return 414;
    }

    @Override
    public String getJob() {
        return "Bandit";
    }
}