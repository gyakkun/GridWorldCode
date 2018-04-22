import info.gridworld.grid.Location;

import java.util.ArrayList;

public class QuickCrab extends CrabCritter {

    public ArrayList<Location> getMoveLocations() {
        ArrayList<Location> locs = new ArrayList<Location>();
        int[] dirs =
                {Location.LEFT, Location.RIGHT};
        for (Location loc : getLocationsInDirections(dirs)) {
            if (getGrid().get(loc) == null) {
                int tmpDir = getLocation().getDirectionToward(loc);
                Location twoStepsFar = loc.getAdjacentLocation(tmpDir);
                if (getGrid().isValid(twoStepsFar)
                        && getGrid().get(twoStepsFar) == null) {
                    locs.add(twoStepsFar);
                } else{
                    locs.add(loc);
                }
            }
        }

        return locs;
    }

}
