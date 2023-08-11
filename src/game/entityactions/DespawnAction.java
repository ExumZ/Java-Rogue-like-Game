package game.entityactions;

import engine_code.actions.Action;
import engine_code.actors.Actor;
import engine_code.positions.GameMap;

/**
 * An action executed to despawn enemies from the map
 * Created by:
 * @author Zhi Yong Lee
 * Modified by:
 *
 */
public class DespawnAction extends Action {

    /**
     * When executed, the actor will be despawned
     *
     * @param actor The actor being despawned
     * @param map The map the actor is on.
     * @return the result of the attack, e.g. whether the target is killed, etc.
     * @see DeathAction
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        map.removeActor(actor);

        return menuDescription(actor);
    }

    /**
     * Describes which actor has been despawned
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " has despawned.";
    }
}
