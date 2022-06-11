// Name: J2-24
// Date: 3/5/2021

import java.util.*;
import java.io.*;

public class SetsOfLetters
{
   public static void main(String[] args) throws FileNotFoundException
   {
      String fileName = "declarationLast.txt";
      fillTheSets(fileName);
   }
   
   public static void fillTheSets(String fn) throws FileNotFoundException
   {
      Scanner infile = new Scanner(new File(fn));
      /*  enter your code here  */

      Set<Character> lowerIntersect = new TreeSet<Character>();
      Set<Character> upperIntersect = new TreeSet<Character>();
      Set<Character> otherIntersect = new TreeSet<Character>();
      
      int iter = 0;
      
      while(infile.hasNextLine())
      {
         System.out.println();
         String str = infile.nextLine();
         System.out.println(str);
         char[] charArr = str.toCharArray();
         
         Set<Character> lowerLetters = new TreeSet<Character>();
         Set<Character> upperLetters = new TreeSet<Character>();
         Set<Character> otherLetters = new TreeSet<Character>();
         
         for(char ch : charArr)
         {
            if(Character.isLowerCase(ch))
               lowerLetters.add(ch);
               
            else if(Character.isUpperCase(ch))
               upperLetters.add(ch);
            
            else
               if(ch != ' ')
                  otherLetters.add(ch);
            
            if(iter == 0)
            {
               if(Character.isLowerCase(ch))
                  lowerIntersect.add(ch);
                  
               else if(Character.isUpperCase(ch))
                  upperIntersect.add(ch);
               
               else
                  if(ch != ' ')
                     otherIntersect.add(ch);
            }
         }
            
         if(iter != 0)
         {
            Iterator<Character> itLower = lowerIntersect.iterator();
            Iterator<Character> itUpper = upperIntersect.iterator();
            Iterator<Character> itOther = otherIntersect.iterator();
            
            while(itLower.hasNext())
            {
               Character st = itLower.next();
               if(!lowerLetters.contains(st))
               {
                  lowerIntersect.remove(st);
                  itLower = lowerIntersect.iterator();
               }              
            }
            
            while(itUpper.hasNext())
            {
               Character st = itUpper.next();
               if(!upperLetters.contains(st))
               {
                  upperIntersect.remove(st);
                  itUpper = upperIntersect.iterator();
               }              
            }
            
            while(itOther.hasNext())
            {
               Character st = itOther.next();
               if(!otherLetters.contains(st))
               {
                  otherIntersect.remove(st);
                  itOther = otherIntersect.iterator();
               }              
            }
         }
         
         System.out.println("Lower Case: " + lowerLetters);
         System.out.println("Upper Case: " + upperLetters);
         System.out.println("Other: " + otherLetters);
         
         iter++;
      }
      
      System.out.println();
      
      System.out.println("Common Lower Case: " + lowerIntersect);
      System.out.println("Common Upper Case: " + upperIntersect);
      System.out.println("Common Other: " + otherIntersect);
   }
}

/***********************************
  ----jGRASP exec: java SetsOfLetters_teacher
 
 We, therefore, the Representatives of the united States of 
 Lower Case: [a, d, e, f, h, i, n, o, p, r, s, t, u, v]
 Upper Case: [R, S, W]
 Other: [ , ,]
 
 America, in General Congress, Assembled, appealing to the 
 Lower Case: [a, b, c, d, e, g, h, i, l, m, n, o, p, r, s, t]
 Upper Case: [A, C, G]
 Other: [ , ,]
 
 Supreme Judge of the world for the rectitude of our intentions,
 Lower Case: [c, d, e, f, g, h, i, l, m, n, o, p, r, s, t, u, w]
 Upper Case: [J, S]
 Other: [ , ,]
 
 do, in the Name, and by the Authority of the good People of 
 Lower Case: [a, b, d, e, f, g, h, i, l, m, n, o, p, r, t, u, y]
 Upper Case: [A, N, P]
 Other: [ , ,]
 
 these Colonies, solemnly publish and declare, That these United 
 Lower Case: [a, b, c, d, e, h, i, l, m, n, o, p, r, s, t, u, y]
 Upper Case: [C, T, U]
 Other: [ , ,]
 
 Colonies are, and of Right ought to be Free and Independent 
 Lower Case: [a, b, d, e, f, g, h, i, l, n, o, p, r, s, t, u]
 Upper Case: [C, F, I, R]
 Other: [ , ,]
 
 States; that they are Absolved from all Allegiance to the 
 Lower Case: [a, b, c, d, e, f, g, h, i, l, m, n, o, r, s, t, v, y]
 Upper Case: [A, S]
 Other: [ , ;]
 
 British Crown, and that all political connection between them 
 Lower Case: [a, b, c, d, e, h, i, l, m, n, o, p, r, s, t, w]
 Upper Case: [B, C]
 Other: [ , ,]
 
 and the State of Great Britain, is and ought to be totally 
 Lower Case: [a, b, d, e, f, g, h, i, l, n, o, r, s, t, u, y]
 Upper Case: [B, G, S]
 Other: [ , ,]
 
 dissolved; and that as Free and Independent States, they have 
 Lower Case: [a, d, e, h, i, l, n, o, p, r, s, t, v, y]
 Upper Case: [F, I, S]
 Other: [ , ,, ;]
 
 full Power to levy War, conclude Peace, contract Alliances, 
 Lower Case: [a, c, d, e, f, i, l, n, o, r, s, t, u, v, w, y]
 Upper Case: [A, P, W]
 Other: [ , ,]
 
 establish Commerce, and to do all other Acts and Things which 
 Lower Case: [a, b, c, d, e, g, h, i, l, m, n, o, r, s, t, w]
 Upper Case: [A, C, T]
 Other: [ , ,]
 
 Independent States may of right do. And for the support of this 
 Lower Case: [a, d, e, f, g, h, i, m, n, o, p, r, s, t, u, y]
 Upper Case: [A, I, S]
 Other: [ , .]
 
 Declaration, with a firm reliance on the protection of divine 
 Lower Case: [a, c, d, e, f, h, i, l, m, n, o, p, r, t, v, w]
 Upper Case: [D]
 Other: [ , ,]
 
 Providence, we mutually pledge to each other our Lives, our 
 Lower Case: [a, c, d, e, g, h, i, l, m, n, o, p, r, s, t, u, v, w, y]
 Upper Case: [L, P]
 Other: [ , ,]
 
 Fortunes and our sacred Honor.
 Lower Case: [a, c, d, e, n, o, r, s, t, u]
 Upper Case: [F, H]
 Other: [ , .]
 
 Common Lower Case: [d, e, n, o, r, t]
 Common Upper Case: []
 Common Other: [ ]
  ----jGRASP: operation complete.
  ************************************/