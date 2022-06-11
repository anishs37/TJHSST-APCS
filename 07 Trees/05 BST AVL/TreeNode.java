 /* TreeNode class for the AP Exams */
 //ejurj modified with an extra field height

   public class TreeNode
   {
      private Object value; 
      private TreeNode left, right;
      private int height; 

      public TreeNode(Object initValue)
      { 
         height = 0;
         value = initValue; 
         left = null; 
         right = null; 
      }
   
      public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
      { 
         value = initValue; 
         left = initLeft; 
         right = initRight; 
      }
      
      public void setHeight(int h) 
      {
	      this.height = h;
      }

      public int getHeight() 
      {
	      return this.height;
      }

      public Object getValue()
      { 
         return value; 
      }
   
      public TreeNode getLeft() 
      { 
         return left; 
      }
   
      public TreeNode getRight() 
      { 
         return right; 
      }
   
      public void setValue(Object theNewValue) 
      { 
         value = theNewValue; 
      }
   
      public void setLeft(TreeNode theNewLeft) 
      {
         left = theNewLeft;
      }
   
      public void setRight(TreeNode theNewRight)
      { 
         right = theNewRight;
      }
   }