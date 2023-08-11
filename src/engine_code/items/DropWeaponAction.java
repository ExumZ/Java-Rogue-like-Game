package engine_code.items;

import engine_code.actors.Actor;
import engine_code.positions.GameMap;
import engine_code.weapons.WeaponItem;

/**
 * Special Action that allows Actors to drop weapons.
 */
public class DropWeaponAction extends DropAction {
    private final WeaponItem weapon;
    /**
     * Constructor.
     *
     * @param weapon the weapon to drop
     */
    public DropWeaponAction(WeaponItem weapon) {
        super(weapon);
        this.weapon = weapon;
    }

    /**
     * Drop the weapon
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of the action suitable for feedback in the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.removeWeaponFromInventory(weapon);
        return super.execute(actor, map);
    }
}
