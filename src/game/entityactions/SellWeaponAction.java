package game.entityactions;

import engine_code.actions.Action;
import engine_code.actors.Actor;
import engine_code.positions.GameMap;
import engine_code.weapons.WeaponItem;
import game.rune.RuneManager;

/**
 * Class that handles selling Weapons to Kale/Enia
 */
public class SellWeaponAction extends Action {

    private WeaponItem w;
    private int amount;

    public SellWeaponAction(WeaponItem w, int amount){
        this.w = w;
        this.amount = amount;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        RuneManager.getInstance().addRune(this.amount);
        actor.removeWeaponFromInventory(this.w);
        result += System.lineSeparator() + menuDescription(actor);
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " sells " + this.w + " for " + this.amount + " runes.";
    }
}
