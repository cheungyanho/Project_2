import java.lang.IllegalArgumentException;

public class PlaceShip {
    private Board board;
    private String shipNumber;
    private boolean isHori;
    private boolean directionSucceeded;

    public PlaceShip(Board theBoard){
        this.board = theBoard;
        this.isHori = false;
        this.directionSucceeded = false;
        
        
    }
   
        
    private boolean setShipDirection(boolean ds) {
        directionSucceeded = ds;
        return directionSucceeded;
    }
    

    private boolean placeRecurse(int rowPlace, int colPlace, int sizePlace) {
        if(!CollisionHandler.check(board, 's', rowPlace, colPlace)) {
            board.addMarker('s', rowPlace, colPlace);
        }
        if(isHori && sizePlace != 0) {
            return placeRecurse(rowPlace + 1, colPlace, sizePlace - 1);
        } else if(!isHori && sizePlace != 0){
            return placeRecurse(rowPlace, colPlace + 1, sizePlace - 1);
        } 
        return true;
    }
    
    private boolean placeIt(int row, int col, int size){
        return placeRecurse(row, col, size);
    }

    public void place(int row, int col, int size, boolean dir) {
        try {
            isHori = dir;
            if(placeIt(row, col, size)){
                setShipDirection(true);
            }
        } catch(IllegalArgumentException iae) {
            Utility.errorMessage(iae, "Could not place ship. Try again.");
            setShipDirection(false);
        }
    }

    public String getShipName() {
        return shipNumber;
    }

}