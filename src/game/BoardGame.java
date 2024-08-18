package src.game;

import src.exception.InvalidGameSizeException;
import src.util.CommonUtil;
import src.util.MathUtil;

/**
 * A parent class for board game which has methods for initializing and printing the grid
 * @author Mark Isla
 */
public class BoardGame {

    /**
     * Grid size of the game.
     */
    protected int gridSize;

    /**
     * Grid array to set the grid and to be used for printing the grid.
     */
    protected String [][] grid;

    /**
     * Column header of the grid to be used in printing the grid.
     */
    protected String [] colHeader;

    /**
     * Row label of the grid to be used in printing the grid.
     */
    protected String [] rowLabel; 

    /**
     * Used in setting grid spaces.
     */
    protected String gridSpacer = " ";

    /**
     * Used in setting spaces between mines.
     */
    protected String mineSpacer = "";

    /**
     * Used for marking squares in the grid.
     */
    protected String squareMarker = "_";

    /**
     * Method for initializing the game.
     * @throws InvalidGameSizeException for invalid grid size.
     */
    protected void initializeGrid() throws InvalidGameSizeException {

        // if invalid grid size is encounterd, throw exception.
        if(gridSize < 1){
            throw new InvalidGameSizeException("Invalid grid size.");
        }

        // initialize the grid space and square marker for better printing
        int power = MathUtil.checkPower(gridSize);
        for(int i = 0; i < power; i++){
            gridSpacer = gridSpacer + " ";
            squareMarker = squareMarker + "_";
            mineSpacer = mineSpacer + " ";
        }
        
        // initialize the grid column header 
        colHeader = new String [gridSize];
        for(int i = 0; i < gridSize; i++){
            colHeader[i] = "";
            for(int j = 0; j < power; j++){
                if(i+1 < Math.pow(10, power)) colHeader[i] = " " + colHeader[i];
            }
            colHeader[i] = colHeader[i] + (i+1);
        }

        // initialize the grid row label
        rowLabel = new String [gridSize];

        // for the first row label, add appropriate spaces depending on the grid size
        rowLabel[0] = "A" + rowLabelSpacer(gridSize,0);

        // for succeeding row labels, determine the A1 reference style and add the appropriate label spacer
        for(int k = 1; k < gridSize; k++){
            rowLabel[k] = CommonUtil.convertIntToLetter(k + 1) + rowLabelSpacer(gridSize,k);
        }

        // initialize the grid
        grid = new String [gridSize][gridSize];
        for(int k = 0; k < gridSize; k++){
            for(int l = 0; l < gridSize; l++){
                grid[k][l] = squareMarker; 
            }
        }
    }

    /**
     * Method to determine appropriate space in row labels for better printing.
     * If grid size is greater than 26, row labels less than AA (e.g. A to Z) should have 1 more space before the square.
     * If grid size is greater than 9 but less than or equal to 26, all row labels should have 1 more space before the square
     * because the column header has one more character (e.g. 10, 11, 25, 26, etc.)
     * If grid size is 9 or less, use default spacing (1 space before the square).
     * @param gridSize grid size of the game as preferred by the player.
     * @param rowLabelArrayPos the array position in integer of the row label.
     * @return the appropriate space between the row label and the square.
     */
    private String rowLabelSpacer(int gridSize, int rowLabelArrayPos){
        if(gridSize > 26 && rowLabelArrayPos < 26){
            return " ";
        }
        else{
            if(gridSize > 9 && gridSize <= 26){
                return " ";
            }
            else{
                return "";
            }
        }

    }

}
