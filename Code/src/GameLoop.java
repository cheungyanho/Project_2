import java.util.InputMismatchException;
import java.util.Scanner;
//import java.lang.IllegalArgumentException;

public class GameLoop {
    private Board player1Board;
    BoardPrinterWrapper player1Printer;
    private Board player2Board;
    BoardPrinterWrapper player2Printer;
    private getUserInput player1UI = new getUserInput(1);
    private getUserInput player2UI = new getUserInput(2);
    private Scanner consoleInput = new Scanner(System.in);
    private Utility Tools;
    private safelyGetCoordinates getCoor;
    private int choice = 0;
    private boolean[] playerWon = {false, false};


    public void placeShipLoop(Board playerBoard, BoardPrinterWrapper playerWrapper) {
        for(int i = 0; i < playerBoard.getNumberOfShips(); i++) {
            playerWrapper.print(true);
            getCoor.getCoordinates();
            playerBoard.setShipCoordinates(i, getCoor.getRow(), getCoor.getCol());
        }
    }

    public void Init(){
        int num1 = 0;
        int num2 = 0;
        num1 = player1UI.runInterface(player1Board, consoleInput);
        player1Board = new Board(9, 9, '~', num1, "player1Board");
        placeShipLoop(player1Board, player1Printer);
        num2 = player2UI.runInterface(player2Board, consoleInput);
        player2Board = new Board(9, 9, '~', num2, "player1Board");
        placeShipLoop(player2Board, player2Printer);
    }

    private void Loop(Board playerBoard, Board other, getUserInput UI){
        do {
            Tools.printMenu();
            do {
                try {
                    choice = UI.askOption(consoleInput);
                } catch (InputMismatchException ime) {
                    System.out.println("Please input an int.");
                }
            } while(choice > 0||choice < 3);
            switch(choice) {
                case 1:
                markBoard(other);
                break;
                case 2:
                TakeTheL(playerBoard);
                break;
            }

        } while(playerWon[0]||playerWon[1]);
    }

    private void markBoard(Board opponent){
        Tools.clearTerminal();
        player1Printer.print(true);
        player2Printer.print(true);
        getCoor.getCoordinates();
        if(opponent.getMarker(getCoor.getRow() - 1, getCoor.getCol() - 1) == 's'){
            opponent.addMarker('x', getCoor.getRow() - 1, getCoor.getCol() - 1);
            System.out.println("It's a hit!");
        } else {
            opponent.addMarker('o', getCoor.getRow() - 1, getCoor.getCol() - 1);
            System.out.println("It's a miss!");
        }


    }

    private void TakeTheL(Board playerBoard){
        if(playerBoard.isEq(player1Board)) {
            playerWon[1] = true;
        } else {
            playerWon[0] = true;
        }
    }

    public void Play(){
        Loop(player1Board, player2Board, player1UI);
        Loop(player2Board, player1Board, player2UI);
    }

    public void Game() {
        Init();
        Play();
    }

}
