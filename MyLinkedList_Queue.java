import java.util.Scanner;

/**
 * Data Structures modified by Cedrick James Felicitas BSIT-2A
 */
public class MyLinkedList_Queue<AnyType> implements Iterable<AnyType> {

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

    public MyLinkedList_Queue() {
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

        if (idx < size() / 2) {
            p = beginMarker.next;
            for (int i = 0; i < idx; i++)
                p = p.next;
        }

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
            MyLinkedList_Queue.this.remove(current.prev);
            expectedModCount++;
            okToRemove = false;
        }
    }

    /**
     * a method that will automatically sort the data of eacch nodes and arrange
     * them in ascending order
     */
    void sorter() {

        AnyType temporary_sub;
        // if the head is null then return a statement that the list is empty
        if (beginMarker == null) {
            System.out.println("List is empty");
            return;
        } else

        {

            // performs 2 loops that will examine the data of which one is bigger and which
            // one is smaller

            for (int i = 0; i < size(); i++) {

                for (int j = i + 1; j < size(); j++) {
                    /**
                     * variable "i" will be compared to variable "j" on which one has a bigger value
                     * so variable "i" starts at index 0 and variable "j" starts at index 1 if ever
                     * that index 0 is bigger than the index 1 then they will swap values
                     * 
                     * performs a typecast so that the "if condition" can compare the data as
                     * integers instead of "Anytype"
                     * 
                     */
                    if ((Integer) getNode(i).data > (Integer) getNode(j).data) {

                        // temp sub of index i because in the next line the index i will change value
                        temporary_sub = getNode(i).data;

                        // replace the value of index i with a value of index j
                        set(i, getNode(j).data);

                        // replace the value of index j with a former value of index i
                        // it just basically performs a swap
                        set(j, temporary_sub);

                        // and so the loop will begin up until to the last index

                    }
                }

            }

        }

    }

    void printlist() {
        // sorts the list first
        
        // prints the list
        for (int i = 0; i < size(); i++) {
            System.out.print(get(i) + " ");
        }
    }


// for queue 

void enqueue(AnyType data){
add(data);

}

void dequeue(){
    
    remove(0);

    for (int i = 0; i < size(); i++) {

        for (int j = i + 1; j < size(); j++) {
             

        }
    }
}


    private int theSize;
    private int modCount;
    private Node<AnyType> beginMarker; // i guess this one is head
    private Node<AnyType> endMarker; // and this one is tail

    public static void main(String args[]) {

        // scanner for data input from the user.
        Scanner kb = new Scanner(System.in);
        MyLinkedList_Queue list = new MyLinkedList_Queue<>();
        int option;

        System.out.println();
        System.out.println();
        System.out.println();

        // initial data that will be inserted just for the show list
        // purposes only
        // the order of adding the data is just like this
        // 10 9 8 7 6 5 4 3 2 1 0
        // descending order

 
        System.out.println("\n");

        // do while loop for the menu driven that will terminate the code once the user
        // chooses option 6
        do {
            System.out.print("1. Enqueue\n2. Dequeue\n3. Show list\n4. Exit\n> ");

            option = kb.nextInt();

            switch (option) {

             case 1: System.out.println("\nEnQueue");
             System.out.print("data > ");       
             int data = kb.nextInt();
             list.enqueue(data);
             System.out.println();
             break;
             case 2: System.out.println("\nDeQueue");
           
             list.dequeue();
             System.out.println();
             break;
             case 3: System.out.println("\nShowList");
                list.printlist();
                System.out.println();
                System.out.println();
break;
                default:
break;
            /* case 6: // test case
                    // this case is just for testing the indexes of the list
             for (int i = 0; i < list.theSize; i++) {
                 System.out.println("index "+i+" is equals to "+list.get(i));
             }

                    break;*/
            }

        } while (option != 4);
        // end of code

    }
}