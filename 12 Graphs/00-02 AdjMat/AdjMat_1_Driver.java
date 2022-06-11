//mlbillington@fcps.edu, May 2014
//graph manipulation, lesson #0

import java.util.*;
import java.io.*;
public class AdjMat_1_Driver
{
   public static void main(String[] args)
   {
      mainCaller(5, -1, 1, 1, -1, -1, 1, 4, 3, 2, 1);
      mainCaller(5, -1, 1, 1, 1, -1, 1, 4, 3, 2, 1);
   }
   
   public static void mainCaller(int size, int action1, int action2, int action3, int action4, int action5, int action6, int source1, int target1, int source2, int target2)
   {
      AdjMat g = new AdjMat(size);
      
      if(action2 != -1)
         addEdge(source1, target1, g);
      
      if(action3 != -1)
         addEdge(source2, target2, g);
         
      if(action4 != -1)
         removeEdge(source1, target1, g);  
      
      if(action1 != -1)
         toStringCall(size, g);
         
      if(action5 != -1)
         isEdge(source1, target1, g);
      
      if(action6 != -1)
         numEdges(g);
   }
   
   public static void toStringCall(int size, AdjMat g)
   {
      System.out.println(g.toString());
   }
   
   public static void addEdge(int source, int target, AdjMat g)
   {
      g.addEdge(source, target);
   }
   
   public static void removeEdge(int source, int target, AdjMat g)
   {
      g.removeEdge(source, target);
   }
   
   public static void isEdge(int source, int target, AdjMat g)
   {
      System.out.println(g.isEdge(source, target) + "");
   }
   
   public static void numEdges(AdjMat g)
   {
      System.out.println(g.edgeCount());
   }
}