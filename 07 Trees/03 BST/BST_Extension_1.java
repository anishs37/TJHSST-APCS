//Name: J2-24
//Date: 2/9/2021
//BST Extension #1
//Note: BST is created and displayed with BST.java (normal lab submission)
//I have included the two sample files for your reference (they are identical).

/*
1. Random BST is displayed in Random BST Creation.txt
2. BST is recreated from Random BST Creation.txt and displayed in BST Recreation.txt
*/

import java.io.*;
import java.util.*;

public class BST_Extension_1
{
   public static PrintWriter outfile1 = null;
   public static PrintWriter outfile2 = null;
   
   public static void main(String[] args)
   {
      try
      {
         outfile1 = new PrintWriter(new FileWriter("Random BST Creation.txt"));
      }
      
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
      
      randomBST(outfile1);
      outfile1.close(); 
      
      try
      {
         outfile2 = new PrintWriter(new FileWriter("BST Recreation.txt"));
      }
      
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
      
      recreateBST(outfile2);
      outfile2.close();
   }
   
   public static void randomBST(PrintWriter out)
   {
      int numOfCharInBST = ((int)(Math.random() * 20)) + 1;
      String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";     
      
      BST randomBST = new BST();
      
      for(int i = 0; i < numOfCharInBST; i++)
      {
         int randLetterIndex = ((int)(Math.random() * 26));
         String randomLetter = alphabet.charAt(randLetterIndex) + "";
         randomBST.add(randomLetter);         
      }
      
      out.println(randomBST.display());
   }
   
   public static void recreateBST(PrintWriter out)
   {
      try
      {
         File file = new File("Random BST Creation.txt");
         Scanner scan = new Scanner(file);
         
         ArrayList<String> vals = new ArrayList<String>();
         
         while(scan.hasNextLine())
         {
            String data = scan.nextLine();
            int whiteSpace = 0;
            
            for(int i = 0; i < data.length(); i++)
               if((data.charAt(i) + "").equals("\t"))
                  whiteSpace++;
            
            data = data.trim();
            String toAdd = whiteSpace + "";
            data = whiteSpace + data;
            
            vals.add(data);
         }
         
         int valsSize = vals.size();
         vals.remove(valsSize - 1);
         
         Collections.sort(vals);
         BST recreatedBST = new BST();
         
         for(int i = 0; i < valsSize - 1; i++)
         {
            String valsData = vals.get(i);
            valsData = valsData.substring(1);
            recreatedBST.add(valsData);
         }
         
         out.println(recreatedBST.display());
         scan.close();
      }
      
      catch(FileNotFoundException e)
      {
         System.out.println("No file found.");
         System.exit(0);
      }
   }
}