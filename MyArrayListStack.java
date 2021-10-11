
/*
Modified by Cedrick James B. Felicitas
Last Date Modified: September 30, 2021 



*/

public class MyArrayListStack<AnyType> implements Iterable<AnyType> {

    private static final int DEFAULT_CAPACITY = 10;
    // ive changed it to static because ive encountered some issues about the size
    // of array in the sort code
    private int theSize;
    private AnyType[] theItems;

    public MyArrayListStack() {
        doClear();
    }

    public void clear() {
        doClear();
    }

    private void doClear() {
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void trimToSize() {
        ensureCapacity(size());
    }

    public AnyType get(int idx) {
        if (idx < 0 || idx >= size())
            throw new ArrayIndexOutOfBoundsException();
        return theItems[idx];
    }

    public AnyType[] getArray() {
        return (AnyType[]) theItems;
    }

    public AnyType set(int idx, AnyType newVal) {
        if (idx < 0 || idx >= size())
            throw new ArrayIndexOutOfBoundsException();
        AnyType old = theItems[idx];
        theItems[idx] = newVal;
        return old;
    }

    public void ensureCapacity(int newCapacity) {
        if (newCapacity < theSize)
            return;
        AnyType[] old = theItems;
        theItems = (AnyType[]) new Object[newCapacity];
        for (int i = 0; i < size(); i++)
            theItems[i] = old[i];
    }

    public boolean add(AnyType x) {
        add(size(), x);
        return true;
    }

    public void add(int idx, AnyType x) {

        if (theItems.length == size())
            ensureCapacity(size() * 2 + 1);
        for (int i = theSize; i > idx; i--)
            theItems[i] = theItems[i - 1];
        theItems[idx] = x;
        theSize++;
    }

    public AnyType remove(int idx) {
        AnyType removedItem = theItems[idx];
        for (int i = idx; i < size() - 1; i++)
            theItems[i] = theItems[i + 1];
        theSize--;
        return removedItem;
    }
    // this is the method that ive added that just basically prints out all the
    // items in the list.

    public void show_list() {
        String s;
        s = "[";
        if (size() > 0)
            s += theItems[0];
        if (size() > 1)
            for (int i = 1; i <= size() - 1; i++) {
                s += ", " + theItems[i];
            }
        System.out.println(s + "]");
    }

    // stack methods

    public void push(AnyType data) {

        add(data);
    }

    public AnyType pop() {

        return theItems[theSize--];
    }

    // accepts a series of parenthesis that is contained in a string
    public boolean parenthesis_balancing(String data) {

        String group_of_parenthesis = data;

        // char array to break the string into pieces and contain them in an array
        Character[] array = new Character[group_of_parenthesis.length()];

        int size_count = 0; // if and only if the value is set into zero then it concludes that the series
                            // is balanced

        // a loop that will break the string into a char one by one and distribute into
        // the array
        for (int i = 0; i < group_of_parenthesis.length(); i++) {
            array[i] = group_of_parenthesis.charAt(i);
        }

        // loop that will count each index of the array
        for (int i = 0; i < array.length; i++) {
            if (array[i] == '(') {
                // increments everytime if it is a push
                size_count++;
            }
            // decrement everytime if it is a pop
            if (array[i] == ')') {
                size_count--;
            }

        }
        // zero indicates that the series is balanced
        if (size_count == 0) {
            return true;
        }
        // if size_count is not a zero then it will conclude that the series is
        // unbalanced.
        else {
            return false;
        }
    }

    // infix to postfix method
    public void infix_to_postfix_conversion(String data) {
        String infix = data;
        String postfix = "";

        Character[] array = new Character[infix.length()];

        for (int i = 0; i < infix.length(); i++) {
            array[i] = infix.charAt(i);
        }
      
        for (int i = 0; i < array.length; i++) {
                  if (array[(array[i]%2!=0)]) {
                     
                     postfix = postfix+array[0]+array[3];
                  }          
        }
           


    }

    public java.util.Iterator<AnyType> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements java.util.Iterator<AnyType> {

        private int current = 0;

        public boolean hasNext() {
            return current < size();
        }

        public AnyType next() {
            return theItems[current++];
        }

        public void remove() {
            MyArrayListStack.this.remove(--current);
        }

    }

    public static void main(String args[]) {

        MyArrayListStack list = new MyArrayListStack<>();

        String set = "(()))((())";

        System.out.println(list.parenthesis_balancing(set));

    }

}
