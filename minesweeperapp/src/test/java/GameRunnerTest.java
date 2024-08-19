import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.minesweeperapp.runner.GameRunner;

public class GameRunnerTest {
    private GameRunner gameRunner;
    private ByteArrayInputStream testIn;
    private InputStream originalIn;

    @Before
    public void setUp() {
        gameRunner = new GameRunner();
        originalIn = System.in;
    }

    @After
    public void restoreSystemIn() {
        System.setIn(originalIn);
    }

    // test private method initializeVars
    @Test
    public void testInitializeVars() throws Exception {
        // make method accessible for testing purposes
        Method initializeVarsMethod = GameRunner.class.getDeclaredMethod("initializeVars");
        initializeVarsMethod.setAccessible(true);
        initializeVarsMethod.invoke(gameRunner);

        // make field accessible for testing purposes
        Field isGameOverField = GameRunner.class.getDeclaredField("isGameOver");
        isGameOverField.setAccessible(true);
        assertFalse((boolean) isGameOverField.get(gameRunner));

        // make field accessible for testing purposes
        Field startAgainField = GameRunner.class.getDeclaredField("startAgain");
        startAgainField.setAccessible(true);
        assertFalse((boolean) startAgainField.get(gameRunner));

        // make field accessible for testing purposes
        Field exceptionEncounteredField = GameRunner.class.getDeclaredField("exceptionEncountered");
        exceptionEncounteredField.setAccessible(true);
        assertFalse((boolean) exceptionEncounteredField.get(gameRunner));

        // make field accessible for testing purposes
        Field gridSizeField = GameRunner.class.getDeclaredField("gridSize");
        gridSizeField.setAccessible(true);
        assertEquals(0, (int) gridSizeField.get(gameRunner));

        // make field accessible for testing purposes
        Field mineNumField = GameRunner.class.getDeclaredField("mineNum");
        mineNumField.setAccessible(true);
        assertEquals(0, (int) mineNumField.get(gameRunner));

        // make field accessible for testing purposes
        Field counterField = GameRunner.class.getDeclaredField("counter");
        counterField.setAccessible(true);
        assertEquals(0, (int) counterField.get(gameRunner));

        // make field accessible for testing purposes
        Field rowField = GameRunner.class.getDeclaredField("row");
        rowField.setAccessible(true);
        assertEquals(0, (int) rowField.get(gameRunner));

        // make field accessible for testing purposes
        Field colField = GameRunner.class.getDeclaredField("col");
        colField.setAccessible(true);
        assertEquals(0, colField.get(gameRunner));

        // make field accessible for testing purposes
        Field moveField = GameRunner.class.getDeclaredField("move");
        moveField.setAccessible(true);
        assertEquals("", (String) moveField.get(gameRunner));

    }

    // test private method printHeader
    @Test
    public void testPrintHeader() throws Exception {
        // make method accessible for testing purposes
        Method printHeaderMethod = GameRunner.class.getDeclaredMethod("printHeader");
        printHeaderMethod.setAccessible(true);

        // doesn't assert anything, but it checks if the method runs without errors
        printHeaderMethod.invoke(gameRunner);
    }

    // test private method isPreferenceValid
    @Test
    public void testIsPreferenceValid() throws Exception {
        // make field accessible for testing purposes
        Field gridSizeField = GameRunner.class.getDeclaredField("gridSize");
        gridSizeField.setAccessible(true);
        Field mineNumField = GameRunner.class.getDeclaredField("mineNum");
        mineNumField.setAccessible(true);

        // make method accessible for testing purposes
        Method isPreferenceValidMethod = GameRunner.class.getDeclaredMethod("isPreferenceValid");
        isPreferenceValidMethod.setAccessible(true);
        
        gridSizeField.set(gameRunner, 0);
        mineNumField.set(gameRunner, 0);
        assertFalse((boolean) isPreferenceValidMethod.invoke(gameRunner));

        gridSizeField.set(gameRunner, 10);
        mineNumField.set(gameRunner, 40);
        assertFalse((boolean) isPreferenceValidMethod.invoke(gameRunner));

        gridSizeField.set(gameRunner, 11);
        mineNumField.set(gameRunner, 10);
        assertTrue((boolean) isPreferenceValidMethod.invoke(gameRunner));

        gridSizeField.set(gameRunner, 5);
        mineNumField.set(gameRunner, 8);
        assertTrue((boolean) isPreferenceValidMethod.invoke(gameRunner));

    }

