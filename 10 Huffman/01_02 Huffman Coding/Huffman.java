//Name: J2-24
//Date: 4/5/2021
 
import java.util.*;
import java.io.*;
public class Huffman
{
   public static Scanner keyboard = new Scanner(System.in);
   public static PrintWriter messageFile = null;
   public static PrintWriter schemeFile = null;
   
   public static void main(String[] args) throws IOException
   {
      //Prompt for two strings 
      System.out.print("Encoding using Huffman codes");
      System.out.print("\nWhat message? ");
      String message = keyboard.nextLine();
   
      System.out.print("\nEnter middle part of filename:  ");
      String middlePart = keyboard.next();
   
      huffmanize( message, middlePart );
   }
   
   public static void huffmanize(String message, String middlePart) throws IOException
   {
      //Make a frequency table of the letters
   	//Put each letter-frequency pair into a HuffmanTreeNode. Put each 
		//        node into a priority queue (or a min-heap).     
   	//Use the priority queue of nodes to build the Huffman tree
   	//Process the string letter-by-letter and search the tree for the 
		//       letter. It's recursive. As you recur, build the path through the tree, 
		//       where going left is 0 and going right is 1.
      //System.out.println the binary message 
   	//Write the binary message to the hard drive using the file name ("message." + middlePart + ".txt")
      //System.out.println the scheme from the tree--needs a recursive helper method
   	//Write the scheme to the hard drive using the file name ("scheme." + middlePart + ".txt")
      
      try
      {
         messageFile = new PrintWriter(new FileWriter("message." + middlePart + ".txt"));
      }
      
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
      
      try
      {
         schemeFile = new PrintWriter(new FileWriter("scheme." + middlePart + ".txt"));
      }
      
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
      
      Map<Character, Integer> freqTable = new HashMap<Character, Integer>();
      int messageLen = message.length();
      
      for(int i = 0; i < messageLen; i++)
      {
         char letter = message.charAt(i);
         
         if(freqTable.containsKey(letter))
         {
            int freq = freqTable.get(letter);
            freqTable.put(letter, freq + 1);
         }
         
         else
            freqTable.put(letter, 1);
      }
      
      PriorityQueue<HuffmanTreeNode> letterFreq = new PriorityQueue<HuffmanTreeNode>();
      Iterator<Character> it = freqTable.keySet().iterator();
      
      while(it.hasNext())
      {
         char ch = it.next();
         String letter = ch + "";
         int freq = freqTable.get(ch);
         
         HuffmanTreeNode toAdd = new HuffmanTreeNode(letter, freq);
         letterFreq.add(toAdd);
      }  
      
      Map<Character, String> blankValCopy = new HashMap<Character, String>();
      
      for(char ch : freqTable.keySet())
         blankValCopy.put(ch, "");
            
      while(letterFreq.size() > 1)
      {
         HuffmanTreeNode tree1 = letterFreq.remove();
         HuffmanTreeNode tree2 = letterFreq.remove();
         
         int tree1Freq = tree1.getFreq();
         int tree2Freq = tree2.getFreq();
         
         HuffmanTreeNode tree3 = new HuffmanTreeNode("*", tree1Freq + tree2Freq, tree1, tree2);
         
         recurse(tree1, blankValCopy, '0');
         recurse(tree2, blankValCopy, '1');
         
         letterFreq.add(tree3);
      }
      
      HuffmanTreeNode lastToRemove = letterFreq.remove();
      
      for(char ch: message.toCharArray())
      {
         messageFile.print(blankValCopy.get(ch));
         System.out.print(blankValCopy.get(ch));
      }
      
      System.out.println("");
      System.out.println("");
      
      for(char freq: blankValCopy.keySet())
      {
         schemeFile.println(freq + blankValCopy.get(freq));
         System.out.println(freq + blankValCopy.get(freq));
      }
      
      messageFile.close();
      schemeFile.close();
   } 
   
   public static void recurse(HuffmanTreeNode toRecurse, Map<Character, String> map, char num)
   {
      if(!toRecurse.getLetter().equals("*"))
      {
         String letter = toRecurse.getLetter();
         char ch = letter.charAt(0);
         map.put(ch, num + map.get(ch));
         return;
      }
      
      recurse(toRecurse.getLeft(), map, num);
      recurse(toRecurse.getRight(), map, num);
   }   
}
 
	/*
	  * This tree node stores two values.  Look at TreeNode's API for some help.
	  * The compareTo method must ensure that the lowest frequency has the highest priority.
	  */
     
class HuffmanTreeNode implements Comparable<HuffmanTreeNode>
{
   private String value;
   private int frequency; 
   private HuffmanTreeNode left, right;
   
   public HuffmanTreeNode(String initValue, int initFreq)
   { 
      value = initValue;
      frequency = initFreq;
      left = null; 
      right = null; 
   }
   
   public HuffmanTreeNode(String initValue, int initFreq, HuffmanTreeNode initLeft, HuffmanTreeNode initRight)
   { 
      value = initValue; 
      frequency = initFreq;
      left = initLeft; 
      right = initRight; 
   }
   
   public String getLetter()
   { 
      return value; 
   }
   
   public int getFreq()
   { 
      return frequency; 
   }
   
   public HuffmanTreeNode getLeft() 
   { 
      return left; 
   }
   
   public HuffmanTreeNode getRight() 
   { 
      return right; 
   } 
 
   public void setValue(String theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(HuffmanTreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(HuffmanTreeNode theNewRight)
   { 
      right = theNewRight;
   }
   
   public int compareTo(HuffmanTreeNode other)
   {
      int freqDiff = frequency - other.getFreq();
      return freqDiff;
   }
   
   public String toString()
   {
      String str = value + ":" + frequency;
      return str;
   }
}