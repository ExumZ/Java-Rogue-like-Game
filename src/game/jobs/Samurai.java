package game.jobs;

import engine_code.weapons.WeaponItem;
import game.weapons.Uchigatana;

/**
 * Samurai class has starting getHp 455 and Uchigatana in their inventory.
 * @author Sadheedh
 * Modified By: Taraaf
 */

public class Samurai extends Job {

    private WeaponItem uchigatana = new Uchigatana();

    @Override
    public WeaponItem startingWeapon(){
        return uchigatana;
    }

    @Override
    public int getHp() {
        return 455;
    }

    @Override
    public String getJob() {
        return "Samurai";
    }
}