// Name: J2-24
// Date: 1/31/21

import java.util.*;

public class TreeLab
{
   public static TreeNode root = null;
   //public static String s = "XCOMPUTERSCIENCE";
   public static String s = "E C S B P W A N R";
   public static String s2 = "N C S B P W A R";
   //public static String s = "XEIABCGB";
   //public static String s = "XThomasJeffersonHighSchool"; 
   //public static String s = "XAComputerScienceTreeHasItsRootAtTheTop";
   //public static String s = "XA";   //comment out lines 44-46 below
   //public static String s = "XAF";  //comment out lines 44-46 below
   //public static String s = "XAFP";  //comment out lines 44-46 below
   //public static String s = "XDFZM";  //comment out lines 44-46 below 
   
   public static void main(String[] args)
   {
      root = buildTree( root, s );
      System.out.print( display( root, 0) );
   
      System.out.print("\nPreorder: " + preorderTraverse(root));
      System.out.print("\nInorder: " + inorderTraverse(root));
      System.out.print("\nParentIn: " + parentIn(root));
      System.out.print("\nPostorder: " + postorderTraverse(root));
      System.out.print("\nParentPost: " + parentPost(root));
      System.out.print("\nParentPre: " + parentPre(root));
      System.out.println("\n\nNodes = " + countNodes(root));
      System.out.println("Leaves = " + countLeaves(root));
      System.out.println("Only children = " + countOnlys(root));
      System.out.println("Grandparents = " + countGrandParents(root));
   
      System.out.println("\nHeight of tree = " + height(root));
      System.out.println("Longest path = " + longestPath(root));
      System.out.println("Min = " + min(root));
      System.out.println("Max = " + max(root));	
   
      System.out.println("\nBy Level: ");
      System.out.println(displayLevelOrder(root));
   }
 
   public static TreeNode buildTree(TreeNode root, String s)
   {
      root = new TreeNode("" + s.charAt(1), null, null);
      for(int pos = 2; pos < s.length(); pos++)
         insert(root, "" + s.charAt(pos), pos, 
            (int)(1 + Math.log(pos) / Math.log(2)));
      insert(root, "A", 17, 5); 
      insert(root, "B", 18, 5); 
      insert(root, "C", 37, 6); //B's right child
      return root;
   }

   public static void insert(TreeNode t, String s, int pos, int level)
   {
      TreeNode p = t;
      for(int k = level - 2; k > 0; k--)
      {
         if((pos & (1 << k)) == 0)
            p = p.getLeft();
         else
            p = p.getRight();
      }
      if((pos & 1) == 0)
         p.setLeft(new TreeNode(s, null, null));
      else
         p.setRight(new TreeNode(s, null, null));
   }
   
   private static String display(TreeNode t, int level)
   {
      // turn your head towards left shoulder visualize tree
      String toRet = "";
      if(t == null)
         return "";
      toRet += display(t.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1); //recurse left
      return toRet;
   }
   
   public static String parentPost(TreeNode t)
   {
      String toReturn = "";
      if(t == null)
         return "";
      
      toReturn += parentPost(t.getLeft());
      toReturn += parentPost(t.getRight());
      
      if((t.getLeft() != null) || (t.getRight() != null))
         toReturn += t.getValue() + " ";
      
      return toReturn;
   }
   
   public static String parentPre(TreeNode t)
   {
      String toReturn = "";
      if(t == null)
         return "";

      if((t.getLeft() != null) || (t.getRight() != null))
         toReturn += t.getValue() + " ";
      
      toReturn += parentPre(t.getLeft());
      toReturn += parentPre(t.getRight());
      
      return toReturn;
   }
   
   public static String parentIn(TreeNode t)
   {
      if(t == null)
        return "";

      String toRet = "";
      
      toRet += parentIn(t.getLeft());
      
      if((t.getLeft() != null) || (t.getRight() != null))
          toRet += t.getValue() + " ";
      
      toRet += parentIn(t.getRight());
      return toRet;
   }
   
   public static String preorderTraverse(TreeNode t)
   { 
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += t.getValue() + " ";              //preorder visit
      toReturn += preorderTraverse(t.getLeft());   //recurse left
      toReturn += preorderTraverse(t.getRight());  //recurse right
      return toReturn;
   }
   
   public static String inorderTraverse(TreeNode t)
   {
      String toReturn = "";
      
      if(t == null)
         return "";
      
      toReturn += inorderTraverse(t.getLeft());      //recurse left	       						 		
      toReturn += t.getValue() + " ";                //inorder visit
      toReturn += inorderTraverse(t.getRight());     //recurse right
      return toReturn;
   }
   
