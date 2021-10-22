
import java.util.Scanner;
// BSIT-2A Cedrick James Felicitas
class Stack {
    private int DEFAULT_CAPACITY = 10;

    private int theSize;
    private char[] theItems;
    int top = -1;

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

    public void trimToSize() {
        ensureCapacity(size());
    }

    public Character get(int idx) {
        if (idx < 0 || idx >= size())
            throw new ArrayIndexOutOfBoundsException();
        return theItems[idx];
    }

    public char[] getArray() {
        return theItems;
    }

    public Character set(int idx, Character newVal) {
        if (idx < 0 || idx >= size())
            throw new ArrayIndexOutOfBoundsException();
        Character old = theItems[idx];
        theItems[idx] = newVal;
        return old;
    }

    public void ensureCapacity(int newCapacity) {
        if (newCapacity < theSize)
            return;
        char[] old = theItems;
        theItems = new char[newCapacity];
        for (int i = 0; i < size(); i++)
            theItems[i] = old[i];
    }

    public boolean add(Character x) {
        add(size(), x);
        return true;
    }

    public void add(int idx, Character x) {

        if (theItems.length == size())
            ensureCapacity(size() * 2 + 1);
        for (int i = theSize; i > idx; i--)
            theItems[i] = theItems[i - 1];
        theItems[idx] = x;
        theSize++;
    }

    public Character remove(int idx) {
        Character removedItem = theItems[idx];
        for (int i = idx; i < size() - 1; i++)
            theItems[i] = theItems[i + 1];
        theSize--;
        return removedItem;
    }

    static int precedence(char x) {
        if (x == '+' || x == '-')
            return 1;
        if (x == '*' || x == '/' || x == '%')
            return 2;
        return 0;
    }

    void push(Character data) {

        theItems[++top] = data;

    }

    char pop() {
        return theItems[top--];
    }

    boolean isEmpty() {
        return (top == -1) ? true : false;
    }

    char peek() {
        return theItems[top];
    }

}

public class MyArraylist_Stack {
    static Stack stack = new Stack();

    public static void main(String args[]) {
        Scanner kb = new Scanner(System.in);
        boolean balance = false;
        int option;
        System.out.println();
        do {
            System.out.println("[1] parenthesis balancing\n[2] infix to postfix conversion\n[3] Exit");
            System.out.print("> ");
            option = kb.nextInt();

            switch (option) {
            case 1:

                System.out.println("\nParenthesis balancing");

                System.out.print("> ");
                String data = kb.next();
                System.out.println("balance = " + balance(data));
                stack.clear();
                System.out.println();

                break;

            case 2:
                System.out.println();
                System.out.println("Infix to postfix conversion");
                System.out.print("infix >");
                String infix = kb.next();
                System.out.println("postfix > " + toPostfix(infix));
                System.out.println();
                break;
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

        {
            symbol = infix.charAt(i);

            if (Character.isLetter(symbol) || Character.isDigit(symbol))
                postfix = postfix + symbol;
            else if (symbol == '(')

            {
                stack.push(symbol);
            } else if (symbol == ')')

            {
                while (stack.peek() != '(') {
                    postfix = postfix + stack.pop();
                }
                stack.pop();
            } else

            {
                while (!stack.isEmpty() && !(stack.peek() == '(')
                        && stack.precedence(symbol) <= stack.precedence(stack.peek()))
                    postfix = postfix + stack.pop();
                stack.push(symbol);
            }
        }
        while (!stack.isEmpty())
            postfix = postfix + stack.pop();
        return postfix;
    }

    static boolean balance(String data) {
        for (int i = 0; i < data.length(); i++) {

            if (data.charAt(i) == '(') {
                stack.push(data.charAt(i));
            }
            if (data.charAt(i) == ')') {
                stack.pop();
            }

        }

        if (stack.top == -1) {
            return true;
        }

        else {
            return false;
        }

    }
}
