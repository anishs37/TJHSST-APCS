//driver for Graph 7 using AdjListWeighted  
// read from the given data file  
//               prints cities and paths   
import java.util.*;
import java.io.*;
public class Dijkstra_7_Driver
{
   public static void main(String[] args) throws FileNotFoundException 
   {   
      AdjListWeighted g = new AdjListWeighted();
      g = g.graphFromEdgeListData(new File("cities.txt"), new File("cityEdgeListWeighted.txt"));
      Scanner key = new Scanner(System.in);      
      System.out.print("Enter start: " );
      String source = key.next(); 
      g.minimumWeightPath(source);  //runs Dijkstra's Algorithm
      for (wVertex v : g.getVertices()) //prints the distances and path from source
      {
         System.out.println("Distance to " + v.getName() + ": " + v.getMinDistance());
         List<String> path = g.getShortestPathTo(v);
         System.out.println("                   Path: " + path);
      }
      
      while(true)
      {
         System.out.print("Enter end: " );
         String end = key.next();
         if(end.equals("-1"))
            break;
         System.out.println( "From " + source + " to "+ end+ ": "+ g.getVertex(end).getMinDistance() );
         System.out.println( "    Shortest path is "+ g.getShortestPathTo( g.getVertex(end)) );
      }
   
   }
}
/**************************************************
 Enter start: Peoria
 Distance to Pendleton: 8.0
                    Path: [Peoria, Pueblo, Pierre, Pendleton]
 Distance to Pensacola: 9.0
                    Path: [Peoria, Pittsburgh, Pensacola]
 Distance to Peoria: 0.0
                    Path: [Peoria]
 Distance to Phoenix: 14.0
                    Path: [Peoria, Pittsburgh, Pensacola, Phoenix]
 Distance to Pierre: 6.0
                    Path: [Peoria, Pueblo, Pierre]
 Distance to Pittsburgh: 5.0
                    Path: [Peoria, Pittsburgh]
 Distance to Princeton: Infinity
                    Path: [Princeton]
 Distance to Pueblo: 3.0
                    Path: [Peoria, Pueblo]
 Enter end: Pittsburgh
 From Peoria to Pittsburgh: 5.0
    Shortest path is [Peoria, Pittsburgh]
Enter end: Pueblo
From Peoria to Pueblo: 3.0
    Shortest path is [Peoria, Pueblo]
Enter end: -1
                    
********************************************/