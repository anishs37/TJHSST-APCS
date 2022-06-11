//Name: J2-24
//Date: 2/26/2021

//BST AVL Extension #1
//Implementing a RedBlackTree

import java.io.*;
import java.util.*;

public class RedBlackTreeImplementation
{
   RedBlackNode root;
   
   static final int BLACK = 1;
   static final int RED = 0;
   static final int NEGATIVE_RED = -1;
   static final int DOUBLE_BLACK = 2;
   
   static int size;
   
   public RedBlackTreeImplementation()
   {
      root = null;
      size = 0;
   }
   
   public int size()
   {
      return size;
   }
   
   public RedBlackNode getRoot()
   {
      return root;
   }
   
   public void add(Comparable obj)
   {
      RedBlackNode addNode = new RedBlackNode(obj);
      
      if(root == null)
         root = addNode;
      
      else
         root.adderNode(addNode);
      
      restoreTreeAfterAdd(addNode);
      size++;
   }
   
   public boolean contains(Comparable obj)
   {
      RedBlackNode copyOfRoot = root;
      
      while(copyOfRoot != null)
      {
         Comparable toCompareTo = copyOfRoot.getValue();
         int diff = toCompareTo.compareTo(obj);
         
         if(diff == 0)
            return true;
         
         else if(diff > 0)
            copyOfRoot = copyOfRoot.getLeft();
         
         else
            copyOfRoot = copyOfRoot.getRight();
      }
      
      return false;
   }
   
   public String display()
   {
      return display(root, 0);
   }
   
   private String display(RedBlackNode t, int level) //recursive helper method
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
   
   public void remove(Comparable target)
   {      
      RedBlackNode toRemoveCopy = root;
      
      boolean contains = false;
      
      while((toRemoveCopy != null) && (contains == false))
      {
         Comparable toCompareTo = toRemoveCopy.getValue();
         int diff = toCompareTo.compareTo(target);
         
         if(diff == 0)
            contains = true;
         
         else if(diff > 0)
            toRemoveCopy = toRemoveCopy.getLeft();
         
         else
            toRemoveCopy = toRemoveCopy.getRight();
      }
      
      if(contains == false)
         return;
      
      else
      {
         if((toRemoveCopy.getLeft() == null) || (toRemoveCopy.getRight() == null))
         {
            RedBlackNode child;
            
            if(toRemoveCopy.getLeft() == null)
               child = toRemoveCopy.getRight();
            
            else
               child = toRemoveCopy.getLeft();
            
            restoreTreeBeforeRemove(toRemoveCopy);
            replacerNode(toRemoveCopy, child);
            return;
         }
         
         else
         {
            RedBlackNode largestInLeft = toRemoveCopy.getLeft();
            
            while(largestInLeft.getRight() != null)
               largestInLeft = largestInLeft.getRight();
            
            toRemoveCopy.setValue(largestInLeft.getValue());
            restoreTreeBeforeRemove(largestInLeft);
            replacerNode(largestInLeft, largestInLeft.getLeft());
         }
      }
      
      size--;
   }

   public void replacerNode(RedBlackNode toReplace, RedBlackNode toReplaceWith)
   {
      if(toReplace.parent == null)
      {
         toReplaceWith.parent = null;
         root = toReplaceWith;
      }
      
      else
      {
         if(toReplace == toReplace.parent.getLeft())
            toReplace.parent.setLeft(toReplaceWith);
         
         else
            toReplace.parent.setRight(toReplaceWith);
      }
   }
   
   public void restoreTreeAfterAdd(RedBlackNode theNewNode)
   {
      if(theNewNode.parent == null)
         theNewNode.color = BLACK;
      
      else
      {
         theNewNode.color = RED;
         
         if(theNewNode.parent.color == RED)
            fixDRedViol(theNewNode);            //have to fix the double red violation --> code for this below
      }
   }
   
   public void restoreTreeBeforeRemove(RedBlackNode theNodeToBeRemoved)
   {
      if(theNodeToBeRemoved.color == RED)
         return;

      else
      {
         if((theNodeToBeRemoved.getLeft() != null) || (theNodeToBeRemoved.getRight() != null))
         {
            if(theNodeToBeRemoved.getLeft() == null)
               theNodeToBeRemoved.right.color = BLACK;
            
            else
               theNodeToBeRemoved.left.color = BLACK;
         }
         
         else
            bubbleUpTheCosts(theNodeToBeRemoved.parent);
      }        
   }
   
   public void bubbleUpTheCosts(RedBlackNode current)    //Transfer the "costs" from the child to the parent (which is current here)
   {
      if(current == null)
         return;
      
      else
      {
         current.color++;
         current.getLeft().color--;
         current.getRight().color--;
         
         if(nRedDRedViolsFixed(current.getLeft()))
            return;
         
         if(nRedDRedViolsFixed(current.getRight()))
            return;
         
         if(current.color == DOUBLE_BLACK)
         {
            if(current.parent == null)
               current.color = BLACK;
            
            else
               bubbleUpTheCosts(current.parent);
         }
      }
   }
   
   public boolean nRedDRedViolsFixed(RedBlackNode current)
   {
      if(current.color == NEGATIVE_RED)
      {
         fixNRedViol(current);
         return true;
      }
      
      else if(current.color == RED)
      {
         if((current.getLeft() != null) && (current.getLeft().color == RED))
         {
            fixDRedViol(current.getLeft());
            return true;
         }
         
         if((current.getRight() != null) && (current.getRight().color == RED))
         {
            fixDRedViol(current.getRight());
            return true;
         }
      }
      
      return false;
   }
   
