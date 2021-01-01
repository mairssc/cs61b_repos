package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<Integer>(10);
        assertEquals(arb.capacity(), 10);
        assertEquals(arb.isEmpty(), true);
        arb.enqueue(0);
        arb.enqueue(1);
        assertEquals(arb.fillCount(), 2);
        assertTrue(arb.peek() == 0);
        assertTrue(arb.dequeue() == 0);
        assertEquals(arb.capacity(), 10);
        assertTrue(arb.peek() == 1);
        assertTrue(arb.dequeue() == 1);
        for (int i = 0; i < 10; i++) {
            arb.enqueue(i);
        }
        assertTrue(arb.isFull());
        assertTrue(arb.fillCount() == 10);
        for (int i = 0; i < 10; i++) {
            arb.dequeue();
            arb.enqueue(i + 10);
        }
        for (int i : arb) {
            System.out.println(i);
        }
        ArrayRingBuffer<Integer> b = new ArrayRingBuffer<Integer>(10);
        ArrayRingBuffer<Integer> c = new ArrayRingBuffer<Integer>(10);

        for (int i = 0; i < 10; i++){
            b.enqueue(i);
            c.enqueue(i);
            assertTrue(b.equals(c));
        }
        b.dequeue();
        for (int i : b) {
            System.out.println(i);
        }
        assertFalse(b.equals(arb));
    }
}
