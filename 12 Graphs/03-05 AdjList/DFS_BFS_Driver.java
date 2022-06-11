//mlbillington@fcps.edu      June 2014
//lesson:  Graphs4: DFS_BFS
//uses AdjList

import java.util.*;
import java.io.*;
 
public class DFS_BFS_Driver
{  
   public static void main(String[] args)throws FileNotFoundException
   {
      System.out.println("Edge List Representation! ");
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
      System.out.println(g.toString());      //let's look at it first
   	    
      System.out.println("\nDepth First Search");
      for (String name : g.getVertexMap().keySet() )
         System.out.println( name + " ---> " + g.depthFirstSearch(name) );
         
      System.out.println("\nBreadth First Search");
      for (String name : g.getVertexMap().keySet() )
         System.out.println( name + " ---> " + g.breadthFirstSearch(name) );
      
      //use the debugger to see the difference:
      System.out.println();  
      System.out.println( "D" + " ---> " + g.depthFirstSearch("D") );
      System.out.println( "D" + " ---> " + g.breadthFirstSearch("D") );
      
      /*  Extension  */   
      System.out.println("\nDepth First Search (Recursive)");
      for (String name : g.getVertexMap().keySet() )
         System.out.println ( name + " ---> " + g.depthFirstRecur(name) );
      
      System.out.println("");
      
      System.out.println ( "D ---> " + g.depthFirstRecur("D") );
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