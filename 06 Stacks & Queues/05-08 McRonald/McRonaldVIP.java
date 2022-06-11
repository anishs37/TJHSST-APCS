//Name: J2-24
//Date: 1/18/21

import java.util.*;
import java.io.*;
public class McRonaldVIP
{
   public static final int TIME = 1080;  //18 hrs * 60 min
   public static double CHANCE_OF_CUSTOMER = .2;
   public static double CHANCE_OF_VIP = .02;
   public static int customers = 0;
   public static int totalMinutes = 0;
   public static int longestWaitTime = 0;
   public static int longestQueue = 0;
   public static int serviceWindow = 0;      // to serve the front of the queue
   public static int thisCustomersTime;
   public static int vipTime = 0;
   public static int vips = 0;
   public static PrintWriter outfile = null; // file to display the queue information
      
   public static int timeToOrderAndBeServed()
   {
      return (int)(Math.random() * 6 + 2);
   }
  
   public static void displayTimeAndQueue(Queue<Customer> vip, int min, Queue<Customer> q)
   {
      if(vip.size() == 0)
         outfile.println("VIP " + min + ": " + vip);
      
      else
      {
         Queue<Customer> vipCopy = new LinkedList<>();
         int vipSize = vip.size();
         
         Customer vipRemoved = vip.remove();
         
         while(!vip.isEmpty())
            vipCopy.add(vip.remove());
            
         outfile.println("VIP " + min + ": " + vipCopy);
         
         vip.add(vipRemoved);
         
         for(int i = 0; i < vipSize - 1; i++)
            vip.add(vipCopy.remove());       
      }
      
      if(q.size() == 0)
         outfile.println("\t" + min + ": " + q);
      
      else
      {
         Queue<Customer> qCopy = new LinkedList<>();
         int qSize = q.size();
         
         Customer removed = q.remove();
         
         while(!q.isEmpty())
            qCopy.add(q.remove());
            
         outfile.println("\t" + min + ": " + qCopy);
         
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
   public static double calculateAverageVIP()
   {
      return (int)(1.0 * vipTime / vips * 10) / 10.0;
   }
     
   public static void main(String[] args)
   {     
    //set up file     
      try
      {
         outfile = new PrintWriter(new FileWriter("McRonaldVIP 2 Queues 1 ServiceArea.txt"));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
      
      outfile.println("Mcronald(regular customer arrival chance is 0.2, vip arrival chance is 0.02) with 2 queues / 1 service areas");
   
      mcRonaldVIP(TIME, outfile);  //call the simulation

      outfile.close();
   }
   
   public static void mcRonaldVIP(int TIME, PrintWriter outfile)   //2 queues, 1 service area
   {
      int waitTime = timeToOrderAndBeServed();          //wait time between 2 to 7 mins
      
      Queue<Customer> cust = new LinkedList<>();
      Queue<Customer> vipQueue = new LinkedList<>();
      
      int[] custWaitTime = new int[1];
      int[] vipWaitTime = new int[1];
      
      custWaitTime[0] = -1;
      vipWaitTime[0] = -1;
      
      for(int i = 0; i < TIME; i++)
      {
         String str = "";
         
         double chance = Math.random();
         
         if(chance < CHANCE_OF_CUSTOMER)
         {
            cust.add(new Customer(i));  
            customers++;
            
            outfile.println("new customer min#" + i);
            
            custWaitTime[0] = waitTime;
         }
         
         else if((chance > 0.2) && (chance < 0.22))
         {
            vipQueue.add(new Customer(i));
            customers++;
            vips++;
            
            outfile.println("new VIP customer min#" + i);
            
            vipWaitTime[0] = waitTime;
         }
         
         if((custWaitTime[0] == 0) || (vipWaitTime[0] == 0))
         {
            if(vipWaitTime[0] == 0)
            {
               int numRemove = vipQueue.peek().getArrived();
               thisCustomersTime = i - vipQueue.remove().getArrived();
               outfile.println("Customer#" + numRemove + " leaves and his total wait time is " + thisCustomersTime);
               
               if(thisCustomersTime > longestWaitTime)
                  longestWaitTime = thisCustomersTime;
               
               waitTime = timeToOrderAndBeServed();
               vipTime += thisCustomersTime;
               
               vipWaitTime[0] = -1;
            }
            
            if(custWaitTime[0] == 0)
            {
               int custRemove = cust.peek().getArrived();
               thisCustomersTime = i - cust.remove().getArrived();
               outfile.println("Customer#" + custRemove + " leaves and his total wait time is " + thisCustomersTime);
               
               if(thisCustomersTime > longestWaitTime)
                  longestWaitTime = thisCustomersTime;
               
               waitTime = timeToOrderAndBeServed();
               totalMinutes += thisCustomersTime;
               
               custWaitTime[0] = -1;
            }
            
            if(!vipQueue.isEmpty())
            { 
               int numPeek = vipQueue.peek().getArrived();
               str = "\t" + "ServiceArea#1 " + numPeek + ":" + waitTime;   
               waitTime--;
               
               vipWaitTime[0] = waitTime;
            }
            
            else if(!cust.isEmpty())
            {
               int numPeek = cust.peek().getArrived();
               str = "\t" + "ServiceArea#1 " + numPeek + ":" + waitTime;
               waitTime--;
               
               custWaitTime[0] = waitTime;
            }
            
            else
               str = "\t" + "ServiceArea#1 -1:-1";
         }
         
         else
         {
            if(vipWaitTime[0] != -1)
            {
               int num = vipQueue.peek().getArrived();
               str = "\t" + "ServiceArea#1 " + num + ":" + waitTime;
               waitTime--;
               
               vipWaitTime[0] = vipWaitTime[0] -1;
            }
                     
            else if(custWaitTime[0] != -1)
            {
               int num = cust.peek().getArrived();
               str = "\t" + "ServiceArea#1 " + num + ":" + waitTime;
               waitTime--;
               
               custWaitTime[0] = custWaitTime[0] -1;
            }
            
            else
               str = "\t" + "ServiceArea#1 -1:-1";
         }
 
         int size = cust.size();
         int vipSize = vipQueue.size();
         
         if(size > longestQueue)
            longestQueue = size;
         
         if(vipSize > longestQueue)
            longestQueue = vipSize;
            
         displayTimeAndQueue(vipQueue, i, cust);
         displayServiceArea(str);
      }
      
      int k = TIME;
 
      if((!cust.isEmpty()) || (!vipQueue.isEmpty()))
      {
         do
         {
            String str = "";
            
            if((custWaitTime[0] == 0) || (vipWaitTime[0] == 0))
            {
               if(vipWaitTime[0] == 0)
               {
                  int numRemove = vipQueue.peek().getArrived();
                  thisCustomersTime = k - vipQueue.remove().getArrived();
                  outfile.println("Customer#" + numRemove + " leaves and his total wait time is " + thisCustomersTime);
                  
                  if(thisCustomersTime > longestWaitTime)
                     longestWaitTime = thisCustomersTime;
                  
                  waitTime = timeToOrderAndBeServed();
                  vipTime += thisCustomersTime;
                  
                  vipWaitTime[0] = -1;
               }
               
               if(custWaitTime[0] == 0)
               {
                  int custRemove = cust.peek().getArrived();
                  thisCustomersTime = k - cust.remove().getArrived();
                  outfile.println("Customer#" + custRemove + " leaves and his total wait time is " + thisCustomersTime);
                  
                  if(thisCustomersTime > longestWaitTime)
                     longestWaitTime = thisCustomersTime;
                  
                  waitTime = timeToOrderAndBeServed();
                  totalMinutes += thisCustomersTime;
                  
                  custWaitTime[0] = -1;
               }
               
               if(!vipQueue.isEmpty())
               { 
                  int numPeek = vipQueue.peek().getArrived();
                  str = "\t" + "ServiceArea#1 " + numPeek + ":" + waitTime;   
                  waitTime--;
                  
                  vipWaitTime[0] = waitTime;
               }
               
               else if(!cust.isEmpty())
               {
                  int numPeek = cust.peek().getArrived();
                  str = "\t" + "ServiceArea#1 " + numPeek + ":" + waitTime;
                  waitTime--;
                  
                  custWaitTime[0] = waitTime;
               }
            }
         
            else
            {
               if(vipWaitTime[0] != -1)
               {
                  int num = vipQueue.peek().getArrived();
                  str = "\t" + "ServiceArea#1 " + num + ":" + waitTime;
                  waitTime--;
                  
                  vipWaitTime[0] = vipWaitTime[0] -1;
               }
                        
               else if(custWaitTime[0] != -1)
               {
                  int num = cust.peek().getArrived();
                  str = "\t" + "ServiceArea#1 " + num + ":" + waitTime;
                  waitTime--;
                  
                  custWaitTime[0] = custWaitTime[0] -1;
               }
            }
 
            int size = cust.size();
            int vipSize = vipQueue.size();
            
            if(size > longestQueue)
               longestQueue = size;
            
            if(vipSize > longestQueue)
               longestQueue = vipSize;
               
            displayTimeAndQueue(vipQueue, k, cust);
            displayServiceArea(str);
            
            k++;
         }
         
         while((!cust.isEmpty()) || (!vipQueue.isEmpty()));
         
         outfile.println("\t" + "ServiceArea#1 -1:-1");
         outfile.println("");   
      }                
               
      /*   display all cumulative variables  */
      System.out.println("1 queue plus VIP queue, 1 service window");
      System.out.println("probability of arrival = "+ CHANCE_OF_CUSTOMER);
      System.out.println("probability of VIP = "+ CHANCE_OF_VIP);
      System.out.println("Total customers served = " + customers);
      System.out.println("Total VIPs served = " + vips);
      System.out.println("Average wait time = " + calculateAverage());
      System.out.println("Average VIP wait time = " + calculateAverageVIP());
      System.out.println("Longest wait time = " + getLongestWaitTime());
      System.out.println("Longest queue = " + getLongestQueue());	
      
      outfile.println("1 queue plus VIP queue, 1 service window");
      outfile.println("probability of arrival = "+ CHANCE_OF_CUSTOMER);
      outfile.println("probability of VIP = "+ CHANCE_OF_VIP);
      outfile.println("Total customers served = " + customers);
      outfile.println("Total VIPs served = " + vips);
      outfile.println("Average wait time = " + calculateAverage());
      outfile.println("Average VIP wait time = " + calculateAverageVIP());
      outfile.println("Longest wait time = " + getLongestWaitTime());
      outfile.println("Longest queue = " + getLongestQueue());	
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