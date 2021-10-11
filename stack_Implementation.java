// this implementation of stack can only handle a fixed size array
public class stack_Implementation{

    private int top=-1;
    private int capacity;
    private int [] array;

    // constructor
    // define first param as array size 
    stack_Implementation(int size){
        this.capacity = size;
        this.array = new int [this.capacity];
    }

    public void push(int data) throws Exception{

        if (size()==capacity) {
            throw new Exception("Stack has reached the capacity size limit/Stack is full");
        }
     this.array[++top]=data;
}

    public int pop(){
        return array[top--];

    }
    public int size() {
        return (top + 1);
   }

    public String toString() {
        String s;
        s = "[";
        if (size() > 0)
            s += array[0];
        if (size() > 1)
        for (int i = 1; i <= size() - 1; i++) {
            s += ", " + array[i];
        }
        return s + "]";
    }

    public boolean isEmpty() {
        return (top < 0);
    }


}