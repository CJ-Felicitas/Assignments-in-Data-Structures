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


void sorter(){
  Node temporary = head;
int temp;

  if (head==null) {
  System.out.println("The list is empty");
  return;
}
while (temporary!=null) {
while (temporary.data>temporary.next.data) {
  temp = temporary.data;
  temporary.data = temporary.next.data;
  temporary.next.data = temp;
}

}


}



void printshit(){

  sorter();

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





public static void main(String[] args) {

  LinkedListImp list = new LinkedListImp();
  

  for (int i = 10; i > 0; i--) {
    list.addNode(i);
  }


  list.printshit();



}

}
