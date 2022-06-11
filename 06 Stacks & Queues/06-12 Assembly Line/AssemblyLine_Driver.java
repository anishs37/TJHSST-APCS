//Name: J2-24      
//Date: 1/20/2021

/*NOTE: Some of the comment indentation may be a little off in CodePost. I do not
  know the reason for this.
*/

import java.util.*;

public class AssemblyLine_Driver
{
   static int NDISKS = 50;
   static int MAXRADIUS = 100;
   public static void main(String[] args)
   {
      AssemblyLine a = new AssemblyLine(NDISKS, MAXRADIUS);
      a.showInput();
      a.process();
      a.showOutput();
   }
}

class AssemblyLine
{
   private Queue<Disk> assemblyLineIn;
   private Queue<Pyramid> assemblyLineOut;
   private Pyramid robotArm;
   
	/**
		* initializes this object so the assemblyLineIn contains 
		* nDisks with random radii;  assemblyLineOut is initialized to * an empty Queue; robotArm is initialized to an empty Pyramid.
   **/
      
	//Part A
   public AssemblyLine( int nDisks, int maxRadius )
   {
      assemblyLineIn = new LinkedList<Disk>();
      
      for(int i = 0; i < nDisks; i++)
      {
         int radius = ((int) (Math.random() * maxRadius)) + 1;
         assemblyLineIn.add(new Disk(radius));
      }
      
      assemblyLineOut = new LinkedList<Pyramid>();
      robotArm = new Pyramid();
   }

	/**
		* "flips over" the pyramid in the robotArm and adds it to the
		* assemblyLineOut queue.
		* Precondition:  robotArm is not empty and holds an inverted 
		*				pyramid of disks
	**/
      
	// Part B
   private void unloadRobot()
   { 
      Pyramid pyr = new Pyramid();
      
      while(!robotArm.isEmpty())
         pyr.push(robotArm.pop());

      assemblyLineOut.add(pyr);
   }

	/**
		* processes all disks from assemblyLineIn; a disk is processed
		* as follows:  if robotArm is not empty and the next disk does
		* not fit on top of robotArm (which must be an inverted 
		* pyramid) then robotArm is unloaded first; the disk from
		* assemblyLineIn is added to robotArm; when all the disks
		* have been retrieved from assemblyLineIn, robotArm is unloaded.
		*  Precondition:  robotArm is empty;
		*				assemblyLineOut is empty
	**/
      
	//Part C
   public void process()
   {
      while(!assemblyLineIn.isEmpty())
      {
         Disk toInsert = assemblyLineIn.remove();
         int l = 0;
         
         if(robotArm.isEmpty())
         {
            l = 1;
            robotArm.push(toInsert);
         }
           
         Disk robotPeek = robotArm.peek();

         if((!robotArm.isEmpty()) && (l == 0))
         {
            if(robotPeek.compareTo(toInsert) > 0)
            {
               unloadRobot();
               robotArm.push(toInsert);
            }
            
            else if(robotPeek.compareTo(toInsert) < 0)
               robotArm.push(toInsert);
         } 
      }   
         
      unloadRobot();
   }
   
   public void showInput()
   {
      System.out.println(assemblyLineIn);
   }
   
   public void showOutput()
   {
      System.out.println(assemblyLineOut);
   }
}

//Disk is standard and straightforward.
class Disk implements Comparable<Disk>
{
   private int myRadius;
   
   public Disk(int radius)
   {
      myRadius = radius;
   }
   
   public int getRadius()
   {
      return myRadius;
   }
   
   public void setRadius(int radius)
   {
      myRadius = radius;
   }
   
   public int compareTo(Disk disk)
   {
      if(myRadius >= disk.getRadius())
         return 1;
      
      else
         return -1;
   }
   
   public String toString()
   {
      String str = "" + myRadius;
      return str;
   }
}

//Pyramid is sly.
class Pyramid extends Stack<Disk>
{
   public Pyramid()
   {
  	   super();
   }
}