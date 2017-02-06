public class MyFour<T>{

     private T item1, item2 ,item3, item4 ; 
     
     public MyFour(T t1, T t2 , T t3, T t4){
     
     
               item1 = t1;
               item2 = t2;
               item3 = t3;
               item4=  t4;
     
     }
     
     public boolean allEqual(){
     
               if(!item1.equals( item2)){
               
                   return false;
               }
               if(!item1.equals( item3)){
               
                   return false;
               }
               if(!item1.equals( item4)){
               
                   return false;
               }
               return true;
     
     }

     public void shiftLeft(){
      
              
              T temp = item1;
              item1 = item2;
              item2 = item3;
              item3 = item4;
              item4 = temp;
              
     }
     
     public String toString(){
       
              return ( item1.toString()+"," + item2.toString()+"," + item3.toString()+"," + item4.toString());
      
     }
     
          public static void main(String[] args){

     
     
              String temp = "Computer";
              MyFour<String> var = new MyFour<String>(temp,temp,temp,temp);
     
              System.out.println(var.toString());
     
              if(var.allEqual()){
                 System.out.println("All are equal");
              }
              else{
                  System.out.println("All not are equal");
              }                       
              
              
                                        
              
              MyFour<Integer> var1 = new MyFour<Integer>(1,2,3,4);
              
              System.out.println(var1.toString());
              if(var1.allEqual()){
                 System.out.println("All are equal");
              }
              else{
                  System.out.println("All not are equal");
              }              
              var1.shiftLeft();
              System.out.println(var1.toString());



         }

}