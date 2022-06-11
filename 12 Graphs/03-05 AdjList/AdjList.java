// Name: J2-24  
// Date: 5/19/2021
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graphs3: EdgeList,
 *              Graphs4: DFS-BFS
 *          and Graphs5: EdgeListCities
 */

/* Graphs 3: EdgeList 
 */
interface VertexInterface
{
   String toString(); // Don't use commas in the list.  Example: "C [C D]"
   String getName();
   ArrayList<Vertex> getAdjacencies();
   void addAdjacent(Vertex v);
} 

class Vertex implements VertexInterface 
{
   private final String name;
   private ArrayList<Vertex> adjacencies;
  
  /* enter your code here  */
  
   public Vertex(String vertex)
   {
      name = vertex;
      adjacencies = new ArrayList<Vertex>(); 
   }
   
   public String toString()
   {
      String toRet = this.getName() + " [";
      int size = adjacencies.size();
      
      for(int i = 0; i < size; i++)
      {
         Vertex element = adjacencies.get(i);
         String elemGetName = element.getName();
         
         if(i == (size - 1))
            toRet += elemGetName;
         
         else
            toRet += elemGetName + " ";
      }
      
      toRet.trim();
      toRet += "]";
      
      return toRet;
   }
   
   public String getName()
   {
      return name;
   }
   
   public ArrayList<Vertex> getAdjacencies()
   {
      return adjacencies;
   }
   
   public void addAdjacent(Vertex v)
   {
      adjacencies.add(v);
   }
}   

interface AdjListInterface 
{ 
   List<Vertex> getVertices();
   Vertex getVertex(int i);
   Vertex getVertex(String vertexName);
   Map<String, Integer> getVertexMap();
   void addVertex(String v);
   void addEdge(String source, String target);
   String toString();  //returns all vertices with their edges (omit commas)
}

  
/* Graphs 4: DFS and BFS 
 */
interface DFS_BFS
{
   List<Vertex> depthFirstSearch(String name);
   List<Vertex> depthFirstSearch(Vertex v);
   List<Vertex> breadthFirstSearch(String name);
   List<Vertex> breadthFirstSearch(Vertex v);
   /*  three extra credit methods */
   List<Vertex> depthFirstRecur(String name);
   List<Vertex> depthFirstRecur(Vertex v);
   void depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable);
}

/* Graphs 5: Edgelist with Cities 
 */
interface EdgeListWithCities
{
   void graphFromEdgeListData(String fileName) throws FileNotFoundException;
   int edgeCount();
   int vertexCount(); //count the vertices in the object
   boolean isReachable(String source, String target);
   boolean isConnected();
}


public class AdjList implements AdjListInterface, DFS_BFS, EdgeListWithCities
{
   private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
   private Map<String, Integer> nameToIndex = new TreeMap<String, Integer>();
   
   public List<Vertex> getVertices()
   {
      return vertices;
   } 
   
   public Vertex getVertex(int i)  
   {
      Vertex toRet = vertices.get(i);
      return toRet;
   }
 
   public Vertex getVertex(String vertexName)
   {
      for(Vertex toRet : vertices)
      {
         String vertName = toRet.getName();
         
         if(vertName.equals(vertexName))
            return toRet;
      }
      
      return null;
   }
   
   public Map<String, Integer> getVertexMap()
   {
      return nameToIndex;
   }
 
   public void addVertex(String v)
   {
      if(nameToIndex.containsKey(v))
         return;
      
      int verticesSize = vertices.size();
      
      nameToIndex.put(v, verticesSize);
      vertices.add(new Vertex(v));
   }
   
   public void addEdge(String source, String target)
   {
      if(!nameToIndex.containsKey(source))
         addVertex(source);
      
      if(!nameToIndex.containsKey(target))
         addVertex(target);
      
      int getSource = nameToIndex.get(source);
      int getTarget = nameToIndex.get(target);
      
      Vertex verticesGetSource = vertices.get(getSource);
      Vertex verticesGetTarget = vertices.get(getTarget);
      
      verticesGetSource.addAdjacent(verticesGetTarget);
   }
   
   public String toString()
   {
      String toRet = "";
      int verticesSize = vertices.size();
      
      for(int i = 0; i < verticesSize; i++)
      {
         Vertex toAdd = vertices.get(i);
         toRet += toAdd.toString();
         
         if(i != verticesSize - 1)
            toRet += "\n";
      }
      
      toRet.trim();
      return toRet;
   } 
   
