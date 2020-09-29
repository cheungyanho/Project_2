import java.util.Scanner;
import java.lang.IllegalArgumentException;

public class GameLoop {
    private Board player1Board;
    BoardPrinterWrapper player1Printer;
    private Board player2Board;
    BoardPrinterWrapper player2Printer;
    private Scanner consoleInput = new Scanner(System.in);
    private Utility Tools;
    private safelyGetCoordinates getCoor;


    public void askForShipNum(int player) {
        boolean validShipNum = false;
        do {
          System.out.println("Select the number of ships you will use.");
          int num = 0;
          num = consoleInput.nextInt();
          validShipNum=Tools.validateShipNum(num);
        } while (validShipNum==false);
        if(player == 1) {
            player1Board = new Board(9, 9, '~', num);
        } else {
            player2Board = new Board(9, 9, '~', num);
        }

    }

    public void placeShipLoop(Board playerBoard) {
        for(int i = 0; i < playerBoard.getNumberOfShips(); i++) {
            getCoor.getCoordinates();
            playerBoard.setShipCoordinates(i, getCoor.getRow(), getCoor.getCol());
        }
    }

    public void Init(){
        askForShipNum(1);
        askForShipNum(2);
        placeShipLoop(player1Board);
        placeShipLoop(player2Board);
    }

    public void Loop(){
        do {
            Tools.printMenu();
        } while();
    }

    public void Game(){

    }

}
