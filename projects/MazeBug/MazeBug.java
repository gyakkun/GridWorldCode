import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
    private Location next;
    private Location last;
    private boolean isEnd = false;
    private Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
    private Integer stepCount = 0;
    private boolean hasShown = false;//final message has been shown

    //Matrix to store visited locations.
    private boolean isVisit[][];

    //List to store some directions.
    private ArrayList<Location> branch;

    //Counter for each direction moves.
    private int left, right, front, back;


    /**
     * Constructs a box bug that traces a square of a given side length
     *
     * @ param length
     * the side length
     */
    public MazeBug() {
        int size = 100;
        setColor(Color.GREEN);
        last = new Location(0, 0);
        isVisit = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                isVisit[i][j] = false;
            }
        }

        Location loc = getLocation();

        branch = new ArrayList<Location>();
        branch.add(loc);

        left = right = front = back = 1;

    }

    /**
     * Moves to the next location of the square.
     */
    public void act() {
        boolean willMove = canMove();
        if (isEnd) {
            //to show step count when reach the goal
            if (!hasShown) {
                String msg = stepCount.toString() + " steps";
                JOptionPane.showMessageDialog(null, msg);
                hasShown = true;
            }
        } else if (willMove) {
            isVisit[next.getRow()][next.getCol()] = true;
            move();
            //increase step count when move
            stepCount++;
        } else {
            goBack();
            //decrease step count when go back
            stepCount++;
        }
    }


    /**
     * Find all positions that can be move to.
     *
     * @param loc the location to detect.
     * @return List of positions.
     */
    public ArrayList<Location> getValid(Location loc) {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return null;
        }
        ArrayList<Location> valid = new ArrayList<>();

        int r, c;
        int[] movableDirs = {
                Location.AHEAD, Location.LEFT,
                Location.RIGHT, Location.HALF_CIRCLE
        };

        for (int dir : movableDirs) {
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + dir);
            r = neighborLoc.getRow();
            c = neighborLoc.getCol();

            if (gr.isValid(neighborLoc)) {
                Actor tmpActor = gr.get(neighborLoc);
                if ((tmpActor == null || tmpActor instanceof Flower)
                        && !isVisit[r][c]) {
                    valid.add(neighborLoc);
                } else if (tmpActor instanceof Rock) {
                    Color red = Color.RED;
                    if (red.equals(tmpActor.getColor())) {
                        isEnd = true;
                    }
                }
            }
        }
        return valid;
    }

    /**
     * Tests whether this bug can move forward into a location that is empty or
     * contains a flower.
     *
     * @return true if this bug can move.
     */
    public boolean canMove() {
        ArrayList<Location> loc = getValid(getLocation());
        int locSize;

        if (loc.isEmpty()) {
            return false;
        } else {
            branch.add(getLocation());
            locSize = loc.size();

            if (locSize >= 2) {
                crossLocation.push(branch);
                branch = new ArrayList<>();
                next = betterLoc(loc);
            } else {
                next = loc.get(0);
            }
        }

        return true;
    }

    public Location betterLoc(ArrayList<Location> a) {
        int leftD, rightD, aheadD, behindD, random, dir;
        leftD = rightD = aheadD = behindD = 0;
        for (Location loc : a) {
            dir = getLocation().getDirectionToward(loc);
            if (dir == 0)
                aheadD = front;
            else if (dir == 90)
                rightD = right;
            else if (dir == 180)
                behindD = back;
            else if (dir == 270)
                leftD = left;
        }
        random = 1 + (int) (Math.random() * (leftD + rightD + aheadD + behindD));
        if (random <= leftD) {
            dir = 270;
            left++;
        } else if (random <= (leftD + rightD)) {
            dir = 90;
            right++;
        } else if (random <= (leftD + rightD + aheadD)) {
            dir = 0;
            front++;
        } else {
            dir = 180;
            back++;
        }
        Location betterLoc = null;
        for (Location loc : a) {
            if (dir == getLocation().getDirectionToward(loc))
                betterLoc = loc;
        }
        return betterLoc;
    }

    /**
     * Moves the bug forward, putting a flower into the location it previously
     * occupied.
     */
    public void move() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        Location loc = getLocation();
        if (gr.isValid(next)) {
            setDirection(getLocation().getDirectionToward(next));
            moveTo(next);
        } else {
            removeSelfFromGrid();
        }
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
    }

    public void goBack() {
        int branchSize;
        if (branch.isEmpty()) {
            branch = crossLocation.pop();
            Location loc = branch.get(branch.size() - 1);
            int dir = getLocation().getDirectionToward(loc);
            if (dir == 0) {
                back--;
            } else if (dir == 90) {
                left--;
            } else if (dir == 180) {
                front--;
            } else {
                right--;
            }
        }
        branchSize = branch.size();
        next = branch.remove(branchSize - 1);
        move();
    }
}
