import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.awt.*;
import java.util.ArrayList;

public class BlusterCritter extends Critter {

    private int lessThan;

    public BlusterCritter(int courage) {
        super();
        lessThan = courage;
    }

    public ArrayList<Actor> ActorsWithinTwoSteps() {
        ArrayList<Actor> result = new ArrayList<Actor>();
        int myRow = getLocation().getRow();
        int myCol = getLocation().getCol();
        for (int rCtr = -2; rCtr < 2; rCtr++) {
            for (int cCtr = -2; rCtr < 2; rCtr++) {
                Location tmpLoc = new Location(rCtr + myRow,
                        cCtr + myCol);
                if (getGrid().isValid(tmpLoc)) {
                    Actor tmpActor = getGrid().get(tmpLoc);
                    if (tmpActor instanceof Critter) {
                        result.add(tmpActor);
                    }
                }
            }
        }
        return result;
    }

    public void colorChange(int val){
        double CHANGING_FACTOR = 0.05;
        Color c = getColor();
        int red = (int) (c.getRed() * (1 + val * CHANGING_FACTOR));
        int green = (int) (c.getGreen() * (1 + val * CHANGING_FACTOR));
        int blue = (int) (c.getBlue() * (1 + val * CHANGING_FACTOR));
        setColor(new Color(red, green, blue));
    }

    public void act(){
        super.act();
        int changingFactor = ActorsWithinTwoSteps().size() > 0 ? 1 : -1;
        colorChange(changingFactor);
    }


}
