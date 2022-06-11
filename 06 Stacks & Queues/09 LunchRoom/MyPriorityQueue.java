//Name: J2-24
//Date: 1/31/20
//MyPriorityQueue - extension to LunchRoom
//LunchRoom is the driver

import java.util.*;
import java.io.*;

public class MyPriorityQueue<E extends Comparable<E>>
{
   private ArrayList<E> a;
   
   public MyPriorityQueue()
   {
      a = new ArrayList<E>();
   }
   
   public boolean add(E obj)
   {
      int size = a.size();
      
      for(int i = 0; i < size; i++)
      {
         if(obj.compareTo(a.get(i)) < 0)
         {
            a.add(i, obj);
            return true;
         }
      }
      
      a.add(obj);
      return true;
   }
   
   public E remove()
   {
      return a.remove(0);
   }
   
   public E peek()
   {
      return a.get(0);
   }
   
   public boolean isEmpty()
   {
      return a.size() == 0;
   }
   
   public String toString()
   { 
      int size = a.size();
      String str = "[";
      
      for(int i = 0; i < size; i++)
      {
         if(i != size - 1)
            str += a.get(i) + ", ";
         
         else
            str += a.get(i);
      }
      
      str += "]";
      return str;
   }
}