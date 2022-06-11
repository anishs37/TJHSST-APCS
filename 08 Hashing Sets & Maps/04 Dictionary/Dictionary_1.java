// Name: J2-24
// Date: 3/8/2021

import java.io.*;
import java.util.*;

public class Dictionary_1
{
   public static void mainCaller()
   {
      main(null);
   }
   
   public static void main(String[] args) 
   {
      Scanner infile = null;
      
      try
      {
         infile = new Scanner(new File("spanglish.txt"));
         System.setOut(new PrintStream(new FileOutputStream("dictionaryOutput_1.txt")));
      }
      catch(Exception e)
      {
      }
      
      Map<String, Set<String>> eng2spn = makeDictionary( infile );
      System.out.println("ENGLISH TO SPANISH");
      display(eng2spn);
      
      System.out.println();
      
      Map<String, Set<String>> spn2eng = reverse(eng2spn);
      System.out.println("SPANISH TO ENGLISH");
      display(spn2eng);
   }
   
   public static Map<String, Set<String>> makeDictionary(Scanner infile)
   {
      Map<String, Set<String>> map = new TreeMap();
      Set<String> repeat = new TreeSet();
      String currentEnglishWord = "";
      String currentSpanishWord = "";
     
      while(infile.hasNextLine())
      {
         String englishWord1 = infile.nextLine();
         String spanishWord1 = infile.nextLine();
         String englishWord2 = infile.nextLine();
         String spanishWord2 = infile.nextLine();
         
         if(englishWord1 == englishWord2)
            spanishWord1 = spanishWord1 + spanishWord2;      
            currentEnglishWord = englishWord1;
         else
         
            currentSpanishWord = spanishWord1;
            map.put(currentEnglishWord, currentSpanishWord);
         
         if(map.containsKey(englishWord))
            repeat.add(spanishWord);
         
         else
         {
            //Set<String> repeat1 = new TreeSet();
            map.put(currentEnglishWord, repeat);
            
            Iterator<String> it = repeat.iterator();
            
            while(it.hasNext())
            {
               String val = it.next();
               if(val.length() != 0)
                  it.remove();
            }
             
            repeat.add(spanishWord);
         }     
      }
      
      return map;
   }
   
   public static void add(Map<String, Set<String>> dictionary, String word, String translation)
   {
      
   }
   
   public static void display(Map<String, Set<String>> m)
   {
      Iterator<String> it = m.keySet().iterator();
      
      while(it.hasNext())
      {
         String key = it.next();
         Set<String> values = m.get(key);
         System.out.println(key + " " + values.toString());
      }
   }
   
   public static Map<String, Set<String>> reverse(Map<String, Set<String>> dictionary)
   {
      Map<String, Set<String>> map = new TreeMap();
      return map;
   }
}


   /********************
	INPUT:
   	holiday
		fiesta
		holiday
		vacaciones
		party
		fiesta
		celebration
		fiesta
     <etc.>
  *********************************** 
	OUTPUT:
		ENGLISH TO SPANISH
			banana [banana]
			celebration [fiesta]
			computer [computadora, ordenador]
			double [doblar, doble, duplicar]
			father [padre]
			feast [fiesta]
			good [bueno]
			hand [mano]
			hello [hola]
			holiday [fiesta, vacaciones]
			party [fiesta]
			plaza [plaza]
			priest [padre]
			program [programa, programar]
			sleep [dormir]
			son [hijo]
			sun [sol]
			vacation [vacaciones]

		SPANISH TO ENGLISH
			banana [banana]
			bueno [good]
			computadora [computer]
			doblar [double]
			doble [double]
			dormir [sleep]
			duplicar [double]
			fiesta [celebration, feast, holiday, party]
			hijo [son]
			hola [hello]
			mano [hand]
			ordenador [computer]
			padre [father, priest]
			plaza [plaza]
			programa [program]
			programar [program]
			sol [sun]
			vacaciones [holiday, vacation]

**********************/