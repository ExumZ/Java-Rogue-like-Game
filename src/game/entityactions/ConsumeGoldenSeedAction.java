package game.entityactions;

import engine_code.actions.Action;
import engine_code.actors.Actor;
import engine_code.positions.GameMap;
import game.usables.Consumables;
import game.usables.FlaskOfCrimsonTears;

/**
 * Action class that executes if the player is conscious and has a consumable in their inventory.
 * When executed, the Player consumes the Golden Seed. It will call addFlask() which is responsible for
 * increasing the maximum flasks a Player can hold.
 *
 * Created by:
 * @author Hussain Sadheedh Shaam
 * Modified by:
 */
public class ConsumeGoldenSeedAction extends Action {
    private Consumables consumable;
    public ConsumeGoldenSeedAction(Consumables consumable){
        this.consumable = consumable;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        // && actor.hasCapability(Status.CAN_CONSUME_RUNE
        if (actor.isConscious()){
            if (consumable.consume(actor)){
                FlaskOfCrimsonTears.getInstance().addFlask();
            }
        }
        result += System.lineSeparator() + menuDescription(actor);
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes a Golden Seed.";
    }
}
