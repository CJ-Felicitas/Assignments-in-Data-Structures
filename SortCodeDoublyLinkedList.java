public class SortCodeDoublyLinkedList{
Node head = null;
Node tail = null;


private class Node{
    int data;
    Node next;
    Node prev;
    Node(int data_in){

        this.data = data_in;
    }
}





// this method is the code for the sort in the linked list

/**
 * its important to name the main node as head node, some will prefer to call it as begin marker but
 * in this one, I prefer to call head  
 */
public void sortList() {  
    Node current = null, index = null;  
    int temp;   
    //Check whether list is empty  
    if(head == null) {  
        return;  
    }  
    else {  
        //Current will point to head  
        for(current = head; current.next != null; current = current.next) {  
            //Index will point to node next to current  
            for(index = current.next; index != null; index = index.next) {  
                //If current's data is greater than index's data, swap the data of current and index  
                if(current.data > index.data) {  
                    temp = current.data;  
                    current.data = index.data;  
                    index.data = temp;  
                }  
            }  
        }  
    }  
  }  


public static void main(String[] args) {
    
}


}



