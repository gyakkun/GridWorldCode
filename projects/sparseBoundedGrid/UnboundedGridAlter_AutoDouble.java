import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

public class UnboundedGridAlter_AutoDouble<E>
        extends AbstractGrid {

    private Object[][] ocpArr;
    private int sideLen;

    public UnboundedGridAlter_AutoDouble(){
        sideLen = 16;
        ocpArr = new Object[sideLen][sideLen];
    }

    public int getNumRows() {
        return -1;
    }

    public int getNumCols() {
        return -1;
    }

    public boolean isValid(Location location) {
        return location.getCol() >= 0 && location.getRow() >= 0;
    }

    public Object put(Location location, Object o) {
        if (location == null) {
            throw new NullPointerException("Invalid location: NULL!");
        }

        if (o == null) {
            throw new NullPointerException("Invalid object: NULL!");
        }

        if (location.getRow() >= sideLen || location.getCol() >= sideLen) {
            resize(location);
        }
        E oldEntry = get(location);
        ocpArr[location.getRow()][location.getCol()] = o;
        return oldEntry;
    }

    private void resize(Location location) {
        int orgSideLen = sideLen;
        while (location.getRow() >= sideLen
                || location.getCol() >= sideLen) {
            sideLen = sideLen * 2;
        }
        Object[][] newOcpArr = new Object[2 * sideLen][2 * sideLen];
        for (int rowCtr = 0; rowCtr < orgSideLen; rowCtr++) {
            for (int colCtr = 0; colCtr < orgSideLen; colCtr++) {
                newOcpArr[rowCtr][colCtr] = ocpArr[rowCtr][colCtr];
            }
        }
        ocpArr = newOcpArr;
    }

    public E remove(Location location) {
        if (!isValid(location)) {
            throw new IllegalArgumentException("Invalid location!"
                    + location.toString());
        }
        if (location.getRow() >= sideLen || location.getCol() >= sideLen)
            return null;
        E oldEntry = get(location);
        ocpArr[location.getRow()][location.getCol()] = null;
        return oldEntry;
    }

    public E get(Location location) {
        if (!isValid(location)) {
            throw new IllegalArgumentException("Invalid location!"
                    + location.toString());
        }
        if (location.getRow() >= sideLen || location.getCol() >= sideLen)
            return null;
        return (E) ocpArr[location.getRow()][location.getCol()];
    }

    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> resultLocs = new ArrayList<Location>();
        for (int rowCtr = 0; rowCtr < sideLen; rowCtr++) {
            for (int colCtr = 0; colCtr < sideLen; colCtr++) {
                Location location = new Location(rowCtr, colCtr);
                if (get(location) != null)
                    resultLocs.add(location);
            }
        }
        return resultLocs;
    }


}

