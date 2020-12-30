import edu.princeton.cs.introcs.StdRandom;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestRedBlackFloorSet {
    @Test
    public void randomizedTest() {
        AListFloorSet aSet = new AListFloorSet();
        RedBlackFloorSet rSet = new RedBlackFloorSet();
        for (int i = 0; i < 1000000; i++) {
            double r = StdRandom.uniform(-5000.0, 5000.0);
            aSet.add(r);
            rSet.add(r);
        }

        for (int i = 0; i < 100000; i++) {
            double r = StdRandom.uniform(-5000.0, 5000.0);
            rSet.floor(r);
            assertEquals(rSet.floor(r), aSet.floor(r), 0.000001);
        }
    }
}
