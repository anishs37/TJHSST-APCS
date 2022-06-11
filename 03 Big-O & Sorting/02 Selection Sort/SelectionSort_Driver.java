// Name: J2-24
// Date: 10/27/2020

import java.util.*;
import java.io.*;

public class SelectionSort_Driver
{
   public static void main(String[] args) throws Exception
   {
     //Part 1, for doubles
      int n = (int)(Math.random()*100)+20;
      double[] array = new double[n];
      for(int k = 0; k < array.length; k++)
         array[k] = Math.random()*100;	
      
      Selection.sort(array);
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
   
      Selection.sort(arrayStr);
      print(arrayStr);
      System.out.println();
      
      if( isAscending(arrayStr) )
         System.out.println("In order!");
      else
         System.out.println("Out of order  :-( ");
   }
  
   public static void print(double[] a)
   {
      for(double d: a)         //for-each
         System.out.print(d+" ");
      System.out.println();
   }
  
   public static void print(Object[] papaya)
   {
      for(Object abc : papaya)     //for-each
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
/////////////////////////////////////////////////////

class Selection
{
   public static void sort(double[] array)
   {
      for(int x = 0; x < array.length; x++)
      {
         int index = findMax(array, array.length - x - 1);
         swap(array, index, array.length - x - 1);
      }
   }
   
   // upper controls where the inner loop of the Selection Sort ends
   private static int findMax(double[] array, int upper)
   {
      int index = 0;
      double maximum = 0;
      
      for(int y = 1; y <= upper; y++)
         if(array[y] > array[index])
            index = y;

      return index;
   }
   private static void swap(double[] array, int a, int b)
   {
      double temp = array[a];
      array[a] = array[b];
      array[b] = temp;
   }   	
   
	/*******  for Comparables ********************/
   @SuppressWarnings("unchecked")
   public static void sort(Comparable[] array)
   {
      for(int x = 0; x < array.length; x++)
      {
         int index = findMax(array, array.length - x - 1);
         swap(array, index, array.length - x - 1);
      }
   }
   
   @SuppressWarnings("unchecked")
   public static int findMax(Comparable[] array, int upper)
   {
      int index = 0;
      Comparable maximum = array[0];
      
      for(int y = 1; y <= upper; y++)
         if(array[y].compareTo(array[index]) > 0)
            index = y;

      return index;
   }
   public static void swap(Object[] array, int a, int b)
   {
      Object temp = array[a];
      array[a] = array[b];
      array[b] = temp;
   }
}

