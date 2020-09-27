public class safelyGetCoordinates {
    private char[] coordinateLetters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i' };
    private boolean invalidInput = true;
    private String input = "";
    private String output = "";
    private boolean colValid = false;
    private boolean rowValid = false;
    private int numLoc = 0;
    private char col = 'z';
    private char row = 'z';
    private int column = 0;
    private int rowboat = 0;

    public int getRow() {
        return rowboat;
    }

    public int getCol() {
        return column;
    }

    private int letterToInt(char col) {
        col = Character.toLowerCase(col);
        for (int i = 0; i < coordinateLetters.length; i++) {
            if (col == coordinateLetters[i]) {
                return (i);
            }
        }
        throw new IllegalArgumentException(col + " is out of bounds.");
    }

    private boolean convertCoordinates() {
        try {
            column = letterToInt(col);
            colValid = true;
        } catch (IllegalArgumentException iae) {
            Utility.errorMessage(iae, "Please input coordinates within the range of A1 - I9");
            colValid = false;
        }
        try {
            rowboat = Integer.parseInt("" + row); // This will throw a NumberFormatException if row is not a
                                               // number
            if (rowboat < 1 || rowboat > 9) {
                System.out.println("Please input coordinates within the range of A1 - I9");
                rowValid = false;
            } else {
                rowValid = true;
            }
        } catch (NumberFormatException nfe) {
            System.out.println(row + " is not an int.");
            System.out.println("Please input coordinates in the format: A1.");
            rowValid = false;
        }
        return true;
    }

    public String getCoordinates() {
        do {
            input = Utility.consoleInput.next();
            if (input.length() < 2) {
                System.out.println("Please input coordinates in the format: A1");
            } else {
                col = input.charAt(0);
                row = input.charAt(1);
                if (convertCoordinates()) {
                    output = "" + col + row;
                    invalidInput = false; // This line won't be reached until there are no errors
                }
            }

        } while (invalidInput);
        return (output);
    }
}