import java.util.*;
import java.io.*;

public class IndexMakerMap_Test
{
   public static void main( String[] args ) throws IOException
   {
      String str = "";
      IndexMakerMap_Print_Test(str);
   }
   
   public static void IndexMakerMap_Print_Test(String line)
   {
      BST balancedTree = new BST();
      String[] str = line.split(" ");
      for(String item : str)
      {
         balancedTree.addBalanced( item );  //implement addBalanced() in your BST class
         System.out.println(balancedTree.display());
      }
   }
}