/* Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
import java.util.*; 
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
        Arraypre = new ArrayList<AnyType>();
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( ) throws Exception
    {
        if( isEmpty( ) )
            throw new Exception("No Elements in tree.");
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( ) throws Exception
    {
        if( isEmpty( ) )
            throw new Exception("No Elements in tree.");
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<AnyType>( x, null, null );
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );    
    }
    
    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType>
    {
            // Constructors
        BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }
    public int nodeCount(){
         
         return nodeCount(root);
    }
    private int nodeCount(BinaryNode<AnyType> root){
         if(root == null){
               return 0;
         }
         int ncl = nodeCount(root.left);
         int ncr = nodeCount(root.right);
         return 1+ncl+ncr;
    }
    public boolean isFull(){
         return isFull(root);
    }
    private boolean isFull(BinaryNode<AnyType> root){
         if(root.left == null && root.right == null){
            return   true;
         }
         if(root.left != null && root.right != null){
            
             return isFull(root.left) && isFull(root.right);
            
         }else{
            return false;
         }
         
    }
    
//     public boolean compareStructure(BinarySearchTree x){
//     
//          return compareStructure(BinarySearchTree x,root);
//     } 
//     private boolean compareStructure(BinarySearchTree x,BinaryNode<AnyType> root){
//          if(x.nodeCount != this.nodeCont){
//             return false;
//          }
//          
//     }

    public ArrayList<AnyType> preArray(){
           Arraypre.clear();
           preArray(root);
           return Arraypre;
    }
    private void preArray(BinaryNode<AnyType> root){
          if(root != null){
               Arraypre.add(root.element);
               preArray(root.left);
               preArray(root.right);
          }
    }     
    public BinarySearchTree<AnyType> copy(){
      BinarySearchTree<AnyType> t = new BinarySearchTree<>();
      return copy(root,t);
    } 
    
    private BinarySearchTree<AnyType> copy(BinaryNode<AnyType> root,BinarySearchTree<AnyType> t){
      
      if(root == null){
         return t;
      }
      t.insert(root.element);
      copy(root.left,t);
      copy(root.right,t);
      return t;
    }    
    
    public boolean equals(BinarySearchTree<AnyType> t){
         ArrayList<AnyType> thattree = t.preArray();
         ArrayList<AnyType> thistree = this.preArray();
         String thattreestring = thattree.toString();
         String thistreestring = thistree.toString();
         return thistreestring.equals(thattreestring);
    }
    
    public BinarySearchTree<AnyType> mirror(){
      
         BinarySearchTree<AnyType> mi = new BinarySearchTree<AnyType>();
         ArrayList<AnyType> thistree = this.preArray();
           
         for(AnyType i : thistree){
            mi.mirrorinsert(i);
         }
         return mi;
    }
    
    public BinaryNode<AnyType> mirrorinsert(AnyType i){
           
           root = mirrorinsert(i,root);
           return root;  
           
    }
    private BinaryNode<AnyType> mirrorinsert(AnyType x, BinaryNode<AnyType> t){
        
        if( t == null )
            return new BinaryNode<AnyType>( x, null, null );
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult > 0 )
            t.left = mirrorinsert( x, t.left );
        else if( compareResult < 0 )
            t.right = mirrorinsert( x, t.right );

        return t;
    }
    
    public boolean isMirror(BinarySearchTree<AnyType> thattree ){
         
        String thattreestring = thattree.preArray().toString();
        BinarySearchTree<AnyType> mir = this.mirror();
        String thistreeString = mir.preArray().toString();
        return thistreeString.equals(thattreestring);
             
    
    } 
    
    public void rotateRight(AnyType i){
        
        root = rotateRight(i,root);

    }
    private BinaryNode<AnyType> rotateRight(AnyType i, BinaryNode<AnyType> root){
    
         if(root == null){
            return root;
         }
         int Compareresult = i.compareTo( root.element );
         
         if(Compareresult > 0){
            root.right =  rotateRight(i, root.right);
         }else if(Compareresult < 0){
            
               root.left = rotateRight(i, root.left);
          
         }else{
               if(root.left != null){
               
                  BinaryNode<AnyType> temp1 = root.left.right;
                  
                  BinaryNode<AnyType> temp2 = root.left;
                  
                  temp2.right = root;
                  root = temp2;
                  root.right.left = temp1;
               }
         }
         return root;
    }
    
    public void rotateLeft(AnyType i){
        
        root = rotateLeft(i,root);

    }
    private BinaryNode<AnyType> rotateLeft(AnyType i, BinaryNode<AnyType> root){
    
         if(root == null){
            return root;
         }
         int Compareresult = i.compareTo( root.element );
         
         if(Compareresult > 0){
            root.right =  rotateLeft(i, root.right);
         }else if(Compareresult < 0){
            
               root.left = rotateLeft(i, root.left);
          
         }else{
               if(root.right != null){
               
                  BinaryNode<AnyType> temp1 = root.right.left;
                  
                  BinaryNode<AnyType> temp2 = root.right;
                  
                  temp2.left = root;
                  root = temp2;
                  root.left.right = temp1;
               }
         }
         return root;
    }
    
    public void printLevels(){
		    Queue<BinaryNode<AnyType>> qe =  new LinkedList<>();
          qe.add(root);
          printLevels(qe);
    }
    
    private void printLevels(Queue<BinaryNode<AnyType>> qe){

          while(!qe.isEmpty()){
          
             BinaryNode<AnyType> t = qe.poll();
             System.out.println(t.element);
             if(t.left != null){
               qe.add(t.left);  
             }
             if(t.right != null){
               qe.add(t.right);
             }
          }
    } 
      /** The tree root. */
    private BinaryNode<AnyType> root;
    public ArrayList<AnyType> Arraypre;
    
    // Test program
    public static void main( String [ ] args )
    {
         
         BinarySearchTree<Integer> t = new BinarySearchTree<>();
        // for( int i = 1; i != 0; i-- )
            t.insert( 100 );
            t.insert( 50 );
            t.insert( 150 );
            t.insert( 40  );
            t.insert( 75  );
            t.insert( 125  );
            t.insert( 175  );
            t.insert( 45  );
            
       // Testing nodeCount
            System.out.println("Number of nodes in Tree : " + t.nodeCount()); 
       // Testing isFull
           System.out.println("Check if tree is full : " + t.isFull());
       // Testing copy
           BinarySearchTree<Integer> DopleGanger = t.copy();
           System.out.println("Printing new copy and original Tree : ");
           // DopleGanger.printTree();
//            t.printTree();
       // Testing Equals 
           System.out.println("Check if both the trees are same :"+t.equals(DopleGanger));
           
       // Testing mirror 
           System.out.println("Printing the mirrer of the  Tree :");
           BinarySearchTree<Integer> mi = t.mirror();
           mi.printTree();
           System.out.println("Printing the original Tree :"); 
           t.printTree();
       // Testing isMirror
           
           System.out.println("Check if given Tree is mirror : "+t.isMirror(mi));    
       // Testing rotateRight
       
           System.out.println("Rotate tree at given element to right : ");
           t.rotateRight(100);
           t.printTree();
           
       // testing rotateleft
           System.out.println("Rotate tree at given element to Left : ");
           t.rotateLeft(50);
           t.printTree();
       // Testing printLevels 
           System.out.println("Printing Tree by levels : ");
           t.printLevels();        
     }


}

        
        

