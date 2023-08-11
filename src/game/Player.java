package game;

import engine_code.actions.Action;
import engine_code.actions.ActionList;
import engine_code.actors.Actor;
import engine_code.displays.Display;
import engine_code.positions.GameMap;
import engine_code.displays.Menu;
import engine_code.positions.Location;
import game.jobs.Job;
import game.resets.ResetManager;
import game.resets.Resettable;
import game.rune.RuneManager;
import game.usables.BoiledCrab;
import game.usables.ExaltedFlesh;
import game.usables.FlaskOfCrimsonTears;
import game.usables.GoldenSeed;
import game.utils.Status;
//import java.util.Objects;

/**
 * Class representing the Player. It implements the Resettable interface.
 * It carries around a club to attack a hostile creature in the Lands Between.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Sadheedh, Taraaf, and Zhi Yong (25/04/2023)
 * Modified by: Sadeedh and Taraaf (20/04/2023)
 * 							Taraaf (23/04/2023)
 * 							Taraaf (30/4/2023)
 * 							Zhi Yong (03/05/2023)
 * 							Taraaf (03/05/2023)
 *
 */
public class Player extends Actor implements Resettable {

	private FlaskOfCrimsonTears flasks = FlaskOfCrimsonTears.getInstance();
	private final Menu menu = new Menu();
	private Job job;
	private RuneManager runeManager = RuneManager.getInstance();
	public static Location previousLocation;
	// For testing purposes, goldenseed should be sold by Kale.
	private GoldenSeed goldenSeed = new GoldenSeed();
	private BoiledCrab boiledCrab = new BoiledCrab();
	private ExaltedFlesh exaltedFlesh = new ExaltedFlesh();
	private int turnsForBoiledCrab = 0;
	private int turnsForExaltedFlesh = 0;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 * @param job 		  Starting class of a player
	 */
	public Player(String name, char displayChar, int hitPoints, Job job, RuneManager runeManager) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.CAN_REST);
		this.addCapability(Status.IN_LIMGRAVE);
		this.addCapability(Status.SP_LIMGRAVE);
		this.addCapability(Status.SOLG_LIMGRAVE_DISC);
		this.hitPoints = job.getHp();
		this.maxHitPoints = job.getHp();
		this.addWeaponToInventory(job.startingWeapon());
		this.addItemToInventory(flasks);
		// testing
		this.addItemToInventory(goldenSeed);
		this.addItemToInventory(boiledCrab);
		this.addItemToInventory(exaltedFlesh);
		ResetManager.getInstance().registerResettable(this);
	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// count how many turns has passed after consuming Boiled Crab / Exalted Flesh
		if(this.hasCapability(Status.CONSUME_BOILED_CRAB)){
			turnsForBoiledCrab ++;
			if(turnsForBoiledCrab == 5){
				this.removeCapability(Status.CONSUME_BOILED_CRAB);
				turnsForBoiledCrab = 0;
			}
		}

		if(this.hasCapability(Status.CONSUME_EXALTED_FLESH)){
			turnsForExaltedFlesh ++;
			if(turnsForExaltedFlesh == 5){
				this.removeCapability(Status.CONSUME_EXALTED_FLESH);
				turnsForExaltedFlesh = 0;
			}
		}

		previousLocation = map.locationOf(this);
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		// display the players name, hp and current number of runes
		display.println(this.name + " (" + this.hitPoints + "/" + this.maxHitPoints +"), " + this.getItemInventory().get(0) + ": " + flasks.getFlasks() + ", Runes: " + runeManager.getTotalAmountOfRunes());
		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	/**
	 * The player can be followed by any actor that has the HOSTILE_TO_PLAYER capability
	 *
	 * @param otherActor the Actor that might be performing attack
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return a list of actions able to be performed to the actor
	 */
	@Override
	public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
		ActionList actions = new ActionList();
		return actions;
	}

	@Override
	public void reset() {
		this.hitPoints = this.maxHitPoints;
	}

	public RuneManager getRuneManager() {
		return runeManager;
	}
}
