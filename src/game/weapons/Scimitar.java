package game.weapons;

import engine_code.actors.Actor;
import engine_code.positions.Location;
import engine_code.weapons.WeaponItem;

public class Scimitar extends WeaponItem {

    /**
     * Constructor
     */
    public Scimitar() {
        super("Scimitar", 's', 118, "Slashes / Pierces", 88);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {}

}
