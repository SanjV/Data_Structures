import java.util.*;

public class MyStack<AnyType>
{
    int Size = 0; 
    ArrayList<AnyType> arr =   new ArrayList<AnyType>();
	
	public void push(AnyType element)
	{    
		if(Size == arr.size()){
		    arr.add( element);
		}
		else
		{
		    arr.set(Size,element);
		}
        Size++;		
	}
	
	public AnyType pop()
	{
	    if(Size == 0){
		    throw new UnsupportedOperationException("POP :No More entries in Stack "); 
		 }
		AnyType p = arr.get(Size-1);
		Size = Size-1;	
      return p;		
	}
	
	public boolean isEmpty(){
		return 0 == Size;
	}
	
}


class teststack {
	
	public static void main(String args[]){
		
		MyStack<String> p = new MyStack<String>();
      String s = new String("[({}{})]");
      
      char[] arr = new char[s.length()];
      s.getChars(0,s.length(),arr,0);
      
      int i = 0;
      while(i < s.length()){
      
           if(-1 != "[".indexOf(arr[i]) || -1 != "(".indexOf(arr[i]) || -1 != "{".indexOf(arr[i])){
            
                  p.push(String.valueOf(arr[i]));
           }else if(-1 != "]".indexOf(arr[i]) || -1 != ")".indexOf(arr[i]) || -1 != "}".indexOf(arr[i])){
           
                 p.pop();
           }
                   
          i++;
      }
      
      if(! p.isEmpty()){
         System.out.println(s+ " is not a balanced Symbool " );
      }else{
         System.out.println(s+ " is a balanced Symbool " );
      }
      
       
		
	}
}