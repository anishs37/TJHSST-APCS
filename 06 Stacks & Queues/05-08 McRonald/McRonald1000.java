//Name: J2-24
//Date: 1/17/2021
      
import java.util.*;
import java.io.*;
public class McRonald1000
{
   public static final int TIME = 1080;     //18 hrs * 60 min
   public static double CHANCE_OF_CUSTOMER = .2;
   public static int customers = 0;
   public static int totalMinutes = 0;
   public static int longestWaitTime = 0;
   public static int longestQueue = 0;
   public static int serviceWindow = 0;      // to serve the front of the queue
   public static int thisCustomersTime;
   public static int largestDay = 0;
   public static int servedThatDay = 0;
   public static PrintWriter outfile = null; // file to display the queue information
      
   public static int timeToOrderAndBeServed()
   {
      return (int)(Math.random() * 6 + 2);
   }
  
   public static void displayTimeAndQueue(Queue<Customer> q, int min)
   { 
      //Billington's
      //outfile.println(min + ": " + q);
      	
      //Jurj's
      //outfile.println("Customer#" + intServiceAreas[i] + 
      //                            " leaves and his total wait time is " + (intMinute - intServiceAreas[i]));                                        	
   }
   
   public static int getCustomers()
   {
      return customers;
   }
   
   public static double calculateAverage()
   {
      return (int)(1.0 * totalMinutes/customers * 10)/10.0;
   }
   
   public static int getLongestWaitTime()
   {
      return longestWaitTime;
   }
   
   public static int getLongestQueue()
   {
      return longestQueue;
   }
            
   public static void main(String[] args)
   {     
      //set up file
       
      try
      {
         outfile = new PrintWriter(new FileWriter("McRonald 1 Queue 1 ServiceArea.txt"));
      }
      
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
      
      outfile.println("Mcronald1000(arrival chance is 0.2) with 1 queue / 1 service areas");
      
      mcRonald(TIME, outfile);   //run the simulation   
      
      outfile.close();	
   }
   
   public static void mcRonald(int TIME, PrintWriter of)
   {
      /***************************************
           Write your code for the simulation   
      **********************************/
      
      for(int day = 1; day <= 1000; day++)
      {
         int numServed = 0;
         int waitTime = timeToOrderAndBeServed();          //wait time between 2 to 7 mins
         
         Queue<Customer> cust = new LinkedList<>();

         for(int i = 0; i < TIME; i++)
         {           
            double chance = Math.random();
            
            if(chance < CHANCE_OF_CUSTOMER)        //20% chance of chance to be less than 0.2
            {
               cust.add(new Customer(i));  
               customers++;
               numServed++;
            }
    
            if(waitTime == 0)
            {
               thisCustomersTime = i - cust.remove().getArrived();
               
               if(thisCustomersTime > longestWaitTime)
                  longestWaitTime = thisCustomersTime;
               
               waitTime = timeToOrderAndBeServed();
               totalMinutes += thisCustomersTime;
               
               if(!cust.isEmpty())
                  waitTime--;
            }
            
            else          
               if(!cust.isEmpty())
                  waitTime--;
    
            int size = cust.size();
            
            if(size > longestQueue)
               longestQueue = size;
         }
      
         int k = TIME;
    
         if(!cust.isEmpty())
         {
            do
            {
               if(waitTime == 0)
               {
                  thisCustomersTime = k - cust.remove().getArrived();
                  
                  if(thisCustomersTime > longestWaitTime)
                     longestWaitTime = thisCustomersTime;
                  
                  waitTime = timeToOrderAndBeServed();
                  totalMinutes += thisCustomersTime;
                  
                  if(!cust.isEmpty())
                     waitTime--;
               }
            
               else                 
                  if(!cust.isEmpty())
                     waitTime--;
    
               int size = cust.size();
               
               if(size > longestQueue)
                  longestQueue = size;
                  
               k++;
            }
            
            while(!cust.isEmpty());
            
            if(numServed > servedThatDay)
            {
               servedThatDay = numServed;
               largestDay = day;
            }
         }
      }
                  
      /*   report the data to the screen    */  
      
      System.out.println("Mcronald1000(arrival chance is 0.2) with 1 queue / 1 service areas");
      System.out.println("Total customers served = " + getCustomers());
      System.out.println("Average wait time = " + calculateAverage());
      System.out.println("Longest wait time = " + longestWaitTime);
      System.out.println("Longest queue = " + longestQueue);
      System.out.println("Average served per day = " + (getCustomers() / 1000));
      System.out.println("Largest day = " + largestDay);
   }
   
   static class Customer      
   {
      private int arrivedAt;
      private int orderAndBeServed;;
      
    /**********************************
       Complete the Customer class with  
       constructor, accessor methods, toString.
    ***********************************/
      
      public Customer(int time)
      {
         arrivedAt = time;
         orderAndBeServed = timeToOrderAndBeServed();
      }
 
      public int getArrived()
      {
         return arrivedAt;
      }
      
      public int getWait()
      {
         return orderAndBeServed;
      }
      
      public String toString()
      {
         String str = "" + arrivedAt;
         return str;
      }
   }
}