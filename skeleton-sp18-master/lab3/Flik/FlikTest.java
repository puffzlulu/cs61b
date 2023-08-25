import static org.junit.Assert.*;

import org.junit.Test;

public class FlikTest {
    @Test
    public void testisSameNumber(){
        assertTrue(!Flik.isSameNumber(1,5));
        assertTrue(Flik.isSameNumber(128,128));
    }
}
