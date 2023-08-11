package game;

import engine_code.displays.Display;
import engine_code.positions.World;
import game.environments.SummonSign;
import game.jobs.*;
import game.npc.Kale;
import game.rune.GoldenRune;
import game.rune.RuneManager;
import game.specialItems.RemembranceOfTheGrafted;
import game.usables.BoiledCrab;
import game.usables.ExaltedFlesh;
import game.usables.GoldenSeed;
import game.utils.FancyMessage;
import game.utils.Map;

import java.util.Scanner;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Sadheedh (23/04/2023)
 * 				Taraaf (03/05/2023)
 *
 */
public class Application {

	public static void main(String[] args) {
		// BEHOLD, ELDEN RING
		for (String line : FancyMessage.ELDEN_RING.split("\n")) {
			new Display().println(line);
			try {
				Thread.sleep(200);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		// console menu to select starting class for player
		boolean ok = false;
		String sel = "";
		while (!ok) {
			try
			{
				Scanner scanner = new Scanner(System.in);
				new Display().println("Select your role:");
				new Display().println("a: Astrologer");
				new Display().println("b: Bandit");
				new Display().println("s: Samurai");
				new Display().println("w: Wretch");
				sel = scanner.nextLine();

				if(sel.equals("a") | sel.equals("b") | sel.equals("s") | sel.equals("w")){
					ok = true;
				} else {
					throw new Exception("Please select a valid starting class for the player.");
				}
			}
			catch(Exception e){
				System.out.println("Please select a valid starting class for the player.");
			}
		}

		// after console menu, create new Player based on the input (sel)
		Job job = switch (sel) {
			case "a" -> new Astrologer();
			case "b" -> new Bandit();
			case "s" -> new Samurai();
			case "w" -> new Wretch();
			default -> null;
		};

		// Create world
		World world = new World(new Display());

		// Add maps to the world
		world.addGameMap(Map.limgrave);
		world.addGameMap(Map.stormveil);
		world.addGameMap(Map.roundtable);

		// Get RuneManager instance and created Player
		RuneManager rm = RuneManager.getInstance();
		Player player = new Player("Tarnished", '@', 300, job, rm);

		// For testing purposes
		player.getRuneManager().addRune(1000);

		// Always start by adding the player to the Limgrave spawn
		world.addPlayer(player, Map.limgrave.at(29, 2));

		//Limgrave Items
		Map.limgrave.at(50,8).addItem(new ExaltedFlesh());
		Map.limgrave.at(30,6).addItem(new GoldenSeed());
		Map.limgrave.at(18,8).addItem(new BoiledCrab());
		// Limgrave Golden Runes
		Map.limgrave.at(30,2).addItem(new GoldenRune());
		Map.limgrave.at(30,18).addItem(new GoldenRune());
		Map.limgrave.at(22,7).addItem(new GoldenRune());
		Map.limgrave.at(15,2).addItem(new GoldenRune());

		// Stormveil Golden Runes
		Map.stormveil.at(30,12).addItem(new GoldenRune());
		Map.stormveil.at(22,7).addItem(new GoldenRune());
		Map.stormveil.at(15,2).addItem(new GoldenRune());

		Map.limgrave.at(38,16).setGround(new SummonSign());
		Map.limgrave.at(32,2).addActor(new Kale());
		player.addItemToInventory(new RemembranceOfTheGrafted());

		Map.stormveil.at(37,12).setGround(new SummonSign());

		world.run();
	}
}
