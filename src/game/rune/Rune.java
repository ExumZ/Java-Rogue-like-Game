package game.rune;

import engine_code.items.Item;

/**
 * Class for Runes - The currency of the game
 */
public class Rune extends Item {

    private int amount;

    /***
     * Constructor of the Rune Item
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public Rune(String name, char displayChar, boolean portable, int amount) {
        super("Rune", '$', true);
        this.amount = amount;
    }

    public int getAmount() {
        return this.amount;
    }
}
