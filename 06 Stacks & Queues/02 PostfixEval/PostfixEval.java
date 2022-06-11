// Name: J2-24
// Date: 1/8/2021

import java.util.*;

public class PostfixEval
{
   public static final String operators = "+ - * / % ^ !";
   
   public static void main(String[] args)
   {
      System.out.println("Postfix  -->  Evaluate");
      ArrayList<String> postfixExp = new ArrayList<String>();
      
      /*  build your list of expressions here  */
      
      postfixExp.add("3 4 5 * +");
      postfixExp.add("3 4 * 5 +");
      postfixExp.add("1.3 2.7 + -6 6 * +");
      postfixExp.add("33 -43 + -55 65 + *");
      postfixExp.add("3 4 * 5 2 / + 5 -");
      postfixExp.add("8 1 2 * + 9 3 / -");
      postfixExp.add("3 4 5 * 6 + *");
      postfixExp.add("3 4 5 - 6 2 * - +");
      postfixExp.add("2 7 3 % +");
      postfixExp.add("2 7 + 3 %");
      postfixExp.add("2 3 ^");
      postfixExp.add("2 -2 ^");
      postfixExp.add("5 !");
      postfixExp.add("1 1 1 1 1 + + + + !");
      
      for( String pf : postfixExp )
      {
         System.out.println(pf + "\t\t" + eval(pf));
      }
   }
   
   public static double eval(String pf)
   {
      List<String> postfixParts = new ArrayList<String>(Arrays.asList(pf.split(" ")));
      Stack<Double> nums = new Stack<Double>();

      for(int i = 0; i < postfixParts.size(); i++)
      {
         String str = postfixParts.get(i);
         
         if(isOperator("" + str))
         {
            if(!str.equals("!"))
               nums.push(eval(nums.pop(), nums.pop(), str));
            
            else
            {
               double fact = nums.pop();
               nums.push(eval(fact, fact, str));
            }
         }
         
         else
            nums.push(Double.parseDouble(str));
      }
      
      return nums.peek();   
   }
   
   public static double eval(double a, double b, String ch)
   {      
      char charac = ch.charAt(0);
      
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
   
   public static boolean isOperator(String op)
   {
      if(operators.contains(op))
         return true;

      return false;
   }
}

/**********************************************
Postfix  -->  Evaluate
 3 4 5 * +		23
 3 4 * 5 +		17
 10 20 + -6 6 * +		-6
 3 4 + 5 6 + *		77
 3 4 5 + * 2 - 5 /		5
 8 1 2 * + 9 3 / -		7
 2 3 ^		8
 20 3 %		2
 21 3 %		0
 22 3 %		1
 23 3 %		2
 5 !		120
 1 1 1 1 1 + + + + !		120
 
 
 *************************************/