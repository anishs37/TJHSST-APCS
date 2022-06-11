// Name: J2-24
// Date: 10/15/2020

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class NQueens extends JPanel
{
   // Instance Variables: Encapsulated data for EACH NQueens problem
   
   private JButton[][] board;
   private int N;
   JSlider speedSlider;
   private int timerDelay;

   public NQueens(int n)
   {
      N = n;
      this.setLayout(new BorderLayout());
   
      JPanel north = new JPanel();
      north.setLayout(new FlowLayout());
      add(north, BorderLayout.NORTH);
      JLabel label = new JLabel( N + "Queens solution");
      north.add(label);
      
      JPanel center = new JPanel();
      center.setLayout(new GridLayout(N,N));
      add(center, BorderLayout.CENTER);
      board = new JButton[N][N];
      for(int r = 0; r < N; r++)
         for(int c = 0; c < N; c++)
         {
            board[r][c] = new JButton();
            board[r][c].setBackground(Color.blue);
            center.add(board[r][c]);
         }
   
      speedSlider = new JSlider();   
      speedSlider.setInverted(true);   
      add(speedSlider, BorderLayout.SOUTH);   
   }

   /** Returns the number of queens to be placed on the board. **/
   
   public int numQueens()
   {
      return N;   
   }

    /** Solves (or attempts to solve) the N Queens Problem. **/
    
   public boolean solve()
   {
      return isPlaced(0, 0);
   }

 /**
  * Iteratively try to place the queen in each column.
  * Recursively try the next row.
  **/      
   
   private boolean isPlaced(int row, int col)
   {
      if(row == N)
         return true;
      
      for(int p = 0; p < N; p++)
      {
         if(locationIsOK(row, p))
         {
            addQueen(row, p);
            if(isPlaced(row + 1, 0))
               return true;
            
            removeQueen(row, p);
         }
      }
      
      return false;
   } 
   
  /** Verify that another queen can't attack this location.
    * You only need to check the locations above this row.
    * Iteration is fine here.
    **/
    
   private boolean locationIsOK(int r, int c)
   {
      int a, b;
      
      for(a = 0; a < r; a++)
      { 
         if(hasQueen(a, c))
            return false;
      }
      
      for(a = r, b = c; a < N && b >= 0; a++, b--)
      {
         if(hasQueen(a, b))
            return false;
      }
      
      for(a = r, b = c; a >= 0 && b >= 0; a--, b--)
      {
         if(hasQueen(a, b))
            return false;
      }
      
      for(a = r, b = c; a >= 0 && b < N; a--, b++)
      {
         if(hasQueen(a, b))
            return false;
      }
      
      for(a = r, b = c; a < N && b < N; a++, b++)
      {
         if(hasQueen(a, b))
            return false;
      }
    
      return true;
   }
   
   /** Helper method that tests if there is a queen
       at the specified location.
      **/
      
   private boolean hasQueen(int r, int c)
   {
      if(board[r][c].getBackground().equals(Color.RED))
         return true;
      
      return false;
   }
   
    /** Adds a queen to the specified location.
       **/
       
   private void addQueen(int r, int c)
   {
      board[r][c].setBackground(Color.RED);
      pause();
   }

    /** Removes a queen from the specified location.
     **/
     
   private void removeQueen(int r, int c)
   {
      board[r][c].setBackground(Color.BLUE);
      pause();
   }
   
   private void pause()
   {
      int timerDelay = speedSlider.getValue(); 
      for(int i=1; i<=timerDelay*1E7; i++)  {}
   }
}