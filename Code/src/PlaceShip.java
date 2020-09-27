import java.lang.IllegalArgumentException;

public class PlaceShip {
    private Board board;
    private String shipNumber;
    private boolean isHori;
    private int col;//1-9
    private int row;//A-I
    private int size;
    private boolean directionSucceeded;

    public PlaceShip(Board theBoard, int theSize, String shipId, int rowPlace, int colPlace, int numShips){
        this.board = theBoard;
        this.shipNumber = shipId;
        this.isHori = false;
        this.directionSucceeded = false;
        this.size = theSize + 1;
        
        
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
    
    private boolean placeIt(){
        return placeRecurse(row, col, size);
    }

    public void place(ship theShip) {
        try {
            if(placeIt()){
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