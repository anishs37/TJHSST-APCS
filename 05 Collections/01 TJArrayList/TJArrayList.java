// Name: J2-24
// Date: 12/11/2020

/**
 * Implements the cheat sheet's List interface.  Implements generics.
 * The backing array is an array of (E[]) new Object[10];
 * @override toString()
 */ 
 
public class TJArrayList<E>
{
   private int size;							//stores the number of objects
   private E[] myArray;
   
   public TJArrayList()                //default constructor makes 10 cells
   {
      myArray = (E[]) new Object[10];
      size = 0;
   }
   
   public int size()
   {
      return size;
   }
   
 	/* appends obj to end of list; increases size;
      must be an O(1) operation when size < array.length, 
         and O(n) when it doubles the length of the array.
	  @return true  */
     
   public boolean add(E obj)
   {
      if(myArray.length == size)
      {
         E[] tempArray = (E[]) new Object[myArray.length * 2];
         int i = 0;
         
         do
         {
            tempArray[i] = myArray[i];
            i++;
         }
         
         while(i < myArray.length);
         
         myArray = tempArray;
      }
      
      myArray[size] = obj;
      
      size++;
      
      return true;
   }
   
   /* inserts obj at position index.  increments size. 
		*/
      
   public void add(int index, E obj) throws IndexOutOfBoundsException  //this the way the real ArrayList is coded
   {
      if(index > size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      
      int i = myArray.length - 1;
      
      do
      {
         myArray[i] = myArray[i - 1];
         i--;
      }
      
      while(i > index);
       
      myArray[index] = obj;
      
      size++;
   }

   /* return obj at position index.  
		*/
      
   public E get(int index) throws IndexOutOfBoundsException
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
     
     E obj = myArray[index];
     
     return obj;   
   }
   
   /**
    * Replaces obj at position index. 
    * @return the object is being replaced.
    */ 
     
   public E set(int index, E obj) throws IndexOutOfBoundsException  
   { 
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      
      E toReturn = myArray[index];
      myArray[index] = obj;
      
      return toReturn;
   }
   
 /*  removes the node from position index. shifts elements 
     to the left.   Decrements size.
	  @return the object at position index.
	 */
    
   public E remove(int index) throws IndexOutOfBoundsException  
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      
      E obj = myArray[index];
      
      int i = index;
      
      do
      {
         myArray[i] = myArray[i + 1];
         i++;
      }
      
      while(i < size);
      
      size--;
      
      return obj;
   }
   
	   /*
		   This method compares objects.
         Must use .equals(), not ==
     	*/
      
   public boolean contains(E obj)
   {
      for(int i = 0; i < myArray.length; i++)
         if(obj.equals(myArray[i]))
            return true;
      
      return false;
   }
   
	 /*returns a String of E objects in the array with 
       square brackets and commas.
     	*/
      
   public String toString()
   {
      if(size == 0)
         return "[ ]";
      
      else
      {
         String str = "[";
         
         int i = 0;

         do
         {
            str += myArray[i];
            
            if(i < size - 1)
               str += ", ";
            
            i++;
         }
         
         while(i < size);
         
         str += "]";
         
         return str;
      }
   }
}