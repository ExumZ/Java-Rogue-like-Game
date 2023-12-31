package engine_code.items;

import engine_code.actions.Action;
import engine_code.actors.Actor;
import engine_code.positions.GameMap;

/**
 * Action to allow items to be picked up.
 */
public class PickUpItemAction extends PickUpAction {

	private final Item item;

	/**
	 * Constructor.
	 *
	 * @param item the item to pick up
	 */
	public PickUpItemAction(Item item) {
		super(item);
		this.item = item;
	}

	/**
	 * Add the item to the actor's inventory.
	 *
	 * @see Action#execute(Actor, GameMap)
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a suitable description to display in the UI
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		actor.addItemToInventory(item);
		return super.execute(actor, map);
	}
}
