package com.minesweeperapp.exception;

/**
 * Custom Exception thrown when the grid size preferred by the player is not valid.
 * @author Mark Isla
 */
public class InvalidGameSizeException extends Exception {

    /**
     * Public method for throwing the invalid game size exception.
     * @param errorMessage returns the error message.
     */
    public InvalidGameSizeException(String errorMessage){
        super(errorMessage);
    }
}