   public List<Vertex> depthFirstSearch(String name)
   {
      int index = nameToIndex.get(name);
      Vertex toSearch = vertices.get(index);
      
      List<Vertex> dfsList = depthFirstSearch(toSearch);
      return dfsList;
   }
   
   public List<Vertex> depthFirstSearch(Vertex v)
   {
      List<Vertex> reachables = new ArrayList<Vertex>();
      Stack<Vertex> stk = new Stack<Vertex>();
      
      stk.push(v);
      
      while(!stk.isEmpty())
      {
         Vertex popped = stk.pop();
         
         if(!reachables.contains(popped))
         {
            for(Vertex addPop : popped.getAdjacencies())
               if(!stk.contains(addPop))
                  stk.add(addPop);
            
            reachables.add(popped);
         }
      }
      
      return reachables;
   }
   
   public List<Vertex> breadthFirstSearch(String name)
   {
      int index = nameToIndex.get(name);
      Vertex toSearch = vertices.get(index);
      
      List<Vertex> bfsList = breadthFirstSearch(toSearch);
      return bfsList;
   }
   
   public List<Vertex> breadthFirstSearch(Vertex v)
   {
      List<Vertex> reachables = new ArrayList<Vertex>();
      Queue<Vertex> queue = new LinkedList<Vertex>();
      
      queue.add(v);
      
      while(!queue.isEmpty())
      {
         Vertex dequeued = queue.remove();
         
         if(!reachables.contains(dequeued))
         {
            for(Vertex add : dequeued.getAdjacencies())
               if(!queue.contains(add))
                  queue.add(add);
            
            reachables.add(dequeued);
         }
      }
      
      return reachables;
   }
   
 /*  three extra credit methods, recursive version  */
   public List<Vertex> depthFirstRecur(String name)
   {
      int vertexLoc = 0;
      int verticesSize = vertices.size();
      
      for(int i = 0; i < verticesSize; i++)
      {
         Vertex toSearch = vertices.get(i);
         String vertexName = toSearch.getName();
         
         if(vertexName.equals(name))
         {
            List<Vertex> dfsList = new ArrayList<Vertex>();
            dfsList = depthFirstRecur(toSearch);
            return dfsList;
         }
      }
      
      return null;
   }
   
   public List<Vertex> depthFirstRecur(Vertex v)
   {
      String name = v.getName();
      int mapGet = nameToIndex.get(name);
      Vertex source = vertices.get(mapGet);
      
      ArrayList<Vertex> reachables = new ArrayList<Vertex>();
      
      reachables.add(source);
      depthFirstRecurHelper(source, reachables);
      return reachables;
   }
   
   public void depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable)
   {
      ArrayList<Vertex> adjacencies = v.getAdjacencies();
      int size = adjacencies.size();
      
      for(int i = size - 1; i >= 0; i--)
      {
         Vertex testReachable = adjacencies.get(i);
         
         if(!reachable.contains(testReachable))
         {
            reachable.add(testReachable);   
            depthFirstRecurHelper(testReachable, reachable);
         }
      }
   }
   
   //EdgeList Cities Code
   
   public void graphFromEdgeListData(String fileName) throws FileNotFoundException
   {
      Scanner readCities = new Scanner(new File(fileName));
      
      while(readCities.hasNext())
      {
         String city = readCities.next();
         String city2 = readCities.next();
         
         addVertex(city);
         addVertex(city2);
         addEdge(city, city2);
      }
   }
   
   public int edgeCount()
   {
      int countEdges = 0;
      
      for(Vertex start : vertices)
         for(Vertex adjacencies : start.getAdjacencies())
            countEdges++;
      
      return countEdges;
   }
   
   public int vertexCount()
   {
      int countVertices = 0;
      
      for(Vertex start : vertices)
         countVertices++;
      
      return countVertices;
   }
   
   public boolean isReachable(String source, String target)
   {
      List<Vertex> getReachables = new ArrayList<Vertex>();
      getReachables = depthFirstSearch(source);
      
      for(Vertex reachable : getReachables)
      {
         String vertexName = reachable.getName();
         
         if(vertexName.equals(target))
            return true;
      }
      
      return false;
   }
   
   public boolean isConnected()
   {
      int vertexCount = vertices.size();
      ArrayList<Integer> indices = new ArrayList<Integer>();
      
      for(Vertex vertex : vertices)
      {
         for(Vertex adjacencies : vertex.getAdjacencies())
         {
            int index = vertices.indexOf(adjacencies);
            
            if(!indices.contains(index))
               indices.add(index);
         }
      }
      
      int indicesSize = indices.size();
      
      if(indicesSize >= vertexCount)
         return true;
      
      return false;
   }
}


