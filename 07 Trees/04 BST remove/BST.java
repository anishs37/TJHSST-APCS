// Name: J2-24
// Date: 2/10/2021

interface BSTinterface
{
   public int size();
   public boolean contains(String obj);
   public void add(String obj);
   //public void addBalanced(String obj);
   public void remove(String obj);
   public String min();
   public String max();
   public String display();
   public String toString();
}

/*******************
Copy your BST code.  Implement the remove() method.
Test it with BST_Delete.java
**********************/
public class BST implements BSTinterface
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
   
   public void remove(String target)
   {
      root = remove(root, target);
      size--;
   }
   
   private TreeNode remove(TreeNode current, String target)
   {      
      if(current == null)
         return null;
      
      String toCheck = String.valueOf(current.getValue());
      
      if(toCheck.compareTo(target) > 0)
      {
         TreeNode toSetLeft = remove(current.getLeft(), target);
         current.setLeft(toSetLeft);
      }
      
      else if(toCheck.compareTo(target) < 0)
      {
         TreeNode toSetRight = remove(current.getRight(), target);
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
         
         String toSetValue = max(current.getLeft()); 
         current.setValue(toSetValue); 
         
         current.setLeft(remove(current.getLeft(), String.valueOf(current.getValue())));
         return current;
      }
      
      return current;
   }
   
   public TreeNode find(TreeNode root, Comparable target)
   {
      if(root == null)
         return null;
      
      else if(target.compareTo((Comparable) root.getValue()) == 0)
         return root;
      
      else if(target.compareTo((Comparable) root.getValue()) < 0)
         return find(root.getLeft(), target);
      
      else
         return find(root.getRight(), target);
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
   
   public String min()
   {
      return min(root);
   }
   
   private String min(TreeNode t)  //use iteration
   {
      if(t == null)
         return null;
      
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
         return null;
      
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
}