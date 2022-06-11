//Name: J2-24
//Date: 2/9/2021

import java.io.*;
import java.util.*;

/*NOTE: I had to uncomment the last two lines in the BSTinterface
        code, because CodePost would give me errors if I didn't.
        Please uncomment those lines of codes + the lines in 
        BST_Extension_3.java and BST_Extension_4.java to test my 
        extension code. Thank you.
*/

interface BSTinterface
{
   public int size();
   public boolean contains(String obj);
   public void add(String obj);
   //public void addBalanced(String obj);
   //public boolean remove(String obj);
   public String min();
   public String max();
   public String toString();
   //public void balanceTree(String obj);
   //public void balanceEntireTree();
}

/*******************
Represents a binary search tree holding Strings. 
Implements (most of) BSTinterface, above. 
The recursive methods all have a public method calling a private helper method. 
Copy the display() method from TreeLab. 
**********************/
class BST implements BSTinterface
{
   private TreeNode root;
   private int size;
   public BST()
   {
      root = null;
      size = 0;
   }
   
   public int size()
   {
      return size;
   }
   
   public TreeNode getRoot()   //for Grade-It
   {
      return root;
   }
   
   /***************************************
   @param s -- one string to be inserted
   ****************************************/
   
   public void add(String s) 
   {
      root = add(root, s);
      size++;
   }
   
   private TreeNode add(TreeNode t, String s) //recursive helper method
   {
      if(t == null)
      {
         TreeNode nullReturn = new TreeNode(s, null, null);
         return nullReturn;
      }
      
      String toCheck = String.valueOf(t.getValue());
      
      if(toCheck.compareTo(s) >= 0)
         t.setLeft(add(t.getLeft(), s));
      
      else
         t.setRight(add(t.getRight(), s));
      
      return t;
   }
   
   public String display()
   {
      return display(root, 0);
   }
   
   private String display(TreeNode t, int level) //recursive helper method
   {
      String toReturn = "";
      
      if(t == null)
         return "";
         
      toReturn += display(t.getRight(), level + 1);
      
      for(int k = 0; k < level; k++)
         toReturn += "\t";
         
      toReturn += t.getValue() + "\n";
      
      toReturn += display(t.getLeft(), level + 1);
      return toReturn;
   }
   
   public boolean contains(String obj)
   {
      return contains(root, obj);
   }
   
   private boolean contains(TreeNode t, String x) //recursive helper method
   {
      if(t == null)
         return false;
      
      String toCheck = String.valueOf(t.getValue());
      
      if(toCheck.compareTo(x) == 0)
         return true;
         
      else if(toCheck.compareTo(x) > 0)
         return contains(t.getLeft(), x);
     
      else
         return contains(t.getRight(), x);
   }
   
   public String min()
   {
      return min(root);
   }
   
   private String min(TreeNode t)  //use iteration
   {
      if(t == null)
         return "";
      
      TreeNode temp = t;
      
      while(temp.getLeft() != null)
         temp = temp.getLeft();
      
      return String.valueOf(temp.getValue());
   }
   
   public String max()
   {
      return max(root);
   }
   
   private String max(TreeNode t)  //recursive helper method
   {
      if(t == null)
         return "";
      
      else
      {
         if(t.getRight() == null)
         {
            String strToReturn = String.valueOf(t.getValue());
            return strToReturn;
         }
         
         else
            return max(t.getRight());
      }
   }
   
   public String toString()
   {
      return toString(root);
   }
   
   private String toString(TreeNode t)  //an in-order traversal.  Use recursion.
   {
      String toReturn = "";
      
      if(t == null)
         return "";
      
      toReturn += toString(t.getLeft());      //recurse left	       						 		
      toReturn += t.getValue() + " ";         //inorder visit
      toReturn += toString(t.getRight());     //recurse right
      return toReturn;
   }
   
   public void balanceTree(String s)  
   {    
      root = balanceTree(root, s);
      size++;
   }
   
