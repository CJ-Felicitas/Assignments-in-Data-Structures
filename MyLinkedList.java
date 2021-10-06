import java.util.Scanner;



/**
 *
 * @author Vera Kim Tequin
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType> {

    private static class Node<AnyType> {

        // first param accepts the data
        // second paramm is for previous node
        // third param is for next node
        public Node(AnyType d, Node<AnyType> p, Node<AnyType> n) {
            data = d;
            prev = p;
            next = n;
        }

        public AnyType data;
        public Node<AnyType> prev;
        public Node<AnyType> next;
    }

    public MyLinkedList() {
        doClear();
    }

    public void clear() {
        doClear();
    }

    private void doClear() {

        beginMarker = new Node<AnyType>(null, null, null);
        endMarker = new Node<AnyType>(null, beginMarker, null);
        beginMarker.next = endMarker;

        theSize = 0;
        modCount++;
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean add(AnyType x) {
        add(size(), x);
        return true;
    }

    public void add(int idx, AnyType x) {
        addBefore(getNode(idx, 0, size()), x);
    }

    private void addBefore(Node<AnyType> p, AnyType x) {
        Node<AnyType> newNode = new Node<>(x, p.prev, p);
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
        modCount++;
    }

    public AnyType get(int idx) {
        return getNode(idx).data;
    }

    public AnyType set(int idx, AnyType newVal) {
        Node<AnyType> p = getNode(idx);
        AnyType oldVal = p.data;
        p.data = newVal;
        return oldVal;
    }

    public AnyType remove(int idx) {
        return remove(getNode(idx));
    }

    private AnyType remove(Node<AnyType> p) {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;
        return p.data;
    }

    private Node<AnyType> getNode(int idx) {
        return getNode(idx, 0, size() - 1);
    }

    private Node<AnyType> getNode(int idx, int lower, int upper) {
        Node<AnyType> p;

        if (idx < lower || idx > upper)
            throw new IndexOutOfBoundsException();
        /*
         * search and get starts at middle of the list if the size is greater than the
         * specified index then search from head to middle
         * 
         * (code)--> (stop) 1 2 3 4 / 5 6 7 8
         */
        if (idx < size() / 2) {
            p = beginMarker.next;
            for (int i = 0; i < idx; i++)
                p = p.next;
        }

        /*
         * 
         * search and get starts at the middle of the list if the specified index is
         * greater than the half size of the list then search at the end of the list
         * 
         * (stop) <---(code) 1 2 3 4 / 5 6 7 8
         * 
         * 
         */
        else {
            p = endMarker;
            for (int i = size(); i > idx; i--)
                p = p.prev;
        }
        return p;
    }

    public java.util.Iterator<AnyType> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements java.util.Iterator<AnyType> {
        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        public boolean hasNext() {
            return current != endMarker;
        }

        public AnyType next() {
            if (modCount != expectedModCount)
                throw new java.util.ConcurrentModificationException();
            if (!hasNext())
                throw new java.util.NoSuchElementException();

            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        public void remove() {
            if (modCount != expectedModCount)
                throw new java.util.ConcurrentModificationException();
            if (!okToRemove)
                throw new IllegalStateException();

            // MyLinkedList.false.remove(current.prev);
            MyLinkedList.this.remove(current.prev);
            expectedModCount++;
            okToRemove = false;
        }
    }

    void sorter() {
     
        AnyType temp;

        if (beginMarker == null) {
            return;
        } else {

            // first loop
            for (int i = 0; i < size(); i++) {
                // second loop
                for (int j = i+1; j < size(); j++) {
                    if ((Integer) getNode(i).data > (Integer) getNode(j).data) {
                                 
         temp = getNode(i).data;
         set(i, getNode(j).data);
         set(j, temp);

                    }
                }

            }

        }

    }

    void printshit() {
AnyType temp;
     
sorter();
        Node node = beginMarker;
        if (beginMarker == null) {
            System.out.println("This list is empty");
            return;
        }   

        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }

    }

    private int theSize;
    private int modCount;
    private Node<AnyType> beginMarker; // ill prolly guess this is head
    private Node<AnyType> endMarker; // and this one is tail

    public static void main(String args[]) {

        MyLinkedList list = new MyLinkedList();

        // scanner for data input from the user.
        Scanner kb = new Scanner(System.in);
list.add(45);
list.add(44);
list.add(34);
list.add(23);



list.printshit();


}
}