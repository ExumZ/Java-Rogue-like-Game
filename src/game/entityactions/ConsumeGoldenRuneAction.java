package game.entityactions;

import engine_code.actions.Action;
import engine_code.actors.Actor;
import engine_code.positions.GameMap;
import game.rune.RuneManager;
import game.usables.Consumables;
import game.utils.RandomNumberGenerator;
import game.utils.Status;

public class ConsumeGoldenRuneAction extends Action {

    private Consumables consumable;

    public ConsumeGoldenRuneAction(Consumables consumable){
        this.consumable = consumable;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";

        if (actor.isConscious() && actor.hasCapability(Status.CAN_CONSUME_RUNE)){
            if (consumable.consume(actor)){
                RuneManager.getInstance().addRune(RandomNumberGenerator.getRandomInt(200, 10000));
            }
        }
        result += System.lineSeparator() + menuDescription(actor);
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes a Golden Rune.";
    }
}