   public void fixDRedViol(RedBlackNode childNode)    //basically traversing the tree to fix violation
   {
      //childNode represents the child that has a red-colored parent
      
      RedBlackNode parentNode = childNode.parent;
      RedBlackNode grandParentNode = parentNode.parent;
      
      if(grandParentNode == null)
      {
         parentNode.color = BLACK;
         return;
      }
      
      else
      {
         RedBlackNode nodeOne, nodeTwo, nodeThree;
         RedBlackNode tempOne, tempTwo, tempThree, tempFour;
         
         if(parentNode  == grandParentNode.getLeft())
         {
            nodeThree = grandParentNode;              //parent of parent (grandparent)
            tempFour = grandParentNode.getRight();    //other parent
            
            if(childNode == parentNode.getLeft())
            {
               nodeOne = childNode;                //child node
               nodeTwo = parentNode;               //parent node
               
               tempOne = childNode.getLeft();      //child of child
               tempTwo = childNode.getRight();     //child of child
               tempThree = parentNode.getRight();  //other child
            }
            
            else
            {
               nodeOne = parentNode;               //parent node
               nodeTwo = childNode;                //child node
               
               tempOne = parentNode.getLeft();     //other child
               tempTwo = childNode.getLeft();      //child of child
               tempThree = childNode.getRight();   //child of child
            }
         }
         
         else
         {
            nodeOne = grandParentNode;
            tempOne = grandParentNode.getLeft();   //not equal to parent node
            
            if(childNode == parentNode.getLeft())
            {
               nodeTwo = childNode;                //child node
               nodeThree = parentNode;             //parent node
               
               tempTwo = childNode.getLeft();      //child of child
               tempThree = childNode.getRight();   //child of child
               tempFour = parentNode.getRight();   //other child
            }
            
            else
            {
               nodeTwo = parentNode;               //parent node
               nodeThree = childNode;              //child node
               
               tempTwo = parentNode.getLeft();     //other child
               tempThree = childNode.getLeft();    //child of child
               tempFour = childNode.getRight();    //child of child
            }
         }
         
         replacerNode(grandParentNode, nodeTwo);
       
         nodeOne.setLeft(tempOne);
         nodeOne.setRight(tempTwo);
         nodeTwo.setLeft(nodeOne);
         nodeTwo.setRight(nodeThree); 
         nodeThree.setLeft(tempThree);
         nodeThree.setRight(tempFour);
         
         nodeTwo.color = grandParentNode.color - 1;
         nodeOne.color = BLACK;
         nodeThree.color = BLACK;
         
         if(nodeTwo == root)
            root.color = BLACK;
         
         else if((nodeTwo.color == RED) && (nodeTwo.parent.color == RED))
            fixDRedViol(nodeTwo);      //still have to fix the violation (if code comes inside here)
      }
   }
   
   public void fixNRedViol(RedBlackNode childNode)
   {
      RedBlackNode parentNode = childNode.parent;
      
      RedBlackNode checkForDRedViol;
      RedBlackNode nodeOne, nodeTwo, nodeThree, nodeFour;
      RedBlackNode tempOne, tempTwo, tempThree;
      
      if(parentNode.getLeft() == childNode)
      {
         nodeOne = childNode.getLeft();      //child of child
         nodeTwo = childNode;                //child node
         nodeThree = childNode.getRight();   //child of child
         nodeFour = parentNode;              //parent node
         
         tempOne = nodeThree.getLeft();        //grandchild of child
         tempTwo = nodeThree.getRight();       //grandchild of child
         tempThree = nodeFour.getRight();        //the other child
         
         nodeOne.color = RED;
         nodeTwo.color = BLACK;
         nodeFour.color = BLACK;
         
         replacerNode(nodeFour, nodeThree);  
         nodeThree.setLeft(nodeTwo);
         nodeThree.setRight(nodeFour);
         nodeTwo.setLeft(nodeOne);
         nodeTwo.setRight(tempOne);
         nodeFour.setLeft(tempTwo);
         nodeFour.setRight(tempThree);
         
         checkForDRedViol = nodeOne;
      }
      
      else
      {
         nodeFour = childNode.getRight();   //child of child
         nodeThree = childNode;             //child node
         nodeTwo = childNode.getLeft();     //child of child
         nodeOne = parentNode;              //parent node
         
         tempThree = nodeThree.getRight();  //grandchild of child
         tempTwo = nodeThree.getLeft();     //grandchild of child
         tempOne = nodeFour.getLeft();      //the other child
         
         nodeFour.color = RED;
         nodeThree.color = BLACK;
         nodeOne.color = BLACK;
         
         replacerNode(nodeOne, nodeTwo);  
         nodeTwo.setRight(nodeThree);
         nodeTwo.setLeft(nodeOne);
         nodeThree.setRight(nodeFour);
         nodeThree.setLeft(tempThree);
         nodeOne.setRight(tempTwo);
         nodeOne.setLeft(tempOne);
         
         checkForDRedViol = nodeFour;
      }
      
      if((childNode.getLeft() != null) && (childNode.getLeft().color == RED))
         fixDRedViol(childNode.getLeft());      //still have to fix the violation (if code comes inside here)
      
      else if((childNode.getRight() != null) && (childNode.getRight().color == RED))
         fixDRedViol(childNode.getRight());     //still have to fix the violation (if code comes inside here)
   }
   
   public String toString()
   {
      return toString(root);
   }
   
   private String toString(RedBlackNode t)  //an in-order traversal.  Use recursion.
   {
      String toReturn = "";
      
      if(t == null)
         return "";
      
      toReturn += toString(t.getLeft());      //recurse left	       						 		
      toReturn += t.getValue() + " ";         //inorder visit
      toReturn += toString(t.getRight());     //recurse right
      return toReturn;
   }
}