import java.util.Scanner;

import javax.swing.plaf.synth.SynthSplitPaneUI;

public class MyArrayList <AnyType> implements Iterable<AnyType>{
    
    private static final int DEFAULT_CAPACITY=10;
    
    private int theSize;
    private AnyType [] theItems;
    
    public MyArrayList() { doClear();}
    public void clear(){ doClear(); }
    
    private void doClear()
    {
        theSize=0;
        ensureCapacity (DEFAULT_CAPACITY);
    }
    
    public int size() {return theSize;}
    public boolean isEmpty() { return size()==0;}
    public void trimToSize(){ensureCapacity(size());}
    
    public AnyType get(int idx){
        if( idx < 0 || idx >= size())
            throw new ArrayIndexOutOfBoundsException();
        return theItems[idx];
    }
    
    public AnyType set (int idx, AnyType newVal){
        if( idx < 0 || idx >= size())
            throw new ArrayIndexOutOfBoundsException();
        AnyType old = theItems[idx];
        theItems[idx] = newVal;
        return old;
    }
    
    public void ensureCapacity (int newCapacity){
        if (newCapacity < theSize)
            return;
        AnyType [] old = theItems;
        theItems = (AnyType[]) new Object[newCapacity];
        for (int i=0; i<size(); i++) theItems[i]=old[i];
    }
    
    public boolean add(AnyType x){
        add(size(),x);
        return true;
    }

    public void show_list() {
        String s;
        s = "[";
        if (size() > 0)
            s += theItems[0];
        if (size() > 1)
        for (int i = 1; i <= size() - 1; i++) {
            s += ", " + theItems[i];
        }
        System.out.println(s+"]");
    }

        

    public void add (int idx, AnyType x){
      
        if(theItems.length == size()) ensureCapacity(size()*2+1);
        for (int i = theSize; i> idx; i--) theItems[i] = theItems [i-1];
        theItems[idx]=x;
        theSize++;
    }
    
    public AnyType remove(int idx){
        AnyType removedItem = theItems[idx];
        for (int i=idx; i<size()-1; i++) theItems[i]=theItems[i+1];
        theSize--;
        return removedItem;
    }
    
    public java.util.Iterator<AnyType> iterator () 
    { 
        return new ArrayListIterator();
    }
    
    private class ArrayListIterator implements java.util.Iterator<AnyType>
    {
        
        private int current =0;
              
        public boolean hasNext() {return current < size();}
        public AnyType next () { return theItems[current++]; }
        public void remove () {MyArrayList.this.remove(--current);}
        
    
    


      

    
    }
    



     public static void main(String args[]) {
         // scanner for data input from the user.
         Scanner kb = new Scanner(System.in);
         MyArrayList<Integer> list = new MyArrayList<Integer>();
 int option;
 char verify;
      System.out.println();
      System.out.println();
      System.out.println();
     
       // initial data that will be inserted to the array
         list.add(1);
         list.add(2);
         list.add(3);
         list.add(4);
         list.add(5);
         list.add(6);
         list.add(7);
         list.add(8);
         list.add(9);
         list.add(10);
         
 
         do{
             System.out.print("1. SHOW LIST \n2. ADD \n3. SEARCH \n4. REMOVE \n5. REPLACE \n6. EXIT \n> ");
 
             option = kb.nextInt();
             
     
         switch (option) {
 
             // displays the list of the array or the elements of the array
             case 1: System.out.println("\nSHOW LIST");
                 list.show_list();
                System.out.println();
                System.out.println();
 
                 break;
 
                 case 2: System.out.println("\nADD");
                 System.out.print("data > ");
               int data = kb.nextInt();
               System.out.print("at index > ");
               int index_insert = kb.nextInt();

                list.add(index_insert,data);
                System.out.println();
                System.out.println();
 break; 
 
 //performs a search and displays the data from the index
             case 3: System.out.println("\nSEARCH");
             System.out.print("data > ");
             int item_search = kb.nextInt();
             boolean found = false;

            // performs a loop to search if an item exists in the list.
            System.out.println();
            for (int i = 0; i < list.size(); i++) {
                if (item_search==list.get(i)) {
         // prints the location of item in the list if ever the item exists in the list
                    System.out.println(item_search+" found at index "+i);
                    found = true;
                }
            }
            if (found==false) {
                System.out.println("The item doesn't exists on the list");
            }
System.out.println("\n\n");
        
           

          
           
 break;
 
 // removes the data from the index of the array
 case 4: System.out.println("\nREMOVE");
          System.out.print("index > ");
          int index_remove = kb.nextInt();
          list.remove(index_remove);
 
          
          break;
 // replaces the value of the index of the array
 
          case 5: System.out.println("\nREPLACE");
      System.out.print("Replace at index > ");
      int index_replace = kb.nextInt();
      System.out.print("Value to be replaced > ");
       int data_replace = kb.nextInt();
       list.set(index_replace, data_replace);
 System.out.println("\n");
       break;
 
          default:
                 break;
         }
 
     } while (option != 6);
     
  
    } 
        
    }
    
    
    
    
    
    

        