   private TreeNode balanceTree(TreeNode t, String s) 
   {
      if(t == null)
      {
         TreeNode nullReturn = new TreeNode(s, null, null);
         return nullReturn;
      }
      
      String currentVal = String.valueOf(t.getValue());
      
      if(currentVal.compareTo(s) >= 0)
         t.setLeft(balanceTree(t.getLeft(), s));
      
      else
         t.setRight(balanceTree(t.getRight(), s));
      
      //A positive height difference means left subtree has height greater than right subtree, 
      //and vice versa.
      
      int heightDifference = height(t.getLeft()) - height(t.getRight());
      
      if(heightDifference < -1)
      {
         if((String.valueOf(t.getRight().getValue())).compareTo(s) < 0)
            return rotateLeft(t);
         
         else
            return setRightRotateLeft(t);
      }
      
      else if(heightDifference > 1)
      {
         if((String.valueOf(t.getLeft().getValue())).compareTo(s) < 0)
            return setLeftRotateRight(t);
         
         else
            return rotateRight(t);
      }
      
      return t;
   }
   
   public void balanceEntireTree()
   {
      root = balanceEntireTree(root);
   }
   
   private TreeNode balanceEntireTree(TreeNode t)
   {
      ArrayList<TreeNode> vals = new ArrayList<TreeNode>();
      storeNodes(t, vals);
      
      int size = vals.size();
      return balanceTreeHelper(vals, 0, size - 1);
   }
   
   private void storeNodes(TreeNode t, ArrayList<TreeNode> vals)
   {
      if(t == null)
         return;
      
      else
      {                                      //inorder traversal
         storeNodes(t.getLeft(), vals);
         vals.add(t);
         storeNodes(t.getRight(), vals);
      }
   }
   
   private TreeNode balanceTreeHelper(ArrayList<TreeNode> vals, int startIndex, int endIndex)
   {
      if(startIndex > endIndex)
         return null;
      
      int midIndex = (startIndex + endIndex) / 2;
      TreeNode midNode = vals.get(midIndex);
      
      midNode.setLeft(balanceTreeHelper(vals, startIndex, midIndex - 1));
      midNode.setRight(balanceTreeHelper(vals, midIndex + 1, endIndex));
      
      return midNode;
   }
   
   public static int height(TreeNode t)
   {
      if(t == null)
         return -1;
      
      int countLeft = height(t.getLeft());
      int countRight = height(t.getRight());
      
      if(countLeft > countRight)
         return 1 + height(t.getLeft());
      
      else
         return 1 + height(t.getRight());
   }
   
   private TreeNode rotateLeft (TreeNode t) 
   {
      TreeNode tCopy = t;
      TreeNode tCopyRight = tCopy.getRight(); 
      TreeNode tCopyRightGetLeft = tCopyRight.getLeft(); 
      
      tCopyRight.setLeft(tCopy); 
      tCopy.setRight(tCopyRightGetLeft); 
      
      t = tCopy;
      
      return tCopyRight;
   }
   
   private TreeNode rotateRight (TreeNode t) 
   {            
      TreeNode tCopy = t;
      TreeNode tCopyLeft = t.getLeft(); 
      TreeNode tCopyLeftGetRight = tCopyLeft.getRight(); 
  
      tCopyLeft.setRight(tCopy); 
      tCopy.setLeft(tCopyLeftGetRight);
      
      t = tCopy;
      
      return tCopyLeft;
   }
      
   private TreeNode setLeftRotateRight(TreeNode t) 
   {
      TreeNode tCopy = t;
      
      tCopy.setLeft(rotateLeft(tCopy.getLeft()));
      
      t = tCopy;
      return rotateRight(t);
   }
   
   private TreeNode setRightRotateLeft(TreeNode t) 
   {
      TreeNode tCopy = t;
      
      tCopy.setRight(rotateRight(tCopy.getRight()));
      
      t = tCopy;
      return rotateLeft(t);
   }
}
