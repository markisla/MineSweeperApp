import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.minesweeperapp.util.MathUtil;

public class MathUtilTest {

    @Test
    public void testCheckPower() {
        int num, expectedPower, actualPower;

        // case 1: num = 0
        num = 0;
        expectedPower = 0;
        actualPower = MathUtil.checkPower(num);
        assertEquals(expectedPower, actualPower);

        // case 2: num = 10
        num = 10;
        expectedPower = 1;
        actualPower = MathUtil.checkPower(num);
        assertEquals(expectedPower, actualPower);

        // case 3: num = 11
        num = 11;
        expectedPower = 1;
        actualPower = MathUtil.checkPower(num);
        assertEquals(expectedPower, actualPower);

        // case 4: num = 52
        num = 52;
        expectedPower = 1;
        actualPower = MathUtil.checkPower(num);
        assertEquals(expectedPower, actualPower);

        // case 5: num = 100
        num = 100;
        expectedPower = 2;
        actualPower = MathUtil.checkPower(num);
        assertEquals(expectedPower, actualPower);

        // case 6: num = 999
        num = 999;
        expectedPower = 2;
        actualPower = MathUtil.checkPower(num);
        assertEquals(expectedPower, actualPower);

        // case 7: num = 1000
        num = 1000;
        expectedPower = 3;
        actualPower = MathUtil.checkPower(num);
        assertEquals(expectedPower, actualPower);

        // case 8: num = -10
        num = -10;
        expectedPower = 0;
        actualPower = MathUtil.checkPower(num);
        assertEquals(expectedPower, actualPower);
    }
    
}
