 //Name: J2-24
 //Date: 3/23/2021
 
import java.util.*;

/* implement the API for java.util.PriorityQueue
 * test this class with HeapPriorityQueue_Driver.java.
 * test this class with LunchRoom.java.
 * add(E) and remove()  must work in O(log n) time
 */
public class HeapPriorityQueue<E extends Comparable<E>> 
{
   private ArrayList<E> myHeap;
   
   public HeapPriorityQueue()
   {
      myHeap = new ArrayList<E>();
      myHeap.add(null);
   }
   
   public boolean add(E obj)
   {
      myHeap.add(obj);
      heapUp(myHeap.size() - 1);
      
      return true;
   }
   
   public E remove()
   {
      E toRemove = myHeap.remove(1);
      
      if(myHeap.size() > 1)
      {
         myHeap.add(1, myHeap.remove(myHeap.size() - 1));
         heapDown(1, myHeap.size() - 1);
      }
      
      return toRemove;
   }
   
   public E peek()
   {
      if(myHeap.size() <= 1)
         return null;
         
      E objPeek = myHeap.get(1);
      return objPeek;
   }
   
   public boolean isEmpty()
   {
      return myHeap.size() == 1;
   }
   
   private void heapUp(int k)
   {
      int bottom = myHeap.size() - 1;
      
      while(bottom > 1)
      {
         int parent = bottom / 2;
         
         if(myHeap.get(parent).compareTo(myHeap.get(bottom)) > 0)
         {
            swap(parent, bottom);
            bottom = parent;
         }
         
         else
            bottom = 0;
      }
   }
   
   private void swap(int a, int b)
   {
      E temp = myHeap.get(a);
      myHeap.set(a, myHeap.get(b));
      myHeap.set(b, temp);
   }
   
   private void heapDown(int k, int size)
   {
      int maxChild = k * 2;
      
      if(maxChild >= size)
         return;
      
      if((((k * 2) + 1) <= size) && (myHeap.get(k * 2).compareTo(myHeap.get((k * 2) + 1)) > 0))
         maxChild++;
         
      if(myHeap.get(k).compareTo(myHeap.get(maxChild)) > 0)
      {
         swap(k, maxChild);
         heapDown(maxChild, size);
      }
   }
   
   public String toString()
   {
      return myHeap.toString();
   }  
}
