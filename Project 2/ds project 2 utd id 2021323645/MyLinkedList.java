/**
 * LinkedList class implements a doubly-linked list.
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType>
{
    /**
     * Construct an empty LinkedList.
     */
    public MyLinkedList( )
    {
        doClear( );
    }
    
    private void clear( )
    {
        doClear( );
    }
    
    /**
     * Change the size of this collection to zero.
     */
    public void doClear( )
    {
        beginMarker = new Node<>( null, null, null );
        endMarker = new Node<>( null, beginMarker, null );
        beginMarker.next = endMarker;
        
        theSize = 0;
        modCount++;
    }
    
    /**
     * Returns the number of items in this collection.
     * @return the number of items in this collection.
     */
    public int size( )
    {
        return theSize;
    }
    
    public boolean isEmpty( )
    {
        return size( ) == 0;
    }
    
    /**
     * Adds an item to this collection, at the end.
     * @param x any object.
     * @return true.
     */
    public boolean add( AnyType x )
    {
        add( size( ), x );   
        return true;         
    }
    
    /**
     * Adds an item to this collection, at specified position.
     * Items at or after that position are slid one position higher.
     * @param x any object.
     * @param idx position to add at.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */
	public void add( int idx, AnyType x )
    {
        addBefore( getNode( idx, 0, size( ) ), x );
    }
    
    /**
     * Adds an item to this collection, at specified position p.
     * Items at or after that position are slid one position higher.
     * @param p Node to add before.
     * @param x any object.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */    
    private void addBefore( Node<AnyType> p, AnyType x )
    {
        Node<AnyType> newNode = new Node<>( x, p.prev, p );
        newNode.prev.next = newNode;
        p.prev = newNode;         
        theSize++;
        modCount++;
    }   
    
    
    /**
     * Returns the item at position idx.
     * @param idx the index to search in.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType get( int idx )
    {
        return getNode( idx ).data;
    }
        
    /**
     * Changes the item at position idx.
     * @param idx the index to change.
     * @param newVal the new value.
     * @return the old value.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType set( int idx, AnyType newVal )
    {
        Node<AnyType> p = getNode( idx );
        AnyType oldVal = p.data;
        
        p.data = newVal;   
        return oldVal;
    }
    
    /**
     * Gets the Node at position idx, which must range from 0 to size( ) - 1.
     * @param idx index to search at.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size( ) - 1, inclusive.
     */
    private Node<AnyType> getNode( int idx )
    {
        return getNode( idx, 0, size( ) - 1 );
    }
    
    /**
     * Gets the Node at position idx, which must range from lower to upper.
     * @param idx index to search at.
     * @param lower lowest valid index.
     * @param upper highest valid index.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between lower and upper, inclusive.
     */    
    private Node<AnyType> getNode( int idx, int lower, int upper )
    {
        Node<AnyType> p;
        
        if( idx < lower || idx > upper )
            throw new IndexOutOfBoundsException( "getNode index: " + idx + "; size: " + size( ) );
            
        if( idx < size( ) / 2 )
        {
            p = beginMarker.next;
            for( int i = 0; i < idx; i++ )
                p = p.next;            
        }
        else
        {
            p = endMarker;
            for( int i = size( ); i > idx; i-- )
                p = p.prev;
        } 
        
        return p;
    }
    
    /**
     * Removes an item from this collection.
     * @param idx the index of the object.
     * @return the item was removed from the collection.
     */
    public AnyType remove( int idx )
    {
        return remove( getNode( idx ) );
    }
    
    /**
     * Removes the object contained in Node p.
     * @param p the Node containing the object.
     * @return the item was removed from the collection.
     */
    private AnyType remove( Node<AnyType> p )
    {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;
        
        return p.data;
    }
    
    /**
     * Returns a String representation of this collection.
     */
    public String toString( )
    {
        StringBuilder sb = new StringBuilder( "[ " );

        for( AnyType x : this )
            sb.append( x + " " );
        sb.append( "]" );

        return new String( sb );
    }

	/**
	 * Swaps the nodes at podition i and j by  
	**/
	public boolean Swap(int idx1,int idx2){
	
	       
	
	      Node<AnyType> p =  getNode(idx1);
		  Node<AnyType> q =  getNode(idx2);

          if(idx1 == idx2){
		        return false;
		  }          
		  
        //set the previous node's next link	
	p.prev.next = q;
	q.prev.next = p;
	
	//set the next node's prev link
	p.next.prev = q;
	q.next.prev = p;
        
		  
		  
	//swap the previous links
	Node<AnyType> a = p.prev;
	p.prev = q.prev;
	q.prev = a;

	//swap the next links
	Node<AnyType> b= p.next;
	p.next = q.next;
	q.next = b;
     		  
		  return true;
		  
	}
	
	
	/**
	 * shifts the linked list by i positions cloxkwise or anticlockwise
	**/
	public boolean Shift(int i){
		
		if(size() == 0 && size() == 1){
			return false;
		}
		
		if(i > 0){
			while(i > 0){
				shiftFirst_last();
				i--;
			}
		}else {
			while(i < 0){
				shiftLast_first();
				i++;
			}
		}
		
		return true;
	}
	/**
	 * shifts the first node in linked list to last position
	**/
	
	public boolean shiftFirst_last(){
		
		Node<AnyType> a = beginMarker.next;
		Node<AnyType> b = endMarker.prev;
		
		beginMarker.next = a.next;
		a.next.prev      = beginMarker;
		
		a.prev = b;
		a.next = endMarker;
		
		b.next = a;
		endMarker.prev = a;
		
		return true;
	}
	/**
	 * shifts the last node in linked list to first position
	**/
	public boolean shiftLast_first(){
		
		Node<AnyType> a = endMarker.prev;
		Node<AnyType> b = beginMarker.next;
		
		endMarker.prev = a.prev;
		a.prev.next = endMarker; 
		
		a.prev = beginMarker;
		a.next = b;
		
		beginMarker.next = a;
		b.prev = a;
        
		return false;
	} 
   
  /* public void shift( int i) {

	//check if the integer is positive or negative
	if( i > 0 )
	{
		Node<AnyType> p;
		p = beginMarker.next;
		for(int j=0; j<i; j++)
		{
			endMarker.prev.next = p;
			endMarker.prev = p;
			beginMarker.next = p.next;
			p.next.prev = beginMarker;
			p.prev = endMarker.prev;
			p.next = endMarker;
			p = beginMarker.next;
		}
	}
	else if( i < 0 )
 	{
		Node<AnyType> p;
		p = endMarker.prev;
		for(int j=0; j<i; j++)
		{
			p.next = beginMarker.next;
			p.prev.next = endMarker;
			endMarker.prev = p.prev;
			p.prev = beginMarker;
			beginMarker.next = p;
			p.next.prev = p;
			p = endMarker.prev;
		}
	}
}*/
   
   
   
   
   
    
   
	/**
	 *  Removes nodes starting with node at idx till idx+marker
	**/
	public boolean Erase(int idx,int no_nodes ){
		
		if(idx < 0 || idx >= size() || idx+no_nodes >= size()){
			throw new IndexOutOfBoundsException( "erase index: " + idx + "or" + idx+no_nodes + "; size: " + size( ) );
		}
      
        Node<AnyType> p = getNode(idx);
		Node<AnyType> q = getNode(idx+no_nodes-1);
		
		Node<AnyType> a = p.prev;
		Node<AnyType> b = q.next;
		
		a.next  = b;
		b.prev  = a;
      
		return true;
	}
	
	/**
	 * Insetrs another linked list at specified position.
	**/
	public boolean Insert(MyLinkedList<AnyType> another , int idx){
		
		
		Node<AnyType> p =  getNode( idx, 0, size( ));
		Node<AnyType> q =  p.prev;
		
		Node<AnyType> r = another.getNode(0);
		Node<AnyType> s = another.getNode(another.size()-1);
		
		q.next = r;
		r.prev = q;
		
		s.next = p;
		p.prev = s;
		
		return true;
	}
	
	
    /**
     * Obtains an Iterator object used to traverse the collection.
     * @return an iterator positioned prior to the first element.
     */
    public java.util.Iterator<AnyType> iterator( )
    {
        return new LinkedListIterator( );
    }

    /**
     * This is the implementation of the LinkedListIterator.
     * It maintains a notion of a current position and of
     * course the implicit reference to the MyLinkedList.
     */
    private class LinkedListIterator implements java.util.Iterator<AnyType>
    {
        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;
        
        public boolean hasNext( )
        {
            return current != endMarker;
        }
        
        public AnyType next( )
        {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException( );
            if( !hasNext( ) )
                throw new java.util.NoSuchElementException( ); 
                   
            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }
        
        public void remove( )
        {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException( );
            if( !okToRemove )
                throw new IllegalStateException( );
                
            MyLinkedList.this.remove( current.prev );
            expectedModCount++;
            okToRemove = false;       
        }
    }
    
    /**
      This is the doubly-linked list node.
     */
    private static class Node<AnyType>
    {
        public Node( AnyType d, Node<AnyType> p, Node<AnyType> n )
        {
            data = d; prev = p; next = n;
        }
        
        public AnyType data;
        public Node<AnyType>   prev;
        public Node<AnyType>   next;
    }
    
    private int theSize;
    private int modCount = 0;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;
}

