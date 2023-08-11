package game.rune;

import engine_code.actors.Actor;
import game.resets.Resettable;
import game.utils.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A Manager class for transferring and adding runes
 * Created by: Taraaf Tazeez Khalidi
 *
 */
public class RuneManager implements Resettable {

    private Actor actor;
    RandomNumberGenerator random;
    /**
     * An arraylist that contains runes collected by the player
     */
    private ArrayList<Integer> runesList = new ArrayList<>();

    /**
     * Enemies and the amount of runes they will drop are stored here
     */
    private Map<Character, Integer> actorRunesMap = new HashMap<>();
    private Rune playerRune = null;
    public static RuneManager newRuneManager = null;

    /**
     * Method to make sure there is only 1 instance of the RuneManager
     * @return a singleton rune manager if there isn't one already
     */
    public static RuneManager getInstance(){
        if (newRuneManager == null){
            newRuneManager = new RuneManager();
        }
        return newRuneManager;
    }

    /**
     * Constructor for the RuneManager.
     * The enemy's display char is used to identify how many runes it will drop.
     */
    public RuneManager(){
        actorRunesMap.put('h', RandomNumberGenerator.getRandomInt(55, 1470));
        actorRunesMap.put('q', RandomNumberGenerator.getRandomInt(35,892));
        actorRunesMap.put('C', RandomNumberGenerator.getRandomInt(318,4961));
        actorRunesMap.put('G', RandomNumberGenerator.getRandomInt(313,  1808));
        actorRunesMap.put('R', RandomNumberGenerator.getRandomInt(500, 2374));
        actorRunesMap.put('x', RandomNumberGenerator.getRandomInt(35,892));
        actorRunesMap.put('a', RandomNumberGenerator.getRandomInt(52,1390));
        actorRunesMap.put('p', RandomNumberGenerator.getRandomInt(38,70));
        actorRunesMap.put('ඞ', RandomNumberGenerator.getRandomInt(1358,5578));
    }

    /**
     * Adds runes to the ArrayList of runes
     * @param runeAmount amount of runes to be added
     */
    public void addRune(int runeAmount){
        runesList.add(runeAmount);
    }


    /**
     * Method to remove runes from the player's inventory
     */
    public void deductRunes(){
        runesList.clear();
    }

    /**
     * Method to return the player's rune object
     * @return the player's rune object
     */
    public Rune getPlayerRune(){
        return this.playerRune;
    }

    /**
     * Method that is called when enemies die so that the player receives the runes
     * @param c the display char of the enemy
     * @return List of the amount of the Player's runes
     */
    public List<Integer> transferRunes(char c){

        runesList.add(actorRunesMap.get(c));

        return runesList;
    }

    /**
     * Adds up the runes in the list
     * @return total amount of runes
     */
    public int getTotalAmountOfRunes() {
        int total = 0;
        for (Integer r: runesList)
            total += r;
        return total;
    }

    /**
     * Creates the Rune that is to be dropped when the player dies.
     * @return a Rune Object
     */
    public Rune dropRune(){
        return new Rune("Runes", '$', true, getTotalAmountOfRunes());
    }

    /**
     * Used by ResetManager to set the amount of runes to 0.
     */
    @Override
    public void reset() {
        runesList.clear();
    }

}
