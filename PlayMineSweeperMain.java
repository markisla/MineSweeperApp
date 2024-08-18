
import java.util.Scanner;

import src.exception.InvalidGameSizeException;
import src.game.MineSweeper;
import src.util.CommonUtil;
import src.util.GameInputValidationUtil;

/**
 * The class containing the main method to play the Minesweeper game
 * @author Mark Isla
 */
public class PlayMineSweeperMain {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;
        String input = "";

        do{
            boolean areInputsInvalid = false;
            boolean isCurrentGameOver = false;

            int gridSize, mineNum = 0;

            try{
                // print the heading of the game
                System.out.println("```");
                System.out.println("Welcome to Minesweeper!");
                System.out.println("");

                // ask players's preference 
                System.out.println("Enter the size of the grid (e.g. 4 for a 4x4 grid). Max grid size is " + GameInputValidationUtil.GRID_MAX_SIZE + ": ");
                gridSize = scanner.nextInt();
                System.out.println("Enter the number of mines to place on the grid (maximum is " + String.format("%.0f", GameInputValidationUtil.MAX_MINES*100) + "% of the total squares): ");
                mineNum = scanner.nextInt();
                scanner.nextLine();

                // validating grid size and number of mines against the numbers allowed by the game
                areInputsInvalid = GameInputValidationUtil.isLessThanOne(gridSize, mineNum);
                if(areInputsInvalid == true){
                    System.out.println("Sorry, the grid size or number of mines cannot be less than one.");
                }
                else{
                    areInputsInvalid = GameInputValidationUtil.isGreaterThanMaxSize(gridSize);
                    if(areInputsInvalid == true){
                        System.out.println("Sorry, the grid size cannot exceed "+  GameInputValidationUtil.GRID_MAX_SIZE +".");
                    }
                    else{
                        areInputsInvalid = GameInputValidationUtil.isGreaterThanMaxMines(gridSize, mineNum);
                        if(areInputsInvalid == true){
                            System.out.println("Sorry, the number of mines cannot exceed 35% of the total squares.");
                        } 
                    }
                }

                
                try{
                    if(!areInputsInvalid){

                        // initialize the game
                        MineSweeper ms = new MineSweeper(gridSize, mineNum);
                        
                        System.out.println("");
                        System.out.println("Here is your minefield:");
                        ms.printGrid();
                        System.out.println("");

                        do{
                            int row = 0;
                            int col = 0; 
                            int minesAround = 0;
                            input = "";
                            areInputsInvalid = false;

                            System.out.print("Select a square to reveal (e.g. A1). Enter '#' to end current game: ");
                            if(scanner.hasNextLine()){
                                input = scanner.nextLine();
                            }

                            if(input.equalsIgnoreCase("#")){
                                isCurrentGameOver = true;
                            }
                            else{
                                // validate square input and extract row and column from the square input
                                String square = CommonUtil.splitSquare(input);
                                String [] squareArr = square.split("-");
                                if(squareArr.length == 2){
                                    row = CommonUtil.convertLetterToInt(squareArr[0]); // convert row letter to number/integer.
                                    
                                    try{
                                        col = Integer.parseInt(squareArr[1]);
                                    }catch(NumberFormatException e) {
                                        areInputsInvalid = true;
                                    }

                                    if(areInputsInvalid == true){ // the column part of the square input is not a valid number/integer.
                                        System.out.println("Sorry, the square value entered is invalid.");
                                    }
                                    else{
                                        areInputsInvalid = GameInputValidationUtil.isLessThanOne(row, col);
                                        if(areInputsInvalid == true){ // the row or column from the square input is less than 1
                                            System.out.println("Sorry, the square value cannot be less than one.");
                                        }
                                        else{
                                            areInputsInvalid = GameInputValidationUtil.isGreaterThanGrid(gridSize, row, col);
                                            if(areInputsInvalid == true){ // the row or column from the square input exceed the grid size.
                                                System.out.println("Sorry, the square value cannot exceed the grid size.");
                                            }
                                        }
                                    }
                                }
                                else{
                                    areInputsInvalid = true;
                                    System.out.println("Sorry, the square value entered is invalid.");
                                }

                                if(!areInputsInvalid){ // input is a valid square
                                    
                                    minesAround = ms.checkMine(row-1, col-1); //subtract one from both row and column since grid and mine array start at 0

                                    if(minesAround == -2){
                                        isCurrentGameOver = true;
                                        System.out.println("Congratulations, you have won the game!");
                                    }
                                    else{
                                        if(minesAround == -1){
                                            isCurrentGameOver = true;
                                            System.out.println("Oh no, you detonated a mine! Game over.");
                                        }
                                        else{
                                            System.out.println("This square contains " + minesAround + " adjacent mines.");
                                            System.out.println("");
                                            System.out.println("Here is your updated minefield:");
                                            ms.printGrid();
                                            System.out.println("");
                                        }
                                        
                                    }
                                    
                                }
                            }
                            
                        }while(!isCurrentGameOver);
                    }


                }catch(InvalidGameSizeException igse){
                    // invalid game size encountered
                    System.out.println("Sorry, the grid size entered is invalid.");
                    areInputsInvalid = true;
                }
                
            }
            catch(java.util.InputMismatchException ime){
                // assumption: current game will end if invalid input is entered by the player.
                System.out.println("Sorry, you have entered and invalid value. Current game will now end.");
            }

            // ask the player if he wants to play again.
            System.out.println("");
            System.out.print("Enter 'Y' or 'y' to start a new game, or enter any other key to exit the app: ");
            if(scanner.hasNextLine()){
                input = scanner.nextLine();
            }
            playAgain = input.equalsIgnoreCase("Y") ? true : false;

        }while(playAgain);

        scanner.close();
    }
}
