// Name: J2-24
// Date: 1/13/2021
//uses PostfixEval
 
import java.util.*;
public class InfixPostfixEval
{
   public static final String LEFT  = "([{<";
   public static final String RIGHT = ")]}>";
   public static final String operators = "+ - * / % ^ !";
   
   public static void main(String[] args)
   {
      System.out.println("Infix  \t-->\tPostfix\t\t-->\tEvaluate");
      ArrayList<String> infixExp = new ArrayList<String>();
      
      /*build your list of Infix expressions here  */
      
      /*infixExp.add("3 + 4 - 5 + 6");                           //3 4 + 5 - 6 +
      infixExp.add("3 + 4 * 5");                               //3 4 5 * +
      infixExp.add("3 * 4 + 5");                               //3 4 * 5 +
      infixExp.add("1.3 + 2.7 + -6 * 6");                      //1.3 2.7 + -6 6 * +
      infixExp.add("3 * 4 + 5 / 2 - 5");                       //3 4 * 5 2 / + 5 -
      infixExp.add("8 + 1 * 2 - 9 / 3");                       //8 1 2 * + 9 3 / -
      infixExp.add("2 + 7 % 3");                               //2 7 3 % +
      infixExp.add("( 33 + -43 ) * ( -55 + 65 )");             //33 -43 + -55 65 + *
      infixExp.add("3 * ( 4 * 5 + 6 )");                       //3 4 5 * 6 + *
      infixExp.add("3 + ( 4 - 5 - 6 * 2 )");                   //3 4 5 - 6 2 * - +
      infixExp.add("( 2 + 7 ) % 3");                           //2 7 + 3 %
      infixExp.add("( 3 + 4 ) * ( 5 + 6 )");                   //3 4 + 5 6 + *
      infixExp.add("( 3 * ( 4 + 5 ) - 2 ) / 5");               //3 4 5 + * 2 - 5 / */
      infixExp.add("5 + [ 2 - ( 1 + 3 ) + 4 % 3 ]");
      infixExp.add("4 - 3 + 2 + 5 * 2 / 3 % 2");
      
      for( String infix : infixExp )
      {
         String pf = infixToPostfix(infix);  //get the conversion to work first
         //System.out.println(infix + "\t\t\t" + pf );  
         System.out.println(infix + "\t\t\t" + pf + "\t\t\t" + PostfixEval.eval(pf));  //PostfixEval must work!
      }
   }
   
   public static String infixToPostfix(String infix)
   {
      List<String> nums = new ArrayList<String>(Arrays.asList(infix.split(" ")));
      Stack<String> opStack = new Stack<String>();
      String result = "";
      
      for(int i = 0; i < nums.size(); i++)
      {
         String str = nums.get(i) + "";
         char str_char = str.charAt(0);
         
         if((!operators.contains("" + str)) && (!LEFT.contains("" + str_char)) && (!RIGHT.contains("" + str_char)))
            result += str + " ";
         
         else
         {
            if(!operators.contains("" + str))
            {
               if(LEFT.contains(str))
                  opStack.push(str);
               
               else if(RIGHT.contains(str))
               {
                  while(!LEFT.contains(opStack.peek()))
                  {
                        result += opStack.pop() + " ";
                  }
                  
                  opStack.pop();
               }
            }
            
            else
            {
               if(!opStack.isEmpty())
               {
                  char opStack_peek_char = opStack.peek().charAt(0);
                  
                  if(LEFT.contains("" + opStack_peek_char))
                     opStack.push(str);
                  
                  else
                  {   
                     if(!isLower(opStack_peek_char, str_char))
                     {
                        int l = 0;
                        while((!opStack.isEmpty()) && (l == 0))
                        {
                           char opStack_peek_char_1 = opStack.peek().charAt(0);
                           
                           if(!LEFT.contains("" + opStack_peek_char_1))
                              result += opStack.pop() + " ";
                           
                           else
                              l = 1;
                        }
                        
                        opStack.push(str);
                     }
                     
                     else
                        opStack.push(str);
                  }
               }
                  
               else
                  opStack.push(str);  
            }    
         }      
      }
      
      while(!opStack.isEmpty())
      {
         char opStack_peek_char_2 = opStack.peek().charAt(0);
         
         if(LEFT.contains("" + opStack_peek_char_2))
            opStack.pop();
         
         else
         {
            if(opStack.size() > 1)
               result += opStack.pop() + " ";
            
            else
               result += opStack.pop();
         }
      }
      
      return result;      
   }
   
   //returns true if c1 has strictly lower precedence than c2
   
   public static boolean isLower(char c1, char c2)
   {      
      if(((c1 == '+') || (c1 == '-')) && ((c2 == '*') || (c2 == '/') || (c2 == '%')))
         return true;
      
      else if(c1 == '%')
         return true;
            
      else if(c1 == c2)
         return false;
         
      return false;
   }
}
 
 
/********************************************
 
 Infix  	-->	Postfix		-->	Evaluate
 3 + 4 * 5			3 4 5 * + 			23.0
 3 * 4 + 5			3 4 5 + * 			27.0
 1.3 + 2.7 + -6 * 6			1.3 2.7 + -6 6 * + 			-32.0
 ( 33 + -43 ) * ( -55 + 65 )			33 -43 + -55 65 + * 			-100.0
 3 * 4 + 5 / 2 - 5			3 4 5 2 5 - / + * 			6.999999999999999
 8 + 1 * 2 - 9 / 3			8 1 2 9 3 / - * + 			7.0
 3 * ( 4 * 5 + 6 )			3 4 5 6 + * * 			132.0
 3 + ( 4 - 5 - 6 * 2 )			3 4 5 - 6 2 * - + 			-10.0
 2 + 7 % 3			2 7 3 % + 			3.0
 ( 2 + 7 ) % 3			2 7 + 3 % 			0.0
     
***********************************************/