 // Name: J2-24   
 // Date: 12/11/2020
 // use for-each loops or iterators, not regular for-loops
 
import java.io.*;
import java.util.*;
public class IteratorLab
{
   public static void main(String[] args)
   {
      System.out.println("Iterator Lab\n");
      int[] rawNumbers = {-9, 4, 2, 5, -10, 6, -4, 24, 20, -28};
      for(int n : rawNumbers )
         System.out.print(n + " ");    
      ArrayList<Integer> numbers = createNumbers(rawNumbers);
      System.out.println("\nArrayList: "+ numbers);      //Implicit Iterator!
      System.out.println("Count negative numbers: " + countNeg(numbers));
      System.out.println("Average: " + average(numbers));
      System.out.println("Replace negative numbers: " + replaceNeg(numbers));
      System.out.println("Delete zeros: " + deleteZero(numbers));
      String[] rawMovies = {"High_Noon", "High_Noon", "Star_Wars", "Tron", "Mary_Poppins", 
               "Dr_No", "Dr_No", "Mary_Poppins", "High_Noon", "Tron"};
      ArrayList<String> movies = createMovies(rawMovies);
      System.out.println("Movies: " + movies);
      System.out.println("Movies: " +  removeDupes(movies));
   }
   
      // pre: an array of just int values 
   	// post: return an ArrayList containing all the values
      
   public static ArrayList<Integer> createNumbers(int[] rawNumbers) 
   {
      ArrayList<Integer> myList = new ArrayList<Integer>();
      
      for(int i : rawNumbers)
         myList.add(i);
         
      return myList;
   }
   
      // pre: an array of just Strings  
   	// post: return an ArrayList containing all the Strings
      
   public static ArrayList<String> createMovies(String[] rawWords) 
   {
      ArrayList<String> myList = new ArrayList<String>();
      for ( String str : rawWords )
         myList.add( str );
      return myList;
   }
   
   	// pre: ArrayList a is not empty and contains only Integer objects
   	// post: return the number of negative values in the ArrayList a
      
   public static int countNeg(ArrayList<Integer> a)
   {
      ListIterator<Integer> myIter = a.listIterator();
      int count = 0;
      
      while(myIter.hasNext())
         if(myIter.next() < 0)
            count++;
            
      return count;
   }
   
   	// pre: ArrayList a is not empty and contains only Integer objects
   	// post: return the average of all values in the ArrayList a
      
   public static double average(ArrayList<Integer> a)
   {
      ListIterator<Integer> myIter = a.listIterator();
      double sum = 0;
      int num = 0;
      
      while(myIter.hasNext())
      {
         sum += myIter.next();
         num++;
      }
      
      double avg = sum / num;
      
      return avg;
   }
   
     	// pre: ArrayList a is not empty and contains only Integer objects
   	// post: replaces all negative values with 0 
      
   public static ArrayList<Integer> replaceNeg(ArrayList<Integer> a)
   {
      ListIterator<Integer> myIter = a.listIterator();
      
      while(myIter.hasNext())
      {
         if(myIter.next() < 0)
            myIter.set(0);
      }
      
      return a;
   }
   
     	// pre: ArrayList a is not empty and contains only Integer objects
   	// post: deletes all zeros in the ArrayList a
      
   public static ArrayList<Integer> deleteZero(ArrayList<Integer> a)
   {
      ListIterator<Integer> myIter = a.listIterator();
      
      while(myIter.hasNext())
      {
         if(myIter.next() == 0)
            myIter.remove();
      }
      
      return a;
   }
   
      // pre: ArrayList a is not empty and contains only String objects
   	// post: return ArrayList without duplicate movie titles
		// strategy: start with an empty array and add movies as needed
      
   public static ArrayList<String> removeDupes(ArrayList<String> a)
   {
      ArrayList<String> myList = new ArrayList<String>();
      
      for(String i : a)
         if(!myList.contains(i))
            myList.add(i);
      
      return myList;
   }
   
}

