// Name: J2-24
// Date: 5/19/2021
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graphs6: Dijkstra
 *              Graphs7: Dijkstra with Cities
 */

class Edge 
{
   public final wVertex target;  //if it's public, you don't need accessor methods
   public final double weight;   //if it's public, you don't need accessor methods
  
   public Edge(wVertex argTarget, double argWeight) 
   {
      target = argTarget;
      weight = argWeight;
   }
}

interface wVertexInterface 
{
   String getName();
   double getMinDistance();
   void setMinDistance(double m);
   wVertex getPrevious();   //for Dijkstra 7
   void setPrevious(wVertex v);  //for Dijkstra 7
   ArrayList<Edge> getAdjacencies();
   void addEdge(wVertex v, double weight);   
   int compareTo(wVertex other);
}

class wVertex implements Comparable<wVertex>, wVertexInterface
{
   private final String name;
   private ArrayList<Edge> adjacencies;
   private double minDistance = Double.POSITIVE_INFINITY;
   private wVertex previous;  //for building the actual path in Dijkstra 7
   
   public wVertex(String wVertex)
   {
      name = wVertex;
      adjacencies = new ArrayList<Edge>(); 
   }
   
   public String getName()
   {
      return name;
   }
   
   public ArrayList<Edge> getAdjacencies()
   {
      return adjacencies;
   }
   
   public double getMinDistance()
   {
      return minDistance;
   }
   
   public void setMinDistance(double m)
   {
      minDistance = m;
   }
   
   public void addEdge(wVertex v, double weight)
   {
      Edge toAdd = new Edge(v, weight);
      adjacencies.add(toAdd);
   }

   //compareTo code given to us in lab document
   public int compareTo(wVertex other)
   {
      return (int)(minDistance - other.minDistance);
   }
   
   public wVertex getPrevious()
   {
      return previous;
   }
   
   public void setPrevious(wVertex newPrev)
   {
      previous = newPrev;
   }
}

interface AdjListWeightedInterface 
{
   List<wVertex> getVertices();
   Map<String, Integer> getNameToIndex();
   wVertex getVertex(int i);   
   wVertex getVertex(String vertexName);
   void addVertex(String v);
   void addEdge(String source, String target, double weight);
   void minimumWeightPath(String vertexName);   //Dijkstra's
}

/* Interface for Graphs 7:  Dijkstra with Cities 
 */

interface AdjListWeightedInterfaceWithCities 
{       
   List<String> getShortestPathTo(wVertex v);
   AdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData) throws FileNotFoundException ;
}
 

public class AdjListWeighted implements AdjListWeightedInterface, AdjListWeightedInterfaceWithCities
{
   private List<wVertex> vertices = new ArrayList<wVertex>();
   private Map<String, Integer> nameToIndex = new HashMap<String, Integer>();
   //the constructor is a no-arg constructor 
  
   public AdjListWeighted()
   {
      
   }
   
   /*  enter your code for Graphs 6 */ 
   
   public List<wVertex> getVertices()
   {
      return vertices;
   }
   
   public Map<String, Integer> getNameToIndex()
   {
      return nameToIndex;
   }
   
   public wVertex getVertex(int i) 
   {
      wVertex toRet = vertices.get(i);
      return toRet;
   }
   
   public wVertex getVertex(String vertexName)
   {
      int index = nameToIndex.get(vertexName);
      wVertex toRet = vertices.get(index);
      return toRet;
   }
   
   public void addVertex(String v)
   {
      if(nameToIndex.containsKey(v))
         return;
      
      int verticesSize = vertices.size();
      
      nameToIndex.put(v, verticesSize);
      vertices.add(new wVertex(v));
   }
   
   public void addEdge(String source, String target, double weight)
   {
      wVertex vertex1 = getVertex(source);
      wVertex vertex2 = getVertex(target);
      vertex1.addEdge(vertex2, weight);
   }
   
   public void minimumWeightPath(String vertexName)
   { 
      for(wVertex vertex : vertices)
         vertex.setMinDistance(Double.POSITIVE_INFINITY);
      
      PriorityQueue<wVertex> pq = new PriorityQueue<wVertex>();
      wVertex sourceVertex = getVertex(vertexName);
      sourceVertex.setMinDistance(0);
      pq.add(sourceVertex);
      
      while(!pq.isEmpty())
      {
         wVertex vertexRemoved = pq.remove();
         double minDistance = vertexRemoved.getMinDistance();
         
         for(Edge neighbors : vertexRemoved.getAdjacencies())
         {   
            double weight = neighbors.weight;
            wVertex neighborsTarget = neighbors.target;
            double targetMinDistance = neighborsTarget.getMinDistance();
            
            if(minDistance + weight < targetMinDistance)
            {
               neighborsTarget.setMinDistance(weight + minDistance);
               neighborsTarget.setPrevious(vertexRemoved);
               pq.add(neighborsTarget);
            }
         }
      }
   }
   
   /*  enter your code for two new methods in Graphs 7 */
   
   public List<String> getShortestPathTo(wVertex v)
   {
      Stack<String> toAdd = new Stack<String>();
      String vNameFirst = v.getName();
      toAdd.push(vNameFirst);
      
      while(v.getPrevious() != null)
      {
         v = v.getPrevious();
         String vName = v.getName();
         toAdd.push(vName);
      }
      
      ArrayList<String> shortestPath = new ArrayList<String>();
      
      while(!toAdd.isEmpty())
      {
         String toInsert = toAdd.pop();
         shortestPath.add(toInsert);
      }
      
      return shortestPath;
   }
   
   public AdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData) throws FileNotFoundException
   {
      Scanner readCities = new Scanner(vertexNames);
      int numCities = Integer.parseInt(readCities.nextLine());
      int iter = 0;
      
      while(iter < numCities)
      {
         String city = readCities.nextLine();
         addVertex(city);
         iter++;
      }
      
      Scanner readEdgeListData = new Scanner(edgeListData);
      
      while(readEdgeListData.hasNextLine())
      {
         String currLine = readEdgeListData.nextLine();
         String[] splitLine = currLine.split(" ");
         
         String city1 = splitLine[0].trim();
         String city2 = splitLine[1].trim();
         
         String strWeight = splitLine[2].trim();
         double weight = Double.parseDouble(strWeight);
         addEdge(city1, city2, weight);
      }
      
      return this;
   }
}