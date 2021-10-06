public class DoublyLinkedList {

	class Node{
		Node prev;
		int data;
		Node next;
		
		public Node(int data) {
			this.data = data;
		}
	}
	
	//attributes
	Node head = null;
	Node tail = null;
	int size = 0;
	
	
	//method to add node to the beginning
	public void addToBeginning(int data) {
		Node newNode = new Node(data);
		if(head == null) {
			head = newNode;
			tail = newNode;
			head.prev = null;
			tail.prev = null;
		}else {
			head.prev = newNode;
			newNode = head;
			newNode.prev = null;
			head = newNode;
		}
	}
	
	//method to add node to the end
	public void addToEnd(int data) {
		Node newNode = new Node(data);
		
		if(head == null) {
			head = newNode;
			tail = newNode;
			head.prev = null;
			tail.prev = null;
		}else {
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
			newNode.next = null;
		}
	}
	
	//method to add node to specific position
	public void addNodeToSpecificPosition(int position,int data) {
		Node newNode = new Node(data);
		if(head == null) {
			System.out.println("list is empty");
		}else {
			if(position == size+1) {
				 tail.next = newNode; 
				 newNode.prev = tail;  
				 tail = newNode;  
				 tail.next = null;  
			}else if(position == 1) {
				addToBeginning(data);
			}else {
				Node current = head;  
                Node temp = null; 
				
				for(int i = 1; i<position-1;i++) {
					current = current.next;
				}
				
				temp = current.next;
				temp.prev = current;
				
				current.next = newNode;
				newNode.prev = current;
				newNode.next = temp;
				temp.prev = newNode;
				
				
			}
			size++;
			
		}
				
	}
	
	
	//method to delete node to specific position
	public void deleteNodeToSpecificPosition(int position) {
	
		if(head == null || position > size) {
			System.out.println("position unavailable");
		}else if(position == 1) {
			head = head.next;
			size--;
		}else if(position ==size) {
			tail = tail.prev;
			size--;
		}else {
			Node current = head;
			for(int i =0;i<position;i++){
				current = current.next;
			}
			
			current.next.prev = current.prev;
			current.prev.next = current.next;
		}
			
			System.out.println("node is deleted from the DL");
	}
	
	//method to display the nodes
	public void print() {
		Node current = head;
		if(head==null) {
			System.out.println("the list is empty");
			return;
		}else {
			while(current != null) {
				System.out.print(current.data+" ");
				current = current.next;
			}
			
		}
	}
	
public static void main(String[] args) {
    DoublyLinkedList list = new DoublyLinkedList();
  
    for (int i = 0; i < 10; i++) {
        list.addToEnd(i);
    }
list.print();
      
list.addNodeToSpecificPosition(0, 56);
System.out.println();
list.print();
System.out.println();
list.addToBeginning(34);
System.out.println();
list.print();
list.addToEnd(89);
System.out.println();
list.print();
list.addToBeginning(45);


}	
	 
	
	
	
	
	
	
}
