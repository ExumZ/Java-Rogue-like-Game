package game.usables;

import engine_code.actors.Actor;
import engine_code.items.Item;
import game.entityactions.ConsumeAction;
import game.resets.ResetManager;
import game.resets.Resettable;

/**
 * The Flask of Crimson Tears Class
 * Created by:
 * @author Hussain Sadheedh Shaam
 * Modified by: Zhi Yong Lee
 *
 */
public class FlaskOfCrimsonTears extends Item implements Resettable, Consumables{
    private int maxFlasks = 2;
    private int flasks;
    private ConsumeAction consumeAction = new ConsumeAction(this);

    /**
     * At the beginning of the game, flaskOfCrimsonTears will be null.
     */
    public static FlaskOfCrimsonTears flaskOfCrimsonTears = null;

    /**
     * Method to make sure there is only 1 instance of the FlaskOfCrimsonTears.
     * @return a singleton FlaskOfCrimsonTears if there isn't one already
     */
    public static FlaskOfCrimsonTears getInstance(){
        if (flaskOfCrimsonTears == null){
            flaskOfCrimsonTears = new FlaskOfCrimsonTears();
        }
        return flaskOfCrimsonTears;
    }

    public FlaskOfCrimsonTears() {
        super("Flask of Crimson Tears", 'c', false); //not portable since flasks cannot be dropped
        this.flasks = maxFlasks;
        this.addAction(consumeAction);
        ResetManager.getInstance().registerResettable(this);
    }

    /**
     * This method will be called when the Player consumes a Golden Seed.
     */
    public void addFlask(){
        if(this.flasks == this.maxFlasks){  // When the Player has 2/2 flasks and then consumes a Golden Seed
            this.maxFlasks ++;
            this.flasks ++;
        } else if (this.flasks < this.maxFlasks){
            this.maxFlasks ++;  // When the Player has 1/2 flasks and then consumes a Golden Seed
        }
    }

    public int getFlasks() {
        return this.flasks;
    }

    @Override
    public boolean consume(Actor actor) {
        if(this.flasks != 0){
            this.flasks -= 1;
            if(this.flasks == 0){this.removeAction(consumeAction);}
            return true;
        } else return false;
    }

    /**
     * Method to reset the number of flasks used
     */
    @Override
    public void reset() {
        this.flasks = maxFlasks;
    }
}
