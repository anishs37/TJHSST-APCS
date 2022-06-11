//Name: J2-24
//Date: 2/9/2021
//BST Extension #3
//Note: BST is created and displayed with BST.java (normal lab submission)

/*NOTE: I had to uncomment the balanceEntireTree method here,
        because CodePost would give me errors if I didn't.
*/

import java.io.*;
import java.util.*;

public class BST_Extension_3
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
         randomBST.add(randomLetter);         
      }
      
      //randomBST.balanceEntireTree();
      System.out.println(randomBST.display());
   }
}