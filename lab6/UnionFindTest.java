import org.junit.Test;
import org.junit.Assert;


public class UnionFindTest {

    UnionFind u = new UnionFind(10);

    @Test
    public void TestUnionFind() {
        UnionFind x = new UnionFind(5);
    }

    @Test
    public void TestSizeOf() {
        UnionFind x = new UnionFind(10);
        x.connect(0, 1);
        x.connect(3, 0);
        x.connect(2, 3);
        x.connect(8, 9);
        x.connect(7, 8);
        x.connect(9, 0);
        x.connect(4, 4);
        x.connect(8, 7);
        x.connect(4, 5);
        x.connect(5, 6);
        Assert.assertEquals(x.sizeOf(0), 7);
        Assert.assertEquals(x.sizeOf(7), 7);
        Assert.assertEquals(x.sizeOf(4), 3);
        x.connect(4, 9);
        Assert.assertEquals(x.sizeOf(9), 10);
    }

    @Test
    public void TestParent() {

    }

    @Test
    public void TestIsConnected() {
        UnionFind x = new UnionFind(10);
        x.connect(0, 1);
        x.connect(3, 0);
        x.connect(2, 3);
        x.connect(8, 9);
        x.connect(7, 8);
        x.connect(9, 0);
        x.connect(4, 4);
        x.connect(8, 7);
        x.connect(4, 5);
        x.connect(5, 6);
        Assert.assertTrue(x.isConnected(0, 1));
        Assert.assertTrue(x.isConnected(0, 0));
        Assert.assertFalse(x.isConnected(0, 4));
    }

    @Test
    public void TestConnect() {
        u.connect(0, 1);
        u.connect(3, 0);
        u.connect(2, 3);
        u.connect(8, 9);
        u.connect(7, 8);
        u.connect(9, 0);
        u.connect(4, 4);
        u.connect(8, 7);
        u.connect(4, 5);
        u.connect(5, 6);
    }

    @Test
    public void TestFind() {
        UnionFind x = new UnionFind(10);
        x.connect(1, 0);
        x.connect(3, 2);
        x.connect(5, 4);
        x.connect(4, 2);
        x.connect(7, 6);
        x.connect(8, 6);
        x.connect(5, 7);
    }
}
