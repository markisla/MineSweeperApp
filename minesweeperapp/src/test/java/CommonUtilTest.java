import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.minesweeperapp.util.CommonUtil;

public class CommonUtilTest {

    @Test
    public void testConvertIntToLetter() {
        assertEquals("A", CommonUtil.convertIntToLetter(1));
        assertEquals("Z", CommonUtil.convertIntToLetter(26));
        assertEquals("AA", CommonUtil.convertIntToLetter(27));
        assertEquals("AZ", CommonUtil.convertIntToLetter(52));
        assertEquals("BA", CommonUtil.convertIntToLetter(53));
        assertEquals("", CommonUtil.convertIntToLetter(-100));
    }

    @Test
    public void testConvertLetterToInt() {
        assertEquals(1, CommonUtil.convertLetterToInt("A"));
        assertEquals(26, CommonUtil.convertLetterToInt("Z"));
        assertEquals(27, CommonUtil.convertLetterToInt("AA"));
        assertEquals(52, CommonUtil.convertLetterToInt("AZ"));
        assertEquals(53, CommonUtil.convertLetterToInt("BA"));
    }

    @Test
    public void testSplitSquare() {
        assertEquals("A-1", CommonUtil.splitSquare("A1"));
        assertEquals("Z-26", CommonUtil.splitSquare("Z26"));
        assertEquals("AA-27", CommonUtil.splitSquare("AA27"));
        assertEquals("AZ-52", CommonUtil.splitSquare("AZ52"));
        assertEquals("BA-53", CommonUtil.splitSquare("BA53"));
        assertEquals("", CommonUtil.splitSquare("abc123"));
    }
}
