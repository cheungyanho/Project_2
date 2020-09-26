import java.lang.IllegalArgumentException;
import java.util.Scanner;

/*
Note for others: the utility class contains
an interior class mainly because it was long and suited to be its own class
but it also uses some of the private methods here. just seemed like an appropriate
design decision. This way it'll be a bit more modular even internally. 
*/

public class Utility {
    private Scanner consoleInput = new Scanner(System.in);
    private char[] coordinateLetters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'};
    private void clearTerminal() {
        for (int i = 0; i < 50; i++) {
            System.out.println("");
        }
    }
    private int letterToInt(char col) {
        
    }
    private boolean validateShipNum() {
        
    }
    private void printMenu() {

    }
    private int safelyGetIntInput() {
        
    }
    private class safelyGetCoordinates {
        private boolean invalidInput = true;
        private String input = "";
        private String output = "";
        private boolean colValid;
        private boolean rowValid;
        private int numLoc;
        char col = 'z';
        char row = 'z';

        private boolean convertCoordinates() {
            try {
                numLoc = letterToInt(col);
                colValid = true;
            } catch(IllegalArgumentException iae) {
                errorMessage(iae, "Please input coordinates within the range of A1 - I9");
                colValid = false;
            }
        }
        public String getCoordinates() {
            while(true) {
                input = consoleInput.next();
            }
        }
        
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

