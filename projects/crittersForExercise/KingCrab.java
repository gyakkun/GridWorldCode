import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.awt.*;
import java.util.ArrayList;

public class KingCrab extends CrabCritter {

	public KingCrab() {
		super();
		setColor(Color.cyan);
	}

	/**
	 * Computes the rounded integer distance between two given locations.
	 */
	public int distanceFrom(Location loc1, Location loc2) {
		int x1 = loc1.getRow();
		int y1 = loc1.getCol();
		int x2 = loc2.getRow();
		int y2 = loc2.getCol();
		double dist = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)) + .5;
		return (int) Math.floor(dist);
	}

	public void processActors(ArrayList<Actor> actors) {
		for (Actor a : actors) {
			boolean flag = false;
			ArrayList<Location> locs = getGrid().getEmptyAdjacentLocations(a.getLocation());
			for (Location loc : locs) {
				if (distanceFrom(getLocation(), loc) > 1) {
					a.moveTo(loc);
					flag = true;
					break;
				}
			}
			if (!flag) {
				a.removeSelfFromGrid();
			}
		}
	}

}
