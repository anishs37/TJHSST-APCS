// Name: J2-24
// Date: 9/24/20
import java.util.*;
import java.io.*;
public class PigLatin
{
   public static void main(String[] args) 
   {
      //part_1_using_pig();
      part_2_using_piglatenizeFile();
      
      /*  extension only    */
       String pigLatin = pig("What!?");
       System.out.print(pigLatin + "\t\t" + pigReverse(pigLatin));   //Yahwta!?
       pigLatin = pig("{(Hello!)}");
       System.out.print("\n" + pigLatin + "\t\t" + pigReverse(pigLatin)); //{(Yaholle!)}
       pigLatin = pig("\"McDonald???\"");
       System.out.println("\n" + pigLatin + "  " + pigReverse(pigLatin));//"YaDcmdlano???"
   }

   public static void part_1_using_pig()
   {
      Scanner sc = new Scanner(System.in);
      while(true)
      {
         System.out.print("\nWhat word? ");
         String s = sc.next();
         if(s.equals("-1"))
         {
            System.out.println("Goodbye!"); 
            System.exit(0);
         }
         String p = pig(s);
         System.out.println(p);
      }		
   }

   public static final String punct = "\",./;:?<>[]{}|`~!@#$%^&*()";
   public static final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
   public static String vowels = "AEIOUYaeiouy";
 
   public static String pBefore(String s)
   {  String punct_before1 = "";
      for(int k = 0; k < s.length(); k++)
      {
         if(!Character.isLetter(s.charAt(k)))
            {
            for(int j = 0; j < punct.length(); j++)
               {
                  if(s.charAt(k) == punct.charAt(j))
                  {
                     punct_before1 = punct_before1 + s.charAt(k);
                  }
               }
            }
          else
            {
               break;
            }
      }
          s = s.substring(punct_before1.length());
          return punct_before1;
   }
//---------------------------
   public static String actualWord(String s)
   {  String actual_word1 = "";
      for(int k = 0; k < s.length(); k++)
      {
         if(Character.isLetter(s.charAt(k)))
            {
            for(int j = 0; j < letters.length(); j++)
               {
                  if(s.charAt(k) == letters.charAt(j))
                  {
                     actual_word1 = actual_word1 + s.charAt(k);
                  }
               }
            }
          else
            {
               break;
            }
      }
          return actual_word1;
   }

//---------------------------
   
   public static String pig(String s)
   {
      String pb = pBefore(s);
      s=s.substring(pb.length());
      String aw= actualWord(s);
      String pa = s.substring(aw.length());
      s = aw;
      
      if(s.length() == 0)
         return "";
      
      String result = "";
      
      if(s.charAt(0) == 'a' || s.charAt(0) =='e' || s.charAt(0) =='i'|| s.charAt(0) =='o' ||s.charAt(0) == 'u' || s.charAt(0) =='A' ||s.charAt(0) == 'E' || s.charAt(0) =='I'||s.charAt(0) == 'O' ||s.charAt(0) == 'U')
      {
         result = s + "way";
      }
      else
      {         
         for(int i = 0; i < s.length(); i++)
         {            
            if(s.charAt(i) == 'Q' || s.charAt(i) == 'q')
            {
               i = i + 2;
            }
            
            if(s.charAt(0) == 'Y' || s.charAt(0) == 'y')
            {
               vowels = vowels.replace("Y", "");
               vowels = vowels.replace("y", "");
            }
               
            for(int vowel = 0; vowel < vowels.length(); vowel++)
            {
               if(s.charAt(i) == vowels.charAt(vowel))
               {
                  char ch1 = s.charAt(0);
                  
                  if(Character.isUpperCase(ch1))
                  {
                     String s1 = s.substring(0, 1).toLowerCase() + s.substring(1);
                     result = pb + s1.substring(i) + s1.substring(0, i) + "ay" + pa;
                     vowel = vowels.length();
                     i = s.length();
                  }
                  
                  else
                  {               
                     result = pb + s.substring(i) + s.substring(0, i) + "ay" + pa; 
                     vowel = vowels.length();
                     i = s.length();
                  }
               }    
            } 
         }
      }
      
      char ch = s.charAt(0);
      
      if(Character.isUpperCase(ch))
      {
         result = result.substring(0, 1).toUpperCase() + result.substring(1);
      }
      
      if(result.equals(""))
      {
          return "**** NO VOWEL ****";
      }

      else
      {
         return result;
      }
   }


