// Name: J2-24   
// Date: 3/15/2021
 
import java.util.*;
import java.text.*;
 
interface PolynomialInterface
{
   public void makeTerm(Double exp, Double coef);
   public Map<Double, Double> getMap();
   public double evaluateAt(double x);
   public Polynomial add(Polynomial other);
   public Polynomial multiply(Polynomial other);
   public String toString();
   public String findDerivative();
   public String findIntegral();
   public double findRoot(Polynomial other);
   public String findMin(Polynomial other, double left, double right);
   public String findMax(Polynomial other, double left, double right);
}
 
class Polynomial implements PolynomialInterface
{
   Map<Double, Double> map = new TreeMap<Double, Double>();
   
   public Polynomial()
   {
      map = new TreeMap<Double, Double>();
   }
   
   public Polynomial(String expr)
   {
      map = new TreeMap<Double, Double>();
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
               if(term.contains("e^"))
               {
                  int indexOfCar = term.indexOf('^');
                  int indexOfX = term.indexOf('x');
                  double powerMult = 1.0;
                  
                  if(indexOfCar + 1 != indexOfX)
                     powerMult = Double.parseDouble(term.substring(indexOfCar + 1, indexOfX));
                     
                  double term2 = 2.718281828;
                  makeTerm(powerMult, term2);
               }
               
               else if(term.contains("sin"))
               {
                  int indexOfSin = term.indexOf('s');
                  int indexOfPar1 = term.indexOf('(');
                  int indexOfPar2 = term.indexOf(')');
                  int indexOfX = term.indexOf('x');
                  
                  double coef = 1.0;
                  int parCoef = 1;
                  int parExp = 1;
                  
                  if(indexOfSin != 0)
                     coef = Double.parseDouble(term.substring(0, indexOfSin));
                  
                  if(indexOfX != indexOfPar1 + 1)
                     parCoef = Integer.parseInt(term.substring(indexOfPar1 + 1, indexOfX));
                  
                  if(term.charAt(indexOfX + 1) == '^')
                     parExp = Integer.parseInt(term.substring(indexOfX + 1, indexOfPar2));
                  
                  String str = "";
                  if((parCoef != 1) && (parExp != 1))
                     str = parCoef + "" + Math.sin(1) + "" + parExp;
                  
                  else
                     str = Math.sin(1) + "";
                     
                  makeTerm(Double.parseDouble(str), coef);
               }
               
               else if(term.contains("cos"))
               {
                  int indexOfSin = term.indexOf('c');
                  int indexOfPar1 = term.indexOf('(');
                  int indexOfPar2 = term.indexOf(')');
                  int indexOfX = term.indexOf('x');
                  
                  double coef = 1.0;
                  int parCoef = 1;
                  int parExp = 1;
                  
                  if(indexOfSin != 0)
                     coef = Double.parseDouble(term.substring(0, indexOfSin));
                  
                  if(indexOfX != indexOfPar1 + 1)
                     parCoef = Integer.parseInt(term.substring(indexOfPar1 + 1, indexOfX));
                  
                  if(term.charAt(indexOfX + 1) == '^')
                     parExp = Integer.parseInt(term.substring(indexOfX + 1, indexOfPar2));
                  
                  String str = "";
                  if((parCoef != 1) && (parExp != 1))
                     str = parCoef + "" + Math.cos(1) + "" + parExp;
                  
                  else
                     str = Math.cos(1) + "";
                     
                  makeTerm(Double.parseDouble(str), coef);
               }
               
               else if(term.contains("tan"))
               {
                  int indexOfSin = term.indexOf('t');
                  int indexOfPar1 = term.indexOf('(');
                  int indexOfPar2 = term.indexOf(')');
                  int indexOfX = term.indexOf('x');
                  
                  double coef = 1.0;
                  int parCoef = 1;
                  int parExp = 1;
                  
                  if(indexOfSin != 0)
                     coef = Double.parseDouble(term.substring(0, indexOfSin));
                  
                  if(indexOfX != indexOfPar1 + 1)
                     parCoef = Integer.parseInt(term.substring(indexOfPar1 + 1, indexOfX));
                  
                  if(term.charAt(indexOfX + 1) == '^')
                     parExp = Integer.parseInt(term.substring(indexOfX + 1, indexOfPar2));
                  
                  String str = "";
                  if((parCoef != 1) && (parExp != 1))
                     str = parCoef + "" + Math.tan(1) + "" + parExp;
                  
                  else
                     str = Math.tan(1) + "";
                     
                  makeTerm(Double.parseDouble(str), coef);
               }
               
               else if(term.contains("x"))
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
      Map<Double, Double> other = expr.getMap();
      map = new TreeMap<Double, Double>(other);
   }
   
