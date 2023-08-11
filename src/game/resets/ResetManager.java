package game.resets;

import game.rune.RuneManager;

import java.util.ArrayList;
import java.util.List;

/**
 * A reset manager class that manages a list of resettables.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Zhi Yong Lee
 *              Taraaf
 *
 */
public class ResetManager {
    private List<Resettable> resettables;
    private static ResetManager instance;

    /**
     * Constructor for Reset Manager
     *
     */
    private ResetManager() {
        this.resettables = new ArrayList<>(); 
    }

    /**
     * Method to get the instance of the ResetManager
     *
     */
    public static ResetManager getInstance() {
        if (instance == null){
            instance = new ResetManager();
        }
        return instance;
    }

    /**
     * Method to run through the list of resettables (Resets everything that can be reset)
     * - Player, Flasks, Enemies
     *
     */
    public void run() {
        for (Resettable r : resettables){
            r.reset();
        }
    }

    public void registerResettable(Resettable resettable) {
        this.resettables.add(resettable);
    }

    public void removeResettable(Resettable resettable) {
        this.resettables.remove(resettable);
    }
}
