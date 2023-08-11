package game.entityactions;

import engine_code.actions.Action;
import engine_code.actors.Actor;
import engine_code.positions.GameMap;
import engine_code.weapons.WeaponItem;
import game.rune.RuneManager;

/**
 * An Action to allow PLayers to buy weapons from Kale
 * Created by: Taraaf Tazeez Khalidi
 * @author Taraaf Tazeez Khalidi
 * Modified by:
 *
 */
public class PurchaseAction extends Action {

    /**
     * The weapon which is to be bought
     */
    private WeaponItem weapon;
    private Purchasable purchasable;
    RuneManager runeManager = RuneManager.getInstance();

    public PurchaseAction(WeaponItem weapon){
        this.weapon = weapon;
    }

    /**
     * When executed, Player will purchase a weapon from kale.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return The result of the transaction, e.g. whether the Player successfully bought the item from Kale
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if(runeManager.getTotalAmountOfRunes() >= purchasable.buyFor()){
            actor.addWeaponToInventory(weapon);
//            runeManager.deductRunes(purchasable.buyFor());
        } else {
            return "Tarnished does not have enough runes";
        }
        String result = System.lineSeparator() + menuDescription(actor);
        return result;
    }

    /**
     *  Describes the details of the transaction
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + weapon + " from Kale";
    }
}
