package src.game;

import java.util.Random;

import src.exception.InvalidGameSizeException;

/**
 * A child class for Minesweeper game which extends the Board Game parent class. 
 * @author Mark Isla
 */
public class MineSweeper extends BoardGame {

    /**
     * Contains the number of mines the player has set.
     */
    private int mineNum;

    /**
     * Contains the mines randomly generated.
     */
    private boolean [][] mineArr;

    /**
     * Contains the number of mines for the adjacent square
     */
    private int [][] adjacentMines; 

    /**
     * Constructor for Minesweeper game.
     * @param gridSize preferred grid size of the player.
     * @param mineNum number of mines entered by the player.
     * @throws InvalidGameSizeException for invalid grid size.
     */
    public MineSweeper(int gridSize, int mineNum) throws InvalidGameSizeException {
        this.gridSize = gridSize;
        this.mineNum = mineNum;
        this.initializeGrid();
        mineArr = new boolean[gridSize][gridSize];
        adjacentMines = new int[gridSize][gridSize];
        this.placeMine();
        this.calculateAdjacentMines();
    }

    /**
     * Method for printing the grid of the game.
     */
    public void printGrid(){
        // print the column header
        System.out.print(gridSpacer + " ");
        for(int i = 0; i < gridSize; i++){
            System.out.print(colHeader[i] + gridSpacer);
        }
        System.out.println("");

        // print the row label and the grid content
        for(int j = 0; j < gridSize; j++){
            System.out.print(rowLabel[j] + " "); // print the row label
            for(int k = 0; k < gridSize; k++){
                System.out.print(grid[j][k] + gridSpacer);
            }
            System.out.println(""); // add next line for the next row
        }
    }

    /**
     * Method for creting random mines and placing them.
     */
    private void placeMine(){
        Random random = new Random();
        int placedMine = 0;
        while(placedMine < mineNum){
            int row = random.nextInt(this.gridSize);
            int col = random.nextInt(this.gridSize);
            if(!mineArr[row][col]){
                mineArr[row][col] = true;
                placedMine++;
            }
        }
    }

    /*
     * Method for calculating the adjacent mines
     */
    private void calculateAdjacentMines(){
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                if (mineArr[row][col]) {
                    continue; // skip square with mines
                }

                int count = 0;
                for (int i = Math.max(0, row - 1); i <= Math.min(gridSize - 1, row + 1); i++) {
                    for (int j = Math.max(0, col - 1); j <= Math.min(gridSize - 1, col + 1); j++) {
                        if (mineArr[i][j]) {
                            count++;
                        }
                    }
                }
                adjacentMines[row][col] = count; // store count of mine
            }
        }
    }

    /**
     * Method for checking if the selected square is a mine or not.
     * @param row row number of the selected square.
     * @param col column number of the selected square.
     * @return -1 if a mine.
     * -2 if not a mine and all non-mine squares have been opened.
     * Or the number of mines near the selected square.
     */
    public int checkMine(int row, int col){
        
        // if selected square is not a mine
        if(!mineArr[row][col]){

            // if minesAround is zero, uncover all adjacent squares until it reaches squares that do have adjacent mines.
            if(adjacentMines[row][col] == 0){
                revealAdjacent(row, col);
            }
            else{
                grid[row][col] = mineSpacer + adjacentMines[row][col];
            }

            // check if the player wins
            if(didPlayerWin()){
                return -2; // if player wins
            }
            else{
                return adjacentMines[row][col]; // return mines around if player has not win yet
            }
        }
        else{
            grid[row][col] = mineSpacer + "X";
            return -1; // return -1 if mine is found
        }
        
    }

    /**
     * Method for uncovering adjacent squares until it reaches squares that do have adjacent mines.
     * @param row row number of the selected square.
     * @param col column number of the selected square.
     */
    private void revealAdjacent(int row, int col){
        if(!mineArr[row][col] && !grid[row][col].contains(this.squareMarker)){
            return; 
        }

        grid[row][col] = mineSpacer + adjacentMines[row][col];
        
        if (adjacentMines[row][col] == 0) {
            for (int i = Math.max(0, row - 1); i <= Math.min(gridSize - 1, row + 1); i++) {
                for (int j = Math.max(0, col - 1); j <= Math.min(gridSize - 1, col + 1); j++) {
                    if (grid[i][j].contains(this.squareMarker)) {
                        revealAdjacent(i, j);
                    }
                }
            }
        }

    }

    /**
     * Method to check if the player wins
     * @return boolean if the player wins
     */
    private boolean didPlayerWin(){
        int countUnopenedSquares = 0;
        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                if(grid[i][j].contains(this.squareMarker)) countUnopenedSquares++;
            }
        }
        return countUnopenedSquares == mineNum ? true : false;
    }

}