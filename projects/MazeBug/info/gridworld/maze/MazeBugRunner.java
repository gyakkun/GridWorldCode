package info.gridworld.maze;

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Rock;

/**
 * This class runs a world that contains maze bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class MazeBugRunner {
    public static void main(String[] args) {

        ActorWorld world = new ActorWorld();
        world.add(new Location(0, 0), new MyMazeBug());

        world.add(new Location(1, 1), new Rock());
        world.show();
    }
}