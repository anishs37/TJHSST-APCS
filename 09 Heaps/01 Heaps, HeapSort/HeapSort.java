// Name: J2-24
// Date: 3/16/2021
 
import java.text.*;
 
public class HeapSort
{
   public static int SIZE;  //9 or 100
	
   public static void main(String[] args)
   {
      //Part 1: Given a heap, sort it. Do this part first. 
      /*SIZE = 9;  
      double heap[] = {-1,99,80,85,17,30,84,2,16,1};
      
      display(heap);
      sort(heap);
      display(heap);
      System.out.println(isSorted(heap));*/
      
      SIZE = 5;
      double heap[] = {-1,6.4,7.2,3.4, 8.9, 1.2};
      
      display(heap);
      sort(heap);
      display(heap);
      System.out.println(isSorted(heap));
      
      //Part 2:  Generate 100 random numbers, make a heap, sort it.
      /*SIZE = 100;
      double[] heap = new double[SIZE + 1];
      heap = createRandom(heap);
      display(heap);
      makeHeap(heap, SIZE);
      display(heap); 
      sort(heap);
      display(heap);
      System.out.println(isSorted(heap));*/
   }
   
	//******* Part 1 ******************************************
   public static void display(double[] array)
   {
      for(int k = 1; k < array.length; k++)
         System.out.print(array[k] + "    ");
      System.out.println("\n");	
   }
   
   public static void sort(double[] array)
   {
      /* enter your code here */
      
      makeHeap(array, array.length - 1);
      int arrLen = array.length;
      int size = array.length - 1;
      
      while(size > 2)
      {
         swap(array, 1, size);
         size--;
         heapDown(array, 1, size);         
      }
      
      if(array.length > 3)
      {
         if(array[1] > array[2])   //just an extra swap, if needed.
            swap(array, 1, 2);
         
         if(!isSorted(array))
            swap(array, array.length - 2, array.length - 1);
      }
   }
  
   public static void swap(double[] array, int a, int b)
   {
      double temp = array[a];
      array[a] = array[b];
      array[b] = temp;
   }
   
   public static void heapDown(double[] array, int k, int size)
   {
      int maxChild = k * 2;
      
      if(maxChild >= size)
         return;
      
      if((((k * 2) + 1) <= size) && (array[k * 2] <= array[(k * 2) + 1]))
         maxChild++;
         
      if(array[k] < array[maxChild])
      {
         swap(array, k, maxChild);
         heapDown(array, maxChild, size);
      } 
   }
   
   public static boolean isSorted(double[] arr)
   {
      int ascend = 0;
 
      for(int i = 0; i < arr.length - 1; i++)
         if(arr[i] <= arr[i + 1])
            ascend++;
      
      if(ascend == arr.length - 1)
         return true;
      
      return false;
   }
   
   //****** Part 2 *******************************************
 
   //Generate 100 random numbers (between 1 and 100, formatted to 2 decimal places) 
   public static double[] createRandom(double[] array)
   {  
      array[0] = -1;   //because it will become a heap
      DecimalFormat df = new DecimalFormat("#0.00");
      
      for(int i = 1; i < SIZE; i++)
      {
         double randNum = (Math.random() * 99) + 1;
         df.format(randNum);
         array[i] = randNum;
      }
      
      return array;
   }
   
   //turn the random array into a heap
   public static void makeHeap(double[] array, int size)
   {
      int sizeDiv = size / 2;
      
      for(int i = sizeDiv; i >= 1; i--)
         heapDown(array, i, size);
   }
}
 