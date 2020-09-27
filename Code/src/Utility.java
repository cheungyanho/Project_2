import java.lang.IllegalArgumentException;
import java.util.Scanner;

/*
Note for others: the utility class contains
an interior class mainly because it was long and suited to be its own class
but it also uses some of the private methods here. just seemed like an appropriate
design decision. This way it'll be a bit more modular even internally. 
*/

public class Utility {
    public static Scanner consoleInput = new Scanner(System.in);
    private char[] coordinateLetters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'};
    private void clearTerminal() {
        for (int i = 0; i < 50; i++) {
            System.out.println("");
        }
    }
    
    private boolean validateShipNum() {
        
    }
    private void printMenu() {

    }
    private int safelyGetIntInput() {
        
    }
    public class safelyGetCoordinates {
        
        
    }

    
    public static void errorMessage(IllegalArgumentException iae, String message) {
        System.out.println(iae.getMessage());
        System.out.println(message);
    }
    

    private void getInput() {
        //safelyGetCoordinates.getCoordinates();
        //input.getCoordinates();
    }

    private void chooseShipNum() {

    }
    public void runUtility(){
        
    }
}

