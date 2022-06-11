// Name: J2-24
// Date: 11/29/2020
 
import java.util.*;
import java.io.*;
 
public class Josephus
{
   private static String WINNER = "Josephus";
   
   public static void main(String[] args) throws FileNotFoundException
   {
      ListNode p = new ListNode("A", null);
      p.setNext(p);
      p = insert(p, "B");
      p = insert(p, "C");
      p = insert(p, "D");
      print(p);
        
      /* run numberCircle first with J_numbers.txt  */
      Scanner sc = new Scanner(System.in);
      System.out.print("How many names (2-20)? ");
      int n = Integer.parseInt(sc.next());
      System.out.print("How many names to count off each time?"  );
      int countOff = Integer.parseInt(sc.next());
      ListNode winningPos = numberCircle(n, countOff, "J_numbers.txt");
      System.out.println(winningPos.getValue() + " wins!");  
     
      /* run josephusCircle next with J_names.txt  */
      System.out.println("\n ****  Now start all over. **** \n");
      System.out.print("Where should "+WINNER+" stand?  ");
      int winPos = Integer.parseInt(sc.next());        
      ListNode theWinner = josephusCircle(n, countOff, "J_names.txt", winPos);
      System.out.println(theWinner.getValue() + " wins!");  
   }
   
   public static ListNode numberCircle(int n, int countOff, String filename) throws FileNotFoundException
   {
      ListNode p = null;
      p = readNLinesOfFile(n, new File(filename));
      p = countingOff(p, countOff, n);
      return p;
   }
   
   public static ListNode josephusCircle(int n, int countOff, String filename, int winPos) throws FileNotFoundException
   {
      ListNode p = null;
      p = readNLinesOfFile(n, new File(filename));
      replaceAt(p, WINNER, winPos);
      p = countingOff(p, countOff, n);
      return p;
   }
 
   /* reads the names, calls insert(), builds the linked list.
	 */
    
   public static ListNode readNLinesOfFile(int n, File f) throws FileNotFoundException
   {
      Scanner file;
      file = new Scanner(f);
      
      ListNode list = new ListNode(file.next(), null);
      list.setNext(list);
      
      for(int i = 0; i < n - 1; i++)
         list = insert(list, file.next());
      
      return list;
   }
   
   /* helper method to build the list.  Creates the node, then
    * inserts it in the circular linked list.
	 */
    
   public static ListNode insert(ListNode p, Object obj)
   {
      /*ListNode temp = p;         //creates copy of node p
      
      //if(temp.getNext() != p)
         while(temp.getNext() != p)
            temp = temp.getNext();
      
      ListNode finalTemp = new ListNode(obj, p);
      temp.setNext(finalTemp);
      
      p = temp;
      return p;*/
      
      //ListNode node = new ListNode(obj, p.getNext());
      //p.setNext(node);
      //p = node;
      
      //p = new ListNode(obj, p.getNext());
      p.setNext(new ListNode(obj, p.getNext()));
      p = p.getNext();
      return p;
   }
   
   /* Runs a Josephus game, counting off and removing each name. Prints after each removal.
      Ends with one remaining name, who is the winner. 
	 */
    
   public static ListNode countingOff(ListNode p, int count, int n)
   {
      ListNode tempCountingOff = p;
      
      print(tempCountingOff);
      
      while((tempCountingOff.getNext() != null) && (tempCountingOff.getNext() != tempCountingOff) && (n > 1))
      {
         tempCountingOff = remove(tempCountingOff, count);
         print(tempCountingOff);
         n--;
      }
      
      p = tempCountingOff;
      
      return p;
   }
   
   /* removes the node after counting off count-1 nodes.
	 */
    
   public static ListNode remove(ListNode p, int count)
   {
      if(count == 1)
      {
         ListNode tempRemove = p;
         
         while(tempRemove.getNext() != p)
            tempRemove = tempRemove.getNext();
         
         tempRemove.setNext(p.getNext());    
      }
      
      else
      {        
         for(int i = 1; i < count; i++)
            p = p.getNext();
            
         p.setNext(p.getNext().getNext());
         return p;
      }
 
      //return p.getNext();
   }
   
   /* prints the circular linked list.
	 */
    
   public static void print(ListNode p)
   {
      ListNode tempPrint = p.getNext();
      
      System.out.print(tempPrint.getValue() + " ");
      tempPrint = tempPrint.getNext();
      
      while(tempPrint != p.getNext())
      {
         System.out.print(tempPrint.getValue() + " ");
         tempPrint = tempPrint.getNext();
      }
      
      p = tempPrint;
      
      System.out.println();
   }
	
   /* replaces the value (the string) at the winning node.
	 */
    
   public static void replaceAt(ListNode p, Object obj, int pos)
   {
      ListNode temp = p;
      
      int i = 1;
      
      do
      {
         temp = temp.getNext();
         i++;
      }
      
      while((temp.getNext() != null) && (i < pos));
      
      temp.setValue(obj);
      
      p = temp; 
   }
}