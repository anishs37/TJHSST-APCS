// Name: J2-24
// Date: 10/8/2020
    
import java.util.*;
  
public class Hailstone
{
   public static void main(String[] args)
   {
      System.out.println("Hailstone Numbers!");
      System.out.print("Enter the start value: ");
      Scanner sc = new Scanner(System.in);
      int start = sc.nextInt();
      int count = hailstone(start, 1);
      System.out.println(" With count variable, it takes " + count + " steps." );
      int count2 = hailstone(start);
      System.out.println(" Without count variable, it takes " + count2 + " steps." );
   }
      
   /**
    * Prints the hailstone sequence that starts with n.
    * Pre-condition: n > 0, count = 1.
    * This method is recursive.
    * If n is even, then the next number is n / 2.
    * If n is odd, then the next number is 3 * n + 1.
    * @param n The beginning hailstone number.
    * @param count The helper variable that counts the steps.
    * @returns An int representing the number of steps from n to 1.
    */
   public static int hailstone(int n, int count)
   {
      if(n == 1)
      {
         System.out.println(1);
         return count;
      }
      
      System.out.print(n + "-");
      
      if(n % 2 == 0)
         return hailstone(n / 2, count + 1);
      
      else
         return hailstone((3 * n) + 1, count + 1);     
 
   }
  
   /**
    * Prints the hailstone sequence that starts with n.
    * This method does not use a variable to count the steps.
    * Pre-condition: n > 0.
    * This method is recursive.
    * If n is even, then the next number is n / 2.
    * If n is odd, then the next number is 3 * n + 1.
    * @param n The beginning hailstone number.
    * @returns An int representing the number of steps from n to 1.
    */
   public static int hailstone(int n)
   {
      if(n == 1)
      {
         System.out.print(1);
         return 1;
      }
      
      System.out.print(n + "-");
      
      if(n % 2 == 0)
         return 1 + hailstone(n / 2);
      
      else
         return 1 + hailstone((3 * n) + 1);
   }
}

/*
------------SAMPLE RUN----------------------

Hailstone Numbers!
Enter the start value: 12
12-6-3-10-5-16-8-4-2-1 With count variable, it takes 10 steps.
12-6-3-10-5-16-8-4-2-1 Without count variable, it takes 10 steps.

Hailstone Numbers!
Enter the start value: 100
100-50-25-76-38-19-58-29-88-44-22-11-34-17-52-26-13-40-20-10-5-16-8-4-2-1 With count variable, it takes 26 steps.
100-50-25-76-38-19-58-29-88-44-22-11-34-17-52-26-13-40-20-10-5-16-8-4-2-1 Without count variable, it takes 26 steps.

*/
