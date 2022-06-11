//Name: J2-24
//Date: 2/9/2021
//BST Extension #2
//Given inorder of ABCDEFGHI and postorder of ACBEIHGFD, preorder is: D B A C F E G H I
//Program will prompt user for inorder and postorder printings of the BST

import java.io.*;
import java.util.*;

public class BST_Extension_2
{
   public static int helperIndex = 0;
   
   public static void main(String[] args)
   {
      Scanner in = new Scanner(System.in);
      System.out.print("Enter in-order representation of BST here, in one continuous string, with no spaces in between characters: ");
      String inorder = in.next();
      in.nextLine();
      System.out.print("Enter post-order representation of BST here, in one continuous string, with no spaces in between characters: ");
      String postorder = in.next();
      
      int inorderLen = inorder.length();
      int postorderLen = postorder.length();
      helperIndex = inorderLen - 1;
      
      Stack<String> stk = new Stack<String>();
      
      findPreOrder(inorder, postorder, 0, helperIndex, stk);
      
      System.out.println("");
      System.out.println("Preorder Traversal:");
      
      while(!stk.isEmpty())
      {
         String val = stk.pop();
         System.out.print(val + " ");
      }      
   }
   
   public static void findPreOrder(String inorder, String postorder, int startIndex, int endIndex, Stack<String> stk)
   {
      int dummyVariable = 0;
      
      if((startIndex > endIndex) || (helperIndex < 0))
         dummyVariable = 1;
      
      else
      {
         String str = postorder.charAt(helperIndex) + "";
         int inorderLen = inorder.length();
         int strInInorderIndex = 0;
         
         for(int i = 0; i < inorderLen; i++)
            if((inorder.charAt(i) + "").equals(str))
               strInInorderIndex = i;
         
         helperIndex--;
         
         //Kind of like a binary search
         findPreOrder(inorder, postorder, strInInorderIndex + 1, endIndex, stk);
         findPreOrder(inorder, postorder, startIndex, strInInorderIndex - 1, stk);
         
         stk.push(str);                      
      }
   }
}