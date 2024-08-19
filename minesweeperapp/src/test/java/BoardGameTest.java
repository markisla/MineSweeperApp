import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

import com.minesweeperapp.game.BoardGame;
import com.minesweeperapp.util.CommonUtil;

public class BoardGameTest{

    private BoardGame boardGame;

    // initialize the BoardGame
    @Before
    public void setUp() {
        boardGame = new BoardGame();
    }

    // make private fields accessible for testing purposes
    private <T> void setFieldAccessibility(T obj, String fieldName, Object value) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }

    // make protected fields accessible for testing purposes
    @SuppressWarnings("unchecked")
    private <T> T getFieldAccessibility(Object obj, String fieldName) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return (T) field.get(obj);
    }

    // test the private method named rowLabelSpacer
    @Test
    public void testRowLabelSpacer() throws Exception {
        Method rowLabelSpacerMethod = BoardGame.class.getDeclaredMethod("rowLabelSpacer", int.class, int.class);
        rowLabelSpacerMethod.setAccessible(true);

        assertEquals(" ", rowLabelSpacerMethod.invoke(boardGame, 30, 0));
        assertEquals("", rowLabelSpacerMethod.invoke(boardGame, 30, 26));
        assertEquals(" ", rowLabelSpacerMethod.invoke(boardGame, 15, 0));
        assertEquals("", rowLabelSpacerMethod.invoke(boardGame, 5, 0));
    }

    // test initialization of grid with zero as grid size
    @Test(expected = InvocationTargetException.class)
    public void testInitializeGridSizeZero() throws Exception {
        int gridSize = 0;
        setFieldAccessibility(boardGame, "gridSize", gridSize);
        Method initializeGridMethod = BoardGame.class.getDeclaredMethod("initializeGrid");
        initializeGridMethod.setAccessible(true);
        initializeGridMethod.invoke(boardGame);

    }

    // test initialization of grid with -1 as grid size
    @Test(expected = InvocationTargetException.class)
    public void testInitializeGridSizeNegative() throws Exception {
        int gridSize = -1;
        setFieldAccessibility(boardGame, "gridSize", gridSize);
        Method initializeGridMethod = BoardGame.class.getDeclaredMethod("initializeGrid");
        initializeGridMethod.setAccessible(true);
        initializeGridMethod.invoke(boardGame);

    }

    // test initialization of grid with 1 as grid size
    @Test
    public void testInitializeGridSizeOne() throws Exception {
        int gridSize = 1;
        setFieldAccessibility(boardGame, "gridSize", gridSize);
        Method initializeGridMethod = BoardGame.class.getDeclaredMethod("initializeGrid");
        initializeGridMethod.setAccessible(true);
        initializeGridMethod.invoke(boardGame);

        // test grid initialization
        String[][] grid = getFieldAccessibility(boardGame, "grid");
        assertEquals(gridSize, grid.length);
        assertEquals(gridSize, grid[0].length);
        for (String[] row : grid) {
            for (String cell : row) {
                assertEquals("_", cell);
            }
        }

        // test grid column header initialization
        String[] colHeader = getFieldAccessibility(boardGame, "colHeader");
        assertEquals(gridSize, colHeader.length);
        for (int i = 0; i < gridSize; i++) {
            assertEquals(String.valueOf(i + 1), colHeader[i]);
        }

        Method rowLabelSpacerMethod = BoardGame.class.getDeclaredMethod("rowLabelSpacer", int.class, int.class);
        rowLabelSpacerMethod.setAccessible(true);

        // test grid row label initialization
        String[] rowLabel = getFieldAccessibility(boardGame, "rowLabel");
        assertEquals(gridSize, rowLabel.length);
        for (int i = 0; i < gridSize; i++) {
            assertEquals(CommonUtil.convertIntToLetter(i+1)+rowLabelSpacerMethod.invoke(boardGame, gridSize, i), rowLabel[i]);
        }
    }

    // test initialization of grid with 4 as grid size
    @Test
    public void testInitializeGridSizeFour() throws Exception {
        int gridSize = 4;
        setFieldAccessibility(boardGame, "gridSize", gridSize);
        Method initializeGridMethod = BoardGame.class.getDeclaredMethod("initializeGrid");
        initializeGridMethod.setAccessible(true);
        initializeGridMethod.invoke(boardGame);

        // test grid initialization
        String[][] grid = getFieldAccessibility(boardGame, "grid");
        assertEquals(gridSize, grid.length);
        assertEquals(gridSize, grid[0].length);
        for (String[] row : grid) {
            for (String cell : row) {
                assertEquals("_", cell);
            }
        }

        // test grid column header initialization
        String[] colHeader = getFieldAccessibility(boardGame, "colHeader");
        assertEquals(gridSize, colHeader.length);
        for (int i = 0; i < gridSize; i++) {
            assertEquals(String.valueOf(i + 1), colHeader[i]);
        }

        // test grid row label initialization
        String[] rowLabel = getFieldAccessibility(boardGame, "rowLabel");
        assertEquals(gridSize, rowLabel.length);
        for (int i = 0; i < gridSize; i++) {
            assertEquals(String.format("%c", 'A' + i), rowLabel[i]);
        }
    }

    // test initialization of grid with 10 as grid size
    @Test
    public void testInitializeGridSizeTen() throws Exception {
        int gridSize = 10;
        setFieldAccessibility(boardGame, "gridSize", gridSize);
        Method initializeGridMethod = BoardGame.class.getDeclaredMethod("initializeGrid");
        initializeGridMethod.setAccessible(true);
        initializeGridMethod.invoke(boardGame);

        // test grid initialization
        String[][] grid = getFieldAccessibility(boardGame, "grid");
        assertEquals(gridSize, grid.length);
        assertEquals(gridSize, grid[0].length);
        for (String[] row : grid) {
            for (String cell : row) {
                assertEquals("__", cell);
            }
        }

        // test grid column header initialization
        String[] colHeader = getFieldAccessibility(boardGame, "colHeader");
        assertEquals(gridSize, colHeader.length);
        for (int i = 0; i < gridSize; i++) {
            assertEquals(String.format("%2d", i + 1), colHeader[i]);
        }

        // test grid row label initialization
        String[] rowLabel = getFieldAccessibility(boardGame, "rowLabel");
        assertEquals(gridSize, rowLabel.length);
        for (int i = 0; i < gridSize; i++) {
            assertEquals(String.format("%c ", 'A' + i), rowLabel[i]);
        }
    }

    // test initialization of grid with 52 as grid size
    @Test
    public void testInitializeGridSize52() throws Exception {
        int gridSize = 52;
        setFieldAccessibility(boardGame, "gridSize", gridSize);
        Method initializeGridMethod = BoardGame.class.getDeclaredMethod("initializeGrid");
        initializeGridMethod.setAccessible(true);
        initializeGridMethod.invoke(boardGame);

        // test grid initialization
        String[][] grid = getFieldAccessibility(boardGame, "grid");
        assertEquals(gridSize, grid.length);
        assertEquals(gridSize, grid[0].length);
        for (String[] row : grid) {
            for (String cell : row) {
                assertEquals("__", cell);
            }
        }

        // test grid column header initialization
        String[] colHeader = getFieldAccessibility(boardGame, "colHeader");
        assertEquals(gridSize, colHeader.length);
        for (int i = 0; i < gridSize; i++) {
            assertEquals(String.format("%2d", i + 1), colHeader[i]);
        }

        Method rowLabelSpacerMethod = BoardGame.class.getDeclaredMethod("rowLabelSpacer", int.class, int.class);
        rowLabelSpacerMethod.setAccessible(true);

        // test grid row label initialization
        String[] rowLabel = getFieldAccessibility(boardGame, "rowLabel");
        assertEquals(gridSize, rowLabel.length);
        for (int i = 0; i < gridSize; i++) {
            assertEquals(CommonUtil.convertIntToLetter(i+1)+rowLabelSpacerMethod.invoke(boardGame, gridSize, i), rowLabel[i]);
        }
    }
}
