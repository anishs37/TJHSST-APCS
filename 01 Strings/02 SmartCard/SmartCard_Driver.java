// Name: Anish Susarla
// Date: 9/12/2020
 
import java.text.DecimalFormat;
public class SmartCard_Driver
{
   public static void main(String[] args) 
   {
      Station downtown = new Station("Downtown", 1);
      Station center = new Station("Center City", 1);
      Station uptown = new Station("Uptown", 2);
      Station suburbia = new Station("Suburb", 4);
   
      SmartCard jimmy = new SmartCard(20.00); 
      jimmy.board(center);                    //Boarded at Center City.  SmartCard has $20.00
      jimmy.disembark(suburbia);              //From Center City to Suburb costs $2.75.  SmartCard has $17.25
      jimmy.disembark(uptown);				//Error:  did not board?!
      System.out.println();
   			
      SmartCard susie = new SmartCard(1.00); 
      susie.board(uptown);            		//Boarded at Uptown.  SmartCard has $1.00
      susie.disembark(suburbia);				//Insuffient funds to exit. Please add more money.
      System.out.println();
   
      SmartCard kim = new SmartCard(.25);    
      kim.board(uptown);            		    //Insuffient funds to board. Please add more money.
      System.out.println();
   
      SmartCard b = new SmartCard(10.00);   
      b.board(center);            		    //Boarded at Center City.  SmartCard has $10.00
      b.disembark(downtown);					//From Center City to Downtown costs $0.50.  SmartCard has $9.50
      System.out.println();
        
      SmartCard mc = new SmartCard(10.00);  
      mc.board(suburbia);            		    //Boarded at Suburbia.  SmartCard has $10.00
      mc.disembark(downtown);					//From Suburbia to Downtown costs $2.75.  SmartCard has $7.25
      System.out.println();
    
      SmartCard test1 = new SmartCard(10.00);
      test1.board(center);
      test1.disembark(center);
      System.out.println();
   }
} 	

//Note SmartCard is not denoted as public.  Why?
class SmartCard 
{
   public final static DecimalFormat df = new DecimalFormat("$0.00");
   public final static double MIN_FARE = 0.5;
   //s = null;
   
   private Station boardStation;
   private boolean hasBoarded;
   private double money; 
   
   
   //the next 3 methods are for use ONLY by Grade-It
   //these accessor methods only return your private data
   //they do not make any changes to your data
   
   public SmartCard(double d)
   {
      money = d;
      hasBoarded = false;
   }
   
   public void addMoney(double d)
   {
      money += d;
      System.out.println("New Balance: " + df.format(money));
   }
   
   public String getBalance()
   {
      return df.format(money);
   }
   
   public boolean getIsOnBoard()
   {
      return hasBoarded;
   }
   
   public double getMoneyRemaining()
   {
     return money;
   }
   
   Station getBoardedAt()
   {
      return boardStation;   
   }
  
   /*boolean getIsOnBoard()
   {
      return false;
   }*/
   
   public void board(Station s)
   {
      if(hasBoarded == true)
      {
         System.out.println("Error: already boarded");
      }
      
      else
      {
         if(money < 0.50)
         {
            System.out.println("Insuffient funds to board. Please add more money.");
         }
         
         else
         {
            hasBoarded = true;
            boardStation = s;
            System.out.println("Boarded at " + boardStation.getName() + ". SmartCard has " + df.format(money));
         }
      }
   }
   
   public double cost(Station s)
   {
      if(hasBoarded == false)
      {
         return 0;
      }
      
      //else
      //{
         double needed_cost = 0.50;
         //Object empty = null;
         int zonesInBetween = boardStation.getZone() - s.getZone();
         int zonesInBetween_abs = Math.abs(zonesInBetween);
         needed_cost += (0.75 * zonesInBetween_abs);
         
            return needed_cost;
      //}
   }
   
   public void disembark(Station s)
   {
      if(hasBoarded == false)
      {
         System.out.println("Error: did not board?!");
      }
      
      else
      {
         double costs = this.cost(s);
         double negative = money - cost(s);
         
         if(negative < 0)
         {
            System.out.println("Insuffient funds to exit. Please add more money.");
         }
         
         else
         {
            money -= this.cost(s);
            hasBoarded = false;
            
            /*if (costs == 0.50)
            {
               System.out.println(" ");
            }
            
            else
            {*/
               System.out.println("From " + boardStation.getName() + " to " + s.getName() + " costs " + df.format(costs) + ".  SmartCard has " + df.format(money));
               boardStation = null;      
            //}
         }
      }
   }
}
   
//Note Station is not a public class.  Why?
class Station
{
     private String station_name;
     private int zone;
     
     public Station(String n, int z)
     {
         station_name = n;
         zone = z;
     }
     
     public String getName()
     {
         return station_name;
     }
     
     public String setName()
     {
         return null;
     }
     public int getZone()
     {
         return zone;
     }
     
     public String toString()
     {
         return station_name;
     }
}

 /*******************  Sample Run ************

 Boarded at Center City.  SmartCard has $20.00
 From Center City to Suburb costs $2.75.  SmartCard has $17.25
 Error: did not board?!
 
 Boarded at Uptown.  SmartCard has $1.00
 Insufficient funds to exit. Please add more money.
 
 Insufficient funds to board. Please add more money.
 
 Boarded at Center City.  SmartCard has $10.00
 From Center City to Downtown costs $0.50.  SmartCard has $9.50
 
 Boarded at Suburb.  SmartCard has $10.00
 From Suburb to Downtown costs $2.75.  SmartCard has $7.25
 
 ************************************************/