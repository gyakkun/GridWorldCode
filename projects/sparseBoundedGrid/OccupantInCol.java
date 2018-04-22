public class OccupantInCol {

    private Object occupant;
    private int col;

    public OccupantInCol(Object occ, int colNum) {
        occupant = occ;
        col = colNum;
    }

    public Object getOccupant() {
        return occupant;
    }

    public int getColNum() {
        return col;
    }

    public void setOccupant(Object occ) {
        occupant = occ;
    }
}