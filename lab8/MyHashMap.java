import java.util.*;

public class MyHashMap<K, V> implements Map61B<K, V> {

    private int bucketSize;
    private double loadFact;
    private ArrayList<HashNode> bucket;
    private HashSet<K> KeySet;


    private int hash(K key, int size) {
        int h = key.hashCode();
        return Math.floorMod(h, size);
    }

    private void putArrayL(K key, V value, ArrayList<HashNode> l) {
        int hashKey = hash(key, l.size());

        if (l.get(hashKey) == null) {
            l.set(hashKey, new HashNode(key, value, null));
        } else {
            HashNode cur = l.get(hashKey);
            if (key.equals(cur.getKey())) {
                cur.changeValue(value);
                return;
            }
            while (cur.getNext() != null) {
                if (key.equals(cur.getKey())) {
                    cur.changeValue(value);
                    return;
                }
                cur = cur.getNext();
            }
            cur.changeNext(new HashNode(key, value, null));
        }
        KeySet.add(key);
    }

    private void resize() {
        ArrayList<HashNode> temp = new ArrayList<HashNode>(bucketSize * 2);
        for (int i = 0; i < bucketSize * 2; i++) {
            temp.add(null);
        }

        for (K key : KeySet) {
            V val = get(key);
            putArrayL(key, val, temp);
        }
        bucket = temp;
        bucketSize *= 2;
    }

    private class HashNode {
        private V value;
        private K key;
        private HashNode next;


        public HashNode(K k, V v, HashNode n) {
            value = v;
            key = k;
            next = n;
        }

        public V getValue() {
            return value;
        }

        public K getKey() {
            return key;
        }

        public HashNode getNext() {
            return next;
        }

        public void changeValue(V v) {
            value = v;
        }

        public void changeNext(HashNode n) {
            next = n;
        }
    }


    public MyHashMap() {
        this(16, 0.75);
    }
    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }
    public MyHashMap(int initialSize, double loadFactor) {
        bucketSize = initialSize;
        loadFact = loadFactor;
        bucket = new ArrayList<HashNode>(bucketSize);
        for (int i = 0; i < bucketSize; i ++) {
            bucket.add(null);
        }
        KeySet = new HashSet<K>();
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        KeySet.clear();
        bucket.clear();
        bucketSize = bucket.size();
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return KeySet.contains(key);
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (!KeySet.contains(key)) {
            return null;
        }

        HashNode curNode = bucket.get(hash(key, bucketSize));
        while (curNode != null) {
            if (curNode.getKey().equals(key)) {
                return curNode.getValue();
            }
            curNode = curNode.getNext();
        }
        return null;
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return KeySet.size();
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    @Override
    public void put(K key, V value) {
        if ((double) (size()+1)/bucketSize >= loadFact) {
            resize();
        }
        putArrayL(key, value, bucket);
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        return KeySet;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return KeySet.iterator();
    }
}
