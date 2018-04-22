import com.sun.corba.se.pept.transport.OutboundConnectionCache;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.LinkedList;

public class SparseBoundedGrid<E> extends AbstractGrid<E> {

    private ArrayList<LinkedList> ocpArr;
    private int numCols;
    private int numRows;

    public SparseBoundedGrid(int rows, int cols){
        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
        numCols = cols;
        numRows = rows;
        ocpArr = new ArrayList();
        for(int ctr = 0; ctr < rows ; ctr++){
            ocpArr.add(new LinkedList<OccupantInCol>());
        }
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public boolean isValid(Location location) {
        if(location.getRow()>=0&& location.getRow()>=0
                && location.getRow()<getNumRows()
                && location.getCol()<getNumRows())
            return true;
        return false;
    }

    public E put(Location location, E e) {
        return null;
    }

    public E remove(Location location) {
        return null;
    }

    public E get(Location location) {
        return null;
    }

    public ArrayList<Location> getOccupiedLocations() {
        return null;
    }
}
