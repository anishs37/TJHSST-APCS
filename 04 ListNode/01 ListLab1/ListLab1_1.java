// Name: J2-24   
// Date: 11/16/2020
import java.util.*;
public class ListLab1_1
{
   public static void main(String[] args)
   {
      ListNode head = new ListNode("hello", null);
      head = new ListNode("foo", head);
      head = new ListNode("boo", head);
      head = new ListNode("nonsense", head);
      head = new ListNode("computer",
         	new ListNode("science",
         		new ListNode("java",
         			new ListNode("coffee", head)
         		)
         	)
         );
      print(head);
      print(head);
      
      /**** uncomment the code below for ListLab1 Assignment  ****/
      
      ListNode a = copyNode(head);
      System.out.println("The head has a value \"" + head.getValue() + "\" at "+ head);
      System.out.println("The copy of head has a value of \"" + a.getValue() + "\" at "+ a);
   // 
      /*System.out.print("Copy the list: ");
      ListNode copy = copyList(head);
      print(copy);
   // 
      System.out.print("The rest of the list: ");
      ListNode theRest = rest(copy);
      print(theRest);
   // 
      System.out.println("First of the rest = " + first(theRest));
      System.out.println("Second of the rest = " + second(theRest));
      ListNode p = pointerToLast(theRest);
      System.out.println("Pointer to Last = " + p.getValue()+ " at " + p);
      ListNode c = copyOfLast(theRest);
      System.out.println("Copy of Last =    " + c.getValue()+ " at " + c);
   // 
      Scanner in = new Scanner(System.in);
      System.out.print("Insert what? ");
      String x = in.next();
      theRest = insertFirst(theRest, x);
      theRest = insertLast(theRest, x);
      print(theRest);*/
      
      //head = insert(head, 3, "bob");
      
      head = mystery(head);
      print(head);
   }
   public static void print(ListNode head)
   {
      System.out.print("[");
      while(head != null)
      {
         System.out.print(head.getValue());
         head = head.getNext();
         if(head != null)
            System.out.print(", ");
      }
      System.out.println("]");
   }
   
   public static ListNode copyNode(ListNode arg)
   {
      ListNode copy = new ListNode(arg.getValue(), arg.getNext());
      return copy;
   }
   
   /*public static ListNode insert(ListNode h, int k, Object obj)
   {
      ListNode temp = new ListNode(obj, null);
      
      if(k == 0)
      {
         temp.setNext(h);
         h = temp;
      }
      
      else
      {
         ListNode p = h;
         for(int i = 0; i < k - 1; i++)
            p = p.getNext();
         
         p.setNext(temp);
         temp.setNext(p.getNext());
      }
         return p;
   }*/
   
   public static void mystery(ListNode p)
   {
      if(p != null)
      {
         mystery(p.getNext().getNext());
         p.setNext(p.getNext.getNext());
      }
   }
   public static ListNode copyList(ListNode arg)
   {
     // while(arg.getValue() != null)
      //{
         if(arg.getNext() == null)
         {
            ListNode copy = new ListNode(arg.getValue(), null);
            return copy;
         }
         
         else
         {
            ListNode copy = new ListNode(arg.getValue(), arg.getNext());
            copyList(arg.getNext());
            return copy;
         }
      //}
      
      //return copy;
   }
   
   public static ListNode rest(ListNode h)
   {
      if((h == null) || (h.getNext() == null))
         return null;
      
      return new ListNode(h.getNext().getValue(), rest(h.getNext()));
   }
    
   public static Object first(ListNode head)
   {
      return head.getValue();  
   }
      
   public static Object second(ListNode head)
   {
      return head.getNext().getValue();
   }
   
   public static ListNode pointerToLast(ListNode head)
   {
      if(head == null)
         return null;
         
      else
      {
         while((head != null) && (head.getNext() != null))
         {
            head = head.getNext();
         }
         
         return head;
      }
   }
   
   public static ListNode copyOfLast(ListNode head)
   {
      if(head == null)
         return null;
         
      else
      {
         while((head != null) && (head.getNext() != null))
         {
            head = head.getNext();
         }
         
         //return head;
         
         ListNode copy = new ListNode(head.getValue(), null);
         return copy;         
      }  
   }
   
   public static ListNode insertFirst(ListNode head, Object arg)
   {
       if(head == null)
         return null;
       
       else
       {
         ListNode insertedListNode = new ListNode(arg, head);
         return insertedListNode;
       }
   }
   
   public static ListNode insertLast(ListNode head, Object arg)
   {
      ListNode temp = head;
      
      while(temp.getNext() != null)
         temp = temp.getNext();
      
      temp.setNext(new ListNode(arg, null));
      
      return head;
   }   
}

/*****************************************
 
 [computer, science, java, coffee, nonsense, boo, foo, hello]
 [computer, science, java, coffee, nonsense, boo, foo, hello]
 The head has a value "computer" at ListNode@15db9742
 The copy of head has a value of "computer" at ListNode@6d06d69c
 Copy the list: [computer, science, java, coffee, nonsense, boo, foo, hello]
 The rest of the list: [science, java, coffee, nonsense, boo, foo, hello]
 First of the rest = science
 Second of the rest = java
 Pointer to Last = hello at ListNode@7852e922
 Copy of Last =    hello at ListNode@4e25154f
 Insert what? p
 [p, science, java, coffee, nonsense, boo, foo, hello, p]
    
  **********************************************/
