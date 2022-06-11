// Name: J2-24
// Date: 12/02/2020

//  implements some of the List and LinkedList interfaces: 
//	 	  size(), add(i, o), remove(i);  addFirst(o), addLast(o); 
//  This class also overrides toString().
//  the list is zero-indexed.
//  Uses DLNode.

public class DLL        //DoubleLinkedList
{
   private int size;
   private DLNode head = new DLNode(); //dummy node--very useful--simplifies the code
   
   public int size()
   {
      return size;
   }
   
   /* appends obj to end of list; increases size;
   	  @return true  */
        
   public boolean add(Object obj)
   {
      addLast(obj);
      return true;   
   }
   
   /* inserts obj at position index.  increments size. 	*/
   
   public void add(int index, Object obj) throws IndexOutOfBoundsException  //this the way the real LinkedList is coded
   {
      if( index > size || index < 0 )
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

      else
      {
         DLNode adder = new DLNode(obj, null, null);
         DLNode tempAdd = head;
         
         for(int k = 0; k < index; k++)
            tempAdd = tempAdd.getNext();
            
         adder.setNext(tempAdd.getNext());
         tempAdd.getNext().setPrev(adder);
         tempAdd.setNext(adder);
         
         adder.setPrev(tempAdd);
         
         size++;
      }              
   }
   
   /* return obj at position index. 	*/
   
   public Object get(int index)
   { 
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      
      else
      {
         DLNode tempGet = head;

         for(int i = 0; i <= index; i++)
            tempGet = tempGet.getNext();
         
         return tempGet.getValue();
      }  
   }
   
   /* replaces obj at position index. 
        returns the obj that was replaced*/
        
   public Object set(int index, Object obj)
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      
      else
      {
         DLNode tempSet = head;
         
         for(int i = 0; i <= index; i++)
            tempSet = tempSet.getNext();
         
         Object objReturn = tempSet.getValue();
         
         tempSet.setValue(obj);
         
         return objReturn;
      }
   }
   
   /*  removes the node from position index (zero-indexed).  decrements size.
       @return the object of the node that was removed.    */
       
   public Object remove(int index)
   {      
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      
      else
      {
         DLNode tempRemove = head;
         
         for(int k = 0; k <= index; k++)
            tempRemove = tempRemove.getNext();
         
         Object objRemove = tempRemove.getValue();
         
         tempRemove.getPrev().setNext(tempRemove.getNext());
         tempRemove.getNext().setPrev(tempRemove.getPrev());
         
         size--;
         
         return objRemove;
      }
   }
   
   /* inserts obj at front of list, increases size   */
   
   public void addFirst(Object obj)
   {      
      DLNode tempAddFirst = new DLNode(obj, head, head.getNext());
      
      head.setNext(tempAddFirst);
      head.getNext().setPrev(tempAddFirst);
      size++;
   }
   
   /* appends obj to end of list, increases size    */
   
   public void addLast(Object obj)
   {
      DLNode tempAddLast = new DLNode(obj, head.getPrev(), head);
      head.getPrev().setNext(tempAddLast);
      head.setPrev(tempAddLast);
      
      size++;
   }
   
   /* returns the first element in this list  */
   
   public Object getFirst()
   {      
      Object objGetFirst = head.getNext().getValue();
      return objGetFirst;
   }
   
   /* returns the last element in this list  */
   
   public Object getLast()
   {
      Object objGetLast = head.getPrev().getValue();
      return objGetLast;
   }
   
   /* returns and removes the first element in this list, or
       returns null if the list is empty  */
       
   public Object removeFirst()
   {
      if(head == null)
         return null;
      
      else
      {
         DLNode forward = head.getNext();
         
         DLNode tempRemoveFirst = head.getNext();

         forward.getPrev().setNext(forward.getNext());
         forward.getNext().setPrev(forward.getPrev());
         
         size--;
         return tempRemoveFirst.getValue();
      }
   }
   
   /* returns and removes the last element in this list, or
       returns null if the list is empty  */
       
   public Object removeLast()
   {
      if(head == null)
         return null;
      
      else
      {
         DLNode backward = head.getPrev();

         Object obj = head.getPrev().getValue();
         
         backward.getPrev().setNext(backward.getNext());
         backward.getNext().setPrev(backward.getPrev());
         
         size--;
         return obj;
      }
   }
   
   /*  returns a String with the values in the list in a 
       friendly format, for example   [Apple, Banana, Cucumber]
       The values are enclosed in [], separated by one comma and one space.
    */
    
   public String toString()
   {
      if(head == null)
         return "[ ]";
      
      else
      {
         String str = "[";
         
         DLNode tempString = head.getNext();

         do
         {
            str += tempString.getValue();
            
            if(tempString.getNext() != head)
               str += ", ";
            
            tempString = tempString.getNext();
         }
         
         while(tempString != head);
         
         str += "]";
         
         return str;
      }
   }
}