package game.npc;

import engine_code.actions.Action;
import engine_code.actions.ActionList;
import engine_code.actions.DoNothingAction;
import engine_code.actors.Actor;
import engine_code.displays.Display;
import engine_code.positions.GameMap;
import game.utils.Status;

/**
 * Enia, the other trader in the game
 * @author Taraaf
 */
public class Enia extends Actor {

    /**
     * Constructor for Enia
     */
    public Enia() {
        super("Enia", 'E', 999999);
        this.addCapability(Status.CAN_BUY);
        this.addCapability(Status.CAN_DO_EXCHANGE);
    }

    /**
     * Enia does not do anything in their turn.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return           a new DoNothingAction
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }
}
