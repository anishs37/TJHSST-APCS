//mlbillington@fcps.edu, May 2012, June 2014
// Graphs 2,  uses AdjMat

import java.util.*;
import java.io.*;
public class AdjList_2_Driver
{
   public static void main( String[] args) 
   {
      String str = adjListToString3();
      System.out.print(str);
   }
   
   public static void getNames()
   {
      Vertex a = new Vertex("Amy");
      Vertex b = new Vertex("Bob");
      Vertex c = new Vertex("Carla");
      a.addAdjacent(b);
      b.addAdjacent(a);
      c.addAdjacent(a);
      c.addAdjacent(b);
      a.addAdjacent(c);
      b.addAdjacent(c);
      
      System.out.println( a.getName() + "\n" + b.getName() + "\n" + c.getName());
   }
   
   public static void getAdjacencies()
   {
      Vertex a = new Vertex("Amy");
      Vertex b = new Vertex("Bob");
      Vertex c = new Vertex("Carla");
      a.addAdjacent(b);
      b.addAdjacent(a);
      c.addAdjacent(a);
      c.addAdjacent(b);
      a.addAdjacent(c);
      b.addAdjacent(c);
      
      System.out.println(a.getAdjacencies() +"\n" + b.getAdjacencies() + "\n" + c.getAdjacencies());
   }
   
   public static void vertexToString()
   {
      Vertex a = new Vertex("Amy");
      Vertex b = new Vertex("Bob");
      Vertex c = new Vertex("Carla");
      a.addAdjacent(b);
      b.addAdjacent(a);
      c.addAdjacent(a);
      c.addAdjacent(b);
      a.addAdjacent(c);
      b.addAdjacent(c);
      
      System.out.println(a.toString() +"\n" + b.toString() +"\n" + c.toString());
   }
   
   public static String getVertices()
   {
      AdjList g = new AdjList();
      g.addVertex("A");      //if it's not in the graph, add it.
      g.addVertex("B");
      g.addVertex("C");
      String str = g.getVertices() + "";
      return str;
   }
   
   public static String getVertexMap()
   {
      AdjList g = new AdjList();
      g.addVertex("A");      //if it's not in the graph, add it.
      g.addVertex("B");
      g.addVertex("C");
      String str = g.getVertexMap() + "";
      return str;
   }
   
   public static String getVertexInt()
   {
      AdjList g = new AdjList();
      g.addVertex("A");      //if it's not in the graph, add it.
      g.addVertex("B");
      g.addVertex("C");
      String str = g.getVertex(1) + ""; 
      return str;
   }
   
   public static String getVertexString()
   {
      AdjList g = new AdjList();
      g.addVertex("A");      //if it's not in the graph, add it.
      g.addVertex("B");
      g.addVertex("C");
      String str = g.getVertex("C").toString();
      return str; 
   }
   
   public static void adjListToString()
   {
      AdjList g = new AdjList();
      g.addVertex("A");      //if it's not in the graph, add it.
      g.addVertex("B");
      g.addVertex("C");
      System.out.println(g.toString()); 
   }
   
   public static String getVertices2()
   {
      AdjList g = new AdjList();
      g.addVertex("A");      //if it's not in the graph, add it.
      g.addVertex("B");
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
      String toRet = g.getVertices() + "";
      return toRet;
   }
   
   public static String getVertexMap2()
   {
      AdjList g = new AdjList();
      g.addVertex("A");      //if it's not in the graph, add it.
      g.addVertex("B");
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
      String str = g.getVertexMap() + "";
      return str;
   }
   
   public static String getVertexInt2()
   {
      AdjList g = new AdjList();
      g.addVertex("A");      //if it's not in the graph, add it.
      g.addVertex("B");
      g.addVertex("A");      //if it's not in the graph, add it.
      g.addVertex("B");
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
      String str = g.getVertex(1) + ""; 
      return str; 
   }
   
   public static String getVertexString2()
   {
      AdjList g = new AdjList();
      g.addVertex("A");      //if it's not in the graph, add it.
      g.addVertex("B");
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
      String str = g.getVertex("C").toString(); 
      return str;
   }
   
   public static void adjListToString2()
   {
      AdjList g = new AdjList();
      g.addVertex("A");      //if it's not in the graph, add it.
      g.addVertex("B");
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
      System.out.println(g.toString()); 
   }
   
   public static String adjListToString3()
   {
      AdjList g = new AdjList();
      g.addVertex("A");      //if it's not in the graph, add it.
      
      String str = g.toString(); 
      return str;
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
