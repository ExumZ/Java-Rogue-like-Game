package game.behaviours;

import engine_code.actions.Action;
import engine_code.actors.Actor;
import engine_code.positions.Exit;
import engine_code.positions.GameMap;
import engine_code.positions.Location;
import game.entityactions.AttackAction;
import game.utils.Status;

/**
 * A class that serves as the logic for AttackAction
 * Checks whether there are any actors within a single block of its vicinity
 *
 * Created by:
 * @author Zhi Yong Lee
 * Modified by:
 *
 */
public class AttackBehaviour implements Behaviour{
    // Deals with the logic of attacking (Checking if any actor is within its vicinity or not)
    private Actor target;
    private String direction;

    /**
     * Returns an Attack Action if there is an enemy not of its type around it
     * If there are no actors of another type around it, return null
     *
     * @param actor the Actor enacting the behaviour
     * @param map the map that actor is currently on
     * @return an Action, or null if no MoveAction is possible
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if(!map.contains(actor))
            return null;

        Location here = map.locationOf(actor);
        for (Exit exit : here.getExits()) {
            if (exit.getDestination().getActor() != null){
                target = exit.getDestination().getActor();
                direction = exit.getName();
                if (actor.hasCapability(Status.IS_STORMVEILMOB) && !target.hasCapability(Status.IS_STORMVEILMOB)){
                    // If the actor has a weapon
                    if (actor.getWeaponInventory().size() > 0){
                        // Add skill check here
                        return new AttackAction(target, direction, actor.getWeaponInventory().get(0));
                    }
                    // If the actor does not have a weapon
                    else {
                        return new AttackAction(target, direction);
                    }
                }
                else if (!actor.hasCapability(Status.IS_STORMVEILMOB) && actor.hasCapability(Status.IS_CANINE) && !target.hasCapability(Status.IS_CANINE)){
                    // If the actor has a weapon
                    if (actor.getWeaponInventory().size() > 0){
                        // Add skill check here
                        return new AttackAction(target, direction, actor.getWeaponInventory().get(0));
                    }
                    // If the actor does not have a weapon
                    else {
                        return new AttackAction(target, direction);
                    }
                }
                else if (actor.hasCapability(Status.IS_CRUSTACEAN) && !target.hasCapability(Status.IS_CRUSTACEAN)){
                    // If the actor has a weapon
                    if (actor.getWeaponInventory().size() > 0){
                        // Add skill check here
                        return new AttackAction(target, direction, actor.getWeaponInventory().get(0));
                    }
                    // If the actor does not have a weapon
                    else {
                        return new AttackAction(target, direction);
                    }
                }
                else if (actor.hasCapability(Status.IS_SKELETON) && !target.hasCapability(Status.IS_SKELETON)){
                    // If the actor has a weapon
                    if (actor.getWeaponInventory().size() > 0){
                        // Add skill check here
                        return new AttackAction(target, direction, actor.getWeaponInventory().get(0));
                    }
                    // If the actor does not have a weapon
                    else {
                        return new AttackAction(target, direction);
                    }
                } else if (actor.hasCapability(Status.ALLY) && (!target.hasCapability(Status.CAN_REST) && !target.hasCapability(Status.ALLY)) ){
                    // If the actor has a weapon
                    if (actor.getWeaponInventory().size() > 0){
                        // Add skill check here
                        return new AttackAction(target, direction, actor.getWeaponInventory().get(0));
                    }
                    // If the actor does not have a weapon
                    else {
                        return new AttackAction(target, direction);
                    }
                } else if (actor.hasCapability(Status.INVADER) && !target.hasCapability(Status.INVADER)) {
                    // If the actor has a weapon
                    if (actor.getWeaponInventory().size() > 0){
                        // Add skill check here
                        return new AttackAction(target, direction, actor.getWeaponInventory().get(0));
                    }
                    // If the actor does not have a weapon
                    else {
                        return new AttackAction(target, direction);
                    }
                }
                else{
                    return null;
                }
            }
        }
        return null;
    }
}
