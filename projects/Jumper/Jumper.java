import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * A <code>Jumper</code> is an actor that can move and turn. It drops flowers as
 * it moves. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class Jumper extends Actor {
    /**
     * Constructor
     */
    public Jumper() {
        setColor(Color.cyan);
    }

    /**
     * Constructor with color parameter.
     * @param jumperColor the color for this
     *                    jumping bug.
     */
    public Jumper(Color jumperColor) {
        setColor(jumperColor);
    }

    /**
     * Moves if it can move, turns otherwise.
     */
    public void act() {
        if (canMove())
            move();
        else
            turn();
    }

    /**
     * Turns the bug 45 degrees to the right without changing its
     * location.
     */
    public void turn() {
        setDirection(getDirection()
                + Location.HALF_RIGHT);
    }


    /**
     * Moves the bug forward by two cells , no flower will be
     * putting into the location it previously occupied.
     */
    public void move() {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (gr.isValid(next))
            moveTo(next);
        else
            removeSelfFromGrid();
    }

    public boolean canMove(){
        return true;
    }
}
