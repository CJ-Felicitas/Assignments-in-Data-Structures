import java.io.*;
import java.util.Scanner;

class Stack<AnyType> implements Iterable<AnyType> {
    private int DEFAULT_CAPACITY = 10;
    // ive changed it to static because ive encountered some issues about the size
    // of array in the sort code
    private int theSize;
    private AnyType[] theItems;
    int top =-1;



    public Stack() {
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
/*
    public boolean isEmpty() {
        return size() == 0;
    }
*/
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
            Stack.this.remove(--current);
        }

    }



    static int prec(char x) {
        if (x == '+' || x == '-')
            return 1;
        if (x == '*' || x == '/' || x == '%')
            return 2;
        return 0;
    }


    char a[] = new char[100];
 

    void push(char c) {
        try {
            a[++top] = c;
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Stack full, no room to push, size=100");
            System.exit(0);
        }
    }

    char pop() {
        return a[top--];
    }

    boolean isEmpty() {
        return (top == -1) ? true : false;
    }

    char peek() {
        return a[top];
    }
}

public class MyArraylist_Stack {
    static Stack stack = new Stack();

    public static void main(String argv[]) throws IOException {
     Scanner kb = new Scanner(System.in);
        int option;
        do {
            System.out.println("[1] parenthesis balancing\n[2] infix to postfix conversion");
            option = kb.nextInt();

            switch (option) {
            case 1:
                boolean balance;
                System.out.println("Parenthesis balancing");
                System.out.println();
                System.out.print(">");
                String data = kb.next();

                for (int i = 0; i < data.length(); i++) {
                    if (data.charAt(i) == '(') {
                        stack.push(data.charAt(i));
                    }
                    if (data.charAt(i) == ')') {
                        stack.pop();
                    }

                }
                if (stack.size() == 0) {
                    balance = true;
                } else {
                    balance = false;
                }

                System.out.println("balance = " + balance);
                stack.clear();
                break;

            case 2:
                System.out.println("Infix to postfix conversion");
                System.out.print("infix >");
                String infix = kb.next();
                System.out.println("postfix > "+toPostfix(infix));
            default:
                break;
            }

        } while (option != 3);
  
  
  
  
  
  
  
  
    }

    private static String toPostfix(String infix)
    // converts an infix expression to postfix
    {
        char symbol;
        String postfix = "";
        for (int i = 0; i < infix.length(); ++i)
        // while there is input to be read
        {
            symbol = infix.charAt(i);
            // if it's an operand, add it to the string
            if (Character.isLetter(symbol))
                postfix = postfix + symbol;
            else if (symbol == '(')
            // push 
            {
                stack.push(symbol);
            } else if (symbol == ')')
            // push everything back to (
            {
                while (stack.peek() != '(') {
                    postfix = postfix + stack.pop();
                }
                stack.pop(); // remove '('
            } else
            // print operators occurring before it that have greater precedence
            {
                while (!stack.isEmpty() && !(stack.peek() == '(') && stack.prec(symbol) <= stack.prec(stack.peek()))
                    postfix = postfix + stack.pop();
                stack.push(symbol);
            }
        }
        while (!stack.isEmpty())
            postfix = postfix + stack.pop();
        return postfix;
    }

   
}
