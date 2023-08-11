package game.weapons;

import engine_code.actors.Actor;
import engine_code.positions.Location;
import engine_code.weapons.WeaponItem;

public class Grossmesser extends WeaponItem {

    /**
     * Constructor
     */
    public Grossmesser() {
        super("Grossmesser", '?', 115, "Slashes / Pierces", 85);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {}

}
