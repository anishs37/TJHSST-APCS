import java.util.*;
import java.io.*;
public class AdjList_4_Driver
{
   public static void main( String[] args) 
   {
      depthFirstExt();
   }
   
   public static String depthFirst(String str)
   {
      AdjList g = new AdjList();
      g.addVertex("A");      //if it's not there, add it.
      g.addVertex("B");
      g.addEdge("A", "C"); // <-- warning!  Be sure to add all the Vertices first, 
                           // or else deal with it. 
      g.addVertex("C");
      g.addVertex("D");
      g.addEdge("B", "A");
      g.addEdge("C", "C");
      g.addEdge("C", "D");
      g.addEdge("D", "C");
      g.addEdge("D", "A");
      
      String toRet = g.depthFirstSearch(str) + "";
      return toRet;
   }
   
   public static String breadthFirst(String str)
   {
      AdjList g = new AdjList();
      g.addVertex("A");      //if it's not there, add it.
      g.addVertex("B");
      g.addEdge("A", "C"); // <-- warning!  Be sure to add all the Vertices first, 
                           // or else deal with it. 
      g.addVertex("C");
      g.addVertex("D");
      g.addEdge("B", "A");
      g.addEdge("C", "C");
      g.addEdge("C", "D");
      g.addEdge("D", "C");
      g.addEdge("D", "A");
      
      String toRet = g.breadthFirstSearch(str) + "";
      return toRet;
   }
   
   public static void depthFirstExt()
   {
      AdjList g = new AdjList();
      g.addVertex("A");      //if it's not there, add it.
      g.addVertex("B");
      g.addEdge("A", "C"); // <-- warning!  Be sure to add all the Vertices first, 
                           // or else deal with it. 
      g.addVertex("C");
      g.addVertex("D");
      g.addEdge("B", "A");
      g.addEdge("C", "C");
      g.addEdge("C", "D");
      g.addEdge("D", "C");
      g.addEdge("D", "A");
      
      for (String name : g.getVertexMap().keySet() )
         System.out.println (name + " ---> " + g.depthFirstRecur(name) );
   }
}

/********************************

 Edge List Representation! 
 A [C]
 B [A]
 C [C D]
 D [C A]
 
 Depth First Search
 	Enter source: A
 				[A [C], C [C D], D [C A]]
 	Enter source: D
 				[D [C A], A [C], C [C D]]
 	Enter source: -1
 
 Breadth First Search
 	Enter source: A
 				[A [C], C [C D], D [C A]]
 	Enter source: D
 				[D [C A], C [C D], A [C]]
 	Enter source: -1
 
******************************/