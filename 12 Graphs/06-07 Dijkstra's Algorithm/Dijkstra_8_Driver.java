//driver for Graph 6, using AdjListWeighted   
import java.util.*;
import java.io.*;
public class Dijkstra_8_Driver
{
   public static void main(String[] args) throws FileNotFoundException 
   {
      String str = testDistances("Peoria");
      System.out.println(str);
   }
   
   public static void testPath(String start) throws FileNotFoundException
   {
      AdjListWeighted g = new AdjListWeighted();
      g = g.graphFromEdgeListData(new File("cities.txt"), new File("cityEdgeListWeighted.txt"));
      g.minimumWeightPath(start);  //runs Dijkstra's Algorithm
      for (wVertex v : g.getVertices()) //prints the distances and path from source
      {
         List<String> path = g.getShortestPathTo(v);
         System.out.println(path);
      }
   }
   
   public static String testDistances(String start) throws FileNotFoundException
   {
      AdjListWeighted g = new AdjListWeighted();
      g = g.graphFromEdgeListData(new File("cities.txt"), new File("cityEdgeListWeighted.txt"));
      g.minimumWeightPath(start);  //runs Dijkstra's Algorithm
      String strToRet = "";
      for (wVertex v : g.getVertices()) //prints the distances and path from source
      {
         strToRet += v.getName() + ": " + v.getMinDistance() + " ";
      }
      
      return strToRet;
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