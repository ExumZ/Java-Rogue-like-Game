package game.environments;

import engine_code.actions.ActionList;
import engine_code.actors.Actor;
import engine_code.positions.Exit;
import engine_code.positions.Location;
import game.entityactions.SpawnAction;

import java.util.Random;

public class SummonSign extends SpawningGround{
    /**
     * Random number generator
     */
    private Random rand = new Random();

    /**
     * Constructor General Spawning Ground
     *
     */
    public SummonSign() {
        super('=');
    }

    /**
     * Randomly chooses whether spawnResult is an Ally or Invader.
     */
    private String spawnResult;

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        int n = rand.nextInt(100);
        if(!(n <= 50)){
            spawnResult = "Ally";
        } else {
            spawnResult = "Invader";
        }
        // check exits around summon sign then call spawnAction
        for (Exit exit : location.getExits()){
            if ((exit.getDestination().canActorEnter(actor))){
                actions.add(new SpawnAction(spawnResult, exit.getDestination()));
                return actions;
            }
        }
        return actions;
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}
