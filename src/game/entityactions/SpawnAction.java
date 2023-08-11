package game.entityactions;

import engine_code.actions.Action;
import engine_code.actors.Actor;
import engine_code.positions.GameMap;
import engine_code.positions.Location;
import game.jobs.*;
import game.summons.Ally;
import game.summons.Invader;

import java.util.Random;

/**
 * SpawnAction will allow the Player to spawn either an Ally or an Invader when interacting with the Summon Sign.
 * RNG is used so that the Summon that is being spawned will have a random archetype (job).
 *
 * Created by:
 * @author Hussain Sadheedh Shaam
 * Modified by:
 */
public class SpawnAction extends Action {
    /**
     * Summon Sign object
     */
    private String spawnResult;

    /**
     * First available exit to summon
     */
    private Location location;

    /**
     * Random number generator
     */
    private Random rand = new Random();

    /**
     * Job that is randomly selected when spawning a Summon
     */
    private Job job;

    /**
     * Constructor
     * @param spawnResult   Ally or Invader
     * @param location      First available exit to spawn an Ally/Invader
     */
    public SpawnAction(String spawnResult, Location location){
        this.spawnResult = spawnResult;
        this.location = location;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        int n = rand.nextInt(100);
        if (n <= 25){
            job = new Astrologer();
        } else if (n > 25 && n <= 50){
            job = new Bandit();
        } else if (n > 50 && n <= 75){
            job = new Samurai();
        } else {
            job = new Wretch();
        }

        if (spawnResult.equals("Ally")){
            map.addActor(new Ally(job), location);
        } else {
            map.addActor(new Invader(job), location);
        }

        return actor + " summons an " + spawnResult + "!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " uses Summon Sign to spawn an Ally/Invader.";
    }
}
