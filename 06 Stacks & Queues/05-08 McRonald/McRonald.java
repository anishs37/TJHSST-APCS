//Updated on 12.14.2020 v2
 
//Name: J2-24
//Date: 1/16/2021
      
import java.util.*;
import java.io.*;

public class McRonald
{
   public static final int TIME = 1080;     //18 hrs * 60 min
   public static double CHANCE_OF_CUSTOMER = .2;
   public static int customers = 0;
   public static int totalMinutes = 0;
   public static int longestWaitTime = 0;
   public static int longestQueue = 0;
   public static int serviceWindow = 0;      // to serve the front of the queue
   public static int thisCustomersTime;
   public static PrintWriter outfile = null; // file to display the queue information
      
   public static int timeToOrderAndBeServed()
   {
      return (int)(Math.random() * 6 + 2);
   }
  
   public static void displayTimeAndQueue(Queue<Customer> q, int min)
   { 
      if(q.size() == 0)
         outfile.println(min + ": " + q);
      
      else
      {
         Queue<Customer> qCopy = new LinkedList<>();
         int qSize = q.size();
         
         Customer removed = q.remove();
         
         while(!q.isEmpty())
            qCopy.add(q.remove());
            
         outfile.println(min + ": " + qCopy);
         
         q.add(removed);
         
         for(int i = 0; i < qSize - 1; i++)
            q.add(qCopy.remove());       
      }                      	
   }
   
   public static void displayServiceArea(String str)
   {
      if(str != "")
      {
         outfile.println(str);
         outfile.println("");
      }
      
      else
         outfile.println("");
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
         
         outfile.println("Mcronald(arrival chance is 0.2) with 1 queue / 1 service areas");
         
         mcRonald(TIME, outfile);   //run the simulation   
         
         outfile.close();	
   }
   
   public static void mcRonald(int TIME, PrintWriter of)
   {
      /***************************************
           Write your code for the simulation   
      **********************************/
      
      int waitTime = timeToOrderAndBeServed();          //wait time between 2 to 7 mins
      
      Queue<Customer> cust = new LinkedList<Customer>(); 
      
      boolean beingServed = false;
      int custBeingServed = -1;
      
      for(int i = 0; i < TIME; i++)
      {
         String str = "";
         
         double chance = Math.random();
         
         if(chance < CHANCE_OF_CUSTOMER)        //20% chance of chance to be less than 0.2
         {
            cust.add(new Customer(i));  
            customers++;
            
            outfile.println("new customer min#" + i);
         }
 
         if(waitTime == 0)
         {
            int numRemove = cust.peek().getArrived();
            thisCustomersTime = i - cust.remove().getArrived();
            outfile.println("Customer#" + numRemove + " leaves and his total wait time is " + thisCustomersTime);
            
            if(thisCustomersTime > longestWaitTime)
               longestWaitTime = thisCustomersTime;
            
            waitTime = timeToOrderAndBeServed();
            totalMinutes += thisCustomersTime;
            
            if(!cust.isEmpty())
            {
               int numPeek = cust.peek().getArrived();
               str = "\t" + "ServiceArea#1 " + numPeek + ":" + waitTime;
               waitTime--;
            }
            
            else
               str = "\t" + "ServiceArea#1 -1:-1";
         }
         
         else
         {            
            if(!cust.isEmpty())
            {
               int num = cust.peek().getArrived();
               str = "\t" + "ServiceArea#1 " + num + ":" + waitTime;
               waitTime--;
            }
            
            else
               str = "\t" + "ServiceArea#1 -1:-1";
         }
 
         int size = cust.size();
         
         if(size > longestQueue)
            longestQueue = size;
         
         displayTimeAndQueue(cust, i);
         displayServiceArea(str);
      }
      
      int k = TIME;
 
      if(!cust.isEmpty())
      {
         do
         {
            String str = "";
            
            if(waitTime == 0)
            {
               int numRemove = cust.peek().getArrived();
               thisCustomersTime = k - cust.remove().getArrived();
               outfile.println("Customer#" + numRemove + " leaves and his total wait time is " + thisCustomersTime);
               
               if(thisCustomersTime > longestWaitTime)
                  longestWaitTime = thisCustomersTime;
               
               waitTime = timeToOrderAndBeServed();
               totalMinutes += thisCustomersTime;
               
               if(!cust.isEmpty())
               {
                  int numPeek = cust.peek().getArrived();
                  str = "\t" + "ServiceArea#1 " + numPeek + ":" + waitTime;
                  waitTime--;
               }
            }
         
            else
            {
               int num = cust.peek().getArrived();
               str = "\t" + "ServiceArea#1 " + num + ":" + waitTime;
               
               if(!cust.isEmpty())
                  waitTime--;
            }
 
            int size = cust.size();
            
            if(size > longestQueue)
               longestQueue = size;
            
            displayTimeAndQueue(cust, k);
            displayServiceArea(str);
            
            k++;
         }
         
         while(!cust.isEmpty());
         
         outfile.println("\t" + "ServiceArea#1 -1:-1");
      }
                  
      /*   report the data to the screen    */  
      
      System.out.println("1 queue, 1 service window, probability of arrival = "+ CHANCE_OF_CUSTOMER);
      System.out.println("Total customers served = " + getCustomers());
      System.out.println("Average wait time = " + calculateAverage());
      System.out.println("Longest wait time = " + longestWaitTime);
      System.out.println("Longest queue = " + (longestQueue - 1));
      
      outfile.println("");
      outfile.println("Total customers served = " + getCustomers());
      outfile.println("Average wait time = " + calculateAverage());
      outfile.println("Longest wait time = " + longestWaitTime);
      outfile.println("Longest queue = " + (longestQueue - 1));
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
         //orderAndBeServed = timeToOrderAndBeServed();
      }
 
      public int getArrived()
      {
         return arrivedAt;
      }
      
      //public int getWait()
      //{
      //   return orderAndBeServed;
      //}
      
      public String toString()
      {
         String str = "" + arrivedAt;
         return str;
      }
   }
}