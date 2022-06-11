// Name: J2-24    
// Date: 12/15/2020
// This program takes a text file, creates an index (by line numbers)
// for all the words in the file and writes the index
// into the output file.  The program prompts the user for the file names.
 
import java.util.*;
import java.io.*;
 
public class IndexMaker
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nEnter input file name: ");
      String inFileName = keyboard.nextLine().trim();
      Scanner inputFile = new Scanner(new File(inFileName));
      String outFileName = "fishIndex.txt";
      PrintWriter outputFile = new PrintWriter(new FileWriter(outFileName));
      indexDocument(inputFile, outputFile);
      inputFile.close(); 						
      outputFile.close();
      System.out.println("Done.");
   }
   public static void indexDocument(Scanner inputFile, PrintWriter outputFile)
   {
      DocumentIndex index = new DocumentIndex();
      String line = null;
      int lineNum = 0;
      while(inputFile.hasNextLine())
      {
         lineNum++;
         index.addAllWords(inputFile.nextLine(), lineNum);
      }
      for(IndexEntry entry : index)
         outputFile.println(entry);
   }   
}
class DocumentIndex extends ArrayList<IndexEntry>
{
    //constructors
    
   public DocumentIndex()
   {
      super();
   }
 
  /** extracts all the words from str, skipping punctuation and whitespace 
 and for each word calls addWord().  In this situation, a good way to 
 extract while also skipping punctuation is to use String's split method, 
 e.g., str.split("[., \"!?]")       */
 
   public void addAllWords(String str, int lineNum) 
   {
      String[] splitStr = str.split("[., \"!?]");
      
      for(String strFor : splitStr) 
      {
         String strForTrim = strFor.trim();
         
         if((!strForTrim.equals("")) && (strForTrim.length() > 0))
            addWord(strForTrim, lineNum);
      }
   }
    
   /** calls foundOrInserted, which returns a position.  At that position,  
   updates that IndexEntry's list of line numbers with lineNum. */
   
   public void addWord(String word, int lineNum)
   {
      int indexLocation = foundOrInserted(word);
        
      this.get(indexLocation).add(lineNum);
   }
        
    /** traverses this DocumentIndex and compares word to the words in the 
    IndexEntry objects in this list, looking for the correct position of 
    word. If an IndexEntry with word is not already in that position, the 
    method creates and inserts a new IndexEntry at that position. The 
    method returns the position of either the found or the inserted 
    IndexEntry.*/
    
   private int foundOrInserted(String word)
   {
      /*int start = 0;
      int end = size() - 1;
      
      while(start <= end)
      {
         word = word.toUpperCase();
         
         int median = (start + end) / 2;
         
         IndexEntry entry = get(median);
         String str = entry.getWord();
            
         if(str.equals(word))
            return median;
         
         else if(str.compareTo(word) > 0)
         {   
            add(end, new IndexEntry(word)); 
            return median; 
            //return foundOrInserted(word, median + 1, end);
         }
         
         //else
           // return foundOrInserted(word, start, median - 1); 
      }
      
      return size() - 1;*/
      
      word = word.toUpperCase();
      
      for(int i = 0; i < size(); i++)
      {         
         IndexEntry entry = get(i);
         String str = entry.getWord();
 
         if(str.equals(word))
            return i;
            
         else if(str.compareTo(word) > 0)
         {
            add(i, new IndexEntry(word));
            return i;         
         }     
      }
      
      add(new IndexEntry(word));
      return size()-1;
   }
}
   
class IndexEntry extends ArrayList<Integer> 
{
   //fields
   
   private String word;
   private ArrayList<Integer> numsList;
 
   //constructors
   
   public IndexEntry (String str) 
   {
      word = str.toUpperCase();
      numsList = new ArrayList<Integer>();
   }   
   
   
   /**  appends num to numsList, but only if it is not already in that list.    
        */
   
   public void add(int num)
   {
      if(!numsList.contains(num))
         numsList.add(num);
   }
      
   /** this is a standard accessor method  */
      
   public String getWord()
   {
      return word;
   }
      
   /**  returns a string representation of this Index Entry.  */
     
   public String toString()
   {
      String str = "";
      
      str += word + " ";
      
      int index = 1;
      
      for(int x : numsList)
      {
         if(index < numsList.size())
            str += x + ", ";
            
         else if(index == numsList.size())
            str += x;
         
         index++;
      }
      
      return str;
   }
   
   //extra compareTo method to override Comparable compareTo method
   
  /* public int compareTo(IndexEntry compare) 
   {
     int compareT = word.compareTo(compare.toString());
     return compareT;
   }*/
}