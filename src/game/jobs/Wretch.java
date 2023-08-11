package game.jobs;

import engine_code.weapons.WeaponItem;
import game.weapons.Club;

/**
 * Wretch class has starting getHp 414 and Club in their inventory.
 * @author Sadeedh
 * Modified by: Taraaf
 */

public class Wretch extends Job{

    private WeaponItem club = new Club();

    @Override
    public WeaponItem startingWeapon(){
        return club;
    }

    @Override
    public int getHp() {
        return 414;
    }

    @Override
    public String getJob() {
        return "Wretch";
    }
}