   public void makeTerm(Integer exp, Integer coef)
   {
      if((exp != 0) || (coef != 0))
      {
         if(map.containsKey(exp + 0.0))
         { 
            double toAdd = map.get(exp + 0.0) + 0.0;
            map.put(exp + 0.0, coef + toAdd);
         }
         
         else
            map.put(exp + 0.0, coef + 0.0);
      }
   }
   
   public void makeTerm(Integer exp, Double coef)
   {   
      if(map.containsKey(exp + 0.0))
      {
         double toAdd = map.get(exp + 0.0);
         map.put(exp + 0.0, coef + toAdd);
      }
      
      else
         map.put(exp + 0.0, coef + 0.0);
   }
   
   public void makeTerm(Double exp, Double coef)
   {
      if(map.containsKey(exp))
      {
         double toAdd = map.get(exp);
         map.put(exp, coef + toAdd);
      }
      
      else
         map.put(exp, coef);
   }
   
   public Map<Double, Double> getMap()
   {
      return map;
   }
   
   public double evaluateAt(double x)
   {
      double value = 0.0;
      
      for( double term : map.keySet() )
      {
         String strTerm = String.valueOf(term);
         double coef = map.get(term);
         double exp = 1.0;

         if(strTerm.contains("0.8414709848078965"))
         {
            int indexOfSin = strTerm.indexOf('0');
            int lastIndexOfSinPlusOne = indexOfSin + 18;
            
            double parCoef = 1;
            double parExp = 1;
            
            if(indexOfSin != 0)
               parCoef = Double.parseDouble(strTerm.substring(0, indexOfSin));
            
            if(lastIndexOfSinPlusOne != strTerm.length())
               parExp = Double.parseDouble(strTerm.substring(lastIndexOfSinPlusOne, strTerm.length()));
            
            double expVal = Math.pow(x, parExp);
            value += coef * Math.sin(parCoef * expVal);
         }
         
         else if(strTerm.contains("0.5403023058681398"))
         {
            int indexOfSin = strTerm.indexOf('0');
            int lastIndexOfSinPlusOne = indexOfSin + 18;
            
            double parCoef = 1;
            double parExp = 1;
            
            if(indexOfSin != 0)
               parCoef = Double.parseDouble(strTerm.substring(0, indexOfSin));
            
            if(lastIndexOfSinPlusOne != strTerm.length())
               parExp = Double.parseDouble(strTerm.substring(lastIndexOfSinPlusOne, strTerm.length()));
            
            double expVal = Math.pow(x, parExp);
            value += coef * Math.cos(parCoef * expVal);
         }
         
         else if(strTerm.contains("1.557"))
         {
            int indexOfSin = strTerm.indexOf('1');
            int lastIndexOfSinPlusOne = indexOfSin + 18;
            
            double parCoef = 1;
            double parExp = 1;
            
            if(indexOfSin != 0)
               parCoef = Double.parseDouble(strTerm.substring(0, indexOfSin));
            
            if(lastIndexOfSinPlusOne != strTerm.length())
               parExp = Double.parseDouble(strTerm.substring(lastIndexOfSinPlusOne, strTerm.length()));
            
            double expVal = Math.pow(x, parExp);
            value += coef * Math.tan(parCoef * expVal);
         }
         
         else if(coef != 2.718281828)
         {
            exp = Math.pow(x, term);
            value += coef * exp;
         }
         
         else
         {
            exp = Math.pow(coef, x);
            value += term * exp;
         }
      }
      
      return value;
   }
   
   public Polynomial add(Polynomial other)
   {
      Polynomial sum = new Polynomial(other);
      
      for( double term : map.keySet() )
      {
         double coef = map.get(term);
         sum.makeTerm(term + 0.0, coef);
      }
         
      return sum;
   }
   
   public Polynomial multiply(Polynomial other)
   {
      Polynomial product = new Polynomial();
      
      for( double term : map.keySet() )
      {
         for( double otherTerm : other.map.keySet())
         {
            double coefTerm = map.get(term);
            double coefOtherTerm = other.map.get(otherTerm);
            product.makeTerm(term + otherTerm + 0.0, coefTerm * coefOtherTerm);
         }
      }
         
      return product;
   }
   
