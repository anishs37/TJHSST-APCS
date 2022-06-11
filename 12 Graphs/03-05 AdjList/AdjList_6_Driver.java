//mlbillington@fcps.edu       May 2014
//lesson:  Graphs5: EdgeListCities
//uses AdjList

import java.util.*;
import java.io.*;
 
public class AdjList_6_Driver
{  
   public static void main(String[] args)throws FileNotFoundException
   {
   }
   
   public static void testGraph() throws FileNotFoundException
   {
      Scanner kb = new Scanner(System.in);
      String fileOfCities = "cityEdgeList.txt";
      AdjList g = new AdjList();
      g.graphFromEdgeListData(fileOfCities);
      System.out.println(g.toString());  //print the graph
   }
   
   public static void testGraph2() throws FileNotFoundException
   {
      Scanner kb = new Scanner(System.in);
      String fileOfCities = "cityEdgeListShort.txt";
      AdjList g = new AdjList();
      g.graphFromEdgeListData(fileOfCities);
      System.out.println(g.toString());  //print the graph
   }
   
   public static void testGraph3() throws FileNotFoundException
   {
      Scanner kb = new Scanner(System.in);
      String fileOfCities = "cityEdgeListNone.txt";
      AdjList g = new AdjList();
      g.graphFromEdgeListData(fileOfCities);
      System.out.println(g.toString());  //print the graph
   }
   
   public static int testEdgeCount() throws FileNotFoundException
   {
      Scanner kb = new Scanner(System.in);
      String fileOfCities = "cityEdgeList.txt";
      AdjList g = new AdjList();
      g.graphFromEdgeListData(fileOfCities);
      return g.edgeCount();
   }
   
   public static int testEdgeCount2() throws FileNotFoundException
   {
      Scanner kb = new Scanner(System.in);
      String fileOfCities = "cityEdgeListShort.txt";
      AdjList g = new AdjList();
      g.graphFromEdgeListData(fileOfCities);
      return g.edgeCount();
   }
   
   public static int testEdgeCount3() throws FileNotFoundException
   {
      Scanner kb = new Scanner(System.in);
      String fileOfCities = "cityEdgeListNone.txt";
      AdjList g = new AdjList();
      g.graphFromEdgeListData(fileOfCities);
      return g.edgeCount();
   }
   
   public static int testVerticesCount() throws FileNotFoundException
   {
      Scanner kb = new Scanner(System.in);
      String fileOfCities = "cityEdgeList.txt";
      AdjList g = new AdjList();
      g.graphFromEdgeListData(fileOfCities);
      return g.vertexCount();
   }
   
   public static int testVerticesCount2() throws FileNotFoundException
   {
      Scanner kb = new Scanner(System.in);
      String fileOfCities = "cityEdgeListShort.txt";
      AdjList g = new AdjList();
      g.graphFromEdgeListData(fileOfCities);
      return g.vertexCount();
   }
   
   public static boolean testGraphConnected() throws FileNotFoundException
   {
      Scanner kb = new Scanner(System.in);
      String fileOfCities = "cityEdgeList.txt";
      AdjList g = new AdjList();
      g.graphFromEdgeListData(fileOfCities);
      return g.isConnected();
   }
   
   public static boolean testGraphConnected2() throws FileNotFoundException
   {
      Scanner kb = new Scanner(System.in);
      String fileOfCities = "cityEdgeListShort.txt";
      AdjList g = new AdjList();
      g.graphFromEdgeListData(fileOfCities);
      return g.isConnected();
   }
   
   public static boolean testGraphConnected3() throws FileNotFoundException
   {
      Scanner kb = new Scanner(System.in);
      String fileOfCities = "cityEdgeListNone.txt";
      AdjList g = new AdjList();
      g.graphFromEdgeListData(fileOfCities);
      return g.isConnected();
   }
   
   public static boolean testReachable(String from, String to) throws FileNotFoundException  
   {
      Scanner kb = new Scanner(System.in);
      String fileOfCities = "cityEdgeList.txt";
      AdjList g = new AdjList();
      g.graphFromEdgeListData(fileOfCities);
      return g.isReachable(from, to);
   }  
   
   public static boolean testReachable2(String from, String to) throws FileNotFoundException  
   {
      Scanner kb = new Scanner(System.in);
      String fileOfCities = "cityEdgeListShort.txt";
      AdjList g = new AdjList();
      g.graphFromEdgeListData(fileOfCities);
      return g.isReachable(from, to);
   } 
   
   public static boolean testReachable3(String from, String to) throws FileNotFoundException  
   {
      Scanner kb = new Scanner(System.in);
      String fileOfCities = "cityEdgeListNone.txt";
      AdjList g = new AdjList();
      g.graphFromEdgeListData(fileOfCities);
      return g.isReachable(from, to);
   }
}
/**********************************
Edge List with Cities! 
Enter file of cities and edges: cityEdgeList

The cities with their edges:
Pendleton [Pueblo]
Pueblo [Pendleton Pierre]
Pensacola [Phoenix]
Phoenix [Pittsburgh Pueblo]
Peoria [Pittsburgh Pueblo]
Pittsburgh [Pensacola Phoenix]
Pierre [Pendleton]
Princeton [Pittsburgh Princeton]

Number of edges: 13

Is this graph connected? false

Can you get there from here?  
Enter start city (-1 to exit): Peoria
Enter end city: Pittsburgh
   true

Can you get there from here?  
Enter start city (-1 to exit): Pittsburgh
Enter end city: Peoria
   false

Can you get there from here?  
Enter start city (-1 to exit): -1

****************************/