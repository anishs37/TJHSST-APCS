//mlbillington@fcps.edu, May 2012, June 2014
// Graphs 2,  uses AdjMat

import java.util.*;
import java.io.*;
public class AdjMat_3_Driver
{
   public static void main( String[] args) throws FileNotFoundException
   {
      mainCallerDisplayVertices();
   }
   
   public static boolean mainCallerReachable(String from, String to) throws FileNotFoundException
   {
      Scanner kb = new Scanner(System.in);
      String fileNames = "cities.txt";
      Scanner sc = new Scanner(new File(fileNames));
      int size = sc.nextInt();
      AdjMat g = new AdjMat(size);
      g.readNames(fileNames);
      String fileGrid = "citymatrix.txt";
      g.readGrid(fileGrid);
      g.allPairsReachability();
      
      boolean isReachable = g.isEdge(from, to);
      return isReachable;
   }
   
   public static void mainCallerDisplayVertices() throws FileNotFoundException
   {
      Scanner kb = new Scanner(System.in);
      String fileNames = "cities.txt";
      Scanner sc = new Scanner(new File(fileNames));
      int size = sc.nextInt();
      AdjMat g = new AdjMat(size);
      g.readNames(fileNames);
      String fileGrid = "citymatrix.txt";
      g.readGrid(fileGrid);
      g.allPairsReachability();
      g.displayVertices();
   }
   
   public static void mainCallerAdjMatDisplay() throws FileNotFoundException
   {
      Scanner kb = new Scanner(System.in);
      String fileNames = "cities.txt";
      Scanner sc = new Scanner(new File(fileNames));
      int size = sc.nextInt();
      AdjMat g = new AdjMat(size);
      g.readNames(fileNames);
      String fileGrid = "citymatrix.txt";
      g.readGrid(fileGrid);
      System.out.println(g.toString());
   }
   
   public static void mainCallerReachabilityMatDisplay() throws FileNotFoundException
   {
      Scanner kb = new Scanner(System.in);
      String fileNames = "cities.txt";
      Scanner sc = new Scanner(new File(fileNames));
      int size = sc.nextInt();
      AdjMat g = new AdjMat(size);
      g.readNames(fileNames);
      String fileGrid = "citymatrix.txt";
      g.readGrid(fileGrid);
      g.allPairsReachability();
      System.out.println(g.toString());
   }
   
   public static int mainCallerEdgeCountAdjMat() throws FileNotFoundException
   {
      Scanner kb = new Scanner(System.in);
      String fileNames = "cities.txt";
      Scanner sc = new Scanner(new File(fileNames));
      int size = sc.nextInt();
      AdjMat g = new AdjMat(size);
      g.readNames(fileNames);
      String fileGrid = "citymatrix.txt";
      g.readGrid(fileGrid);
      
      int edgeCount = g.edgeCount();
      return edgeCount;
   }
   
   public static int mainCallerEdgeCountReachabilityMat() throws FileNotFoundException
   {
      Scanner kb = new Scanner(System.in);
      String fileNames = "cities.txt";
      Scanner sc = new Scanner(new File(fileNames));
      int size = sc.nextInt();
      AdjMat g = new AdjMat(size);
      g.readNames(fileNames);
      String fileGrid = "citymatrix.txt";
      g.readGrid(fileGrid);
      g.allPairsReachability();
  
      int edgeCount = g.edgeCount();
      return edgeCount;
   }
   
   public static void mainCallerGetReachables() throws FileNotFoundException
   {
      Scanner kb = new Scanner(System.in);
      String fileNames = "cities.txt";
      Scanner sc = new Scanner(new File(fileNames));
      int size = sc.nextInt();
      AdjMat g = new AdjMat(size);
      g.readNames(fileNames);
      String fileGrid = "citymatrix.txt";
      g.readGrid(fileGrid);
      g.allPairsReachability();
      
      for(String city : g.getVertices().keySet() )
         System.out.println(city + "--> " + g.getReachables(city) );
   }
}

/*******************************      
Floyd's Algorithm! Enter file of names: cities
Enter file of the matrix: citymatrix

Adjacency Matrix
 0  9999  9999  9999  9999  9999  9999  8 
 9999  0  9999  5  9999  9999  9999  9999 
 9999  9999  0  9999  9999  5  9999  3 
 9999  9999  9999  0  9999  10  9999  3 
 2  9999  9999  9999  0  9999  9999  9999 
 9999  4  9999  10  9999  0  9999  9999 
 9999  9999  9999  9999  9999  2  0  9999 
 8  9999  9999  9999  3  9999  9999  0 

Number of Edges: 12

0-Pendleton
1-Pensacola
2-Peoria
3-Phoenix
4-Pierre
5-Pittsburgh
6-Princeton
7-Pueblo

Cost Matrix
 0  9999  9999  9999  11  9999  9999  8 
 13  0  9999  5  11  15  9999  8 
 8  9  0  14  6  5  9999  3 
 8  14  9999  0  6  10  9999  3 
 2  9999  9999  9999  0  9999  9999  10 
 17  4  9999  9  15  0  9999  12 
 19  6  9999  11  17  2  0  14 
 5  9999  9999  9999  3  9999  9999  0 

Number of Edges: 33

What is the cost?  Enter start city (-1 to exit): Pittsburgh
                Enter end city: Phoenix
9

What is the cost?  Enter start city (-1 to exit): Pendleton
                Enter end city: Phoenix
9999

What is the cost?  Enter start city (-1 to exit): -1

*************************************************************/     
