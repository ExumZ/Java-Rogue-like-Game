package game.entityactions;

import engine_code.actions.Action;
import engine_code.actors.Actor;
import engine_code.positions.GameMap;
import game.usables.Consumables;

public class ConsumeAction extends Action {
    /**
     * Consumable that performs the action.
     */
    private Consumables consumable;
    /**
     * Final amount that heals the Player.
     */
    private final int HEAL_POINTS = 250;

    /**
     * Constructor
     * @param consumable that performs the ConsumeAction.
     */
    public ConsumeAction(Consumables consumable){
        this.consumable = consumable;

    }

    /**
     * When the Player uses the Flask of Crimson Tears,
     * it will increase hitpoints of the Player by 250.
     * Player will heal only if they are above 0 HP and has remaining consumable items.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return result of HealAction displayed on the UI.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";

        if(actor.isConscious()) {
            if(consumable.consume(actor)){
                actor.heal(HEAL_POINTS);
            }

        }

        result += System.lineSeparator() + menuDescription(actor);
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes a Flask of Crimson Tears.";
    }
}
