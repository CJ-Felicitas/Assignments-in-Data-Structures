// dear future me, incase you're wondering what's this program about
// here are the instructions for this performance task 6

/**
 * Modify the given binary tree program code
that will accept an arithmetic expression then display its equivalent in inorder, pre-order, and post-order
notations. Consider the use of parentheses in the infix notation.
Submit program code and video.
 * 
 * 
 * 
 * 
 */
import java.util.Scanner;
import java.util.Stack;

public class BinaryTree<AnyType extends Comparable<? super AnyType>>
{
    public static class BinaryNode<AnyType>
    {
    
        BinaryNode(AnyType theElement) { this(theElement, null, null);}
        BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode <AnyType> rt)
        { element = theElement; left=lt; right= rt; }
        
        AnyType element;
        BinaryNode<AnyType> left;
        BinaryNode<AnyType> right;
    }
    
    private BinaryNode<AnyType> root;
    
    public BinaryTree(){ root= null; }

    
    public void makeEmpty() {root = null;}
    
    public boolean isEmpty() {return root==null;}
    
    public boolean contains(AnyType x) { return contains(x, root);}
    
    public AnyType findMin() throws UnderflowException {
        if (isEmpty()) throw new UnderflowException();
        return findMin(root).element;
    }
    
    public AnyType findMax() throws UnderflowException{
        if (isEmpty()) throw new UnderflowException();
        return findMax(root).element;
    }
    public BinaryNode<AnyType> insert(AnyType x){root = insert(x, root); return root;}
    
    public void remove(AnyType x){root = remove(x,root);}
    
    public void printTree(){
        if (isEmpty())
            System.out.println("Empty Tree");
        else
            InOrder(root);
            PostOrder(root);
            PreOrder(root);
    }
    


     
   // modifications starts from here 
    public void Inorder_with_parenthesis(String exp)
    {

        int exp_size = exp.length();
        char[] expression = new char[exp_size];

for (int i = 0; i < exp.length(); i++) {
    expression[i] = exp.charAt(i);

}

        Stack<String> stack = new Stack<String>();
     
        for (int i = 0; i < exp_size; i++)
        {
           
            if ((expression[i]>= 'a' &&expression[i]<= 'z')||(expression[i] >= 'A' && expression[i] <= 'Z'))
            {
            stack.push(exp.charAt(i) + "");
            }

            else
            {
                String op1 = stack.peek();
                stack.pop();
                String op2 = stack.peek();
                stack.pop();
                stack.push("(" + op2 + expression[i] +  op1 + ")");
            }
        }
     

        System.out.println(stack.peek());
    }
    

    private void InOrder(BinaryNode<AnyType> t){
        if (t != null)
        {
            InOrder(t.left);
            System.out.print(t.element);
            InOrder(t.right);
        }
    }
    private void PreOrder(BinaryNode<AnyType> t){
        if (t != null)
        {
            System.out.print(t.element);
            PreOrder(t.left);         
            PreOrder(t.right);
        }
    }
     private void PostOrder(BinaryNode<AnyType> t){
        if (t != null)
        {    
            PostOrder(t.left);         
            PostOrder(t.right);
            System.out.print(t.element);
        }
    }
    
    private boolean contains(AnyType x, BinaryNode<AnyType> t){
        
        if (t==null) return false;
        
        int compareResult = x.compareTo(t.element);
        
        if (compareResult < 0 ) return contains (x, t.left);
        else if (compareResult > 0 ) return contains (x, t.right);
        else return true;
    }
    
    private BinaryNode<AnyType> findMin (BinaryNode t)
    {
        if (t==null) return null;
        else if (t.left == null) return t;
        return findMin(t.left);
    }
    
    private BinaryNode<AnyType> findMax (BinaryNode t)
    {
        if (t==null) return null;
        else if (t.right == null) return t;
        return findMin(t.right);
    }
    
    public BinaryNode<AnyType> insert (AnyType x, BinaryNode<AnyType> t)
    {
        if (t==null) return new BinaryNode<>(x, null, null);
        else if(t.left == null) t.left = insert(x,t.left);
        else if(t.right == null) t.right = insert(x,t.right);      
        return t;
    }
     public BinaryNode<AnyType> insert (BinaryNode<AnyType> t)
    {
        if (t==null) return t;// new BinaryNode<>(x, null, null);
        else if(t.left == null) t.left = t;
        else if(t.right == null) t.right = t;    
        return t;
    }
     
     public BinaryNode<AnyType> insert (BinaryNode<AnyType> ot, BinaryNode<AnyType> t)
    {
      
        if(ot.left == null) ot.left = insert(ot.left, t);
        else if(ot.right == null) ot.right = insert(ot.right, t);    
        return t;
    }
    
   
    private BinaryNode<AnyType> remove (AnyType x, BinaryNode<AnyType> t)
    {
        if (t==null) return t;
        int compareResult = x.compareTo(t.element);
        
        if (compareResult < 0 ) t.left = remove(x, t.left);
        else if (compareResult > 0 )  t.right = remove(x, t.right);
        else if (t.left != null && t.right != null)
        {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        }
        else 
            t = (t.left != null ) ? t.left:t.right;
        
        return t;
    }
     private static class UnderflowException extends Exception {

        public UnderflowException() {
            super("UnderFlowException been called !");
        }
    }
    public static void main(String args[]) {
        
        Scanner kb = new Scanner (System.in);

        BinaryTree BT = new BinaryTree();
        BinaryNode BN[] = new BinaryNode[10];
        BinaryNode BNTemp1;
        int idx=0;
        
System.out.print("Arithmetic Expression: ");
        String AE = kb.next();
        for (int i=0; i<AE.length();i++){
           
            if(AE.charAt(i)=='+' || AE.charAt(i)=='-' || AE.charAt(i)=='*' || AE.charAt(i)=='/'){
                BNTemp1 = new BinaryNode(AE.charAt(i),BN[idx-2],BN[idx-1]);
                idx-=2;
                BN[idx] = BNTemp1;
                idx++;
            }
            else{
                BinaryNode BinN = new BinaryNode(AE.charAt(i));
                BN[idx] = BinN; 
                idx++;  
               
            }
        }
        System.out.println();
        BT.root = BN[0];
        System.out.print("\nInorder Modified: " );BT.Inorder_with_parenthesis(AE); 
        System.out.print("\nPreOrder: ");BT.PreOrder(BT.root);
        System.out.print("\nPostOrder: ");BT.PostOrder(BT.root);
       
    }

   
    
    
    }
    
