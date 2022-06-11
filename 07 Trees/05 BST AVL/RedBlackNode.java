//Name: J2-24
//Date: 2/26/2021

//Modified TreeNode class for use in BST AVL Extension #1
//RedBlackNode class used in RedBlackTreeImplementation.java (for BST AVL Extension #1)

class RedBlackNode
{
   public Comparable value; 
   public RedBlackNode left, right, parent;
   public int color;
   
   public RedBlackNode(Comparable initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }

   public RedBlackNode(Comparable initValue, RedBlackNode initLeft, RedBlackNode initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }

   public Comparable getValue()
   { 
      return value; 
   }

   public RedBlackNode getLeft() 
   { 
      return left; 
   }

   public RedBlackNode getRight() 
   { 
      return right; 
   }

   public void setValue(Comparable theNewValue) 
   { 
      value = theNewValue; 
   }

   public void setLeft(RedBlackNode theNewLeft) 
   { 
      left = theNewLeft;
      
      if(theNewLeft != null)
         theNewLeft.parent = this;   //updating parent reference
   }

   public void setRight(RedBlackNode theNewRight)
   { 
      right = theNewRight;
      
      if(theNewRight != null)
         theNewRight.parent = this;  //updating parent reference
   }
   
   public void adderNode(RedBlackNode theNewNode)
   {
      Comparable valToCompareTo = theNewNode.getValue();
      int valDiff = valToCompareTo.compareTo(value);
      
      if(valDiff != 0)
      {
         if(valDiff < 0)
         {
            if(left == null)
            {
               left = theNewNode;
               left.parent = this;  //Updating parent reference
            }
            
            else
               left.adderNode(theNewNode);
         }
         
         else
         {
            if(right == null)
            {
               right = theNewNode;
               right.parent = this; //Updating parent reference
            }
            
            else
               right.adderNode(theNewNode);
         }
      }
      
      else
         right.adderNode(theNewNode);
   }
}