class TestLinkedList
{
    public static void main( String [ ] args )
    {
        MyLinkedList<Integer> lst = new MyLinkedList<>( );
        
        MyLinkedList<Integer> lst2 = new MyLinkedList<>( );

        for( int i = 0; i < 10; i++ )
                lst.add( i );
                
        for( int i = 20; i < 30; i++ )
                lst2.add( 0, i );

        
        //Testing Swap
        System.out.println("Testing Swap function");
        printlist("initial list",lst+" "); 
           
        lst.Swap(4,7);
        printlist("After Swaping 2nd and 5th indexed nodes",lst+"\n\n");
        lst.Swap(4,7);
        
        //Tesing Shift +2
        System.out.println("Testing Shift function");
        printlist("initial list",lst+" ");
        
        lst.Shift(2);
        printlist("After shifting list by +2",lst+"\n\n");
        lst.Shift(-2);
        
        //Tesing Shift -2
        System.out.println("Testing Shift function");
        printlist("initial list",lst+" ");
        
        lst.Shift(-2);
        printlist("After shifting list by -2",lst+"\n\n");
        lst.Shift(2);
        
        
        //Testing erase 
        System.out.println("Testing Erase function");
        printlist("initial list",lst+" ");
        
        lst.Erase(0,4);
        printlist("After Removing index 1 to 4",lst+"\n\n");
        
        //Testing Insert 
        System.out.println("Testing Insert function");
        printlist("initial list",lst+" ");
        
        lst.Insert(lst2,10);
        printlist("After inserting a list at index I",lst+"\n\n");
        
        
    }
    public static void printlist(String Statemnt, String lst){
    
        System.out.println( Statemnt );
        System.out.println( lst );
    
    }
    
    
}