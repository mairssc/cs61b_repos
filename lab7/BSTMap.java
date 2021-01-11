import edu.princeton.cs.algs4.BST;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private BSTNode rootNode;
    private int size;

    private class BSTNode {
        private V value;
        private K key;
        private BSTNode left;
        private BSTNode right;

        public BSTNode(V v, K k, BSTNode l, BSTNode r) {
            value = v;
            key = k;
            left = l;
            right = r;
        }

        public V value() {
            return value;
        }

        public K key() {
            return key;
        }

        public BSTNode left() {
            return left;
        }

        public BSTNode right() {
            return right;
        }

        public void assignLeft(BSTNode N) {
            left = N;
        }

        public void assignRight(BSTNode N) {
            right = N;
        }

        public void assignValue(V v) {
            value = v;
        }
    }

    private BSTNode find(K key, BSTNode N) {
        int compare = 0;
        while (N != null) {
            compare = key.compareTo(N.key());
            if (compare == 0) {
                return N;
            } else if (compare < 0) {
                N = N.left();
            } else {
                N = N.right();
            }
        }
        return null;
    }

    private BSTNode insert(K key, V value, BSTNode N) {
        if (N == null) {
            size += 1;
            return new BSTNode(value, key, null, null);
        }
        int compare = key.compareTo(N.key());

        if (compare < 0) {
            N.assignLeft(insert(key, value, N.left()));
        } else if (compare > 0) {
            N.assignRight(insert(key, value, N.right()));
        } else {
            N.assignValue(value);
        }
        return N;
    }

    private void printBST(BSTNode N) {
        if (N == null) {
            return;
        }
        System.out.println("key: " + N.key() + "  value: " + N.value);
        printBST(N.left());
        printBST(N.right());
    }


    public BSTMap() {
        rootNode = null;
        size = 0;
    }

    @Override
    public void clear() {
        rootNode = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (find(key, rootNode) == null) {
            return false;
        }
        return true;
    }

    @Override
    public V get(K key) {
        BSTNode n = find(key, rootNode);
        if (n == null) {
            return null;
        }
        return n.value();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        rootNode = insert(key, value, rootNode);
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    public void printInOrder() {
        printBST(rootNode);
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
