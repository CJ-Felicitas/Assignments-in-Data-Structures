import java.util.Stack;

public class BinaryTree<AnyType extends Comparable<? super AnyType>>
{
    public static class BinaryNode<AnyType>
    {
        //constructors
        BinaryNode(AnyType theElement) { this(theElement, null, null);}
        BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode <AnyType> rt)
        { element = theElement; left=lt; right= rt; }
        
        AnyType element;
        BinaryNode<AnyType> left;
        BinaryNode<AnyType> right;
    }
    
    private BinaryNode<AnyType> root;
    
    public BinaryTree(){ root= null; }
    
    //public BinaryTree(AnyType x){ root = null; root.element=x; root.left=null ; root.right=null; }
    
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
    

    public boolean isOperand(char x)
    {
        return (x >= 'a' && x <= 'z') ||
                (x >= 'A' && x <= 'Z');
    }
     
    // Get Infix for a given postfix
    // expression
    public void getInfix(String exp)
    {
        Stack<String> s = new Stack<String>();
     
        for (int i = 0; i < exp.length(); i++)
        {
            // Push operands
            if (isOperand(exp.charAt(i)))
            {
            s.push(exp.charAt(i) + "");
            }
     
            // We assume that input is
            // a valid postfix and expect
            // an operator.
            else
            {
                String op1 = s.peek();
                s.pop();
                String op2 = s.peek();
                s.pop();
                s.push("(" + op2 + exp.charAt(i) +
                        op1 + ")");
            }
        }
     
        // There must be a single element
        // in stack now which is the required
        // infix.
        System.out.println(s.peek());
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
    
        BinaryTree BT = new BinaryTree();
        BinaryNode BN[] = new BinaryNode[10];
        BinaryNode BNTemp1;
        int idx=0;
        
        String AE="ab+cde+**f+";
     
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
        System.out.print("Inorder: ");BT.InOrder(BT.root);
        System.out.print("\nPreOrder: ");BT.PreOrder(BT.root);
        System.out.print("\nPostOrder: ");BT.PostOrder(BT.root);
        System.out.print("\nInorder Modified kadtong naay parenthesis: " );BT.getInfix(AE); 
       
    }

   
    
    
    }
    
