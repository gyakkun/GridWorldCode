import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.util.ArrayList;

public class KingCrab extends CrabCritter {

    public void processActors(ArrayList<Actor> actors) {
        for (Actor a : actors) {
            Location tmpLoc = a.getLocation();
            int dirTowardKing = tmpLoc.
                    getDirectionToward(getLocation());
            if (getGrid().isValid(
                    tmpLoc.getAdjacentLocation(
                            dirTowardKing))) {
                a.moveTo(tmpLoc.getAdjacentLocation(dirTowardKing));
            } else {
                a.removeSelfFromGrid();
            }
        }
    }
}
