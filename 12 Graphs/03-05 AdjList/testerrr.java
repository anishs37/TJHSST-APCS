import java.util.*;
import java.io.*;

public class testerrr
{
   public static void main(String[] args)
   {
      ArrayList<Integer> test = new ArrayList<Integer>();
      test.add(0);
      test.add(3);
      test.add(2);
      test.add(1);
      
      update(test);
      System.out.println(test);
   }
   
   public static void update(ArrayList<Integer> input)
   {
      for(int i = 0; i < input.size() - 2; i++)
      {
         input.remove(i);
      }
   }
}