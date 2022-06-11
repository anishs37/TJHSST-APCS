// Name: J2-24
// Date: 2/19/2021
 
/*Note: I have to uncomment my removeBalanced method, or else
  CodePost would give me errors. Please uncomment this (in interface BSTinterface)
  and the actual code below when testing my code. Thanks!) Additionally, please uncomment
  the one line of code in the stress test file I uploaded (I had to uncomment this,
  because CodePost would give me errors if I didn't.)
*/
 
interface BSTinterface
{
   public int size();
   public boolean contains(String obj);
   public void add(String obj);   //does not balance
   public void addBalanced(String obj);
   public void remove(String obj);//does not balance
   public void removeBalanced(String obj);
   public String min();
   public String max();
   public String display();
   public String toString();
}
 
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
   
   /*  start the addBalanced methods */
   public void addBalanced(String value)  
   {
      size++;
      root = add(root, value);
      root = balanceTree(root, value);   // for an AVL tree.  You may change this line.
   }
   
   private TreeNode balanceTree(TreeNode t, String s) 
   {
      if(t == null)
         return null;
      
      String currentVal = String.valueOf(t.getValue());
      
      if(currentVal.compareTo(s) >= 0)
         t.setLeft(balanceTree(t.getLeft(), s));
      
      else
         t.setRight(balanceTree(t.getRight(), s));
      
      //A positive height difference means left subtree has height greater than right subtree, 
      //and vice versa.
      
      int heightDifference = balanceFactor(t);
      
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
   
   public static int balanceFactor(TreeNode t)
   {
      if(t == null)
         return 0;
       
      return height(t.getLeft()) - height(t.getRight());
   }
   
   public void removeBalanced(String value)
   {
      size--;
      root = remove(root, value);
      root = balanceTreeForRemove(root, value);
   }
 
   private TreeNode balanceTreeForRemove(TreeNode t, String s) 
   {
      if(t == null)
         return null;
      
      String currentVal = String.valueOf(t.getValue());
      
      if(currentVal.compareTo(s) >= 0)
         t.setLeft(balanceTreeForRemove(t.getLeft(), s));
      
      else
         t.setRight(balanceTreeForRemove(t.getRight(), s));
        
      //A positive height difference means left subtree has height greater than right subtree, 
      //and vice versa (for int heightDifference)
      
      int heightDifference = balanceFactor(t);
      
      if(heightDifference > 1)
      {
         String toCheck = t.getValue() + "";
         
         if(s.compareTo(toCheck) <= 0)
            return rotateRightForRemove(t);
         
         else if(s.compareTo(toCheck) > 0)
         {
            t.setLeft(rotateLeftForRemove(t.getLeft()));
            return rotateRightForRemove(t);
         }
      }
      
      else if(heightDifference < -1)
      {
         String toCheck = t.getValue() + "";
         
         if(s.compareTo(toCheck) > 0)
            return rotateLeftForRemove(t);
         
         else if(s.compareTo(toCheck) <= 0)
         {
            t.setRight(rotateRightForRemove(t.getRight()));
            return rotateLeftForRemove(t);
         }
      }  
      
      return t;  
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
      /*TreeNode tCopy = t;
      TreeNode tCopyLeft = t.getLeft(); 
      TreeNode tCopyLeftGetRight = tCopyLeft.getRight(); 
  
      tCopyLeft.setRight(tCopy); 
      tCopy.setLeft(tCopyLeftGetRight);
      
      t = tCopy;
      
      return tCopyLeft;*/
      
      /*t.getLeft().setRight(t);
      t.setLeft( t.getLeft().getRight() );
      return t.getLeft();*/
      
      /*TreeNode c = t, b = t.getLeft(), a = t.getLeft().getLeft();
      TreeNode t0 = a.getLeft(),
               t1 = a.getRight(),
               t2 = b.getRight(),
               t3 = c.getRight();
      
      b.setRight(c);
      c.setLeft(t2);
      return b;*/   
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
   
   private TreeNode rotateLeftForRemove(TreeNode t)
   {    
      if((t.getRight() == null) || (t.getRight().getLeft() == null))
         return rotateLeft(t);
       
      TreeNode tCopy = t.getRight();
      TreeNode temp3 = tCopy.getLeft();
      
      tCopy.setLeft(t);
      t.setRight(temp3);
      
      return tCopy;
   }
   
   private TreeNode rotateRightForRemove(TreeNode t)
   {
      if((t.getLeft() == null) || (t.getLeft().getRight() == null))
         return rotateRight(t);
        
      TreeNode tCopy = t.getLeft();
      TreeNode temp3 = tCopy.getRight();
      
      tCopy.setRight(t);
      t.setLeft(temp3);
      
      return tCopy;
   }
}