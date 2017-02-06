import java.util.*;

public class EvaluateTemperature{


         public static String forecast(Float temp,String scale){
         
                String weather = new String();
                if(scale.equalsIgnoreCase("C"))
                { 
                    
                    temp =  ( temp * 9)/5 + 32;      // formula to convert celsius to farenheit
                    System.out.println("The temperature in farenheit is : "+temp);
                }
   
                   
                 //     < 0   Extremely cold 
                 //     0-32   Very cold
                 //     33-50  Cold
                 //     51-70  Mild 
                 //     71-90  Warm
                 //     91-100 Hot
                 //     > 100  Very hot
                
                if(temp <  0){
                   weather = "Extremely cold";
                }else if(temp >= 0 && temp <= 32){
                   
                   weather  = "Very cold";
                
                }else if(temp >= 33&& temp <= 50){
                   
                  weather  = "Cold";
                
                }else if(temp >= 51&& temp <= 70){
                   
                   weather  = "mild";
                
                }else if(temp >= 71&& temp <= 90){
                   
                   weather  = "Warm";
                
                }else if(temp >= 91 && temp <= 100){
                   
                   weather  = "Hot";
                
                }else if(temp > 100){
                   
                   weather  = "Very Hot";
                
                }   
                   
                return weather ;
         
         }
         
            public static void main(String args[]){
     
                    System.out.println("Program to evaluate temperature");
      
                    Float temp = new Float (0);
                    String Measurement ;
         
                    Scanner sc = new Scanner(System.in);
         
                    System.out.println("Enter Temperature");
                    temp = sc.nextFloat();
         
                    System.out.println("For celcius enter c and for forehite enter f");
                    Measurement = new String(sc.next());
         
                    String rf = forecast(temp,Measurement);
         
                    System.out.println("Weather is "+rf);
                    sc.close();
           } 

}