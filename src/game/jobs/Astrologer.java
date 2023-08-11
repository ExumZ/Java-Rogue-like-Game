package game.jobs;

import engine_code.weapons.WeaponItem;
import game.weapons.AstrologerStaff;
import game.weapons.Uchigatana;

/**
 * Astrologer class has starting getHp 396 and Astrologer's Staff in their inventory.
 * @author Sadheedh
 * Modified By:
 */
public class Astrologer extends Job{
    private WeaponItem astrologerStaff = new AstrologerStaff();
    // Adding Uchigatana for Astrologer since Astrologer's Staff is not completed.
    private WeaponItem uchigatana = new Uchigatana();
    @Override
    public WeaponItem startingWeapon() {
        return uchigatana;
    }

    @Override
    public int getHp() {
        return 396;
    }

    @Override
    public String getJob() {
        return "Astrologer";
    }
}
