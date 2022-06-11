// Name: J2-24
// Date: 2/9/21
/*  Represents a binary expression tree.
 *  The BXT builds itself from postorder expressions. It can
 *  evaluate and print itself.  Also prints inorder and postorder strings. 
 */
 
import java.util.*;

public class BXT
{
   private TreeNode root;   
   
   public BXT()
   {
      root = null;
   }
   
   public TreeNode getRoot()   //for Grade-It
   {
      return root;
   }
    
   public void buildTree(String str)
   {
      Stack<TreeNode> stk = new Stack<TreeNode>();
      
      for(String exp : str.split(" "))
      {
         if(isOperator(exp))
         {
            TreeNode temp = stk.pop();
            TreeNode expPushPopTemp = new TreeNode(exp, stk.pop(), temp);
            stk.push(expPushPopTemp);
         }
         
         else
         {
            TreeNode expPushNullNull = new TreeNode(exp, null, null);
            stk.push(expPushNullNull);
         }
      }
      
      root = stk.pop();
   }
   
   public double evaluateTree()
   {
      return evaluateNode(root);
   }
   
   private double evaluateNode(TreeNode t)  //recursive
   {
      if(t == null)
         return 0.0;
         
      String str = String.valueOf(t.getValue());
      
      if(isOperator(str))
         return computeTerm(str, evaluateNode(t.getRight()), evaluateNode(t.getLeft()));
         
      else
         return Double.parseDouble(str);
   }
   
   private double computeTerm(String s, double a, double b)
   {
      char charac = s.charAt(0);
      
      if(charac == '+')
         return a + b;
      
      else if(charac == '-')
         return b - a;
      
      else if(charac == '*')
         return a * b;
      
      else if(charac == '/')
         return b / a;
      
      else if(charac == '%')
         return b % a;
      
      else if(charac == '^')
      {
         double resPow = b;
         
         int i = 1;
         
         do
         {
            resPow *= b;
            i++;
         }
         
         while(i < a);
         
         if(a > 1.0)
            return resPow;
         
         else
            return (1 / resPow);
      }
      
      else if(charac == '!')
      {
         double product = 1.0;
         
         for(double i = b; i >= 2.0; i--)
            product *= i;
         
         return product;
      }
      
      return 0;
   }
   
   private boolean isOperator(String s)
   {
      String operators = "+ - * / % ^ !";
      
      if(operators.contains(s))
         return true;

      return false;
   }
   
   public String display()
   {
      return display(root, 0);
   }
   
   private String display(TreeNode t, int level)
   {
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
    
   public String inorderTraverse()
   {
      return inorderTraverse(root);
   }
   
   private  String inorderTraverse(TreeNode t)
   {
      String toReturn = "";
      
      if(t == null)
         return "";
      
      toReturn += inorderTraverse(t.getLeft());      //recurse left	       						 		
      toReturn += t.getValue() + " ";                //inorder visit
      toReturn += inorderTraverse(t.getRight());     //recurse right
      return toReturn;
   }
   
   public String preorderTraverse()
   {
      return preorderTraverse(root);
   }
   
   private String preorderTraverse(TreeNode root)
   {
      String toReturn = "";
      
      if(root == null)
         return "";
         
      toReturn += root.getValue() + " ";              //preorder visit
      toReturn += preorderTraverse(root.getLeft());   //recurse left
      toReturn += preorderTraverse(root.getRight());  //recurse right
      return toReturn;
   }
   
   private static int height(TreeNode t)
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
   
   /* extension */
  
   public String inorderTraverseWithParentheses()
   {
      return inorderTraverseWithParentheses(root);
   }

   private String inorderTraverseWithParentheses(TreeNode t)
   {
      /*if(t == null)
         return "";
      
      String toReturn = "";
      
      if(((t.getLeft() != null) && (isOperator(String.valueOf(t.getLeft().getValue())))) && (height(t.getLeft()) < 2))
         toReturn += "( " + inorderTraverseWithParentheses(t.getLeft()) + ") ";
      
      else
         toReturn += inorderTraverseWithParentheses(t.getLeft());
      
      toReturn += t.getValue() + " ";
      
      if(((t.getRight() != null) && (isOperator(String.valueOf(t.getRight().getValue())))) && (((height(t.getRight()) < 2)) || (height(t.getRight()) == 3)))
         toReturn += "( " + inorderTraverseWithParentheses(t.getRight()) + ") ";
      
      else
         toReturn += inorderTraverseWithParentheses(t.getRight());

      return toReturn;*/
      
      if(t == null)
         return "";
      
      String toReturn = "";;
      
      if((t.getLeft() != null) && (isOperator(String.valueOf(t.getLeft().getValue()))))
         toReturn += "( " + inorderTraverseWithParentheses(t.getLeft()) + ") ";
      
      else
         toReturn += inorderTraverseWithParentheses(t.getLeft());
      
      toReturn += t.getValue() + " ";
      
      if((t.getRight() != null) && (isOperator(String.valueOf(t.getRight().getValue()))))
         toReturn += "( " + inorderTraverseWithParentheses(t.getRight()) + ") ";
      
      else
         toReturn += inorderTraverseWithParentheses(t.getRight());
         
      return toReturn;
   }
}