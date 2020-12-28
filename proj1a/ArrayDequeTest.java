import static org.junit.Assert.*;
import org.junit.Test;

public class ArrayDequeTest {
    @Test
    public void testAddFirst() {
        ArrayDeque<Integer> a = new ArrayDeque<Integer>();
        for (int i = 1; i <= 10000; i++) {
            a.addFirst(i);
        }
        assertTrue(a.removeFirst() == 10000);
        assertTrue(a.get(0) == 9999);
    }

    @Test
    public void testAddLast() {
        ArrayDeque<Integer> a = new ArrayDeque<Integer>();
        for (int i = 1; i <= 1000000; i++) {
            a.addLast(i);
        }
        assertTrue(a.get(10000) == 10001);
    }

    @Test
    public void testIsEmpty() {
        ArrayDeque<Integer> a = new ArrayDeque<Integer>();
        assertTrue(a.isEmpty());
        a.addLast(1);
        assertTrue(!a.isEmpty());
    }

    @Test
    public void testSize() {
        ArrayDeque<Integer> a = new ArrayDeque<Integer>();
        assertTrue(a.size() == 0);
        for (int i = 0; i <= 130; i++) {
            a.addLast(i);
        }
        assertTrue(a.size() == 131);
    }

    @Test
    public void testPrintDeque() {
        ArrayDeque<Integer> a = new ArrayDeque<Integer>();
        for (int i = 0; i <= 1000; i++) {
            a.addLast(i);
        }
        a.printDeque();
    }

    @Test
    public void testRemoveFirst() {
        ArrayDeque<Integer> a = new ArrayDeque<Integer>();
        for (int i = 0; i <= 1000; i++) {
            if (i%2 == 0) {
                a.addFirst(i);
            } else {
                a.addLast(i);
            }
        }

        for (int i = 0; i <= 130; i++) {
            System.out.print(a.removeFirst());
            System.out.print(' ');
        }
        System.out.println();
        a.printDeque();
    }

    @Test
    public void testRemoveLast() {
        ArrayDeque<Integer> a = new ArrayDeque<Integer>();
        for (int i = 0; i <= 1000; i++) {
            a.addFirst(i);
        }
        for (int i = 0; i <= 100 ; i++) {
            assertTrue(a.removeLast() == i);
        }
        a.printDeque();
    }

    @Test
    public void testGet() {
        ArrayDeque<Integer> a = new ArrayDeque<Integer>();
        for (int i = 0; i <= 4; i++) {
            a.addFirst(i);
        }
        assertTrue(a.get(0) == 4);
        assertTrue(a.get(2) == 2);
        assertTrue(a.get(5) == null);
    }
}
