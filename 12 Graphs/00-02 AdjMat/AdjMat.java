// Name: J2-24  
// Date: 4/19/2021
 
import java.util.*;
import java.io.*;
 
/* Resource classes and interfaces
 * for use with Graph0 AdjMat_0_Driver,
 *              Graph1 WarshallDriver,
 *          and Graph2 FloydDriver
 */
 
interface AdjacencyMatrix
{
   void addEdge(int source, int target);
   void removeEdge(int source, int target);
   boolean isEdge(int from, int to);
   String toString();   //returns the grid as a String
   int edgeCount();
   List<Integer> getNeighbors(int source);
   public List<String> getReachables(String from);  //Warshall extension
}
 
interface Warshall      //User-friendly functionality
{
   boolean isEdge(String from, String to);
   Map<String, Integer> getVertices();     
   void readNames(String fileName) throws FileNotFoundException;
   void readGrid(String fileName) throws FileNotFoundException;
   void displayVertices();
   void allPairsReachability();  // Warshall's Algorithm
}
 
interface Floyd
{
   int getCost(int from, int to);
   int getCost(String from, String to);
   void allPairsWeighted(); 
}
 
public class AdjMat implements AdjacencyMatrix, Warshall, Floyd 
{
   private int[][] grid = null;   //adjacency matrix representation
   private Map<String, Integer> vertices = null;   // name maps to index (for Warshall & Floyd)
   /*for Warshall's Extension*/  ArrayList<String> nameList = null;  //reverses the map, index-->name
	  
   /*  enter your code here  */  
   
   //Adjacency Matrix Code
   
   public AdjMat(int size)
   {
      grid = new int[size][size];
      vertices = new TreeMap<String, Integer>();
      nameList = new ArrayList<String>();
   }
   
   public void addEdge(int source, int target)
   {
      grid[source][target] = 1;
   }
   
   public void removeEdge(int source, int target)
   {
      grid[source][target] = 0;
   }
   
   public boolean isEdge(int from, int to)
   {
      if(grid[from][to] == 1)
         return true;
      
      return false;
   }
 
   public String toString()
   {
      String toRet = "";
      
      int rowLen = grid.length;
      int colLen = grid[0].length;
      
      for(int i = 0; i < rowLen; i++)
      {
         for(int k = 0; k < colLen; k++)
            toRet += grid[i][k] + " ";
         
         toRet += "\n";
      }
      
      return toRet;
   }
   
   public int edgeCount()
   {
      int numEdges = 0;
            
      int rowLen = grid.length;
      int colLen = grid[0].length;
      
      for(int i = 0; i < rowLen; i++)
         for(int k = 0; k < colLen; k++)
            if((grid[i][k] != 0) && (grid[i][k] != 9999))
               numEdges++;
      
      return numEdges;
   }
   
   public List<Integer> getNeighbors(int source)
   {
      List<Integer> list = new ArrayList<Integer>();
      int len = grid.length;
      
      for(int i = 0; i < len; i++)
         if(grid[source][i] == 1)
            list.add(i);
      
      return list;
   }
   
   //Warshall Code
 
   public boolean isEdge(String from, String to)
   {
      int fromGet = vertices.get(from);
      int toGet = vertices.get(to);
      
      if(grid[fromGet][toGet] == 1)
         return true;
      
      return false;
   }
   
   public Map<String, Integer> getVertices()
   {
      return vertices;
   }
   
   public void readNames(String filename) throws FileNotFoundException
   {
      Scanner readCities = new Scanner(new File(filename));
      int numCities = Integer.parseInt(readCities.nextLine());
      int iter = 0;
      
      while(iter < numCities)
      {
         String city = readCities.nextLine();
         
         if(!city.equals(""))
         {
            vertices.put(city, iter);
            nameList.add(city);
            iter++;
         }
      }
   }
   
   public void readGrid(String filename) throws FileNotFoundException
   {
      Scanner readGrid = new Scanner(new File(filename));
      int size = Integer.parseInt(readGrid.next());
      
      boolean firstBlank = false;
      int trueIter = 0;
      
      for(int i = 0; i < size; i++)
      {
         if((firstBlank) && (trueIter == 1))
            i--;
            
         String gridRow = readGrid.nextLine();
         
         if(!gridRow.equals(""))
         {
            String[] splitNums = gridRow.split(" ");
            
            for(int j = 0; j < size; j++)
            {
               int num = Integer.parseInt(splitNums[j]);
               grid[i][j] = num;
            }
         }
         
         else
            firstBlank = true;
         
         trueIter++;
      }
   }
   
   public void displayVertices()
   {
      int iter = 0;
      
      for(String city : vertices.keySet())
      {
         System.out.println(iter + "-" + city);
         iter++;
      }
   }
   
   public void allPairsReachability()
   {
      int size = grid.length;
      
      for(int i = 0; i < size; i++)
         for(int v = 0; v < size; v++)
            for(int j = 0; j < size; j++)
               if((grid[i][v] == 1) && (grid[v][j] == 1))
                  grid[i][j] = 1;
   }
   
   public List<String> getReachables(String from)
   {
      List<String> getReachables = new ArrayList<String>();
      
      int fromGet = vertices.get(from);
      int gridLength = grid.length;
      
      for(int i = 0; i < gridLength; i++)
      {
         if(grid[fromGet][i] == 1)
         {
            String city = nameList.get(i);
            getReachables.add(city);
         }
      }
      
      Collections.sort(getReachables);
      return getReachables;
   }
   
   //Floyd Code
   
   public int getCost(int from, int to)
   {
      int cost = grid[from][to];
      return cost;
   }
   
   public int getCost(String from, String to)
   {
      int fromGet = vertices.get(from);
      int toGet = vertices.get(to);
      
      int cost = grid[fromGet][toGet];
      return cost;
   }
   
   public void allPairsWeighted()
   {
      int size = grid.length;
      
      for(int i = 0; i < size; i++)
         for(int j = 0; j < size; j++)
            for(int k = 0; k < size; k++)
               if(grid[j][k] > (grid[j][i] + grid[i][k]))
                  grid[j][k] = grid[j][i] + grid[i][k];
 
   } 
}