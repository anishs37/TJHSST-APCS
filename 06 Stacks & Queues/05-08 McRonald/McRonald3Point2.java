//Updated on 12.14.2020 v2

//Name: J2-24
//Date: 1/18/2021
      
import java.util.*;
import java.io.*;
public class McRonald3Point2
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
      outfile.println(min + ": " + q);                          	
   }
   
   //sac stands for service area customer (customer # that enters service area)
   //sawt stands for service area wait time (wait time of customer)
   
   public static void displayServiceAreas(int[] sac, int[] sawt)
   {
      outfile.println("\t" + "ServiceArea#1 " + sac[0] + ":" + sawt[0] + "\t" + "ServiceArea#2 " + sac[1] + ":" + sawt[1] + "\t" + "ServiceArea#3 " + sac[2] + ":" + sawt[2]);
      
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
         outfile = new PrintWriter(new FileWriter("McRonald 1 Queue 3 ServiceArea point 2.txt"));
      }
      
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
      
      outfile.println("Mcronald(arrival chance is 0.2) with 1 queue / 3 service areas");
      
      mcRonald(TIME, outfile);   //run the simulation   
      
      outfile.close();	
   }
   
   public static void mcRonald(int TIME, PrintWriter of)
   {
      /***************************************
           Write your code for the simulation   
      **********************************/
      
      int waitTime = timeToOrderAndBeServed();          //wait time between 2 to 7 mins
      
      Queue<Customer> cust = new LinkedList<>();
      
      int[] serviceAreaCustomer = new int[3];
      int[] serviceAreaWaitTime = new int[3];
      
      serviceAreaCustomer[0] = -1;
      serviceAreaWaitTime[0] = -1;
      
      serviceAreaCustomer[1] = -1;
      serviceAreaWaitTime[1] = -1;
      
      serviceAreaCustomer[2] = -1;
      serviceAreaWaitTime[2] = -1;
      
      for(int i = 0; i < TIME; i++)
      {
         double chance = Math.random();
         
         if(chance < CHANCE_OF_CUSTOMER)        //20% chance of chance to be less than 0.2
         {
            cust.add(new Customer(i));          
            customers++;  
            outfile.println("new customer min#" + i);
            
            if(((serviceAreaCustomer[0] == -1) || (serviceAreaCustomer[1] == -1) || (serviceAreaCustomer[2] == -1)))
            {
               if(serviceAreaCustomer[0] == -1)
               {
                  if(!cust.isEmpty())
                  {
                     waitTime = timeToOrderAndBeServed();
                     int custToServe = cust.peek().getArrived();
                     serviceAreaCustomer[0] = custToServe;
                     serviceAreaWaitTime[0] = waitTime;
                     
                     cust.remove();
                  }
               }
               
               if(serviceAreaCustomer[1] == -1)
               {
                  if(!cust.isEmpty())
                  {
                     waitTime = timeToOrderAndBeServed();
                     int custToServe = cust.peek().getArrived();
                     serviceAreaCustomer[1] = custToServe;
                     serviceAreaWaitTime[1] = waitTime;
                     
                     cust.remove();
                  }
               }
               
               if(serviceAreaCustomer[2] == -1)
               {      
                  if(!cust.isEmpty())
                  {
                     waitTime = timeToOrderAndBeServed();
                     int custToServe = cust.peek().getArrived();
                     serviceAreaCustomer[2] = custToServe;
                     serviceAreaWaitTime[2] = waitTime;
                     
                     cust.remove();
                  }
               }
            }   
         }
 
         if((serviceAreaWaitTime[0] == 0) ||(serviceAreaWaitTime[1] == 0) || (serviceAreaWaitTime[2] == 0))
         {
            waitTime = timeToOrderAndBeServed();
            
            if(serviceAreaWaitTime[0] == 0)
            {
               thisCustomersTime = i - serviceAreaCustomer[0];
               outfile.println("Customer#" + serviceAreaCustomer[0] + " leaves and his total wait time is " + thisCustomersTime);
               serviceAreaCustomer[0] = -1;
               serviceAreaWaitTime[0] = -1;
               
               if(thisCustomersTime > longestWaitTime)
                  longestWaitTime = thisCustomersTime;
                  
               totalMinutes += thisCustomersTime;
            }
            
            if(serviceAreaWaitTime[1] == 0)
            {
               thisCustomersTime = i - serviceAreaCustomer[1];
               outfile.println("Customer#" + serviceAreaCustomer[1] + " leaves and his total wait time is " + thisCustomersTime);
               serviceAreaCustomer[1] = -1;
               serviceAreaWaitTime[1] = -1;
               
               if(thisCustomersTime > longestWaitTime)
                  longestWaitTime = thisCustomersTime;
                  
               totalMinutes += thisCustomersTime;
            }
            
            if(serviceAreaWaitTime[2] == 0)
            {
               thisCustomersTime = i - serviceAreaCustomer[2];
               outfile.println("Customer#" + serviceAreaCustomer[2] + " leaves and his total wait time is " + thisCustomersTime);
               serviceAreaCustomer[2] = -1;
               serviceAreaWaitTime[2] = -1;
               
               if(thisCustomersTime > longestWaitTime)
                  longestWaitTime = thisCustomersTime;
                  
               totalMinutes += thisCustomersTime;
            }
            
            waitTime = timeToOrderAndBeServed();
         }
         
         displayTimeAndQueue(cust, i);
         displayServiceAreas(serviceAreaCustomer, serviceAreaWaitTime);
         
         if(!((serviceAreaWaitTime[0] == 0) ||(serviceAreaWaitTime[1] == 0) || (serviceAreaWaitTime[2] == 0)))
         {                           
            if(serviceAreaWaitTime[0] != -1)
               serviceAreaWaitTime[0] = serviceAreaWaitTime[0] - 1;
            
            if(serviceAreaWaitTime[1] != -1)
               serviceAreaWaitTime[1] = serviceAreaWaitTime[1] - 1;
            
            if(serviceAreaWaitTime[2] != -1)
               serviceAreaWaitTime[2] = serviceAreaWaitTime[2] - 1;
            
            if(waitTime != 0)
               waitTime--;
         }
 
         int size = cust.size();
         
         if(size > longestQueue)
            longestQueue = size;
      }
      
      int k = TIME;
      
      int x = serviceAreaWaitTime[0] + serviceAreaWaitTime[1] + serviceAreaWaitTime[2];
      boolean timeCheck = false;
      
      if((!cust.isEmpty()) && (x != -3))
         timeCheck = true;

      while(timeCheck)
      {           
         if(((serviceAreaCustomer[0] == -1) || (serviceAreaCustomer[1] == -1) || (serviceAreaCustomer[2] == -1)))
         {
            if(serviceAreaCustomer[0] == -1)
            {
               if(!cust.isEmpty())
               {
                  waitTime = timeToOrderAndBeServed();
                  int custToServe = cust.peek().getArrived();
                  serviceAreaCustomer[0] = custToServe;
                  serviceAreaWaitTime[0] = waitTime;
                  
                  cust.remove();
               }
            }
            
            if(serviceAreaCustomer[1] == -1)
            {
               if(!cust.isEmpty())
               {
                  waitTime = timeToOrderAndBeServed();
                  int custToServe = cust.peek().getArrived();
                  serviceAreaCustomer[1] = custToServe;
                  serviceAreaWaitTime[1] = waitTime;
                  
                  cust.remove();
               }
            }
            
            if(serviceAreaCustomer[2] == -1)
            {      
               if(!cust.isEmpty())
               {
                  waitTime = timeToOrderAndBeServed();
                  int custToServe = cust.peek().getArrived();
                  serviceAreaCustomer[2] = custToServe;
                  serviceAreaWaitTime[2] = waitTime;
                  
                  cust.remove();
               }
            }
         }
         
         if((serviceAreaWaitTime[0] == 0) ||(serviceAreaWaitTime[1] == 0) || (serviceAreaWaitTime[2] == 0))
         {
            waitTime = timeToOrderAndBeServed();
            
            if(serviceAreaWaitTime[0] == 0)
            {
               thisCustomersTime = k - serviceAreaCustomer[0];
               outfile.println("Customer#" + serviceAreaCustomer[0] + " leaves and his total wait time is " + thisCustomersTime);
               serviceAreaCustomer[0] = -1;
               serviceAreaWaitTime[0] = -1;
               
               if(thisCustomersTime > longestWaitTime)
                  longestWaitTime = thisCustomersTime;
                  
               totalMinutes += thisCustomersTime;
            }
            
            if(serviceAreaWaitTime[1] == 0)
            {
               thisCustomersTime = k - serviceAreaCustomer[1];
               outfile.println("Customer#" + serviceAreaCustomer[1] + " leaves and his total wait time is " + thisCustomersTime);
               serviceAreaCustomer[1] = -1;
               serviceAreaWaitTime[1] = -1;
               
               if(thisCustomersTime > longestWaitTime)
                  longestWaitTime = thisCustomersTime;
                  
               totalMinutes += thisCustomersTime;
            }
            
            if(serviceAreaWaitTime[2] == 0)
            {
               thisCustomersTime = k - serviceAreaCustomer[2];
               outfile.println("Customer#" + serviceAreaCustomer[2] + " leaves and his total wait time is " + thisCustomersTime);
               serviceAreaCustomer[2] = -1;
               serviceAreaWaitTime[2] = -1;
               
               if(thisCustomersTime > longestWaitTime)
                  longestWaitTime = thisCustomersTime;
                  
               totalMinutes += thisCustomersTime;
            }
            
            waitTime = timeToOrderAndBeServed();
         }
         
         displayTimeAndQueue(cust, k);
         displayServiceAreas(serviceAreaCustomer, serviceAreaWaitTime);
                                   
         if(serviceAreaWaitTime[0] != -1)
            serviceAreaWaitTime[0] = serviceAreaWaitTime[0] - 1;
         
         if(serviceAreaWaitTime[1] != -1)
            serviceAreaWaitTime[1] = serviceAreaWaitTime[1] - 1;
         
         if(serviceAreaWaitTime[2] != -1)
            serviceAreaWaitTime[2] = serviceAreaWaitTime[2] - 1;
         
         if(waitTime != 0)
            waitTime--;
 
         int size = cust.size();
         
         if(size > longestQueue)
            longestQueue = size;
         
         k++;
         
         int y = serviceAreaWaitTime[0] + serviceAreaWaitTime[1] + serviceAreaWaitTime[2];
         
         if(!cust.isEmpty() || y != -3)
            timeCheck = true;
         
         else
            timeCheck = false;
      }
                  
      /*   report the data to the screen    */  
      
      System.out.println("1 queue, 3 service windows, probability of arrival = "+ CHANCE_OF_CUSTOMER);
      System.out.println("Total customers served = " + getCustomers());
      System.out.println("Average wait time = " + calculateAverage());
      System.out.println("Longest wait time = " + longestWaitTime);
      System.out.println("Longest queue = " + longestQueue);
      
      outfile.println("");
      outfile.println("Total customers served = " + getCustomers());
      outfile.println("Average wait time = " + calculateAverage());
      outfile.println("Longest wait time = " + longestWaitTime);
      outfile.println("Longest queue = " + longestQueue);
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