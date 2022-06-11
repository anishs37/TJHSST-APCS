//driver for Graph 6, using AdjListWeighted   
import java.util.*;
import java.io.*;
public class Dijkstra_6_Driver
{
   public static void main(String[] args) throws FileNotFoundException 
   {
   /* Graphs 6 Dijkstra:  hard-coded A-B-C-D   	*/
   
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
      Scanner key = new Scanner(System.in);
      System.out.print("Enter start: " );
      String source = key.next(); 
      graph.minimumWeightPath(source);   //runs Dijkstra's Algorithm
      for (wVertex v : graph.getVertices()) //prints all the distances from the source
      {
         System.out.println("Distance to " + v.getName() + ": " + v.getMinDistance());
      }
      while(true)
      {
         System.out.print("Enter end: " );
         String end = key.next();
         if(end.equals("-1"))
            break;
         System.out.println( "From " + source + " to "+ end+ ": "+graph.getVertex(end).getMinDistance() );
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