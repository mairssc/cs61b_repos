package es.datastructur.synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    private class ArrayRingIterator implements Iterator<T> {
        private int wizPos;
        private boolean firstFull;
        ArrayRingIterator() {
            wizPos = first;
            if (first == last) {
                firstFull = true;
            } else {
                firstFull = false;
            }
        }

        public boolean hasNext() {
            if (firstFull) {
                firstFull = false;
                return true;
            }
            return wizPos != last;
        }

        public T next() {
            T returnItem = rb[wizPos];
            wizPos = incrItem(wizPos);
            return returnItem;
        }
    }


    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    /**
     * Increments given int i
     */
    private int incrItem(int i) {
        if (i == rb.length - 1) {
            i = 0;
        } else {
            i++;
        }
        return i;
    }

    /**
     * Return size of buffer
     */
    @Override
    public int capacity() {
        return rb.length;
    }

    /**
     * Return number of items currently in the buffer
     */
    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (this.isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last = incrItem(last);
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (this.isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T temp = rb[first];
        rb[first] = null;
        first = incrItem(first);
        fillCount -= 1;
        return temp;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (this.isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    /**
     * Makes Iterator for ArrayRingBuffer
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayRingIterator();
    }


    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        ArrayRingBuffer<T> other = (ArrayRingBuffer<T>) o;
        if (this.fillCount != other.fillCount) {
            return false;
        }
        Iterator<T> thisIter = this.iterator();
        Iterator<T> oIter = other.iterator();
        while (thisIter.hasNext() && oIter.hasNext()) {
            if (thisIter.next() != oIter.next()) {
                return false;
            }
        }
        return true;
    }
}

