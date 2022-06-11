//mlbillington@fcps.edu       July 2014
//lesson:  Graphs3: EdgeList
//uses AdjList

import java.util.*;
import java.io.*;
 
public class AdjList_3_Driver
{  
   public static void main(String[] args)throws FileNotFoundException
   {
      System.out.println("Edge List Representation! ");
      System.out.println("Test the Vertex class");
      Vertex a = new Vertex("alpha");
      Vertex b = new Vertex("beta");
      a.addAdjacent(b);
      b.addAdjacent(a);
      System.out.println("get the names:\n  " + a.getName() + "\n  " + b.getName() );
      System.out.println("get the list of adjacencies: \n  " + a.getAdjacencies() +"\n  " + b.getAdjacencies());
      System.out.println("toString() shows the names plus the name of the neighbor(s): \n  " + a.toString() +"\n  " + b.toString());
     
      System.out.println("\nTest the AdjList class");
      AdjList g = new AdjList();
      g.addVertex("A");      //if it's not in the graph, add it.
      g.addVertex("B");
      System.out.println("list of vertices in the graph:  " + g.getVertices());
      System.out.println("  map string to index:  " + g.getVertexMap());  
      System.out.println("  get vertex by index 1:  " + g.getVertex(1).toString());  
      System.out.println("  get vertex by name \"B\":  " + g.getVertex("B").toString());
      System.out.println("the whole graph:\n" + g.toString());
      
      g.addEdge("A", "C"); // <-- warning!  Be sure to add all the Vertices first; 
                           // or else deal with it. 
      g.addVertex("C");
      g.addVertex("D");
      g.addEdge("B", "A");  // <-- warning! Do not add a new Vertex("A").  You must get
                           // the old Vertex B and addAdjacent() the old Vertex A.
      g.addEdge("C", "C");
      g.addEdge("C", "D");
      g.addEdge("D", "C");
      g.addEdge("D", "A");
      System.out.println("\nlist of vertices in the graph:  " + g.getVertices());
      System.out.println("  map string to index:  " + g.getVertexMap());  
      System.out.println("  get vertex by index:  " + g.getVertex(1));  
      System.out.println("  get vertex by name:  " + g.getVertex("B").toString());  
      System.out.println("the whole graph:\n"+g.toString());       
   }
}

/***************************
 Edge List Representation! 
 Test the Vertex class
 get the names:
   alpha
   beta
 get the list of adjacencies: 
   [beta [alpha]]
   [alpha [beta]]
 toString() shows the names plus the name of the neighbor(s): 
   alpha [beta]
   beta [alpha]
 
 Test the AdjList class
 list of vertices in the graph:  [A [], B []]
   map string to index:  {A=0, B=1}
   get vertex by index 1:  B []
   get vertex by name "B":  B []
 the whole graph:
 A []
 B []
 
 list of vertices in the graph:  [A [C], B [A], C [C D], D [C A]]
   map string to index:  {A=0, B=1, C=2, D=3}
   get vertex by index:  B [A]
   get vertex by name:  B [A]
 the whole graph:
 A [C]
 B [A]
 C [C D]
 D [C A]
     ************************/ 
