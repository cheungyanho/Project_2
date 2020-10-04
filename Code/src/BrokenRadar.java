import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.Collections;

public class BrokenRadar {
    private char[] coordinateLetters = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I' };
    private ship[] shipArray;
    private int size;
    private int numberOfShips;
    private Random rand = new Random();
    private int numRows;
    private int numCols;
    private LinkedList<String> Coordinates = new LinkedList<String>();
    private HashMap<Integer, String> used = new HashMap<Integer, String>();
    private HashMap<Integer, String> trueCombo = new HashMap<Integer, String>();

    public BrokenRadar(Board opponent){
        int temp = opponent.getNumberOfShips();
        this.numRows = opponent.getXSize();
        this.numCols = opponent.getYSize();
        this.numberOfShips = temp;
        this.shipArray = opponent.getShipArray();
        this.size = temp * (temp + 1) / 2;//1 + 2 + 3... is the number of locations with ships
    } 

    private String convertCoor(int row, int col){
        return Integer.toString(col) + coordinateLetters[row];
    }

    private void fillMap(){
        for(int i = 0; i < numberOfShips; i++){
            String[] set = new String[i];
            if(i < numberOfShips)
            {
                for(int j = 0; j < numberOfShips; j++){
                    set[j] = shipArray[i].getShipLoc(j);

                }
            }
            int s = i * (i + 1)/2;
            for (int j = s; j < s + i; j++) {
                trueCombo.put(j, set[j - s]);
            }
            
        }
    }//N^2 complexity but better than a brute force search of the board

    public void removeFromList(int location){
        String coor = trueCombo.get(location);
        trueCombo.remove(location, coor);
        used.put(location, coor);
    }

    

    private void getRealCoordinate(){
        int randomLoc = rand.nextInt(size);
        boolean check = true;
        do {
            if (trueCombo.containsKey(randomLoc)){
                Coordinates.push(trueCombo.get(randomLoc));
                removeFromList(randomLoc);
                check = false;
            } else {
                randomLoc = rand.nextInt(size);
                check = true;
            }
        } while(check);
    }

    private void getRandomCoordinate(){
        boolean check = true;
        int randomRow = 0;
        int randomCol = 0;
        String place = "";
        do {
            randomRow = rand.nextInt(numRows);
            randomCol = rand.nextInt(numCols);
            place = convertCoor(randomRow, randomCol);
            if(trueCombo.containsValue(place) || used.containsValue(place)){
                check = true;
            } else {
                Coordinates.push(place);
                check = false;
            }
        } while(check);
        

    }

    private boolean findCoordinates(){
        if(trueCombo.isEmpty()){
            return false;
        } else {
            getRealCoordinate();
            getRealCoordinate();
            getRandomCoordinate();
            return true;
        }
        
    }

    private void shuffleList(){
        Collections.shuffle(Coordinates);
    }

    private void emptyList(){
        Coordinates.clear();
    }

    private void setBroken(){
        if(!Coordinates.isEmpty()){
            emptyList();
        }
        else {
            findCoordinates();
            shuffleList();
        }
    }

    private void printList(){
        
        System.out.println("Their locations: " + Coordinates.get(0) + " " + Coordinates.get(1) + " " + Coordinates.get(2));
        
    }

    public void runRadar(){
        if(Coordinates.isEmpty())
        {
            fillMap();
        }
        setBroken();
        printList();
    }
  


  /*public void peekOpponent(Board opponent) {
    int row = opponent.getXSize;
    int col = opponent.getYSize;


    //is this too nested?
    for (int i = 0; i < row; i++) {
      for (int j = 0; i < col; i++) {

        if (!isHit(row, col) && isShip(row, col)) {
          //is not already hit and is a ship
          //row = conditionRow
          //col = condiationCol
        }
      }
    }
    
  }

  }*/
}