   public static void part_2_using_piglatenizeFile() 
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("input filename including .txt: ");
      String fileNameIn = sc.next();
      System.out.print("output filename including .txt: ");
      String fileNameOut = sc.next();
      piglatenizeFile( fileNameIn, fileNameOut );
      System.out.println("Piglatin done!");
   }

/****************************** 
*  piglatinizes each word in each line of the input file
*    precondition:  both fileNames include .txt
*    postcondition:  output a piglatinized .txt file 
******************************/
   public static void piglatenizeFile(String fileNameIn, String fileNameOut) 
   {
      Scanner infile = null;
      PrintWriter outfile = null;
      File in_filename =  null;
      File out_filename = null;
      
      try
      {    
         in_filename = new File(fileNameIn); 
         infile = new Scanner(in_filename);
         out_filename = new File(fileNameOut);
         outfile = new PrintWriter(out_filename);
      }      
      
      catch(IOException e)
      {
         System.out.println("oops");
         System.exit(0);   
      }
         
        while(infile.hasNextLine())
         {
            String line = infile.nextLine();
            String[] words_in_line = line.split(" ");
            for(int output = 0; output < words_in_line.length; output++)
            {
               if(!words_in_line[output].equals(""))
               {
                  outfile.print(pig(words_in_line[output].trim()) + " ");
               }
            }
            outfile.println();
         }
          
      outfile.close();
      infile.close();
   }
   
   /* EXTENSION: Output each PigLatin word in reverse, preserving before-and-after 
       punctuation.  */
             
         /*while(infile.hasNextLine())
         {
            String line = infile.nextLine();
            String[] words_in_line = line.split(" ");
            System.out.println(words_in_line);
            for(int output = 0; output <= words_in_line.length(); output++)
            {
               if(!words_in_line[output].equals(""))
               {
                  outfile.print(pig(words_in_line[output].trim()) + " ");
               }
               outfile.println();
            }
         }*/
           
   public static String pigReverse(String s)
   {
   
      /*String pb_1 = pBefore(s);
      s=s.substring(pb_1.length());
      String aw_1 = actualWord(s);
      String pa_1 = s.substring(aw_1.length());*/
      //s = aw_1;
      
      if(s.length() == 0)
         return "";
       
      String str = new String(s);
      String p_before = "";
      String p_after = "";
      
      int start = 0;
      int end_word = s.length();
      
      /*
      else
      {
         String s_1 = pig(s);
         String reversed_word = "";
         
         for(int go = s_1.length() - 1; go >= 0; go--)
         {
            reversed_word = reversed_word + s_1.charAt(go);
         }
         
         return pb_1 + reversed_word + pa_1;
      }*/
      
      if(punct.contains(s.charAt(0) + ""))
      {
         for(int i = 0; i < s.length(); i++)
         {
            if(punct.contains(s.charAt(i) + ""))
            {
               p_before += s.charAt(i);
            }
            
            else
            {
               start = i;
               break;
            }
         }
      }
      
      if(punct.contains(s.charAt(s.length() - 1) + ""))
      {
         for(int i = s.length() - 1; i >= 0; i--)
         {
            if(punct.contains(s.charAt(i) + ""))
            {
               p_after = s.charAt(i) + p_after;
            }
            
            else
            {
               end_word = i + 1;
               break;
            }
         }
      }
      
      str = s.substring(start, end_word);
      String reversed_word = "";
      
      for(int i = str.length() - 1; i >= 0; i--)
      {
         String char_str = "" + str.charAt(i);
         
         if(i == 0)
         {
            char_str = char_str.toLowerCase();
         }
         
         if(i == str.length() - 1)
         {
            char_str = char_str.toUpperCase();
         }
         
         reversed_word += char_str;
      }
      
      return p_before + reversed_word + p_after;
   }
}
