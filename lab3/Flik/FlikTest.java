import static org.junit.Assert.*;
import org.junit.Test;


public class FlikTest {

    @Test
    public void testIsSameNumber() {
        int a = 10;
        int b = 10;
        int c = 0;
        assertTrue(Flik.isSameNumber(a, b));
        assertFalse(Flik.isSameNumber(a, c));

        int i = 129;
        int j = 129;
        assertFalse(Flik.isSameNumber(i, j));
    }

}
