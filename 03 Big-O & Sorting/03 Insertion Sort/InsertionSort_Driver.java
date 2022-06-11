 //Name: J2-24
 //Date: 10/29/2020

import java.util.*;
import java.io.*;

public class InsertionSort_Driver
{
   public static void main(String[] args) throws Exception
   {
      //Part 1, for doubles
      int n = (int)(Math.random()*100)+20;
      double[] array = new double[n];
      for(int k = 0; k < array.length; k++)
         array[k] = Math.random()*100;	
      
      Insertion.sort(array);
      print(array);
      
      if( isAscending(array) )
         System.out.println("In order!");
      else
         System.out.println("Out of order  :-( ");
      System.out.println();
      
      //Part 2, for Strings
      int size = 100;
      Scanner sc = new Scanner(new File("declaration.txt"));
      Comparable[] arrayStr = new String[size];
      for(int k = 0; k < arrayStr.length; k++)
         arrayStr[k] = sc.next();	
   
      Insertion.sort(arrayStr);
      print(arrayStr);
      System.out.println();
      
      if( isAscending(arrayStr) )
         System.out.println("In order!");
      else
         System.out.println("Out of order  :-( ");
   }
   
   public static void print(double[] a)
   {
      for(double d: a)         // for-each loop
         System.out.print(d+" ");
      System.out.println();
   }
   
   public static void print(Object[] papaya)
   {
      for(Object abc : papaya)    
         System.out.print(abc+" ");
   }
   
   public static boolean isAscending(double[] a)
   {
      int ascend = 0;

      for(int i = 0; i < a.length - 1; i++)
         if(a[i] <= a[i + 1])
            ascend++;
      
      if(ascend == a.length - 1)
         return true;
      
      return false;
  }
   
   @SuppressWarnings("unchecked")
   public static boolean isAscending(Comparable[] a)
   {
      int ascend = 0;

      for(int i = 0; i < a.length - 1; i++)
         if(a[i].compareTo(a[i + 1]) <= 0)
            ascend++;
      
      if(ascend == a.length - 1)
         return true;
      
      return false;
   }
}

//**********************************************************

class Insertion
{
   public static void sort(double[] array)
   { 
      double temp = 0;
      for(int x = 1; x < array.length; x++)
      {
         temp = array[x];
         int insert_index = shift(array, x, array[x]);
         array[insert_index] = temp;
      }
   }
 
   private static int shift(double[] array, int index, double value)
   {
      while((index > 0) && (array[index - 1] > value))
      {
         array[index] = array[index-1];
         index--;
      }
      
      return index;
   }
  
   @SuppressWarnings("unchecked")
   public static void sort(Comparable[] array)
   { 
      for(int x = 1; x < array.length; x++)
      {
         Comparable temp = array[x];
         int insert_index = shift(array, x, array[x]);
         array[insert_index] = temp;
      }
   }
   
   @SuppressWarnings("unchecked")
   private static int shift(Comparable[] array, int index, Comparable value)
   {
     while((index > 0) && (array[index - 1].compareTo(value) > 0))
     {
        array[index] = array[index-1];
        index--;
     }
      
     return index;
   }
}