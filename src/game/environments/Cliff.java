package game.environments;

import engine_code.actors.Actor;
import engine_code.positions.Ground;
import engine_code.positions.Location;
import game.entityactions.DeathAction;
import game.utils.Status;

/**
 * Class for Cliff ground
 * Created by:
 * @author Zhi Yong Lee
 * Modified by:
 *
 */
public class Cliff extends Ground {

    public Cliff() {
        super('+');
    }

    /**
     * For every tick, it will check if there's a player on it.
     * If there is, run the reset manager.
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (location.containsAnActor() && location.getActor().hasCapability(Status.CAN_REST)){
            new DeathAction(location.getActor()).execute(location.getActor(), location.map());
        }
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.CAN_REST);
    }
}
