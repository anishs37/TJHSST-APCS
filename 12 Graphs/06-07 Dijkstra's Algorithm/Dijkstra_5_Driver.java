//driver for Graph 6, using AdjListWeighted   
import java.util.*;
import java.io.*;
public class Dijkstra_5_Driver
{
   public static void main(String[] args) throws FileNotFoundException 
   {
      testStart2("A");
   }
   
   public static void testStart(String start)
   {
      AdjListWeighted graph = new AdjListWeighted();
      graph.addVertex("A");
      graph.addVertex("B");
      graph.addVertex("C");
      graph.addVertex("D"); 
      graph.addEdge("A","B", 9); 
      graph.addEdge("A","C", 3); 
      graph.addEdge("C","B", 5); 
      graph.addEdge("C","D", 2);
      graph.addEdge("D","B", 1);    	
      graph.minimumWeightPath(start);   //runs Dijkstra's Algorithm
      for (wVertex v : graph.getVertices()) //prints all the distances from the source
      {
         System.out.println("Distance to " + v.getName() + ": " + v.getMinDistance());
      }
   }  
}

/***********************************

 Enter start: A
 Distance to A: 0.0
 Distance to B: 6.0
 Distance to C: 3.0
 Distance to D: 5.0
 Enter end: B
 From A to B: 6.0
 Enter end: D
 From A to D: 5.0
 Enter end: -1
 
 ******************************/