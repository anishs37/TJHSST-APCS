// Name: J2-24
// Date: 2/9/2021
import java.util.*;
/*******************
This driver provides an ArrayList of input strings. One by one, it adds  
the letters to the tree.   Display it as a sideways 
tree (take the code from TreeLab).  Prompt the user for a target and 
search the BST for it.  Display the tree's minimum and maximum values.  
Print the letters in order from smallest to largest.
**********************/
public class BST_Driver
{
   public static void main(String[] args)
   {
      Scanner keyboard = new Scanner(System.in);
      ArrayList<String> list = new ArrayList<String>();
      list.add("6 4 1 8 10 5");
      list.add("2 5 1 4 0 3");
      list.add("5 1 2 6 3 4");
      list.add("9 7 2 1 4 0");
      list.add("2 4 7 5 8 10");
      /*list.add("H D J A G K F E O L T M N S U");;
      list.add("D B N A C F S E J H M");
      list.add("E C S B P W A N R");
      list.add("N C S B P W A R");
      list.add("E H F F A B O");
      list.add("2 1 3 5 7");
      list.add("C B C E D F E");
      list.add("A B C D E");
      list.add("C B C E D F");
      list.add("C");
      list.add("D E B C A");
      list.add("M A E N I R A C");
      list.add("A M E R I C A N");
      list.add("A A C E I M N R");
      list.add("A");
      list.add("6 8 2 9 3 0 1");
      list.add("Florida Oklahoma Colorado Massachusetts Arizona Iowa New_Hampshire Washington West_Virginia Kazakhstan Arkansas");*/
   
      for( String string : list )
      {
         BST bst = new BST();   //we want to start anew 
         String[] str = string.split(" ");
         for(String item : str)
            bst.add( item );
      
         System.out.println( bst.display() );
         System.out.println( "Size = " + bst.size() );
         System.out.println("Min = " + bst.min());
         System.out.println("Max = " + bst.max());	
         /*System.out.print("Input target: ");
         String target =  keyboard.next();    
         boolean itemFound = bst.contains(target);
         if(itemFound)
            System.out.println("found: " + target);
         else
            System.out.println(target +" not found.");*/
            
         System.out.println("in-order traversal: " + bst.toString());
         System.out.println(bst.toString());
         
         if(bst.toString() == "A A C E I M N R")
            System.out.println("LOOK HERE");
            
         System.out.println("--------------------------");
      }
   }
}
   
/***************************************
 ----jGRASP exec: java BST_Driver_Teacher
 		R
 	N
 M
 			I
 		E
 			C
 	A
 		A
 
 Size = 8
 Min = A
 Max = R
 Input target: C
 found: C
 A A C E I M N R 
 --------------------------
 		R
 			N
 	M
 			I
 		E
 			C
 A
 	A
 
 Size = 8
 Min = A
 Max = R
 Input target: 
  ----jGRASP: process ended by user.
 
  ----jGRASP exec: java BST_Driver_Teacher
 		R
 	N
 M
 			I
 		E
 			C
 	A
 		A
 
 Size = 8
 Min = A
 Max = R
 Input target: N
 found: N
 in-order traversal: A A C E I M N R 
 --------------------------
 		R
 			N
 	M
 			I
 		E
 			C
 A
 	A
 
 Size = 8
 Min = A
 Max = R
 Input target: C
 found: C
 in-order traversal: A A C E I M N R 
 --------------------------
 						R
 					N
 				M
 			I
 		E
 	C
 A
 	A
 
 Size = 8
 Min = A
 Max = R
 Input target: X
 X not found.
 in-order traversal: A A C E I M N R 
 --------------------------
 A
 
 Size = 1
 Min = A
 Max = A
 Input target: A
 found: A
 in-order traversal: A 
 --------------------------
 		9
 	8
 6
 		3
 	2
 			1
 		0
 
 Size = 7
 Min = 0
 Max = 9
 Input target: 0
 found: 0
 in-order traversal: 0 1 2 3 6 8 9 
 --------------------------
 			West_Virginia
 		Washington
 	Oklahoma
 			New_Hampshire
 		Massachusetts
 				Kazakhstan
 			Iowa
 Florida
 	Colorado
 			Arkansas
 		Arizona
 
 Size = 11
 Min = Arizona
 Max = West_Virginia
 Input target: Iowa
 found: Iowa
 in-order traversal: Arizona Arkansas Colorado Florida Iowa Kazakhstan Massachusetts New_Hampshire Oklahoma Washington West_Virginia 
 -------------------------- 
 ************************************/