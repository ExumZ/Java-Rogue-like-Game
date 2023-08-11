package game.entityactions;

import engine_code.actions.Action;
import engine_code.actors.Actor;
import engine_code.items.Item;
import engine_code.positions.GameMap;
import engine_code.weapons.WeaponItem;

public class ExchangeAction extends Action {

    private WeaponItem w;

    public ExchangeAction(WeaponItem w){
        this.w = w;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Item x = null;
        actor.addWeaponToInventory(this.w);
        for (Item i: actor.getItemInventory()){
            if (i.getDisplayChar() == 'O'){
                x = i;
            }
        }
        actor.removeItemFromInventory(x);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " exchanges Remembrance of the Grafted for " + this.w;
    }
}
