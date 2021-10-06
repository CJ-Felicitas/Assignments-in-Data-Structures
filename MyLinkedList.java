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

    /**
     * 
     * 
     * a method that automatically sorts the list
     * base on ascending order
     * 
     * 
     */
    void sorter() {
     
        AnyType temp;

        if (beginMarker == null) {
            return;
        } else {

            
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

    void printList() {

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

 
        // scanner for data input from the user.
        Scanner kb = new Scanner(System.in);
        MyLinkedList list = new MyLinkedList<>();
        int option;

        System.out.println();
        System.out.println();
        System.out.println();

        // initial data that will be inserted to the array just for the show list
        // purposes only
        // the order of adding the data is just like this
        // 10 9 9 7 6 5 4 3 2 1
        for (int i = 10; i > 0; i--) {
            list.add(i);
            System.out.print(" " + i);
        }

        System.out.println("\n");

        // do while loop for the menu driven that will terminate the code once the user
        // chooses option 6
        do {
            System.out.print("1. SHOW LIST \n2. ADD \n3. SEARCH \n4. REMOVE \n5. REPLACE \n6. EXIT \n> ");

            option = kb.nextInt();

            switch (option) {

                // displays the list of the array or the elements of the array
                case 1:
                    System.out.println("\nSHOW LIST");

                    System.out.println();
                    System.out.println();

            

                    list.printList();
                    System.out.println("\n");
                    break;

                case 2:
                    System.out.println("\nADD");
                    System.out.print("> ");
                    int data = kb.nextInt();

                    // adds a data from the list, since ive only used the method that has one param
                    // the data that will be added was supposed to sit at the end of the list
                    // and when we try to choose the option show list, the data that has been added
                    // will be sorted based on his proper position in the ascending order

                    list.add(data);

                    System.out.println();
                    System.out.println();
                    break;

                // performs a search and displays the data from the index
                case 3:
                    System.out.println("\nSEARCH");
                    System.out.print("index > ");
                    int index_search = kb.nextInt();
                    // returns the data of the specified index in the list
                    System.out.println("\n" + list.get(index_search));
                    System.out.println();
                    System.out.println();
                    break;

                // removes the data from the index of the array
                case 4:
                    System.out.println("\nREMOVE");
                    System.out.print("index > ");
                    int index_remove = kb.nextInt();
                    // removes a data of the specified index in the list
                    list.remove(index_remove);

                    break;

                case 5:
                    System.out.println("\nREPLACE");
                    System.out.print("Replace at index > ");
                    int index_replace = kb.nextInt();
                    System.out.print("Value to be replaced > ");
                    int data_replace = kb.nextInt();

                    // replaces the value of the specified index and replaces it with a specified
                    // data
                    list.set(index_replace, data_replace);
                    System.out.println("\n");
                    break;

                default:
                    break;
            }

        } while (option != 6);
        // end of code


}
}