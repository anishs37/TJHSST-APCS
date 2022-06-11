//Name: J2-24
//Date: 2/9/21

import java.util.*;

/*  Driver for the BXT class.
 *  Input: a postfix string with space delimited tokens. 
 */
public class BXT_Driver
{
   public static void main(String[] args)
   {
      ArrayList<String> postExp = new ArrayList<String>();
      postExp.add("3 4 + 8 * 5 2 ^ -");
      postExp.add("P + ( Q - R ) * A / B");
      postExp.add("a b + c / d -");
      postExp.add("14 -5 /");
      postExp.add("20.0 3.0 -4.0 + *");
      postExp.add("2 3 + 5 / 4 5 - *");
      postExp.add("5.6");
      
      for( String postfix : postExp )
      {
         System.out.println("Postfix Exp: "  + postfix);
         BXT tree = new BXT();
         tree.buildTree( postfix );
         System.out.println("BXT: "); 
         System.out.println( tree.display() );
         System.out.print("Infix order:  ");
         System.out.println( tree.inorderTraverse() );
         System.out.print("Prefix order:  ");
         System.out.println( tree.preorderTraverse() );
         System.out.print("Evaluates to " + tree.evaluateTree());
         System.out.println( "\n------------------------");
      }
      
     /*  extension:  prints parentheses */
     
      BXT tree1 = new BXT();
      tree1.buildTree("20.0 3.0 -4 + *");   // "20.0 * ( 3.0 + -4 )"
      System.out.println(tree1.inorderTraverseWithParentheses());  
      
      BXT tree2 = new BXT(); 
      tree2.buildTree("10 5 3 2 1 + * + * 5 +");     
      System.out.println(tree2.inorderTraverseWithParentheses());  
      
      BXT tree3 = new BXT(); 
      tree3.buildTree("3 2 1 - - 2 3 4 + 5 6 - * + -");     
      System.out.println(tree3.inorderTraverseWithParentheses());
      
      BXT tree4 = new BXT(); 
      tree4.buildTree("4 5 + 8 *");     
      System.out.println(tree4.inorderTraverseWithParentheses());
      
      BXT tree5 = new BXT(); 
      tree5.buildTree("1 2 3 4 5 + + + +");     
      System.out.println(tree5.inorderTraverseWithParentheses());
      
      BXT tree6 = new BXT(); 
      tree6.buildTree("4 2 + 3 5 1 - * +");     
      System.out.println(tree6.inorderTraverseWithParentheses());
   }
}

/***************************************

 Postfix Exp: 14 -5 /
 	-5
 /
 	14
 Infix order:  14 / -5 
 Prefix order:  / 14 -5 
 Evaluates to -2.8
 ------------------------
 Postfix Exp: 20.0 3.0 -4.0 + *
 		-4.0
 	+
 		3.0
 *
 	20.0
 Infix order:  20.0 * 3.0 + -4.0 
 Prefix order:  * 20.0 + 3.0 -4.0 
 Evaluates to -20.0
 ------------------------
 Postfix Exp: 2 3 + 5 / 4 5 - *
 		5
 	-
 		4
 *
 		5
 	/
 			3
 		+
 			2
 Infix order:  2 + 3 / 5 * 4 - 5 
 Prefix order:  * / + 2 3 5 - 4 5 
 Evaluates to -1.0
 ------------------------
 Postfix Exp: 5.6
 5.6
 Infix order:  5.6 
 Prefix order:  5.6 
 Evaluates to 5.6
 ------------------------
 
 *******************************************/