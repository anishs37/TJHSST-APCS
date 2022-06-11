// Name: J2-24
// Date: 10/24/2020

public class MatrixRecreate
{
   public static void main(String[] args)
   {
      int[][] matrix = TheMatrix.create();
      int[] rowcount = new int[matrix.length];
      int[] colcount = new int[matrix[0].length];
      TheMatrix.count(matrix, rowcount, colcount);
      TheMatrix.display(matrix, rowcount, colcount);
      TheMatrix.re_create(rowcount, colcount);
      int[][] new_matrix = TheMatrix.getRecreatedMatrix();
      if( new_matrix == null )
         System.out.println("Did not find a match.");
      else
         TheMatrix.display( new_matrix, rowcount, colcount );
   }
}
class TheMatrix
{
	//do not instantiate recreatedMatrix yet. Only instantiate and set that in recur.
   private static int[][] recreatedMatrix = null;
   
   public static int[][] getRecreatedMatrix()
   {
      return recreatedMatrix;
   }
   
   public static int[][] create()
   {
      int rows = ((int)(Math.random()*5)) + 2;
      int cols = ((int)(Math.random()*5)) + 2;
      
      int[][] matrix = new int[rows][cols];
      
      for(int i = 0; i < rows; i++)
         for(int k = 0; k < cols; k++)
            matrix[i][k] = ((int)(Math.random()*2));
      
      return matrix;

   }
   
   public static void count(int[][] matrix, int[] rowcount, int[] colcount)
   {
      for(int i = 0; i < matrix.length; i++)
      {
         for(int k = 0; k < matrix[0].length; k++)
         {
            if(matrix[i][k] == 1)
            {
               rowcount[i]++;
               colcount[k]++;
            }
         }
      }
   }
   
   public static void display(int[][] matrix, int[] rowcount, int[] colcount)
   {
      String count_str = "";
      String count_line = " -";
      
      for(int i = 0; i < colcount.length; i++)
      {
         count_str = count_str + colcount[i];
         count_line = count_line + "-";
      }   
      
      System.out.print("  " + count_str);
      System.out.println();
      System.out.println(count_line);
         
      for(int a = 0; a < matrix.length; a++)
      {
         System.out.print(rowcount[a] + "|");
         for(int b = 0; b < matrix[0].length; b++)
         { 
            System.out.print(matrix[a][b]);
         }
         System.out.println();
      }
      
      System.out.println();
   }
   
   public static void re_create(int[] orig_rowcount, int[] orig_colcount)
   {
      int[][] new_matrix = new int[orig_rowcount.length][orig_colcount.length];	
      recur(new_matrix, orig_rowcount, orig_colcount, 0, 0);
   }
   
   private static void recur(int[][] new_matrix, int[] orig_rowcount, int[] orig_colcount, int row, int col)
   {
      if(compare(new_matrix, orig_rowcount, orig_colcount))    //base case: if new_matrix works, then copy over to recreatedMatrix
      {
      	//copy over from new_matrix to recreatedMatrix (not just references)
         recreatedMatrix = new int[new_matrix.length][];
         for(int i = 0; i < new_matrix.length; i++)
         {
            recreatedMatrix[i] = new int[new_matrix[i].length];
            for (int j = 0; j < new_matrix[i].length; j++)
            {
               recreatedMatrix[i][j] = new_matrix[i][j];
            }
         }           //we've found it!
      }
      
   	//ENTER YOUR PERMUTATION CODE HERE            
      
      else
      {
         if((row < new_matrix.length) && (col < new_matrix[0].length))
         {
            new_matrix[row][col] = 0;
            recur(new_matrix, orig_rowcount, orig_colcount, row, col + 1);
            new_matrix[row][col] = 1;
            recur(new_matrix, orig_rowcount, orig_colcount, row, col + 1);
         } 
         
         else if(row == orig_rowcount.length-1)
         {
            if(col > orig_colcount.length-1)
               return;
         }
         
         else
            recur(new_matrix, orig_rowcount, orig_colcount, row + 1, 0);
      }
   }
   
   private static boolean compare(int[][] new_matrix, int[] orig_rowcount, int[] orig_colcount)
   {
      int[] count_row = new int[new_matrix.length];
      int[] count_col = new int[new_matrix[0].length];
      
      count(new_matrix, count_row, count_col);
      
      for(int i = 0; i < count_row.length; i++)
      {
         if(count_row[i] != orig_rowcount[i])
            return false;
      }
      
      for(int k = 0; k < count_col.length; k++)
      {
         if(count_col[k] != orig_colcount[k])
            return false;
      }
      
      return true;
   }
}
