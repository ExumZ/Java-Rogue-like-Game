package game.usables;

import engine_code.actors.Actor;

public interface Consumables {
    /**
     * Checks whether the Player has any remaining consumable item left.
     * @param actor actor that consumes an item
     */
    boolean consume(Actor actor);
}
