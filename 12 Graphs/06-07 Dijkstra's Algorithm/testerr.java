import java.util.*;
import java.io.*;

public class testerr
{
   public static void main(String[] args)
   {
      PriorityQueue<String> q = new PriorityQueue<String>();
      q.add("M");
      q.add("A");
      q.add("N");
      q.add("G");
      q.add("O");
      q.add("E");
      q.add("S");
   }
   
   public static List<Integer> process1(int n)
   {
      ArrayList<Integer> someList = new ArrayList<>();
      for(int k = 0; k < n; k++)
      {
         someList.add(0, new Integer(k));
      }
      return someList;
   }
   
   public static List<Integer> process2(int n)
   {
      LinkedList<Integer> someList = new LinkedList<>();
      for(int k = 0; k < n; k++)
      {
         someList.add(0, new Integer(k));
      }
      return someList;
   }
}