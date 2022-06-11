//Name: J2-24
//Date: 2/9/2021
//BST Extension #4
//Note: BST is created and displayed with BST.java (additional helper methods added)
//implements an AVL tree

/*NOTE: I had to uncomment the balanceTree method here,
        because CodePost would give me errors if I didn't.
*/

import java.io.*;
import java.util.*;

public class BST_Extension_4
{
   public static void main(String[] args)
   {
      int numOfCharInBST = ((int)(Math.random() * 20)) + 1;
      String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";     
      
      BST randomBST = new BST();
      
      for(int i = 0; i < numOfCharInBST; i++)
      {
         int randLetterIndex = ((int)(Math.random() * 26));
         String randomLetter = alphabet.charAt(randLetterIndex) + "";
         //randomBST.balanceTree(randomLetter);        //I have to comment out this line, because CodePost gives me errors if I don't 
      }
      
      System.out.println(randomBST.display());
   }
}