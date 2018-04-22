import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;

import java.awt.Color;

/**
 * A <code>Jumper</code> is an actor that can move and turn. It drops flowers as
 * it moves. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class Jumper extends Bug {
	/**
	 * Constructor
	 */
	public Jumper() {
		setColor(Color.cyan);
	}

	/**
	 * Constructor with color parameter.
	 *
	 * @param jumperColor
	 *            the color for this jumping bug.
	 */
	public Jumper(Color jumperColor) {
		setColor(jumperColor);
	}

	/**
	 * To act as a jumper.
	 */
	public void act() {
		if (canJump()) {
			jump();
		} else {
			turn();
		}
	}

	/**
	 * The jumper should jump over flowers and rocks.
	 */
	public void jump() {
		Grid<Actor> gr = getGrid();
		if (gr == null) {
			return;
		}
		Location loc = getLocation();
		Location nextFirst = loc.getAdjacentLocation(getDirection());
		if (gr.isValid(nextFirst)) {
			Location nextSecond = nextFirst.getAdjacentLocation(getDirection());
			if (gr.isValid(nextSecond)) {
				moveTo(nextSecond);
			}
		} else {
			removeSelfFromGrid();
		}
	}

	/**
	 * Determine the jumper can jump or not.
	 */
	public boolean canJump() {
		Grid<Actor> gr = getGrid();
		if (gr == null) {
			return false;
		}
		Location loc = getLocation();
		Location nextFirst = loc.getAdjacentLocation(getDirection());
		Location nextSecond = nextFirst.getAdjacentLocation(getDirection());
		if (!gr.isValid(nextFirst)) {
			return false;
		}
		if (!gr.isValid(nextSecond)) {
			return false;
		}
		Actor neighbor = gr.get(nextSecond);
		return (neighbor == null) || (neighbor instanceof Flower);
	}

}
