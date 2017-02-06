import java.util.*;
public class MyHashTable<AnyType> {
	 private ArrayList<LinkedList<AnyType>> arr;
	 private long Size; 
	
	 
	 public long gethashvalue(AnyType key){
		 long hashValue = 0;
       long lsize = Size;
		 if(key instanceof Integer){
			 hashValue = Integer.valueOf(String.valueOf(key));
		 }else if(key instanceof String){
			 
			 String s = String.valueOf(key);
			 for(int i = 0;i < s.length()&& i < 10;i++){
				 char c = s.charAt(i);
				 int val = (int)c;
				 hashValue = 13*hashValue+val;
			 }
		 }else{
			 hashValue = key.hashCode();
		 }
       
       return hashValue % lsize;
	 }
	 public void insert(AnyType key){
		 
		 Long hashValue = gethashvalue(key);
       
 		 LinkedList<AnyType> llist = arr.get(hashValue.intValue());
 		 llist.add(key);
	 }
	 public MyHashTable(int tableSize){ 
		arr = new ArrayList<>();
		Size = tableSize;
		long i = 0;
		while(i < tableSize){
			arr.add(new LinkedList<AnyType>());
			i++;
		}
	 }
	 public boolean contains(AnyType key){		
		 boolean present = false;
		 Long hashValue = gethashvalue(key);
       Integer intValue = hashValue.intValue();
		 LinkedList<AnyType> llist = arr.get(intValue);
		 if(llist.contains(key)){
			 present = true;
		 }
		 return present;
	 }
}
