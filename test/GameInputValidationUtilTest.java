package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import src.util.GameInputValidationUtil;

public class GameInputValidationUtilTest {
    
    @Test
    public void testIsLessThanOne() {
        assertTrue(GameInputValidationUtil.isLessThanOne(0, 0));
        assertTrue(GameInputValidationUtil.isLessThanOne(-1, 5));
        assertTrue(GameInputValidationUtil.isLessThanOne(5, -1));
        assertFalse(GameInputValidationUtil.isLessThanOne(5, 2));
    }

    @Test
    public void testIsGreaterThanMaxSize() {
        assertFalse(GameInputValidationUtil.isGreaterThanMaxSize(10));
        assertTrue(GameInputValidationUtil.isGreaterThanMaxSize(GameInputValidationUtil.GRID_MAX_SIZE + 1));
    }

    @Test
    public void testIsGreaterThanMaxMines() {
        assertFalse(GameInputValidationUtil.isGreaterThanMaxMines(10, 4));
        assertTrue(GameInputValidationUtil.isGreaterThanMaxMines(10, (int) (10 * 10 * GameInputValidationUtil.MAX_MINES + 1)));
    }

    @Test
    public void testIsGreaterThanGrid() {
        assertFalse(GameInputValidationUtil.isGreaterThanGrid(10, 5, 5));
        assertTrue(GameInputValidationUtil.isGreaterThanGrid(10, 11, 5));
        assertTrue(GameInputValidationUtil.isGreaterThanGrid(10, 5, 11));
    }
}
