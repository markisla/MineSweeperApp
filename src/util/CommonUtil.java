package src.util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A utility class for row label and square input which are in A1 reference style
 * 
 * @author Mark Isla
 */
public class CommonUtil {

    /**
     * Contains the regex patter for A1 references style used in row label of the game.
     */
    private static final String A1_PATTERN = "^[A-Z]+\\d+$";
    private static final Pattern pattern = Pattern.compile(A1_PATTERN);

    /**
     * Utility method for converting row label from integer to A1 reference style.
     * @param row the row to be converted in integer format.
     * @return the converted row in A1 reference style.
     */
    public static String convertIntToLetter(int row){
        StringBuilder newRowLabel = new StringBuilder();
        while(row > 0) {
            int remainder = (row - 1) % 26;
            newRowLabel.insert(0, (char)('A' + remainder)); // do addition to the char A to get its int value
            row = (row - remainder) / 26;
        }
        return newRowLabel.toString();
    }

    /**
     * Utility method for converting row label from A1 reference style to integer.
     * @param letter the row to be converted in A1 reference style.
     * @return the converted row as integer.
     */
    public static int convertLetterToInt(String letter){
        int letterVal, row = 0;
        for(int i = 0; i < letter.length(); i++){
            letterVal = letter.charAt(i) - 'A' + 1; // do subtraction to the char to get the int value
            row = row * 26 + letterVal;
        }

        return row;
    }

    /**
     * Utility method for determining if square is in A1 reference style and returning the letter and number parts.
     * @param squareInput the input square value.
     * @return a string containing 'letter-number' extracted from the square input, or empty if the input is not a valid square.
     */
    public static String splitSquare(String squareInput){
        Matcher matcher = pattern.matcher(squareInput);
        if(matcher.find()){
            String referenceStyle = matcher.group();
            String [] parts = referenceStyle.split("(?<=\\D)(?=\\d)");
            String letters = parts[0];
            String numbers = parts[1];
            return letters + "-" + numbers;
        }
        else{
            return "";
        }
    }
}
