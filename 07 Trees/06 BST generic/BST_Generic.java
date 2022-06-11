// Name: J2-24
// Date: 2/12/2021
import java.util.*;

interface BSTinterface<E>
{
   public int size();
   public boolean contains(E element);
   public E add(E element);
   //public E addBalanced(E element);
   public E remove(E element);
   public E min();
   public E max();
   public String display();
   public String toString();
   public List<E> toList();  //returns an in-order list of E
}

/*******************
  Copy your BST code.  Implement generics.
**********************/
public class BST_Generic<E extends Comparable<E>> implements BSTinterface<E>
{
   private TreeNode<E> root;
   private int size;
   
   public BST_Generic()
   {
      root = null;
      size = 0;
   }
   
   public int size()
   {
      return size;
   }
   
   public TreeNode<E> getRoot()   //for Grade-It
   {
      return root;
   }
   
   /***************************************
   @param s -- one string to be inserted
   ****************************************/
   
   public E add(E s) 
   {
      root = add(root, s);
      size++;
      return s;
   }
   
   private TreeNode<E> add(TreeNode<E> t, E s) //recursive helper method
   {
      if(t == null)
      {
         TreeNode<E> nullReturn = new TreeNode(s, null, null);
         return nullReturn;
      }
      
      E toCheck = t.getValue();
      
      if(toCheck.compareTo(s) >= 0)
         t.setLeft(add(t.getLeft(), s));
      
      else
         t.setRight(add(t.getRight(), s));
      
      return t;
   }
   
   public E remove(E target)
   {
      root = remove(root, target);
      size--;
      return target;
   }
   
   private TreeNode<E> remove(TreeNode<E> current, E target)
   {      
      if(current == null)
         return null;
      
      E toCheck = current.getValue();
      
      if(toCheck.compareTo(target) > 0)
      {
         TreeNode<E> toSetLeft = remove(current.getLeft(), target);
         current.setLeft(toSetLeft);
      }
      
      else if(toCheck.compareTo(target) < 0)
      {
         TreeNode<E> toSetRight = remove(current.getRight(), target);
         current.setRight(toSetRight);
      }
      
      else
      {
         if((current.getLeft() == null) && (current.getRight() == null))
            return null;
         
         else if(current.getLeft() == null)
            return current.getRight();
         
         else if(current.getRight() == null)
            return current.getLeft();

         E toSetValue = max(current.getLeft()); 
         current.setValue(toSetValue); 
         
         current.setLeft(remove(current.getLeft(), current.getValue()));
         return current;
      }
      
      return current;
   }
   
   public String display()
   {
      return display(root, 0);
   }
   
   private String display(TreeNode<E> t, int level) //recursive helper method
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
   
   public boolean contains(E obj)
   {
      return contains(root, obj);
   }
   
   private boolean contains(TreeNode<E> t, E x) //recursive helper method
   {
      if(t == null)
         return false;
      
      E toCheck = t.getValue();
      
      while(t != null)
      {
         if(toCheck.compareTo(x) == 0)
            return true;
            
         else if(toCheck.compareTo(x) > 0)
            return contains(t.getLeft(), x);
        
         else
            return contains(t.getRight(), x);       
      }
      
      return false;
   }
   
   public E min()
   {
      return min(root);
   }
   
   private E min(TreeNode<E> t)  //use iteration
   {
      if(t == null)
         return null;
      
      TreeNode<E> temp = t;
      
      while(temp.getLeft() != null)
         temp = temp.getLeft();
      
      return temp.getValue();
   }
   
   public E max()
   {
      return max(root);
   }
   
   private E max(TreeNode<E> t)  //recursive helper method
   {
      if(t == null)
         return null;
      
      else
      {
         if(t.getRight() == null)
            return t.getValue();
         
         else
            return max(t.getRight());
      }
   }
   
   public String toString()
   {
      return toString(root);
   }
   
   private String toString(TreeNode<E> t)  //an in-order traversal.  Use recursion.
   {
      String toReturn = "";
      
      if(t == null)
         return "";
      
      toReturn += toString(t.getLeft());      //recurse left	       						 		
      toReturn += t.getValue() + " ";         //inorder visit
      toReturn += toString(t.getRight());     //recurse right
      return toReturn;
   }
   
   public List<E> toList()
   {
      return toList(root);
   }
   
   private List<E> toList(TreeNode<E> t)
   {      
      if(t == null)
         return null;
      
      ArrayList<E> toReturn = new ArrayList<E>();
      
      if(t.getLeft() != null)
         toReturn.addAll(toList(t.getLeft()));
      
      toReturn.add(t.getValue());
      
      if(t.getRight() != null)
         toReturn.addAll(toList(t.getRight()));
      
      return toReturn;
   }
}

/*******************
  Copy your TreeNode code.  Implement generics.
**********************/
class TreeNode<E>
{
   private E value; 
   private TreeNode<E> left, right;
   
   public TreeNode(E initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }
   
   public TreeNode(E initValue, TreeNode<E> initLeft, TreeNode<E> initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
   
   public E getValue()
   { 
      return value; 
   }
   
   public TreeNode<E> getLeft() 
   { 
      return left; 
   }
   
   public TreeNode<E> getRight() 
   { 
      return right; 
   }
   
   public void setValue(E theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(TreeNode<E> theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(TreeNode<E> theNewRight)
   { 
      right = theNewRight;
   }
}