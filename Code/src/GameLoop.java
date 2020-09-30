import java.util.InputMismatchException;
import java.util.Scanner;
//import java.lang.IllegalArgumentException;

public class GameLoop {
    private Board player1Board;
    private BoardPrinterWrapper player1Printer;
    private Board player2Board;
    private BoardPrinterWrapper player2Printer;
    private getUserInput player1UI;
    private getUserInput player2UI;
    private Scanner consoleInput = new Scanner(System.in);
    private Utility Tools;
    private safelyGetCoordinates getCoor;
    private int choice = 0;
    private boolean[] playerWon = {false, false};
    private PlaceShip player1place;
    private PlaceShip player2place; 

    public GameLoop() {
        this.player1UI = new getUserInput(1);
        this.player2UI = new getUserInput(2);
        this.getCoor = new safelyGetCoordinates();
        this.Tools = new Utility();
    }


    public void placeShipLoop(Board playerBoard, BoardPrinterWrapper playerWrapper, PlaceShip placeIt) {
        for(int i = 0; i < playerBoard.getNumberOfShips(); i++) {
            playerWrapper.print(false);
            getCoor.getCoordinates();
            System.out.println("Horizontal or vertical? Enter H or V.");
            boolean hori = (consoleInput.next() == "h" || consoleInput.next() == "H")&&(consoleInput.next() != "v"||consoleInput.next() != "V");
            placeIt.place(getCoor.getRow(), getCoor.getCol(), i + 1, hori);
            
        }
    }

    public void Init(){
        int num1 = 0;
        int num2 = 0;
        num1 = player1UI.runInterface(player1Board, consoleInput);
        player1Board = new Board(9, 9, '~', num1, "player1Board");
        player1Printer = new BoardPrinterWrapper(player1Board, 's', '~', true);
        player1place = new PlaceShip(player1Board);
        placeShipLoop(player1Board, player1Printer, player1place);

        num2 = player2UI.runInterface(player2Board, consoleInput);
        player2Board = new Board(9, 9, '~', num2, "player1Board");
        player2Printer = new BoardPrinterWrapper(player2Board, 's', '~', true);
        player2place = new PlaceShip(player2Board);
        placeShipLoop(player2Board, player2Printer, player2place);
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
        if(playerWon[0]){
            System.out.println("Congratulations! Player 1 has won.");
        } else {
            System.out.println("Congratulations! Player 2 has won.");
        }
    }

    private void markBoard(Board opponent){
        Tools.clearTerminal();
        player1Printer.print(false);
        player2Printer.print(false);
        getCoor.getCoordinates();
        if(opponent.getMarker(getCoor.getRow(), getCoor.getCol()) == 's'){
            opponent.addMarker('x', getCoor.getRow(), getCoor.getCol());
            System.out.println("It's a hit!");
        } else {
            opponent.addMarker('o', getCoor.getRow(), getCoor.getCol());
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
