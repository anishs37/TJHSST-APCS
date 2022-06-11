// Name: J2-24
// Date: 3/4/2021
 
import java.io.*;
import java.util.*;  
 
/* This program takes a text file, creates an index (by line numbers)
 * for all the words in the file and writes the index
 * into the output file.  The program prompts the user for the file names.
 */
public class IndexMakerMap
{
   static void mainCaller(String value) throws IOException
   {
      Scanner inputFile = new Scanner(new File(value));
      DocumentIndex index = makeIndex(inputFile);
      System.out.println( index.toString() );
      main(null);
   }
   
   public static void main(String[] args) throws IOException
   {
      //Scanner keyboard = new Scanner(System.in);
      //System.out.print("\nEnter input file name: ");
      //String infileName = keyboard.nextLine().trim();
     // String infileName = "fish2.txt";
      //Scanner inputFile = new Scanner(new File(infileName));
      //DocumentIndex index = makeIndex(inputFile);
      
      //System.out.println( index.toString() );
      //PrintWriter outputFile = new PrintWriter(new FileWriter("fishIndex.txt"));
      //outputFile.println(index.toString());
      //inputFile.close(); 						
      //outputFile.close();
      mainCaller("book1.txt");
      System.out.println("Done.");
   }
   
   public static DocumentIndex makeIndex(Scanner inputFile)
   {
      DocumentIndex index = new DocumentIndex(); 	
      int lineNum = 0;
      while(inputFile.hasNextLine())
      {
         lineNum++;
         index.addAllWords(inputFile.nextLine(), lineNum);
      }
      return index;  
   }
}
 
class DocumentIndex extends TreeMap<String, TreeSet<Integer>>
{
  
  /** Extracts all the words from str, skipping punctuation and whitespace
   *  and for each word calls addWord(word, num).  A good way to skip punctuation
   *  and whitespace is to use String's split method, e.g., split("[., \"!?]") 
   */
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
 
  /** Makes the word uppercase.  If the word is already in the map, updates the lineNum.
   *  Otherwise, adds word and ArrayList to the map, and updates the lineNum   
   */
   public void addWord(String word, int lineNum)
   {
      word = word.toUpperCase();
      
      if(containsKey(word))
      {
         TreeSet<Integer> toAdd = get(word);
         toAdd.add(lineNum);
         put(word, toAdd);
      }
      
      else
      {
         TreeSet<Integer> toAdd = new TreeSet<Integer>();
         toAdd.add(lineNum);
         put(word, toAdd);
      }
   }
   
   public String toString()
   {
      String str = "";
      String finalStr = "";
      for( String word : keySet() )
      {
         str += word + " ";
         str += get(word);
         
         int wordLength = word.length();
         int strLength = str.length();
         str = str.substring(0, wordLength + 1) + str.substring(wordLength + 2, strLength - 1);
         
         finalStr += str + "\n";
         str = "";
      }
      
      finalStr = finalStr.trim();      
      return finalStr;
   }
}
 
/**********************************************
     ----jGRASP exec: java -ea IndexMakerMap
 
 Enter input file name: fish.txt
 Done.
 
  ----jGRASP: operation complete.
  
************************************************/
/****************** fishIndex.txt  **************
A 12, 14, 15
ARE 16
BLACK 6
BLUE 4, 7
CAR 14
FISH 1, 2, 3, 4, 6, 7, 8, 9, 16
HAS 11, 14
LITTLE 12, 14
LOT 15
NEW 9
OF 16
OLD 8
ONE 1, 11, 14
RED 3
SAY 15
STAR 12
THERE 16
THIS 11, 14
TWO 2
WHAT 15   
   ************************/
 