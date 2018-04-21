import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;

public class JumperRunner {

    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        Jumper bob = new Jumper();
        world.add(new Location(9,2),new Rock());
        world.add(new Location(2,9), new Flower());
        world.add(new Location(3,7), bob);
        world.show();
    }
}
