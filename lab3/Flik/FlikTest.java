import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class FlikTest {
    private boolean l;

    @Test
    public void testisSameNumber(){
        l = Flik.isSameNumber(1, 2);
        assertEquals(false, l);
        l = Flik.isSameNumber(2, 2);
        assertEquals(true, l);

        /*
        The parameters for isSameNumber are Integer objects, which returns the same
        instance of the Integer class up until 127. If we want isSameNumber to work
        for the value of the int, then the parameters should be of int type.
         */
        l = Flik.isSameNumber(128, 128);
        assertEquals(true, l);
    }
}
