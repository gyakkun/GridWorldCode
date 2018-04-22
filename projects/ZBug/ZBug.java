
/*
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * @author Cay Horstmann
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 */

import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

/**
 * A <code>BoxBug</code> traces out a square "box" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class ZBug extends Bug {
	private int steps;
	private int sideLength;
	private int crossLineLength;
	private int sideOfZ = 0; // Can be 0,1,2, correspond with
	// the "Z"'s three sides.

	/**
	 * Constructs a box bug that traces a square of a given side length
	 *
	 * @param length
	 *            the side length
	 */
	public ZBug(int length) {
		steps = 0;
		sideLength = length - 1;
		crossLineLength = (int) Math.sqrt(length * length * 2) - 2;
		this.setDirection(Location.EAST);
	}

	/*
	 *
	 *
	 */
	public void turn() {
		setDirection(getDirection() + Location.HALF_RIGHT);
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
		if ((steps < sideLength
				|| ((steps >= sideLength + crossLineLength - 1) && steps < sideLength * 2 + crossLineLength))
				&& canMove() && (sideOfZ == 0 || sideOfZ == 2)) {
			boolean turnOrNot = false;
			move();
			steps++;
			if (steps == sideLength) {
				turnOrNot = true;
			}
			if (turnOrNot) {
				for (int ctr = 0; ctr < 3; ctr++) {
					turn();
				}
				sideOfZ = (sideOfZ + 1) % 3;
			}
		} else if (steps >= sideLength && steps < (sideLength + crossLineLength - 1) && canMove() && (sideOfZ == 1)) {
			move();
			steps++;
			if (steps == sideLength + crossLineLength - 1) {
				for (int ctr = 0; ctr < 5; ctr++) {
					turn();
				}
				sideOfZ++;
			}
		} else {
			return;
		}
	}

}