    // test private method initializeGame
    @Test
    public void testInitializeGame() throws Exception {
        // make field accessible for testing purposes
        Field gridSizeField = GameRunner.class.getDeclaredField("gridSize");
        gridSizeField.setAccessible(true);
        gridSizeField.set(gameRunner, 4);

        // make field accessible for testing purposes
        Field mineNumField = GameRunner.class.getDeclaredField("mineNum");
        mineNumField.setAccessible(true);
        mineNumField.set(gameRunner, 3);

        // make method accessible for testing purposes
        Method initializeGameMethod = GameRunner.class.getDeclaredMethod("initializeGame");
        initializeGameMethod.setAccessible(true);
        initializeGameMethod.invoke(gameRunner);

        // make field accessible for testing purposes
        Field msField = GameRunner.class.getDeclaredField("ms");
        msField.setAccessible(true);

        assertNotNull(msField.get(gameRunner));
    }

    // test private method printGame
    @Test
    public void testPrintGame() throws Exception {
        // make field accessible for testing purposes
        Field gridSizeField = GameRunner.class.getDeclaredField("gridSize");
        gridSizeField.setAccessible(true);
        gridSizeField.set(gameRunner, 4);

        // make field accessible for testing purposes
        Field mineNumField = GameRunner.class.getDeclaredField("mineNum");
        mineNumField.setAccessible(true);
        mineNumField.set(gameRunner, 3);

        // make method accessible for testing purposes
        Method initializeGameMethod = GameRunner.class.getDeclaredMethod("initializeGame");
        initializeGameMethod.setAccessible(true);
        initializeGameMethod.invoke(gameRunner);

        // make method accessible for testing purposes
        Method printGameMethod = GameRunner.class.getDeclaredMethod("printGame");
        printGameMethod.setAccessible(true);

        // doesn't assert anything, but it checks if the method runs without errors
        printGameMethod.invoke(gameRunner);
    }

    // test private method isInputValid
    @Test
    public void testIsInputValid() throws Exception {
        // make field accessible for testing purposes
        Field moveField = GameRunner.class.getDeclaredField("move");
        moveField.setAccessible(true);
        Field gridSizeField = GameRunner.class.getDeclaredField("gridSize");
        gridSizeField.setAccessible(true);

        // make method accessible for testing purposes
        Method isInputValidMethod = GameRunner.class.getDeclaredMethod("isInputValid");
        isInputValidMethod.setAccessible(true);

        testIn = new ByteArrayInputStream("A1".getBytes());
        System.setIn(testIn);
        moveField.set(gameRunner, "A1");
        gridSizeField.set(gameRunner, 5);
        assertTrue((boolean) isInputValidMethod.invoke(gameRunner));

        testIn = new ByteArrayInputStream("A0".getBytes());
        System.setIn(testIn);
        moveField.set(gameRunner, "A0");
        assertFalse((boolean) isInputValidMethod.invoke(gameRunner));

        testIn = new ByteArrayInputStream("F5".getBytes());
        System.setIn(testIn);
        moveField.set(gameRunner, "F5");
        assertFalse((boolean) isInputValidMethod.invoke(gameRunner));

        testIn = new ByteArrayInputStream("A6".getBytes());
        System.setIn(testIn);
        moveField.set(gameRunner, "F6");
        assertFalse((boolean) isInputValidMethod.invoke(gameRunner));

        testIn = new ByteArrayInputStream("invalid".getBytes());
        System.setIn(testIn);
        moveField.set(gameRunner, "invalid");
        assertFalse((boolean) isInputValidMethod.invoke(gameRunner));
    }



}
