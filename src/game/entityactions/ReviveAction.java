package game.entityactions;

import engine_code.actions.Action;
import engine_code.actors.Actor;
import engine_code.positions.GameMap;
import engine_code.positions.Location;
import game.enemies.GeneralEnemy;
import game.enemies.eastenemy.SkeletalBandit;
import game.enemies.westenemy.HeavySkeletalSwordsman;
import game.utils.Status;

/**
 * An action executed if Pile of Bones gets the chance to revive
 * Created by:
 * @author Zhi Yong Lee
 * Modified by:
 *
 */
public class ReviveAction extends Action {
    private Actor attacker;
    private Location location;
    private final int MID_OF_MAP = 38;

    public ReviveAction(Actor actor) {
        this.attacker = actor;
    }

    /**
     * When executed, the Pile of Bones will either spawn an HSS or SB
     *
     * @param target The actor performing the action.
     * @param map    The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor target, GameMap map) {
        String result = "";
        location = map.locationOf(target);
        if (target.hasCapability(Status.IS_HSS)) {
            map.removeActor(target);
            GeneralEnemy hss = new HeavySkeletalSwordsman();
            map.at(location.x(), location.y()).addActor(hss);
        } else {
            map.removeActor(target);
            GeneralEnemy sb = new SkeletalBandit();
            map.at(location.x(), location.y()).addActor(sb);
        }

        result += System.lineSeparator() + menuDescription(target);
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " is revived.";
    }
}
