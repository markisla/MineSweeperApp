package test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

import src.exception.InvalidGameSizeException;
import src.game.MineSweeper;

public class MineSweeperTest {

    private MineSweeper mineSweeper;
    private int gridSize;
    private int mineNum;

    @Before
    public void setUp() throws InvalidGameSizeException {
        gridSize = 4;
        mineNum = 3;
        mineSweeper = new MineSweeper(gridSize, mineNum);
    }

    // test the private method named placeMine
    @Test
    public void testPlaceMine() throws Exception {
        // make private fields accessible for testing purposes
        Field mineArrField = MineSweeper.class.getDeclaredField("mineArr");
        mineArrField.setAccessible(true);
        boolean [][] mineArr = (boolean [][]) mineArrField.get(mineSweeper);

        int mineCount = 0;
        for (boolean [] row : mineArr) {
            for (boolean cell : row) {
                if (cell) {
                    mineCount++;
                }
            }
        }
        assertEquals(mineNum, mineCount);
    }

    // test the public method named checkMine
    @Test
    public void testCheckMine() throws Exception {
        Field mineArrField = null;
        try {
            // make private fields accessible for testing purposes
            mineArrField = MineSweeper.class.getDeclaredField("mineArr");
            mineArrField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        boolean [][] mineArr = new boolean[gridSize][gridSize];
        mineArr [1][1] = true; // set one mine
        mineArrField.set(mineSweeper, mineArr);

        int result = mineSweeper.checkMine(3, 3);
        assertEquals(0, result);

        result = mineSweeper.checkMine(1, 1);
        assertEquals(-1, result);
    }


    // test the private method named calculateAdjacentMines
    @Test
    public void testCalculateAdjacentMines() throws Exception {

        // make field accessible for testing purposes
        Field mineArrField = MineSweeper.class.getDeclaredField("mineArr");
        mineArrField.setAccessible(true);
        boolean [][] mineArr = new boolean [gridSize][gridSize];
        mineArr [2][3] = true;
        mineArr [3][1] = true;
        mineArr [3][2] = true;
        mineArrField.set(mineSweeper, mineArr);

        // make private method accessible for testing purposes
        Method calculateAdjacentMinesMethod = MineSweeper.class.getDeclaredMethod("calculateAdjacentMines");
        calculateAdjacentMinesMethod.setAccessible(true);

        // make field accessible for testing purposes
        Field adjacentMinesField = MineSweeper.class.getDeclaredField("adjacentMines");
        adjacentMinesField.setAccessible(true);
        int [][] adjacentMines = (int [][]) adjacentMinesField.get(mineSweeper);

        calculateAdjacentMinesMethod.invoke(mineSweeper);

        assertEquals(3, adjacentMines[2][2]);
        
    }

    // test the private method named didPlayerWin
   @Test
    public void testDidPlayerWin() throws Exception {
        // make field accessible for testing purposes
        Field gridField = MineSweeper.class.getSuperclass().getDeclaredField("grid");
        gridField.setAccessible(true);
        String [][] grid = (String [][]) gridField.get(mineSweeper);

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = "0";
            }
        }

        // set up a grid where player wins, 3 mines and grid has 3 squares with underscore in them instead of mine count.
        grid[1][1] = "__";
        grid[1][3] = "__";
        grid[2][2] = "__";

        gridField.set(mineSweeper, grid);

        // make private method accessible for testing purposes
        Method didPlayerWinMethod = MineSweeper.class.getDeclaredMethod("didPlayerWin");
        didPlayerWinMethod.setAccessible(true);

        boolean didPlayerWin = (boolean) didPlayerWinMethod.invoke(mineSweeper);
        assertTrue(didPlayerWin);

        // set up a grid where player does not win yet, 3 mines and grid has 4 squares with underscore in them instead of mine count.
        grid[1][1] = "__";
        grid[1][3] = "__";
        grid[2][2] = "__";
        grid[3][3] = "__";

        gridField.set(mineSweeper, grid);

        didPlayerWin = (boolean) didPlayerWinMethod.invoke(mineSweeper);
        assertFalse(didPlayerWin);

    }

}