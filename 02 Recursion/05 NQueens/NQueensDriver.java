    // mlbillington@fcps.edu,  10-20-2019

import javax.swing.JFrame;
import java.util.*;

public class NQueensDriver
{
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("Please enter the size of the board (N x N): ");
      int size = Integer.parseInt(sc.nextLine());
      
      JFrame frame = new JFrame("N-Queens");
      frame.setSize(400, 400);
      frame.setLocation(200, 100);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      NQueens nqueens = new NQueens(size);
      frame.setContentPane(nqueens);
      frame.setVisible(true);
                  
      if(nqueens.solve())
         System.out.println("solved");
      else
         System.out.println("Solution not found");
   }
}