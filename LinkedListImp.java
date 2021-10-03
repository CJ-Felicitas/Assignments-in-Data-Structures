public class LinkedListImp {
Node head = null;
Node tail = null;


// the node itself
class Node{
    // data itself
    int data;

      Node next; // next node
      Node prev; // prev node

    // constructor
    Node(int data_in){
this.data = data_in;
    }
}


void addNode(int data){
   
  // create a new node
  Node newNode = new Node(data);

  if (head==null) {
head = newNode;
tail = newNode;

head.prev = null;
tail.next = null;
}

else{
tail.next = newNode;
newNode.prev = tail;
tail = newNode;
tail.next = null;
}

}



void printshit(){
  sortList();

  Node node = head;
  if (head==null) {
    System.out.println("This list is empty");
    return;
  }

  while (node!=null) {
    System.out.print(node.data + " ");
    node = node.next;
  }

}

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

  LinkedListImp list = new LinkedListImp();
  

  for (int i = 10; i > 0; i--) {
    list.addNode(i);
  }


  list.printshit();



}

}
