import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


public class WordPuzzle {
	
	private int rows;
	private int coloumns;
	private char[][] arr;
	private ArrayList<String> words = new ArrayList<>();
	public ArrayList<String> subwords = new ArrayList<>();

	
	
	private void findsubStrings(){		
		for(String s : words){
			for(int i = 0;i < s.length();i++){
				String str = new String();
				for(int j = i;j < s.length();j++){
					str = str.concat(String.valueOf(s.charAt(j)));
				}
				addWord(str, subwords);
			}
		}
		
	}
	
	private void findHVDStrings(){
      String s;
      for(int i = 0;i < rows;i++){
         for(int j = 0;j <coloumns;j++){
               if(i == 0){
                     s = new String();
                     for(int t = 0;t < rows;t++){
                         s = s.concat(String.valueOf(arr[t][j]));
                     }
                     addWord(s,words);
               }
               if(j == 0){
                     s = new String();
                     for(int t = 0;t < coloumns;t++){
                         s = s.concat(String.valueOf(arr[i][t]));  
                     }
                     addWord(s,words);
               }
               if(i == 0 || j == 0 || i == rows-1 || j == coloumns-1)
               {
                     s = new String();
                     int t1 = i;
                     int t2 = j;
                     while(t1 < rows && t2 < coloumns){
                           s = s.concat(String.valueOf(arr[t1][t2]));
                           t1++;
                           t2++;      
                     }
                     addWord(s,words);
                     s = new String();
                     t1 = i;
                     t2 = j;
                     while(t1 >= 0 && t2 >= 0)
                     {
                           s = s.concat(String.valueOf(arr[t1][t2]));
                           t1--;
                           t2--;
                     }
                     addWord(s,words);
               }
         }   
      }
	}
	private void addWord(String s,ArrayList<String> list){
		StringBuilder rev = new StringBuilder(s);
		rev.reverse();
		if(!list.contains(s)){
			list.add(s);
		}
		if(!list.contains(rev.toString())){
			list.add(rev.toString());
		}
	}
	
	public WordPuzzle(int r, int c) {
		rows = r;
		coloumns = c;
		arr = new char[rows][];
      System.out.println("Word puzzle with rows = "+r+" coloumns = "+c);
		for (int i = 0;i < rows;i++){
			arr[i] = new char[coloumns];
			for(int j = 0;j < coloumns;j++){
				Random rand = new Random();
				char cr = (char)(rand.nextInt(26) + 'a');
				arr[i][j] = cr;
			}
			System.out.println(arr[i]);
		}
		findHVDStrings();
 		findsubStrings();
      System.out.println(words);
  		System.out.println(subwords);
		
	}
   public static void main(String args[]){
		int rows;
		int coloumns;
		String line;
      long startTime;
      long endTime;
      long duration;
      long tomilli = 1000000;
		Scanner sc = new Scanner(System.in);
      LinkedList<String> ldict = new LinkedList<>();
      AvlTree<String> tdict = new AvlTree<String>();
      MyHashTable<String> hdict = new MyHashTable<String>(120577); 
      

      LinkedList<String> llistmatch;
      LinkedList<String> tlistmatch;
      LinkedList<String> hlistmatch;
      		
      //Get Rows and coloumns from user
      
      System.out.println("Enter number of rows");
      rows = sc.nextInt();
      System.out.println("Enter number of coloumns");
      coloumns = sc.nextInt();
      
      //Create a puzzle of given dimensions
      WordPuzzle puzzle = new WordPuzzle(rows,coloumns);
       try {
 			    InputStream fis = new FileInputStream("dictionary.txt");
 			    InputStreamReader isr = new InputStreamReader(fis);
 			    BufferedReader br = new BufferedReader(isr);
 			    while ((line = br.readLine()) != null) {
 			        ldict.add(line);
 			        tdict.insert(line);
                 hdict.insert(line);      
 			    }
 			    br.close();
 		}catch (Exception e) {
 				System.out.println(e.getMessage());
 		}
      startTime = System.nanoTime();
      llistmatch = listMatch(ldict ,puzzle );
		endTime = System.nanoTime();
      duration = endTime - startTime;
      System.out.println("From Linked list "+llistmatch);
      System.out.println("Time taken when dictnory is stored in LinkedList "+Double.valueOf(duration)/Double.valueOf(tomilli)+"ms");
      
      startTime = System.nanoTime();
      tlistmatch = treeMatch(tdict ,puzzle );
		endTime = System.nanoTime();
      duration = endTime - startTime;
      System.out.println("From Linked list "+tlistmatch);
      System.out.println("Time taken when dictnory is stored in Tree "+Double.valueOf(duration)/Double.valueOf(tomilli)+"ms"); 
      
      startTime = System.nanoTime();
      hlistmatch = hashMatch(hdict ,puzzle );
		endTime = System.nanoTime();
      duration = endTime - startTime;
      System.out.println("From Linked list "+hlistmatch);
      System.out.println("Time taken when dictnory is stored in HashList "+Double.valueOf(duration)/Double.valueOf(tomilli)+"ms"); 

	}


   public static LinkedList<String> listMatch(LinkedList<String> ldict ,WordPuzzle puzzle ){ 
         LinkedList<String> llistmatch = new LinkedList<>();
         for(String str : puzzle.subwords){
      			if(ldict.contains(str)){
      				llistmatch.add(str);
      			}
		   }
         // System.out.println("From Linked list "+llistmatch);
         return llistmatch;
   }
    public static LinkedList<String> treeMatch(AvlTree<String> tdict ,WordPuzzle puzzle ){
         LinkedList<String> tlistmatch = new LinkedList<>();
         for(String str : puzzle.subwords){
      			if(tdict.contains(str)){
       				tlistmatch.add(str);
       			}
		   }
         // System.out.println("From Tree list "+tlistmatch);
         return tlistmatch;
   }
   public static LinkedList<String> hashMatch(MyHashTable<String> hdict ,WordPuzzle puzzle ){
		   LinkedList<String> hlistmatch = new LinkedList<>();
         for(String str : puzzle.subwords){
      			if(hdict.contains(str)){
       				hlistmatch.add(str);
       			}
		   }
         return hlistmatch;
         // System.out.println("From Hash list "+hlistmatch);
   }
   
}