   public String toString()
   {
      String toReturn = "";
      DecimalFormat df = new DecimalFormat("#0.0");
      
      ArrayList<Double> keys = new ArrayList<Double>();
      ArrayList<Double> values = new ArrayList<Double>();
      
      for( double term : map.keySet() )
      {
         keys.add(term);
         values.add(map.get(term));
      }
      
      int mapSize = keys.size();
      
      for(int i = mapSize - 1; i >= 0; i--)
      {
         boolean numPdKey = false;
         boolean numPdValue = false;
         String strKey = String.valueOf(keys.get(i));
         String strValue = String.valueOf(values.get(i));
         
         if(strKey.equals("0.5403023058681398"))
            toReturn += "cos(x)";
         
         else if(strKey.equals("0.8414709848078965"))
            toReturn += "sin(x)";
         
         else if(strKey.equals("1.5574077246549023"))
            toReturn += "tan(x)";
            
         else if(strValue.equals("2.718281828"))
            toReturn += "e^x";
         
         else
         { 
            if(strKey.contains("."))
            {
               int indexOfPeriod = strKey.indexOf('.');
               
               if(strKey.charAt(indexOfPeriod + 1) == '0')
               {
                  strKey = strKey.substring(0, indexOfPeriod);
                  int intKey = Integer.parseInt(strKey);
                  numPdKey = true;
               }
            }
            
            if(strValue.contains("."))
            {
               int indexOfPeriod = strValue.indexOf('.');
               
               if(strValue.charAt(indexOfPeriod + 1) == '0')
               {
                  strValue = strValue.substring(0, indexOfPeriod);
                  int intValue = Integer.parseInt(strValue);
                  numPdValue = true;
               }
            }
            
            if((!numPdKey) && (!numPdValue))
            {
               double key = keys.get(i);
               double value = values.get(i);
               
               if((value != 1.0) && (value != -1.0))
                  toReturn += df.format(value) + "";
               
               if(key >= 1)
                  toReturn += "x";
               
               if(key >= 2)
                  toReturn += "^" + df.format(key);
            }
            
            else if((numPdKey) && (!numPdValue))
            {
               int key = Integer.parseInt(strKey);
               double value = values.get(i);
               
               if((value != 1) && (value != -1))
                  toReturn += df.format(value) + "";
               
               if(key >= 1)
                  toReturn += "x";
               
               if(key >= 2)
                  toReturn += "^" + key;
            }
            
            else if((!numPdKey) && (numPdValue))
            {
               double key = keys.get(i);
               int value = Integer.parseInt(strValue);
               
               if((value != 1) && (value != -1))
                  toReturn += value + "";
               
               if(key >= 1)
                  toReturn += "x";
               
               if(key >= 2)
                  toReturn += "^" + df.format(key);
            }
            
            else
            {
               int key = Integer.parseInt(strKey);
               int value = Integer.parseInt(strValue);
               
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
            }
         }
         
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
   
   public String findDerivative()
   {
      String toReturn = "";
      DecimalFormat df = new DecimalFormat("#0.0");
      
      ArrayList<Double> keys = new ArrayList<Double>();
      ArrayList<Double> values = new ArrayList<Double>();
      
      for( double term : map.keySet() )
      {
         keys.add(term);
         values.add(map.get(term));
      }
      
      int mapSize = keys.size();
      
      for(int i = mapSize - 1; i >= 0; i--)
      {
         boolean numPdKey = false;
         boolean numPdValue = false;
         String strKey = String.valueOf(keys.get(i));
         String strValue = String.valueOf(values.get(i));
         
         if(strKey.equals("0.5403023058681398"))
            toReturn += "-sin(x)";
         
         else if(strKey.equals("0.8414709848078965"))
            toReturn += "cos(x)";
         
         else if(strKey.equals("1.5574077246549023"))
            toReturn += "sec(2x)";
            
         else if(strValue.equals("2.718281828"))
            toReturn += "e^x";
         
         else
         { 
            if(strKey.contains("."))
            {
               int indexOfPeriod = strKey.indexOf('.');
               
               if(strKey.charAt(indexOfPeriod + 1) == '0')
               {
                  strKey = strKey.substring(0, indexOfPeriod);
                  int intKey = Integer.parseInt(strKey);
                  numPdKey = true;
               }
            }
            
            if(strValue.contains("."))
            {
               int indexOfPeriod = strValue.indexOf('.');
               
               if(strValue.charAt(indexOfPeriod + 1) == '0')
               {
                  strValue = strValue.substring(0, indexOfPeriod);
                  int intValue = Integer.parseInt(strValue);
                  numPdValue = true;
               }
            }
            
            if((!numPdKey) && (!numPdValue))
            {
               double key = Double.parseDouble(strKey);
               double value = Double.parseDouble(strValue);
               
               double product = key * value;
               
               if(product < 0)
               {
                  toReturn += product;
                  
                  if(key > 1)
                     toReturn += "x^" + (key - 1);
               }
               
               else if(product > 0)
               {
                  if(i == mapSize - 1)
                     toReturn += product + "";
                  
                  else
                     toReturn += "+" + product;
                     
                  if(key > 1)
                     toReturn += "x^" +  (key - 1);
               }
            }
            
            else if((numPdKey) && (!numPdValue))
            {
               int key = Integer.parseInt(strKey);
               double value = Double.parseDouble(strValue);
               
               double product = key * value;
               
               if(product < 0)
               {
                  toReturn += product;
                  
                  if(key >= 2)
                     toReturn += "x";
                  
                  if(key >= 3)
                     toReturn += "^" + (key - 1);
               }
                
               else if(product > 0)
               {
                  if(i == mapSize - 1)
                     toReturn += product + "";
                  
                  else
                     toReturn += "+" + product;
                     
                  if(key >= 2)
                     toReturn += "x";
                  
                  if(key >= 3)
                     toReturn += "^" + (key - 1);
               }
               
               toReturn += " ";
            }
            
            else if((!numPdKey) && (numPdValue))
            {
               double key = Double.parseDouble(strKey);
               int value = Integer.parseInt(strValue);
               
               double product = key * value;
               
               if(product < 0)
               {
                  toReturn += product;
                  
                  if(key > 1)
                     toReturn += "x^" +  (key - 1);
               }
                
               else if(product > 0)
               {
                  if(i == mapSize - 1)
                     toReturn += product + "";
                  
                  else
                     toReturn += "+" + product;
                     
                  if(key > 1)
                     toReturn += "x^" +  (key - 1);
               }
               
               toReturn += " ";
            }
            
            else
            {
               int key = Integer.parseInt(strKey);
               int value = Integer.parseInt(strValue);
               
               int product = key * value;
               
               if(product < 0)
               {
                  toReturn += product;
                  
                  if(key >= 2)
                     toReturn += "x";
                  
                  if(key >= 3)
                     toReturn += "^" + (key - 1);
               }
                
               else if(product > 0)
               {
                  if(i == mapSize - 1)
                     toReturn += product + "";
                  
                  else
                     toReturn += "+" + product;
                     
                  if(key >= 2)
                     toReturn += "x";
                  
                  if(key >= 3)
                     toReturn += "^" + (key - 1);
               }
               
               toReturn += " ";
            }            
         }
      }
   
      if(toReturn.length() == 0)
         toReturn = "0";
         
      toReturn = toReturn.trim();
      return toReturn;
   }
   
   public String findIntegral()
   {
      String toReturn = "";
      DecimalFormat df = new DecimalFormat("#0.0");
      
      ArrayList<Double> keys = new ArrayList<Double>();
      ArrayList<Double> values = new ArrayList<Double>();
      
      for( double term : map.keySet() )
      {
         keys.add(term);
         values.add(map.get(term));
      }
      
      int mapSize = keys.size();
      
      for(int i = mapSize - 1; i >= 0; i--)
      {
         boolean numPdKey = false;
         boolean numPdValue = false;
         String strKey = String.valueOf(keys.get(i));
         String strValue = String.valueOf(values.get(i));
         
         if(strKey.equals("0.5403023058681398"))
            toReturn += "sin(x)";
         
         else if(strKey.equals("0.8414709848078965"))
            toReturn += "-cos(x)";
         
         else if(strKey.equals("1.5574077246549023"))
            toReturn += "ln|cos(x)|";
            
         else if(strValue.equals("2.718281828"))
            toReturn += "e^x";
         
         else
         { 
            if(strKey.contains("."))
            {
               int indexOfPeriod = strKey.indexOf('.');
               
               if(strKey.charAt(indexOfPeriod + 1) == '0')
               {
                  strKey = strKey.substring(0, indexOfPeriod);
                  int intKey = Integer.parseInt(strKey);
                  numPdKey = true;
               }
            }
            
            if(strValue.contains("."))
            {
               int indexOfPeriod = strValue.indexOf('.');
               
               if(strValue.charAt(indexOfPeriod + 1) == '0')
               {
                  strValue = strValue.substring(0, indexOfPeriod);
                  int intValue = Integer.parseInt(strValue);
                  numPdValue = true;
               }
            }
            
            if((!numPdKey) && (!numPdValue))
            {
               double key = Double.parseDouble(strKey);
               double value = Double.parseDouble(strValue);
               
               double division = value / (key + 1);
               String strDouble = String.valueOf(division);
               boolean strInt = false;
               int divisionInt = 0;
               
               if(strDouble.contains(".0"))
               {
                  strInt = true;
                  divisionInt = Integer.parseInt(strDouble.substring(0, strDouble.indexOf('.')));
               }
               
               if(division < 0)
               {
                  if(!strInt)
                     toReturn += division + "x";
                  
                  else
                     toReturn += divisionInt + "x";
                     
                  if(key >= 1)
                     toReturn += "^" + (key + 1);
               }
                
               else if(division > 0)
               {
                  if(!strInt)
                  {
                     if(i == mapSize - 1)
                        toReturn += division + "x";
                     
                     else
                        toReturn += "+" + division + "x";
                  }
                  
                  else
                  {
                     if(i == mapSize - 1)
                        toReturn += divisionInt + "x";
                     
                     else
                        toReturn += "+" + divisionInt + "x";
                  }
                  
                  if(key >= 1)
                     toReturn += "^" + (key + 1);
               }
            }
            
            else if((numPdKey) && (!numPdValue))
            {
               int key = Integer.parseInt(strKey);
               double value = Double.parseDouble(strValue);
               
               double division = value / (key + 1);
               String strDouble = String.valueOf(division);
               boolean strInt = false;
               int divisionInt = 0;
               
               if(strDouble.contains(".0"))
               {
                  strInt = true;
                  divisionInt = Integer.parseInt(strDouble.substring(0, strDouble.indexOf('.')));
               }
               
               if(division < 0)
               {
                  if(!strInt)
                     toReturn += division + "x";
                  
                  else
                     toReturn += divisionInt + "x";
                     
                  if(key >= 1)
                     toReturn += "^" + (key + 1);
               }
                
               else if(division > 0)
               {
                  if(!strInt)
                  {
                     if(i == mapSize - 1)
                        toReturn += division + "x";
                     
                     else
                        toReturn += "+" + division + "x";
                  }
                  
                  else
                  {
                     if(i == mapSize - 1)
                        toReturn += divisionInt + "x";
                     
                     else
                        toReturn += "+" + divisionInt + "x";
                  }
                  
                  if(key >= 1)
                     toReturn += "^" + (key + 1);
               }
            }
            
            else if((!numPdKey) && (numPdValue))
            {
               double key = Double.parseDouble(strKey);
               int value = Integer.parseInt(strValue);
               
               double division = value / (key + 1);
               String strDouble = String.valueOf(division);
               boolean strInt = false;
               int divisionInt = 0;
               
               if(strDouble.contains(".0"))
               {
                  strInt = true;
                  divisionInt = Integer.parseInt(strDouble.substring(0, strDouble.indexOf('.')));
               }
               
               if(division < 0)
               {
                  if(!strInt)
                     toReturn += division + "x";
                  
                  else
                     toReturn += divisionInt + "x";
                     
                  if(key >= 1)
                     toReturn += "^" + (key + 1);
               }
                
               else if(division > 0)
               {
                  if(!strInt)
                  {
                     if(i == mapSize - 1)
                        toReturn += division + "x";
                     
                     else
                        toReturn += "+" + division + "x";
                  }
                  
                  else
                  {
                     if(i == mapSize - 1)
                        toReturn += divisionInt + "x";
                     
                     else
                        toReturn += "+" + divisionInt + "x";
                  }
                  
                  if(key >= 1)
                     toReturn += "^" + (key + 1);
               }
            }
            
            else
            {
               int key = Integer.parseInt(strKey);
               int value = Integer.parseInt(strValue);
               
               double division = value / (key + 1);
               String strDouble = String.valueOf(division);
               boolean strInt = false;
               int divisionInt = 0;
               
               if(strDouble.contains(".0"))
               {
                  strInt = true;
                  divisionInt = Integer.parseInt(strDouble.substring(0, strDouble.indexOf('.')));
               }
               
               if(division < 0)
               {
                  if(!strInt)
                     toReturn += division + "x";
                  
                  else
                     toReturn += divisionInt + "x";
                     
                  if(key >= 1)
                     toReturn += "^" + (key + 1);
               }
                
               else if(division > 0)
               {
                  if(!strInt)
                  {
                     if(i == mapSize - 1)
                        toReturn += division + "x";
                     
                     else
                        toReturn += "+" + division + "x";
                  }
                  
                  else
                  {
                     if(i == mapSize - 1)
                        toReturn += divisionInt + "x";
                     
                     else
                        toReturn += "+" + divisionInt + "x";
                  }
                  
                  if(key >= 1)
                     toReturn += "^" + (key + 1);
               }
            }            
         }
      }
   
      if(toReturn.length() == 0)
         toReturn = "0";
         
      toReturn = toReturn.trim();
      return toReturn;
   }
   
   public double findRoot(Polynomial poly)
   {
      double left = -5.0;
      double right = 5.0;
      return findRoot(poly, left, right, 0);
   }
   
   private double findRoot(Polynomial poly, double left, double right, long time)
   {
      long startTime = System.nanoTime();
      
      if((left > right) || (time > 4))
         return -1.000001;
         
      double middle = (left + right) / 2;
      double d = poly.evaluateAt(middle);
      
      long endTime = System.nanoTime();
      long timeElapsed = endTime - startTime;
      
      if(d == 0.0)
         return middle;
      
      else if(d > 0)
         return findRoot(poly, left, middle, time + (timeElapsed / 1000000));
      
      else
         return findRoot(poly, middle, right, time + (timeElapsed / 1000000));
   }
   
   public String findMin(Polynomial poly, double left, double right)
   {
      if(left > right)
         return "-1";
      
      Polynomial poly2 = new Polynomial(poly);
      String str = poly2.findDerivative();
      Polynomial poly3 = new Polynomial(str);
      
      double evalAtLeft = poly.evaluateAt(left);
      double evalAtRight = poly.evaluateAt(right);
      double criticalVal = 0;
      criticalVal = poly3.findRoot(poly3); 
      double evalAtCritical = poly.evaluateAt(criticalVal);
      
      ArrayList<Double> vals = new ArrayList<Double>();
      vals.add(evalAtLeft);
      vals.add(evalAtRight);
      
      if(criticalVal != -1.000001)
         vals.add(evalAtCritical);

      Collections.sort(vals);
      
      double toCheck = vals.get(0);
      double xVal = 0;
      
      if(poly.evaluateAt(left) == toCheck)
         xVal = left;
      
      else if(poly.evaluateAt(right) == toCheck)
         xVal = right;
      
      else
         xVal = criticalVal;
         
      return "Minimum value on [" + left + "," + right + "] is " + toCheck + " at x=" + xVal;
   }
   
   public String findMax(Polynomial poly, double left, double right)
   {
      if(left > right)
         return "-1";
      
      Polynomial poly2 = new Polynomial(poly);
      String str = poly2.findDerivative();
      Polynomial poly3 = new Polynomial(str);
      
      double evalAtLeft = poly.evaluateAt(left);
      double evalAtRight = poly.evaluateAt(right);
      double criticalVal = 0;
      criticalVal = poly3.findRoot(poly3); 
      double evalAtCritical = poly.evaluateAt(criticalVal);
      
      ArrayList<Double> vals = new ArrayList<Double>();
      vals.add(evalAtLeft);
      vals.add(evalAtRight);
      
      if(criticalVal != -1.000001)
         vals.add(evalAtCritical);

      Collections.sort(vals);
      
      double toCheck = vals.get(vals.size() - 1);
      double xVal = 0;
      
      if(poly.evaluateAt(left) == toCheck)
         xVal = left;
      
      else if(poly.evaluateAt(right) == toCheck)
         xVal = right;
      
      else
         xVal = criticalVal;
         
      return "Maximum value on [" + left + "," + right + "] is " + toCheck + " at x=" + xVal;
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