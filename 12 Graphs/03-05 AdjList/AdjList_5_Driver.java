//mlbillington@fcps.edu       May 2014
//lesson:  Graphs5: EdgeListCities
//uses AdjList

import java.util.*;
import java.io.*;
 
public class AdjList_5_Driver
{  
   public static void main(String[] args)throws FileNotFoundException
   {
      System.out.println("Edge List with Cities! ");
      Scanner kb = new Scanner(System.in);
      System.out.print("Enter file of cities and edges: "); 
   											             //cityEdgeList
      String fileOfCities = kb.next()+".txt";
      AdjList g = new AdjList();
      g.graphFromEdgeListData(fileOfCities);
     
      System.out.println("\nThe cities with their edges:");
      System.out.println(g.toString());  //print the graph
   	
      System.out.println("Number of edges: " + g.edgeCount());
      
      System.out.print("\nIs this graph connected? " + g.isConnected() );
      
      while(true)
      {
         System.out.print("\nCan you get there from here?  \n\tEnter start city (-1 to exit): ");
         String from = kb.next();
         if(from.equals("-1"))
            break;
         System.out.print("\tEnter end city: "); 
         String to = kb.next();  
         System.out.println( "\t\t" + g.isReachable(from, to) );
      }
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