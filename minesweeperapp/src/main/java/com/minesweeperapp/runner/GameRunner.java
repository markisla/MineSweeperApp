package com.minesweeperapp.runner;
import java.util.Scanner;

import com.minesweeperapp.exception.InvalidGameSizeException;
import com.minesweeperapp.game.MineSweeper;
import com.minesweeperapp.util.CommonUtil;
import com.minesweeperapp.util.GameInputValidationUtil;

/**
 * The game runner class containing methods that will be invoked to play the game
 * @author Mark Isla
 */
public class GameRunner {
    /**
     * Marker the player can input to end the game.
     */
    private final String END_GAME_MARKER = "#";

    private Scanner scanner = new Scanner(System.in);
    private MineSweeper ms;
    private int gridSize, mineNum;
    private int counter, row, col;
    private String move = "";

    private boolean isGameOver, startAgain, exceptionEncountered;

    /**
     * Constructor for game runner.
     */
    public GameRunner(){
    }

    /**
     * Public method which can be called to run the game.
     */
    public void run(){
        do{
            initializeVars();
            printHeader();
            askPreference();
            if(!exceptionEncountered){ // number/integer was entered as preference and valid grid size was initialized 
                if(isPreferenceValid()){
                    initializeGame();
                    printGame();
                    while(!isGameOver){
                        askInput();
                        if(move.equalsIgnoreCase(this.END_GAME_MARKER)){
                            this.isGameOver = true;
                        }
                        else{
                            if(isInputValid()){   
                                // input is a valid square
                                processInput();
                            }
                        }
                    }            
                }
                playAgain();
            }
        } while(startAgain);
        scanner.close();
    }

    /**
     * Method to initialize and re-initialize variables before the game.
     */
    private void initializeVars(){
        this.isGameOver = false;
        this.startAgain = false;
        this.exceptionEncountered = false;
        this.gridSize = 0;
        this.mineNum = 0;
        this.counter = 0;
        this.row = 0;
        this.col = 0;
        this.move = "";
    }

    /**
     * Method for printing the header of the game.
     */
    private void printHeader(){
        System.out.println("```");
        System.out.println("Welcome to Minesweeper!");
        System.out.println("");
    }

    /**
     * Method for asking the player's preference to build the grid.
     */
    private void askPreference(){
        try{
            // ask players's preference 
            System.out.println("Enter the size of the grid (e.g. 4 for a 4x4 grid). Max grid size is " + GameInputValidationUtil.GRID_MAX_SIZE + ": ");
            this.gridSize = this.scanner.nextInt();
            System.out.println("Enter the number of mines to place on the grid (maximum is " + String.format("%.0f", GameInputValidationUtil.MAX_MINES*100) + "% of the total squares): ");
            this.mineNum = this.scanner.nextInt();
            this.scanner.nextLine();
        } catch (java.util.InputMismatchException ime){
            // assumption: current game will end if invalid input is entered by the player.
            System.out.println("Sorry, you have entered and invalid value. Game will now end.");
            this.exceptionEncountered = true;
        }
    }

    /**
     * Method to validate if the player's preferences are valid.
     * @return true if all the player's preferences are valid and allowed by the game. Otherwise, will return false.
     */
    private boolean isPreferenceValid(){
        // validating grid size and number of mines against the numbers allowed by the game
        if(GameInputValidationUtil.isLessThanOne(this.gridSize, this.mineNum)){
            System.out.println("Sorry, the grid size or number of mines cannot be less than one.");
            return false;
        }
        else if(GameInputValidationUtil.isGreaterThanMaxSize(gridSize)){
            System.out.println("Sorry, the grid size cannot exceed "+  GameInputValidationUtil.GRID_MAX_SIZE +".");
            return false;
        }
        else if(GameInputValidationUtil.isGreaterThanMaxMines(gridSize, mineNum)){
            System.out.println("Sorry, the number of mines cannot exceed 35% of the total squares.");
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Method for initializing the mine sweeper game.
     */
    private void initializeGame(){
        try{
            ms = new MineSweeper(this.gridSize, this.mineNum);
        }catch(InvalidGameSizeException igse){
            // invalid game size encountered
            System.out.println("Sorry, the grid size entered is invalid.");
            this.exceptionEncountered = true;
        }
    }

    /**
     * Method for printing the game's grid.
     */
    private void printGame(){
        String updated = (counter > 0) ? " updated " : " ";
        System.out.println("");
        System.out.println("Here is your" + updated + "minefield:");
        this.ms.printGrid();
        System.out.println("");
    }

    /**
     * Method for asking the player's input.
     */
    private void askInput(){
        this.move = "";
        System.out.print("Select a square to reveal (e.g. A1). Enter '#' to end current game: ");
        if(this.scanner.hasNextLine()){
            this.move = this.scanner.nextLine();
        }
    }

    /**
     * Method for validating if the player's input is a valid square value, and extract row and column from the square input. 
     * @return true if the input is a valid square value. Otherwise, return false.
     */
    private boolean isInputValid(){
        String square = CommonUtil.splitSquare(this.move);
        String [] squareArr = square.split("-");
        
        if(squareArr.length == 2){
            this.row = CommonUtil.convertLetterToInt(squareArr[0]); // convert row letter to number/integer.
            try{
                this.col = Integer.parseInt(squareArr[1]);
                // the row or column from the square input is less than 1
                if(GameInputValidationUtil.isLessThanOne(this.row, this.col)){
                    System.out.println("Sorry, the square value cannot be less than one.");
                    return false;
                }
                // the row or column from the square input exceed the grid size.
                else if(GameInputValidationUtil.isGreaterThanGrid(gridSize, this.row, this.col)){
                    System.out.println("Sorry, the square value cannot exceed the grid size.");
                    return false;
                }
                else{
                    return true;
                }
                
            }catch(NumberFormatException e) {
                // the column part of the square input is not a valid number/integer.
                System.out.println("Sorry, the square value entered is invalid.");
                return false;
            }
        }
        else{
            System.out.println("Sorry, the square value entered is invalid.");
            return false;
        }
    }

    /**
     * Method for processing the player's input and for checking if the player wins, loses, or the game still proceeds.
     */
    private void processInput(){              
        int minesAround = this.ms.checkMine(row-1, col-1); //subtract one from both row and column since grid and mine array start at 0

        if(minesAround == -2){
            this.isGameOver = true;
            System.out.println("Congratulations, you have won the game!");
        }
        else if(minesAround == -1){
            this.isGameOver = true;
            System.out.println("Oh no, you detonated a mine! Game over.");
        }
        else{
            System.out.println("This square contains " + minesAround + " adjacent mines.");
            counter++;
            printGame();
        }
    }

    /**
     * Method for asking the player if he wants to play again after ending the current game.
     */
    private void playAgain(){
        String input = "";
        System.out.println("");
        System.out.print("Enter 'Y' or 'y' to start a new game, or enter any other key to exit the app: ");
        if(this.scanner.hasNextLine()){
            input = this.scanner.nextLine();
        }
        startAgain = input.equalsIgnoreCase("Y") ? true : false;
    }
    
}
