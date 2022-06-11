// Name: J2-24  
// Date: 4/18/2021
 
import java.util.*;
import java.io.*;

interface Warshall_Interface    //User-friendly functionality
{
   boolean isEdge(String from, String to);
   Map<String, Integer> getVertices();     
   void readNames(String fileName) throws FileNotFoundException;
   void readGrid(String fileName) throws FileNotFoundException;
   void displayVertices();
   void allPairsReachability();  // Warshall's Algorithm
}

public class Warshall implements Warshall_Interface
{
   private int[][] grid = null;   //adjacency matrix representation
   private Map<String, Integer> vertices = null;   // name maps to index (for Warshall & Floyd)
   /*for Warshall's Extension*/  ArrayList<String> nameList = null;  //reverses the map, index-->name
	  
   /*  enter your code here  */  
   
   //Adjacency Matrix code
   public Warshall(int size)
   {
      grid = new int[size][size];
      vertices = new TreeMap<String, Integer>();
   }
   
   public boolean isEdge(String from, String to)
   {
      int size = grid.length;
      
      boolean rowEdge = false;
      boolean colEdge = false;
      
      for(int i = 0; i < size; i++)
      {
         int vertexColLoc = vertices.get(from);
         
         if(grid[vertexColLoc][i] == 1)
            rowEdge = true;
      }
      
      for(int i = 0; i < size; i++)
      {
         int vertexRowLoc = vertices.get(to);
         
         if(grid[i][vertexRowLoc] == 1)
            colEdge = true;
      }
      
      if(rowEdge && colEdge)
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
         for(int j = 0; j < size; j++)
            for(int k = 0; k < size; k++)
               if((grid[i][j] == 1) && (grid[j][k] == 1))
                  grid[i][k] = 1;
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
            if((grid[i][k] != 0) || (grid[i][k] != 9999))
               numEdges++;
      
      return numEdges;
   }
}