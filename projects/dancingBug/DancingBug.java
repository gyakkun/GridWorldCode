
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
import java.util.Arrays;

/**
 * A <code>BoxBug</code> traces out a square "box" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class DancingBug extends Bug {
	private int steps;
	private int sideNum;
	private int sideLength;
	private int whichTurn;
	private int[] turnsNumArr;
	private int sideOfZ = 0; // Can be 0,1,2, correspond with
	// the "Z"'s three sides.

	/**
	 * Constructs a box bug that traces some sides according to a given side length
	 * array.
	 *
	 * @param int[]
	 *            turnAround: The turning point after the previous turn.
	 * 
	 */
	public DancingBug(int length, int[] turnAround) {
		steps = 0;
		sideLength = length;
		whichTurn = 0;
		turnsNumArr = Arrays.copyOf(turnAround, turnAround.length);
	}

	/**
	 * 
	 */
	public void turn() {
		setDirection(getDirection() + Location.HALF_RIGHT);
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
		if (steps < sideLength && canMove()) {
			move();
			steps++;
		} else {
			for (int ctr = 0; ctr < turnsNumArr[whichTurn]; ctr++)
				turn();
			steps = 0;
			whichTurn = (whichTurn + 1) % turnsNumArr.length;
		}
	}

}
