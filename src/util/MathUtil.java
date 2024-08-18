package src.util;

/**
 * A utility class containing arithmetic methods
 * 
 * @author Mark Isla
 */
public class MathUtil {

    /**
     * Method to check what power of 10 the input number is less than or equal to.
     * @param num integer
     * @return the power of 10
     */
    public static int checkPower(int num){
        int pow = 0;
        int base = 10;

        while(num >= Math.pow(base, pow + 1)){
            pow++;
        }
        return pow;
    }
}
