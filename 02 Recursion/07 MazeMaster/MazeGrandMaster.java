// Name: J2-24
// Date: 10/26/2020

import java.util.*;
import java.io.*;
import java.io.File;

public class MazeGrandMaster
{
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("Enter the maze's filename (no .txt): ");
      Maze m = new Maze(sc.next());   
      m.display();      
      System.out.println("Options: ");
      System.out.println("1: Find the shortest path\n\tIf no path exists, say so.");
      System.out.println("2: Mark only the shortest correct path and display the count of STEPs.\n\tIf no path exists, say so.");
      System.out.print("Please make a selection: ");
      m.solve(sc.nextInt());
   } 
}

class Maze
{
   //Constants
   private final char WALL = 'W';
   private final char DOT = '.';
   private final char START = 'S';
   private final char EXIT = 'E';
   private final char STEP = '*';
   //Instance Fields
   private char[][] maze;
   private int startRow, startCol;
  
   //constructors	
	
	/* 
	 * Constructor with an array that makes a deep copy of the array
	 */
    
   public Maze(char[][] m)  
   {
      maze = m;
      for(int r = 0; r < maze.length; r++)
      {
         for(int c = 0; c < maze[0].length; c++)
         { 
            if(maze[r][c] == START)      //identify start
            {
               startRow = r;
               startCol = c;
            }
         }
      }
   } 
	
	/* 
	 * Use a try-catch block
	 * Use next(), not nextLine()  
	 */
    
   public Maze(String filename)    
   {
      Scanner sc = null;
      
      try
      {
        sc = new Scanner(new File(filename));
      }

      catch(FileNotFoundException e)
      {
         System.out.println("File Not Found.");
         return;
      }
      
      int rows = sc.nextInt();
      int cols = sc.nextInt();
      
      char[][] maze_before = new char[rows][cols];
      String[] str_rows = new String[rows];
      
      for(int i = 0; i < rows; i++)
         str_rows[i] = sc.next();
      
      for(int i = 0; i < rows; i++)
      {
         for(int k = 0; k < cols; k++)
             maze_before[i][k] = str_rows[i].charAt(k);
      }
      
      maze = maze_before;
      
      for(int r = 0; r < maze.length; r++)
      {
         for(int c = 0; c < maze[0].length; c++)
         { 
            if(maze[r][c] == START)      //identify start location
            {
               startRow = r;
               startCol = c;
            }
         }
      }
   }
   
   public char[][] getMaze()
   {
      return maze;
   }
   
   public void display()
   {
      if(maze==null) 
         return;
      for(int a = 0; a<maze.length; a++)
      {
         for(int b = 0; b<maze[0].length; b++)
         {
            System.out.print(maze[a][b]);
         }
         System.out.println("");
      }
      System.out.println("");
   }
   
   public void solve(int n)
   {
      switch(n)
      {    
         case 1:
            {   
               int shortestPath = findShortestLengthPath(startRow, startCol);
               if( shortestPath!=-1 )
                  System.out.println("Shortest path is " + shortestPath);
               else
                  System.out.println("No path exists."); 
               break;
            }   
            
          case 2:
            {
               String strShortestPath = findShortestPath(startRow, startCol);
               if( strShortestPath.length()!=0 )
               {
                  System.out.println("Shortest lenght path is: " + getPathLength(strShortestPath));
                  System.out.println("  Shortest path is: " + strShortestPath);
                  markPath(strShortestPath);
                  display();  //display solved maze
               }
               else
                  System.out.println("No path exists."); 
               break;
            }
         default:
            System.out.println("File not found");   
      }
   }
   
 /*  1   recur until you find E, then return the shortest path
     returns -1 if it fails
     precondition: Start can't match with Exit
 */ 
 
   public int findShortestLengthPath(int r, int c)
   {
      return -1; //replace this with something ore useful
   }  
   
/*  2   recur until you find E, then build the True path 
     use only the shortest path
     returns "" if it fails
     precondition: Start can't match with Exit
 */
 
   public String findShortestPath(int r, int c)
   {
      return ""; //replace this with something useful
   }	


   //a recursive method that takes an argument created by the method 2 in the form of 
   //((5,0),10),((5,1),9),((6,1),8),((6,2),7),((6,3),6),((6,4),5),((6,5),4),((6,6),3),((5,6),2),((4,6),1),((4,7),0)
   //and it marks the actual path in the maze with STEP
   //precondition:   the String is either an empty String or one that has the correct format above
   //                the indexes must be correct for this method to work  
   
   public void markPath(String strPath)
   {
   
   }
   
    //returns the length when the format of the path is "((3,4),10),((3,5),9),..."
   //precondition: string is either empty or follows the formt above
   //returns - if the string is empty
   
   public int getPathLength(String strPath)
   {
      return -1;  //replace this with something more useful
   }
     
}

 // Enter the maze's filename (no .txt): maze0
 // WWWWWWWW
 // W....W.W
 // WW.W...W
 // W....W.W
 // W.W.WW.E
 // S.W.WW.W
 // W......W
 // WWWWWWWW
 // 
 // Options: 
 // 1: Find the shortest path
 // 	If no path exists, say so.
 // 2: Mark only the shortest correct path and display the count of STEPs.
 // 	If no path exists, say so.
 // Please make a selection: 2
 // Shortest lenght path is: 10
 //   Shortest path is: ((5,0),10),((5,1),9),((6,1),8),((6,2),7),((6,3),6),((6,4),5),((6,5),4),((6,6),3),((5,6),2),((4,6),1),((4,7),0)
 // WWWWWWWW
 // W....W.W
 // WW.W...W
 // W....W.W
 // W.W.WW*E
 // S*W.WW*W
 // W******W
 // WWWWWWWW