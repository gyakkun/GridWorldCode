import com.sun.corba.se.pept.transport.OutboundConnectionCache;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class SparseBoundedGrid<E> extends AbstractGrid<E> {

    private ArrayList<LinkedList> ocpArr;
    private int numCols;
    private int numRows;

    public SparseBoundedGrid(int rows, int cols) {
        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
        numCols = cols;
        numRows = rows;
        ocpArr = new ArrayList<LinkedList>();
        for (int ctr = 0; ctr < rows; ctr++) {
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

//        System.out.println("Location is : " + location.toString());
//
//        System.out.println("We have" + getNumRows() + "Rows");
//
//        System.out.println("We have" + getNumCols() + "Cols");


        /*return 0 <= location.getRow()
                && location.getRow() < getNumRows()
                && 0 <= location.getCol()
                && location.getCol() < getNumCols();*/
        if (location.getRow() >= 0 && location.getRow() >= 0
                && location.getRow() < getNumRows()
                && location.getCol() < getNumRows())
            return true;
        return false;
    }

    public E put(Location location, E e) {
        if (!isValid(location))
            throw new IllegalArgumentException("Invalid location:"
                    + location + "!"
            );
        if (e == null)
            throw new NullPointerException("Null obj!");

        E oldEle = remove(location);

        ocpArr.get(location.getRow()).add(
                new OccupantInCol(e, location.getCol())
        );

        return oldEle;
    }

    public E remove(Location location) {
        if (!isValid(location))
            throw new IllegalArgumentException("Invalid location:"
                    + location + "!"
            );

        E oldEle = get(location);
        if (oldEle == null) return null;

        LinkedList<OccupantInCol> row = ocpArr.get(location.getRow());

        if (row != null) {
            Iterator<OccupantInCol> itr = row.iterator();
            while (itr.hasNext()) {
                if (itr.next().getColNum() == location.getCol()) {
                    itr.remove();
                    break;
                }
            }
        }

        return oldEle;
    }

    public E get(Location location) {
        if (!isValid(location))
            throw new IllegalArgumentException("Invalid location:"
                    + location + "!"
            );

        LinkedList<OccupantInCol> row = ocpArr.get(location.getRow());

        if(row!=null){
            for(OccupantInCol ocp : row){
                if(location.getCol() == ocp.getColNum()){
                    return (E)ocp.getOccupant(); //Cast to E
                }
            }
        }

        return null;
    }

    public ArrayList<Location> getOccupiedLocations() {

        ArrayList<Location> resultLocs = new ArrayList<Location>();

        for(int ctr = 0 ; ctr < getNumRows(); ctr++){
            LinkedList<OccupantInCol> row = ocpArr.get(ctr);
            if(row!=null){
                for(OccupantInCol ocp : row) {
                    Location location = new Location(ctr, ocp.getColNum());
                    resultLocs.add(location);
                }
            }
        }

        return resultLocs;
    }
}