   public static String postorderTraverse(TreeNode t)
   {
      String toReturn = "";
      
      if(t == null)
         return "";
      
      toReturn += postorderTraverse(t.getLeft());    //recurse left
      toReturn += postorderTraverse(t.getRight());   //recurse right
      toReturn += t.getValue() + " ";                //postorder visit
      return toReturn;
   }
   
   public static int countNodes(TreeNode t)
   {
      if(t == null)
         return 0;

      return 1 + countNodes(t.getLeft()) + countNodes(t.getRight());
   }
   
   public static int countLeaves(TreeNode t)
   {
      if(t == null)
         return 0;
      
      if((t.getLeft() == null) && (t.getRight() == null))
         return 1;
         
      return countLeaves(t.getLeft()) + countLeaves(t.getRight());  
   }
   
   /*  there are clever ways and hard ways to count grandparents  */ 
   public static int countGrandParents(TreeNode t)
   {
      int count = 0;
      
      if(t == null)
         return 0;
      
      count += countGrandParents(t.getLeft());
      count += countGrandParents(t.getRight());
      
      if(height(t) >= 2)
         count++;
      
      return count;
   }
   
   public static int countOnlys(TreeNode t)
   {
      int count = 0;
      
      if(t == null)
         return 0;
      
      boolean test1 = (t.getLeft() == null) && (t.getRight() != null);
      boolean test2 = (t.getLeft() != null) && (t.getRight() == null);
      
      if(test1 || test2)
         count++;
         
      count += countOnlys(t.getLeft());
      count += countOnlys(t.getRight());
      return count;
   }
   
  /* returns the max of the heights to the left and the heights to the right  
     returns -1 in case the tree is null
    */
    
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
   
 /* return the max of the sum of the heights to the left and the heights to the right  
 */
   public static int longestPath(TreeNode t)
   {
      if(t == null)
         return 0;
      
      int path1 = Math.max(longestPath(t.getLeft()), longestPath(t.getRight()));
      int path2 = (height(t.getLeft()) + 1) + (height(t.getRight()) + 1);
      
      return Math.max(path1, path2);
   }
   
   /*  Object must be cast to Comparable in order to call .compareTo  
    */
    
   @SuppressWarnings("unchecked")
   public static Object min(TreeNode t)
   {
      if(t == null)
         return (Object) null;
      
      else
      {
         Comparable minObj = (Comparable) t.getValue();
         Comparable leftObj = (Comparable) min(t.getLeft());
         Comparable rightObj = (Comparable) min(t.getRight());
         
         if(leftObj != null)
            if(leftObj.compareTo(minObj) < 0)
               minObj = leftObj;
         
         if(rightObj != null)
            if(rightObj.compareTo(minObj) < 0)
               minObj = rightObj;
         
         return minObj;
      }
   }
   
   /*  Object must be cast to Comparable in order to call .compareTo  
    */
    
   @SuppressWarnings("unchecked")
   public static Object max(TreeNode t)
   {
      if(t == null)
         return (Object) null;
      
      else
      {
         Comparable maxObj = (Comparable) t.getValue();
         Comparable leftObj = (Comparable) max(t.getLeft());
         Comparable rightObj = (Comparable) max(t.getRight());
         
         if(leftObj != null)
            if(leftObj.compareTo(maxObj) > 0)
               maxObj = leftObj;
         
         if(rightObj != null)
            if(rightObj.compareTo(maxObj) > 0)
               maxObj = rightObj;
         
         return maxObj;
      }
   }
      
   /* This method is not recursive.  Use a local queue
    * to store the children of the current TreeNode.
    */
    
   public static String displayLevelOrder(TreeNode t)
   {
      String toReturn = "";
      Queue<TreeNode> queue = new LinkedList<TreeNode>();
      queue.add(t);
      
      while(!queue.isEmpty())
      {
         TreeNode temp = queue.peek();
         toReturn += queue.remove().getValue();
         
         if(temp.getLeft() != null)
            queue.add(temp.getLeft());
         
         if(temp.getRight() != null)
            queue.add(temp.getRight());
      }
      
      return toReturn;
   }
}

/***************************************************
    ----jGRASP exec: java TreeLab
 		  	E
 		E
 			C
 	M
 			N
 		T
 			E
 C
 			I
 		U
 			C
 	O
 			S
 					C
 				B
 		P
 				A
 			R
 
 Preorder: C O P R A S B C U C I M T E N E C E 
 Inorder: R A P B C S O C U I C E T N M C E E 
 Postorder: A R C B S P C I U O E N T C E E M C 
 
 Nodes = 18
 Leaves = 8
 Only children = 3
 Grandparents = 5
 
 Height of tree = 5
 Longest path = 8
 Min = A
 Max = U
 
 By Level: 
 COMPUTERSCIENCEABC    
 *******************************************************/
