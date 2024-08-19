package com.minesweeperapp.util;

/**
 * A utility class for validating game inputs. 
 * 
 * @author Mark Isla
 */
public class GameInputValidationUtil {
    
    /**
     * Assumed percentage (in decimal format) of mines against the total number of squares.
     */
    public static final double MAX_MINES = 0.35;

    /**
     * Assumed maximum grid size so that the game can fit nicely on the screen
     */
    public static final int GRID_MAX_SIZE = 52;

    /**
     * Method for validating if grid size and number of mines entered are less than what is allowed by the game.
     * @param gridSize preferred grid size of the player.
     * @param mineNum number of mines entered by the player.
     * @return boolean value if the grid size and number of mines entered are less than the allowable number.
     */
    public static boolean isLessThanOne(int gridSize, int mineNum){
        return gridSize < 1 || mineNum < 1 ? true : false;
    }

    /**
     * Method for validating if grid size entered is greater than the game max size.
     * @param gridSize preferred grid size of the player.
     * @return boolean value if the grid size is greater than the max size.
     */
    public static boolean isGreaterThanMaxSize(int gridSize){
        return gridSize > GRID_MAX_SIZE ? true : false;
    }

    /**
     * Method for validating if the number of mines is greater than the maximum allowed by the game.
     * @param gridSize preferred grid size of the player.
     * @param mineNum number of mines entered by the player.
     * @return boolean value if the number of mines is greater than the allowable number.
     */
    public static boolean isGreaterThanMaxMines(int gridSize, int mineNum){
        return mineNum > gridSize * gridSize * MAX_MINES ? true : false;
    }

    /**
     * Method for validating if the row or column is greater than the grid size
     * @param gridSize preferred grid size of the player.
     * @param row row number extracted from the square value entered by the player.
     * @param col column number extracted from the square value entered by the player.
     * @return boolean value if the row or column is greater than the grid size.
     */
    public static boolean isGreaterThanGrid(int gridSize, int row, int col){
        return row > gridSize || col > gridSize ? true : false;
    }

}
