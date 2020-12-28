public class ArrayDeque<Item> {
    private int size;
    private Item[] items;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (Item[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    /** Resizes items to len */
    private void resize(int len) {
        //Decides whether resize is an increase or decrease
        if (len > items.length) {
            increaseSize(len);
        } else {
            reduceSize(len);
        }
    }

    /** Increase items by len */
    private void increaseSize(int len) {
        int midPoint = (size - nextFirst);
        Item[] a = (Item[]) new Object[len];
        System.arraycopy(items, nextFirst + 1, a, 0, size - nextFirst);
        System.arraycopy(items, 0, a, midPoint, nextLast);
        nextFirst = a.length - 1;
        nextLast = size;
        items = a;
    }

    /** Reduces items by len */
    private void reduceSize(int len) {
        Item[] a = (Item[]) new Object[len];
        int start = nextFirst + 1;
        int stop = nextLast;
        System.arraycopy(items, nextFirst + 1, a, 0, items.length - start);
        System.arraycopy(items, 0, a, (items.length - start), stop);
        nextFirst = a.length - 1;
        nextLast = size;
        items = a;
    }

    /** Checks if 1 less than the size is at least 25% of items.length
     * If it the usage factor is less than 25%, then items.length
     * is resized to 4*size
     */
    private void checkSize() {
        if (items.length >= 16) {
            //usageFactor is percentage of size - 1 used
            double usageFactor = (size - 1) / (double) items.length;
            if (usageFactor < 0.25) {
                resize(size * 4);
            }
        }
    }

    /** Checks the nextFirst and nextLast to see if they are in items
     * If they are out of bounds, nextFirst is set to the end of items
     * nextLast is set to the front of items
     */
    private void checkFirstLast() {
        if (nextFirst == -1) {
            nextFirst = items.length - 1;
        }

        if (nextLast == items.length) {
            nextLast = 0;
        }
    }

    /** Adds item to the front of items */
    public void addFirst(Item item) {
        //Checks nextFirst, nextLast, and items.length
        checkFirstLast();
        checkSize();

        //Increase size if the items.length is full
        if (nextFirst == nextLast) {
            resize(size * 2);
        }

        items[nextFirst] = item;
        nextFirst -= 1;
        size += 1;
    }


    /** Adds the given item to the last index of the list.
     * This is done by changing the prev attribute of sentLast.
     */
    public void addLast(Item item) {
        //Checks nextFirst, nextLast, and items.length
        checkFirstLast();
        checkSize();

        //Increase size if the items.length is full
        if (nextFirst == nextLast) {
            resize(size * 2);
        }

        items[nextLast] = item;
        nextLast += 1;
        size += 1;
    }

    /** Returns true if size is 0 */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /** Returns size  */
    public int size() {
        return size;
    }

    /** Prints all of Deque */
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i));
            System.out.print(' ');
        }
        System.out.println();
    }

    /** Removes first item, if it exists.
     * Then, returns the value of that item
     */
    public Item removeFirst() {
        checkSize();

        //Returns null if size is 0
        if (size == 0) {
            return null;
        }

        //nextFirst changes to -1
        if (nextFirst == items.length - 1) {
            nextFirst = -1;
        }

        nextFirst += 1;
        Item temp = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;
        return temp;
    }

    /** Removes last item, if it exists.
     * Then, returns the value of that item
     */
    public Item removeLast() {
        checkSize();

        //Returns null if size is 0
        if (size == 0) {
            return null;
        }

        //nextLast changes to items.length
        if (nextLast == 0) {
            nextLast = items.length;
        }

        nextLast -= 1;
        Item temp = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        return temp;
    }

    /** Finds item at index and returns it */
    public Item get(int index) {
        if (index < 0 || index >= size) {
            //Return null for bad index
            return null;
        } else if (nextLast > nextFirst) {
            //Start from the prev nextFirst to index
            return items[nextFirst + 1 + index];
        } else if (nextFirst == items.length - 1) {
            //Give index
            return items[index];
        } else {
            //endTail is elements from end of items to first
            int endTail = (items.length - 1) - (nextFirst + 1);

            if (index <= endTail) {
                //Start from prev nextFirst to index
                return items[nextFirst + 1 + index];
            } else {
                //Items at the start of items, subtract endTail+1
                return items[index - (endTail + 1)];
            }
        }
    }
}
