import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

public class BlusterCritter extends Critter {

    private int lessThan;

    public BlusterCritter(int count) {
        super();
        lessThan = count;
    }

    public int ActorsWithinTwoSteps() {
        int howMany = 0;
        int myRow = getLocation().getRow();
        int myCol = getLocation().getCol();
        for (int rCtr = -2; rCtr < 2; rCtr++) {
            for (int cCtr = -2; rCtr < 2; rCtr++) {
                Location tmpLoc = new Location(rCtr + myRow,
                        cCtr + myCol);
                if (getGrid().isValid(tmpLoc)) {
                    Actor tmpActor = getGrid().get(tmpLoc);
                    if (tmpActor instanceof Critter) {
                        howMany++;
                    }
                }
            }
        }
        return howMany;
    }




}
