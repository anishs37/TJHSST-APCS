// Name: J2-24   
// Date: 3/15/2021
 
import java.util.*;
import java.text.*;
 
public class Polynomial_Driver
{
   public static void main(String[] args)
   {
      Polynomial poly = new Polynomial();
      poly.makeTerm(2, -4);
      poly.makeTerm(1, 3);
      
      Polynomial poly2 = new Polynomial();
      poly2.makeTerm(2, 4);
      poly2.makeTerm(1, -3);
      
      Polynomial poly3 = poly2.add(poly);
      System.out.println("" + poly3.getMap());
   }
}
 
interface PolynomialInterface
{
   public void makeTerm(Integer exp, Integer coef);
   public void makeTermOther(Integer exp, Integer coef);
   public Map<Integer, Integer> getMap();
   public double evaluateAt(double x);
   public Polynomial add(Polynomial other);
   public Polynomial multiply(Polynomial other);
   public String toString();
}
 
class Polynomial implements PolynomialInterface
{
   Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
   
   public Polynomial()
   {
      map = new TreeMap<Integer, Integer>();
   }
   
   public Polynomial(String expr)
   {
      map = new TreeMap<Integer, Integer>();
      String[] terms = expr.split(" ");
      
      for( String term : terms )
      {
         int termLength = term.length();
         int termLengthMinusOne = termLength - 1;
         int termLengthMinusThree = termLength - 3;
         
         if(!term.contains("+"))
         {  
            if(term.contains("x^"))
            {
               int term1 = Integer.parseInt(term.substring(termLengthMinusOne, termLength));
               int term2 = Integer.parseInt(term.substring(0, termLengthMinusThree));
               makeTerm(term1, term2);
            }
            
            else
            {
               if(term.contains("x"))
               {
                  int term2 = Integer.parseInt(term.substring(0, termLengthMinusOne));
                  makeTerm(1, term2);
               }
               
               else
               {
                  int term2 = Integer.parseInt(term);
                  makeTerm(0, term2);
               }
            }
         }
      }
   }
   
   public Polynomial(Polynomial expr)
   {
      Map<Integer, Integer> other = expr.getMap();
      map = new TreeMap<Integer, Integer>(other);
   }
   
   public void makeTerm(Integer exp, Integer coef)
   {
      if(coef != 0)
         map.put(exp, coef);
      
      else
         map.put(0, 0);
   }
   
   public void makeTermOther(Integer exp, Integer coef)
   {
      if(map.containsKey(exp))
      { 
         int toAdd = map.get(exp);
         
         if(coef + toAdd != 0)
            map.put(exp, coef + toAdd);
         
         else
         {
            Iterator<Integer> it = map.keySet().iterator();
            
            while(it.hasNext())
            {
               int expIt = it.next();
               
               if(expIt == exp)
                  it.remove();
            }
            
            map.put(0, 0);
         }
      }
      
      else
      {
         if(coef != 0)
            map.put(exp, coef);
         
         else if(map.size() == 0)
            map.put(0, 0);
      }
   }
   
   public Map<Integer, Integer> getMap()
   {
      if(map.size() > 1)
      {
         Iterator<Integer> it = map.keySet().iterator();
               
         while(it.hasNext())
         {
            int expIt = it.next();
            int coefIt = map.get(expIt);
            
            if(coefIt == 0)
               it.remove();
         }
      }
      
      return map;
   }
   
   public double evaluateAt(double x)
   {
      double value = 0.0;
      
      for( int term : map.keySet() )
      {
         int coef = map.get(term);
         double exp = Math.pow(x, term);
         value += coef * exp;
      }
      
      return value;
   }
   
   public Polynomial add(Polynomial other)
   {
      Polynomial sum = new Polynomial(other);
      
      for( int term : map.keySet() )
      {
         int coef = map.get(term);
         sum.makeTermOther(term, coef);
      }
         
      return sum;
   }
   
   public Polynomial multiply(Polynomial other)
   {
      Polynomial product = new Polynomial();
      
      for( int term : map.keySet() )
      {
         for( int otherTerm : other.map.keySet())
         {
            int coefTerm = map.get(term);
            int coefOtherTerm = other.map.get(otherTerm);
            product.makeTermOther(term + otherTerm, coefTerm * coefOtherTerm);
         }
      }
         
      return product;
   }
   
   public String toString()
   {
      String toReturn = "";
      DecimalFormat df = new DecimalFormat("#0.0");
      
      ArrayList<Integer> keys = new ArrayList<Integer>();
      ArrayList<Integer> values = new ArrayList<Integer>();
      
      for( int term : map.keySet() )
      {
         keys.add(term);
         values.add(map.get(term));
      }
      
      int mapSize = keys.size();
      
      for(int i = mapSize - 1; i >= 0; i--)
      {
         int key = keys.get(i);
         int value = values.get(i);
         
         if(value != 0)
         {
            if((value != 1) && (value != -1))
               toReturn += value + "";
            
            if((value == -1) || (value == 1))
            {
               if(value == -1)
               {
                  if(key == 0)
                     toReturn += value + "";
                   
                  else
                     toReturn += "-";
               }
               
               if(value == 1)
                  if(key == 0)
                     toReturn += value + "";
            }
               
            if(key >= 1)
               toReturn += "x";
            
            if(key >= 2)
               toReturn += "^" + key;
         }
         
         else if((i == 0) && (toReturn.length() > 3))
            toReturn = toReturn.substring(0, toReturn.length() - 3);
         
         if(i != 0)
         {
            if((toReturn.length() < 2) && (toReturn.length() != 0))
               toReturn += " + ";
            
            else if(toReturn.length() >= 2)
               if(toReturn.charAt(toReturn.length() - 2) != '+')
                  toReturn += " + ";
         }
      }
      
      toReturn = toReturn.trim();
      
      if(toReturn.length() == 0)
         toReturn = "0";
         
      return toReturn;
   }
}

/***************************************  
  ----jGRASP exec: java Polynomial_teacher
 Map:  {0=2, 1=-4, 3=2}
 String:  2x^3 + -4x + 2
 Evaluated at 2.0: 10.0
 -----------
 Map:  {0=-3, 1=-5, 2=1, 4=2}
 String:  2x^4 + x^2 + -5x + -3
 Evaluated at -10.5: 24469.875
 -----------
 Sum: 2x^4 + 2x^3 + x^2 + -9x + -1
 Product:  4x^7 + -6x^5 + -6x^4 + -10x^3 + 22x^2 + 2x + -6
 
  ----jGRASP: operation complete.
 ********************************************/