import java.util.*;

public class Scores{

  public static void  main(String args[]){
  
  
       int No_of_students = 10;
       int No_of_quizes = 5;
       String[] name = new String[No_of_students];
       Integer[][] arr = new Integer[No_of_students][No_of_quizes];
       Scanner sc = new Scanner(System.in);
       int i = 0;
       int j = 0;
       while(i < name.length){
            
            System.out.println("Enter name of student no "+(i+1));
            name[i] =  sc.next();
            
            while(j < arr[i].length){
                  System.out.println("Enter the score of student "+name[i]+" in "+"Quiz "+(j+1));
                  arr[i][j] = sc.nextInt();                
                  j++;
            }
            j = 0;
            i++;
          
       }
       i = 0;
       int sum = 0;
       while(i < name.length){
            while(j < arr[i].length){
                  
                  sum = sum+arr[i][j];               // Sum of scores of each student 
                  j++;
            }
            System.out.println(name[i]+"            "+(sum/No_of_quizes)+"  ");   //Calculatiing average of scores ny dividing sum of scores by number of quizes
            sum = 0;
            j = 0;
            i++;
       }
  }

}