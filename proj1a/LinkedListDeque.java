public class LinkedListDeque<Item> {

    public class ItemNode {

        private Item val;
        private ItemNode next;
        private ItemNode prev;


        public ItemNode(Item v, ItemNode n, ItemNode p) {
            val = v;
            next = n;
            prev = p;
        }

        /** Removes all references to the ItemNode */
        public void removeRef() {
            val = null;
            next = null;
            prev = null;
        }
    }

    private int size;
    private ItemNode sentFirst;
    private ItemNode sentLast;

    public LinkedListDeque() {
        size = 0;
        sentFirst = new ItemNode(null, null, null);
        sentLast = new ItemNode(null, null, null);
        sentFirst.next = sentLast;
        sentLast.prev = sentFirst;
    }


    /** Adds the given item to the first index of the list
     * This is done by changing the next attribute of sentFirst
     */
    public void addFirst(Item item) {
        ItemNode temp = sentFirst.next;
        sentFirst.next = new ItemNode(item, sentFirst.next, sentFirst);
        temp.prev = sentFirst.next;
        size += 1;
    }

    /** Adds the given item to the last index of the list.
     * This is done by changing the prev attribute of sentLast.
     */
    public void addLast(Item item) {
        //Sets temp to the last sentinel's prev attribute
        ItemNode temp = sentLast.prev;

        //The current last sentinel's prev attribute becomes a new ItemNode with Item as the val
        sentLast.prev = new ItemNode(item, sentLast, sentLast.prev);

        //The next attribute for the previous last ItemNode becomes the new ItemNode
        temp.next = sentLast.prev;
        size += 1;
    }

    /** Returns true if the size of the list is 0 */
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

    /** Prints the value all ItemNodes in LinkedListDeque from first to last,
     * with a space in between each value.
     */
    public void printDeque() {
        ItemNode tempNode = sentFirst;
        int tempSize = 0;

        //Prints each value for each ItemNode in the list
        while (tempSize != size) {
            tempNode = tempNode.next;
            System.out.print(tempNode.val);
            System.out.print(' ');
            tempSize += 1;
        }
        System.out.println();
    }

    /** Removes first ItemNode, if it exists. Then, returns the value of that node */
    public Item removeFirst() {
        //returns null if the size is 0
        if (size == 0) {
            return null;
        }

        //Stores current first node in temp
        ItemNode temp = sentFirst.next;
        Item tempVal = temp.val;

        //Changes first node to second node
        sentFirst.next = temp.next;
        temp.next.prev = sentFirst;

        //Removes old first node
        temp.removeRef();
        size -= 1;
        return tempVal;
    }

    /** Removes last ItemNode, if it exists. Then, returns the value of that node. */
    public Item removeLast() {
        //returns null if the size is 0
        if (size == 0) {
            return null;
        }

        //Stores current last node in temp
        ItemNode temp = sentLast.prev;
        Item tempVal = temp.val;

        //Changes last node to second-to-last node
        sentLast.prev = temp.prev;
        temp.prev.next = sentLast;

        //Removes old last node
        temp.removeRef();
        size -= 1;
        return temp.val;
    }

    /** Finds the ItemNode at index and returns it */
    public Item get(int index) {
        int tempSize = 0;
        ItemNode tempNode;

        //returns null if the index does not exist
        if (size == 0 || index >= size || index < 0) {
            return null;
        }

        //If index is <= half size of list, start from front
        if ((index + 1) <= (size / 2)) {
            //Starts from front and gets ItemNode at index
            tempSize = 0;
            tempNode = sentFirst.next;
            while (tempSize != index) {
                tempNode = tempNode.next;
                tempSize += 1;
            }
        } else {
            //Starts from back and gets ItemNode at index
            tempSize = size - 1;
            tempNode = sentLast.prev;
            while (tempSize != index) {
                tempNode = tempNode.prev;
                tempSize -= 1;
            }
        }
        return tempNode.val;
    }
}
