// Name: J2-24
// Date: 3/8/2021

//First password for the extension is 804.
//Extension test: extension(eng2spn, spn2eng, 804);

import java.io.*;
import java.util.*;

public class Dictionary
{
   public static void mainCaller(String file)
   {
      Scanner infile = null;
      
      try
      {
         infile = new Scanner(new File(file));
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
   
   public static void main(String[] args) 
   {
       mainCaller("");  
   }
   
   public static Map<String, Set<String>> makeDictionary(Scanner infile)
   {
      Map<String, Set<String>> map = new TreeMap();

      while(infile.hasNextLine())
      {
         String englishWord = infile.nextLine();
         String spanishWord = infile.nextLine();     
         add(map, englishWord, spanishWord);     
      }
      
      return map;
   }
   
   public static void add(Map<String, Set<String>> dictionary, String word, String translation)
   {
      if(dictionary.containsKey(word))
      {
         Set<String> set = dictionary.get(word);
         set.add(translation);
         dictionary.put(word, set); 
      }
         
      else
      {            
         Set<String> newWords = new TreeSet();
         newWords.add(translation);
         dictionary.put(word, newWords);
      }
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
      int iter = 0;
      
      for( String str : dictionary.keySet() )
      {
         if(iter == 0)
         {
            String englishWord1 = str;
            Set<String> spanishWords1 = dictionary.get(englishWord1);
            Set<String> englishWords1 = new TreeSet<String>();
            englishWords1.add(englishWord1);
            
            for( String str1 : spanishWords1 )
               map.put(str1, englishWords1);           
         }
         
         else
         {
            String englishWord = str;
            Set<String> spanishWords2 = dictionary.get(englishWord);
            
            for( String str2 : spanishWords2 )
            {
               if(map.containsKey(str2))
               {
                  Set<String> englishWordAdded = map.get(str2);
                  englishWordAdded.add(englishWord);
                  map.put(str2, englishWordAdded);
               }
               
               else
               {
                  Set<String> englishWords2 = new TreeSet<String>();
                  englishWords2.add(englishWord);
                  map.put(str2, englishWords2);
               } 
            }
         }
         
         iter++; 
      }
      
      return map;
   }
   
   public static void extension(Map<String, Set<String>> eng2spn, Map<String, Set<String>> spn2eng, int password)
   {
      Scanner sc = new Scanner(System.in); 
      System.out.println("Type 1 if you want to translate from English to Spanish.");
      System.out.println("Or type 2 if you want to translate from Spanish to English.");  
      int option = sc.nextInt();
      
      if(option == 1)
      {
         Scanner sc1 = new Scanner(System.in); 
         System.out.println("Enter English word to look up.");
         String engWord = sc1.nextLine();
         
         if(!eng2spn.containsKey(engWord))
         {
            System.out.println("Sorry, I did not that find that word in the dictionary.");
            System.out.println("Would you like to add a translation to the dicionary? Type 1 for yes, and 2 for no.");
            int optionAdd1 = sc.nextInt();
            
            if(optionAdd1 == 1)
            {
               System.out.println("This is an administrative action.");
               System.out.println("Enter the password to continue.");
               int passwordAttempt1 = sc.nextInt();
               
               if(passwordAttempt1 == password)
               {
                  System.out.println("You have entered the system.");
                  System.out.println("What translation would you like to add for: " + engWord);
                  String tranToEnter = sc1.nextLine();
                  Set<String> toAdd = new TreeSet<String>();
                  toAdd.add(tranToEnter);
                  eng2spn.put(engWord, toAdd);
                  System.out.println("Translation added.");
                  
                  System.out.println("Would you like to enter a new password for the system?");
                  System.out.println("Type 1 for yes, and 2 for no.");
                  int newPwdChoice1 = sc.nextInt();
                  System.out.println("Enter new password. Must be an integer password.");
                  int newPwd1 = sc.nextInt();
                  password = newPwd1;
                  System.out.println("Password updated.");
                  System.out.println("Enter new password to enter the system.");
                  
                  int testNewPwd1 = sc.nextInt();
                  if(testNewPwd1 == password)
                  {
                     System.out.println("Thank you for confirming.");
                     System.out.println("Would you like to remove or modify an entry in the dictionary?");
                     System.out.println("If you would like to remove an entry, type 1");
                     System.out.println("If you would like to modify an entry, type 2");
                     System.out.println("If you would like to exit the program, type 3 [Password will reset to first password]");
                     int testRemoveModifyExit1 = sc.nextInt();
                     
                     if(testRemoveModifyExit1 == 1)
                     {
                        Scanner sc2 = new Scanner(System.in);
                        System.out.println("For which english word would you like to remove a translation?");
                        String engWordToRemove1 = sc2.nextLine();
                        
                        if(eng2spn.containsKey(engWordToRemove1))
                        {
                           Set<String> engWordToRemove1Get = eng2spn.get(engWordToRemove1);
                           System.out.println(engWordToRemove1 + " " + engWordToRemove1Get.toString());
                           System.out.println("Which translation would you like to remove?");
                           
                           String tranToRemove1 = sc2.nextLine();
                           if(engWordToRemove1Get.contains(tranToRemove1))
                           {
                              Iterator<String> it = engWordToRemove1Get.iterator();
                              while(it.hasNext())
                              {
                                 String value = it.next();
                                 if(value.equals(tranToRemove1))
                                 {
                                    engWordToRemove1Get.remove(tranToRemove1);
                                    it = engWordToRemove1Get.iterator();
                                 }
                              }
                              
                              eng2spn.put(engWordToRemove1, engWordToRemove1Get);
                              System.out.println("Translation removed.");
                              System.out.println(engWordToRemove1 + " " + engWordToRemove1Get.toString());
                              System.out.println("Exiting program. Password will reset to first password.");
                              System.out.println("Have a good day.");
                           }
                           
                           else
                           {
                              System.out.println("Translation not found. Exiting program. Password will reset to first password.");
                              System.out.println("Have a good day.");
                           }                           
                        }
                        
                        else
                        {
                           System.out.println("Dictionary does not contain that word. Exiting program. Password will reset to first password.");
                           System.out.println("Have a good day.");
                        }
                     }
                     
                     else if(testRemoveModifyExit1 == 2)
                     {
                        Scanner sc2 = new Scanner(System.in);
                        System.out.println("For which english word would you like to modify a translation?");
                        String engWordToRemove1 = sc2.nextLine();
                        
                        if(eng2spn.containsKey(engWordToRemove1))
                        {
                           Set<String> engWordToRemove1Get = eng2spn.get(engWordToRemove1);
                           System.out.println(engWordToRemove1 + " " + engWordToRemove1Get.toString());
                           System.out.println("Which translation would you like to modify?");
                           
                           String tranToRemove1 = sc2.nextLine();
                           if(engWordToRemove1Get.contains(tranToRemove1))
                           {
                              System.out.println("What translation would you like to replace it with?");
                              String tranToReplace1 = sc2.nextLine();
                              engWordToRemove1Get.add(tranToReplace1);
                              Iterator<String> it = engWordToRemove1Get.iterator();
                              while(it.hasNext())
                              {
                                 String value = it.next();
                                 if(value.equals(tranToRemove1))
                                 {
                                    engWordToRemove1Get.remove(tranToRemove1);
                                    it = engWordToRemove1Get.iterator();
                                 }
                              }
                              
                              eng2spn.put(engWordToRemove1, engWordToRemove1Get);
                              System.out.println("Translation replaced.");
                              System.out.println(engWordToRemove1 + " " + engWordToRemove1Get.toString());
                              System.out.println("Exiting program. Password will reset to first password.");
                              System.out.println("Have a good day.");
                           }
                           
                           else
                           {
                              System.out.println("Translation not found. Exiting program. Password will reset to first password.");
                              System.out.println("Have a good day.");
                           }                           
                        }
                        
                        else
                        {
                           System.out.println("Dictionary does not contain that word. Exiting program. Password will reset to first password.");
                           System.out.println("Have a good day.");
                        }
                     }
                     
                     else
                        System.out.println("Thank you for using the program. Have a good day.");
                  }
               }
               
               else
                  System.out.println("Incorrect password. Program will end now.");              
            }
            
            else
            {
               System.out.println("Enter the password to continue.");
               int passwordAttempt1 = sc.nextInt();
               
               if(passwordAttempt1 == password)
               {
                  System.out.println("Would you like to enter a new password for the system?");
                  System.out.println("Type 1 for yes, and 2 for no.");
                  int newPwdChoice1 = sc.nextInt();
                  System.out.println("Enter new password. Must be an integer password.");
                  int newPwd1 = sc.nextInt();
                  password = newPwd1;
                  System.out.println("Password updated.");
                  System.out.println("Enter new password to enter the system.");
                  
                  int testNewPwd1 = sc.nextInt();
                  if(testNewPwd1 == password)
                  {
                     System.out.println("Thank you for confirming.");
                     System.out.println("Would you like to remove or modify an entry in the dictionary?");
                     System.out.println("If you would like to remove an entry, type 1");
                     System.out.println("If you would like to modify an entry, type 2");
                     System.out.println("If you would like to exit the program, type 3 [Password will reset to first password]");
                     int testRemoveModifyExit1 = sc.nextInt();
                     
                     if(testRemoveModifyExit1 == 1)
                     {
                        Scanner sc2 = new Scanner(System.in);
                        System.out.println("For which english word would you like to remove a translation?");
                        String engWordToRemove1 = sc2.nextLine();
                        
                        if(eng2spn.containsKey(engWordToRemove1))
                        {
                           Set<String> engWordToRemove1Get = eng2spn.get(engWordToRemove1);
                           System.out.println(engWordToRemove1 + " " + engWordToRemove1Get.toString());
                           System.out.println("Which translation would you like to remove?");
                           
                           String tranToRemove1 = sc2.nextLine();
                           if(engWordToRemove1Get.contains(tranToRemove1))
                           {
                              Iterator<String> it = engWordToRemove1Get.iterator();
                              while(it.hasNext())
                              {
                                 String value = it.next();
                                 if(value.equals(tranToRemove1))
                                 {
                                    engWordToRemove1Get.remove(tranToRemove1);
                                    it = engWordToRemove1Get.iterator();
                                 }
                              }
                              
                              eng2spn.put(engWordToRemove1, engWordToRemove1Get);
                              System.out.println("Translation removed.");
                              System.out.println(engWordToRemove1 + " " + engWordToRemove1Get.toString());
                              System.out.println("Exiting program. Password will reset to first password.");
                              System.out.println("Have a good day.");
                           }
                           
                           else
                           {
                              System.out.println("Translation not found. Exiting program. Password will reset to first password.");
                              System.out.println("Have a good day.");
                           }                           
                        }
                        
                        else
                        {
                           System.out.println("Dictionary does not contain that word. Exiting program. Password will reset to first password.");
                           System.out.println("Have a good day.");
                        }
                     }
                     
                     else if(testRemoveModifyExit1 == 2)
                     {
                        Scanner sc2 = new Scanner(System.in);
                        System.out.println("For which english word would you like to modify a translation?");
                        String engWordToRemove1 = sc2.nextLine();
                        
                        if(eng2spn.containsKey(engWordToRemove1))
                        {
                           Set<String> engWordToRemove1Get = eng2spn.get(engWordToRemove1);
                           System.out.println(engWordToRemove1 + " " + engWordToRemove1Get.toString());
                           System.out.println("Which translation would you like to modify?");
                           
                           String tranToRemove1 = sc2.nextLine();
                           if(engWordToRemove1Get.contains(tranToRemove1))
                           {
                              System.out.println("What translation would you like to replace it with?");
                              String tranToReplace1 = sc2.nextLine();
                              engWordToRemove1Get.add(tranToReplace1);
                              Iterator<String> it = engWordToRemove1Get.iterator();
                              while(it.hasNext())
                              {
                                 String value = it.next();
                                 if(value.equals(tranToRemove1))
                                 {
                                    engWordToRemove1Get.remove(tranToRemove1);
                                    it = engWordToRemove1Get.iterator();
                                 }
                              }
                              
                              eng2spn.put(engWordToRemove1, engWordToRemove1Get);
                              System.out.println("Translation replaced.");
                              System.out.println(engWordToRemove1 + " " + engWordToRemove1Get.toString());
                              System.out.println("Exiting program. Password will reset to first password.");
                              System.out.println("Have a good day.");
                           }
                           
                           else
                           {
                              System.out.println("Translation not found. Exiting program. Password will reset to first password.");
                              System.out.println("Have a good day.");
                           }                           
                        }
                        
                        else
                        {
                           System.out.println("Dictionary does not contain that word. Exiting program. Password will reset to first password.");
                           System.out.println("Have a good day.");
                        }
                     }
                     
                     else
                        System.out.println("Thank you for using the program. Have a good day.");
                  }
               }
               
               else
                  System.out.println("Incorrect password. Program will end now."); 
            }
         } 
         
         else
         {
            System.out.println("Current translation for this word.");
            System.out.println(engWord + " " + eng2spn.get(engWord).toString());
            System.out.println("Would you like to add a translation to the dicionary? Type 1 for yes, and 2 for no.");
            int optionAdd1 = sc.nextInt();
            
            if(optionAdd1 == 1)
            {
               System.out.println("This is an administrative action.");
               System.out.println("Enter the password to continue.");
               int passwordAttempt1 = sc.nextInt();
               
               if(passwordAttempt1 == password)
               {
                  System.out.println("You have entered the system.");
                  System.out.println("What translation would you like to add for: " + engWord);
                  String tranToEnter = sc1.nextLine();
                  Set<String> toAdd = eng2spn.get(engWord);;
                  toAdd.add(tranToEnter);
                  eng2spn.put(engWord, toAdd);
                  System.out.println("Translation added.");
                  
                  System.out.println("Would you like to enter a new password for the system?");
                  System.out.println("Type 1 for yes, and 2 for no.");
                  int newPwdChoice1 = sc.nextInt();
                  System.out.println("Enter new password. Must be an integer password.");
                  int newPwd1 = sc.nextInt();
                  password = newPwd1;
                  System.out.println("Password updated.");
                  System.out.println("Enter new password to enter the system.");
                  
                  int testNewPwd1 = sc.nextInt();
                  if(testNewPwd1 == password)
                  {
                     System.out.println("Thank you for confirming.");
                     System.out.println("Would you like to remove or modify an entry in the dictionary?");
                     System.out.println("If you would like to remove an entry, type 1");
                     System.out.println("If you would like to modify an entry, type 2");
                     System.out.println("If you would like to exit the program, type 3 [Password will reset to first password]");
                     int testRemoveModifyExit1 = sc.nextInt();
                     
                     if(testRemoveModifyExit1 == 1)
                     {
                        Scanner sc2 = new Scanner(System.in);
                        System.out.println("For which english word would you like to remove a translation?");
                        String engWordToRemove1 = sc2.nextLine();
                        
                        if(eng2spn.containsKey(engWordToRemove1))
                        {
                           Set<String> engWordToRemove1Get = eng2spn.get(engWordToRemove1);
                           System.out.println(engWordToRemove1 + " " + engWordToRemove1Get.toString());
                           System.out.println("Which translation would you like to remove?");
                           
                           String tranToRemove1 = sc2.nextLine();
                           if(engWordToRemove1Get.contains(tranToRemove1))
                           {
                              Iterator<String> it = engWordToRemove1Get.iterator();
                              while(it.hasNext())
                              {
                                 String value = it.next();
                                 if(value.equals(tranToRemove1))
                                 {
                                    engWordToRemove1Get.remove(tranToRemove1);
                                    it = engWordToRemove1Get.iterator();
                                 }
                              }
                              
                              eng2spn.put(engWordToRemove1, engWordToRemove1Get);
                              System.out.println("Translation removed.");
                              System.out.println(engWordToRemove1 + " " + engWordToRemove1Get.toString());
                              System.out.println("Exiting program. Password will reset to first password.");
                              System.out.println("Have a good day.");
                           }
                           
                           else
                           {
                              System.out.println("Translation not found. Exiting program. Password will reset to first password.");
                              System.out.println("Have a good day.");
                           }                           
                        }
                        
                        else
                        {
                           System.out.println("Dictionary does not contain that word. Exiting program. Password will reset to first password.");
                           System.out.println("Have a good day.");
                        }
                     }
                     
                     else if(testRemoveModifyExit1 == 2)
                     {
                        Scanner sc2 = new Scanner(System.in);
                        System.out.println("For which english word would you like to modify a translation?");
                        String engWordToRemove1 = sc2.nextLine();
                        
                        if(eng2spn.containsKey(engWordToRemove1))
                        {
                           Set<String> engWordToRemove1Get = eng2spn.get(engWordToRemove1);
                           System.out.println(engWordToRemove1 + " " + engWordToRemove1Get.toString());
                           System.out.println("Which translation would you like to modify?");
                           
                           String tranToRemove1 = sc2.nextLine();
                           if(engWordToRemove1Get.contains(tranToRemove1))
                           {
                              System.out.println("What translation would you like to replace it with?");
                              String tranToReplace1 = sc2.nextLine();
                              engWordToRemove1Get.add(tranToReplace1);
                              Iterator<String> it = engWordToRemove1Get.iterator();
                              while(it.hasNext())
                              {
                                 String value = it.next();
                                 if(value.equals(tranToRemove1))
                                 {
                                    engWordToRemove1Get.remove(tranToRemove1);
                                    it = engWordToRemove1Get.iterator();
                                 }
                              }
                              
                              eng2spn.put(engWordToRemove1, engWordToRemove1Get);
                              System.out.println("Translation replaced.");
                              System.out.println(engWordToRemove1 + " " + engWordToRemove1Get.toString());
                              System.out.println("Exiting program. Password will reset to first password.");
                              System.out.println("Have a good day.");
                           }
                           
                           else
                           {
                              System.out.println("Translation not found. Exiting program. Password will reset to first password.");
                              System.out.println("Have a good day.");
                           }                           
                        }
                        
                        else
                        {
                           System.out.println("Dictionary does not contain that word. Exiting program. Password will reset to first password.");
                           System.out.println("Have a good day.");
                        }
                     }
                     
                     else
                        System.out.println("Thank you for using the program. Have a good day.");
                  }
               }
               
               else
                  System.out.println("Incorrect password. Program will end now.");              
            }
            
            else
            {
               System.out.println("Enter the password to continue.");
               int passwordAttempt1 = sc.nextInt();
               
               if(passwordAttempt1 == password)
               {
                  System.out.println("Would you like to enter a new password for the system?");
                  System.out.println("Type 1 for yes, and 2 for no.");
                  int newPwdChoice1 = sc.nextInt();
                  System.out.println("Enter new password. Must be an integer password.");
                  int newPwd1 = sc.nextInt();
                  password = newPwd1;
                  System.out.println("Password updated.");
                  System.out.println("Enter new password to enter the system.");
                  
                  int testNewPwd1 = sc.nextInt();
                  if(testNewPwd1 == password)
                  {
                     System.out.println("Thank you for confirming.");
                     System.out.println("Would you like to remove or modify an entry in the dictionary?");
                     System.out.println("If you would like to remove an entry, type 1");
                     System.out.println("If you would like to modify an entry, type 2");
                     System.out.println("If you would like to exit the program, type 3 [Password will reset to first password]");
                     int testRemoveModifyExit1 = sc.nextInt();
                     
                     if(testRemoveModifyExit1 == 1)
                     {
                        Scanner sc2 = new Scanner(System.in);
                        System.out.println("For which english word would you like to remove a translation?");
                        String engWordToRemove1 = sc2.nextLine();
                        
                        if(eng2spn.containsKey(engWordToRemove1))
                        {
                           Set<String> engWordToRemove1Get = eng2spn.get(engWordToRemove1);
                           System.out.println(engWordToRemove1 + " " + engWordToRemove1Get.toString());
                           System.out.println("Which translation would you like to remove?");
                           
                           String tranToRemove1 = sc2.nextLine();
                           if(engWordToRemove1Get.contains(tranToRemove1))
                           {
                              Iterator<String> it = engWordToRemove1Get.iterator();
                              while(it.hasNext())
                              {
                                 String value = it.next();
                                 if(value.equals(tranToRemove1))
                                 {
                                    engWordToRemove1Get.remove(tranToRemove1);
                                    it = engWordToRemove1Get.iterator();
                                 }
                              }
                              
                              eng2spn.put(engWordToRemove1, engWordToRemove1Get);
                              System.out.println("Translation removed.");
                              System.out.println(engWordToRemove1 + " " + engWordToRemove1Get.toString());
                              System.out.println("Exiting program. Password will reset to first password.");
                              System.out.println("Have a good day.");
                           }
                           
                           else
                           {
                              System.out.println("Translation not found. Exiting program. Password will reset to first password.");
                              System.out.println("Have a good day.");
                           }                           
                        }
                        
                        else
                        {
                           System.out.println("Dictionary does not contain that word. Exiting program. Password will reset to first password.");
                           System.out.println("Have a good day.");
                        }
                     }
                     
                     else if(testRemoveModifyExit1 == 2)
                     {
                        Scanner sc2 = new Scanner(System.in);
                        System.out.println("For which english word would you like to modify a translation?");
                        String engWordToRemove1 = sc2.nextLine();
                        
                        if(eng2spn.containsKey(engWordToRemove1))
                        {
                           Set<String> engWordToRemove1Get = eng2spn.get(engWordToRemove1);
                           System.out.println(engWordToRemove1 + " " + engWordToRemove1Get.toString());
                           System.out.println("Which translation would you like to modify?");
                           
                           String tranToRemove1 = sc2.nextLine();
                           if(engWordToRemove1Get.contains(tranToRemove1))
                           {
                              System.out.println("What translation would you like to replace it with?");
                              String tranToReplace1 = sc2.nextLine();
                              engWordToRemove1Get.add(tranToReplace1);
                              Iterator<String> it = engWordToRemove1Get.iterator();
                              while(it.hasNext())
                              {
                                 String value = it.next();
                                 if(value.equals(tranToRemove1))
                                 {
                                    engWordToRemove1Get.remove(tranToRemove1);
                                    it = engWordToRemove1Get.iterator();
                                 }
                              }
                              
                              eng2spn.put(engWordToRemove1, engWordToRemove1Get);
                              System.out.println("Translation replaced.");
                              System.out.println(engWordToRemove1 + " " + engWordToRemove1Get.toString());
                              System.out.println("Exiting program. Password will reset to first password.");
                              System.out.println("Have a good day.");
                           }
                           
                           else
                           {
                              System.out.println("Translation not found. Exiting program. Password will reset to first password.");
                              System.out.println("Have a good day.");
                           }                           
                        }
                        
                        else
                        {
                           System.out.println("Dictionary does not contain that word. Exiting program. Password will reset to first password.");
                           System.out.println("Have a good day.");
                        }
                     }
                     
                     else
                        System.out.println("Thank you for using the program. Have a good day.");
                  }
               }
               
               else
                  System.out.println("Incorrect password. Program will end now."); 
            }
         }       
      }
      
      else  //spn2eng
      {
         Scanner sc1 = new Scanner(System.in); 
         System.out.println("Enter Spanish word to look up.");
         String spnWord = sc1.nextLine();
         
         if(!spn2eng.containsKey(spnWord))
         {
            System.out.println("Sorry, I did not that find that word in the dictionary.");
            System.out.println("Would you like to add a translation to the dicionary? Type 1 for yes, and 2 for no.");
            int optionAdd1 = sc.nextInt();
            
            if(optionAdd1 == 1)
            {
               System.out.println("This is an administrative action.");
               System.out.println("Enter the password to continue.");
               int passwordAttempt1 = sc.nextInt();
               
               if(passwordAttempt1 == password)
               {
                  System.out.println("You have entered the system.");
                  System.out.println("What translation would you like to add for: " + spnWord);
                  String tranToEnter = sc1.nextLine();
                  Set<String> toAdd = new TreeSet<String>();
                  toAdd.add(tranToEnter);
                  spn2eng.put(spnWord, toAdd);
                  System.out.println("Translation added.");
                  
                  System.out.println("Would you like to enter a new password for the system?");
                  System.out.println("Type 1 for yes, and 2 for no.");
                  int newPwdChoice1 = sc.nextInt();
                  System.out.println("Enter new password. Must be an integer password.");
                  int newPwd1 = sc.nextInt();
                  password = newPwd1;
                  System.out.println("Password updated.");
                  System.out.println("Enter new password to enter the system.");
                  
                  int testNewPwd1 = sc.nextInt();
                  if(testNewPwd1 == password)
                  {
                     System.out.println("Thank you for confirming.");
                     System.out.println("Would you like to remove or modify an entry in the dictionary?");
                     System.out.println("If you would like to remove an entry, type 1");
                     System.out.println("If you would like to modify an entry, type 2");
                     System.out.println("If you would like to exit the program, type 3 [Password will reset to first password]");
                     int testRemoveModifyExit1 = sc.nextInt();
                     
                     if(testRemoveModifyExit1 == 1)
                     {
                        Scanner sc2 = new Scanner(System.in);
                        System.out.println("For which Spanish word would you like to remove a translation?");
                        String spnWordToRemove1 = sc2.nextLine();
                        
                        if(spn2eng.containsKey(spnWordToRemove1))
                        {
                           Set<String> spnWordToRemove1Get = spn2eng.get(spnWordToRemove1);
                           System.out.println(spnWordToRemove1 + " " + spnWordToRemove1Get.toString());
                           System.out.println("Which translation would you like to remove?");
                           
                           String tranToRemove1 = sc2.nextLine();
                           if(spnWordToRemove1Get.contains(tranToRemove1))
                           {
                              Iterator<String> it = spnWordToRemove1Get.iterator();
                              while(it.hasNext())
                              {
                                 String value = it.next();
                                 if(value.equals(tranToRemove1))
                                 {
                                    spnWordToRemove1Get.remove(tranToRemove1);
                                    it = spnWordToRemove1Get.iterator();
                                 }
                              }
                              
                              spn2eng.put(spnWordToRemove1, spnWordToRemove1Get);
                              System.out.println("Translation removed.");
                              System.out.println(spnWordToRemove1 + " " + spnWordToRemove1Get.toString());
                              System.out.println("Exiting program. Password will reset to first password.");
                              System.out.println("Have a good day.");
                           }
                           
                           else
                           {
                              System.out.println("Translation not found. Exiting program. Password will reset to first password.");
                              System.out.println("Have a good day.");
                           }                           
                        }
                        
                        else
                        {
                           System.out.println("Dictionary does not contain that word. Exiting program. Password will reset to first password.");
                           System.out.println("Have a good day.");
                        }
                     }
                     
                     else if(testRemoveModifyExit1 == 2)
                     {
                        Scanner sc2 = new Scanner(System.in);
                        System.out.println("For which Spanish word would you like to modify a translation?");
                        String spnWordToRemove1 = sc2.nextLine();
                        
                        if(spn2eng.containsKey(spnWordToRemove1))
                        {
                           Set<String> spnWordToRemove1Get = spn2eng.get(spnWordToRemove1);
                           System.out.println(spnWordToRemove1 + " " + spnWordToRemove1Get.toString());
                           System.out.println("Which translation would you like to modify?");
                           
                           String tranToRemove1 = sc2.nextLine();
                           if(spnWordToRemove1Get.contains(tranToRemove1))
                           {
                              System.out.println("What translation would you like to replace it with?");
                              String tranToReplace1 = sc2.nextLine();
                              spnWordToRemove1Get.add(tranToReplace1);
                              Iterator<String> it = spnWordToRemove1Get.iterator();
                              while(it.hasNext())
                              {
                                 String value = it.next();
                                 if(value.equals(tranToRemove1))
                                 {
                                    spnWordToRemove1Get.remove(tranToRemove1);
                                    it = spnWordToRemove1Get.iterator();
                                 }
                              }
                              
                              spn2eng.put(spnWordToRemove1, spnWordToRemove1Get);
                              System.out.println("Translation replaced.");
                              System.out.println(spnWordToRemove1 + " " + spnWordToRemove1Get.toString());
                              System.out.println("Exiting program. Password will reset to first password.");
                              System.out.println("Have a good day.");
                           }
                           
                           else
                           {
                              System.out.println("Translation not found. Exiting program. Password will reset to first password.");
                              System.out.println("Have a good day.");
                           }                           
                        }
                        
                        else
                        {
                           System.out.println("Dictionary does not contain that word. Exiting program. Password will reset to first password.");
                           System.out.println("Have a good day.");
                        }
                     }
                     
                     else
                        System.out.println("Thank you for using the program. Have a good day.");
                  }
               }
               
               else
                  System.out.println("Incorrect password. Program will end now.");              
            }
            
            else
            {
               System.out.println("Enter the password to continue.");
               int passwordAttempt1 = sc.nextInt();
               
               if(passwordAttempt1 == password)
               {
                  System.out.println("Would you like to enter a new password for the system?");
                  System.out.println("Type 1 for yes, and 2 for no.");
                  int newPwdChoice1 = sc.nextInt();
                  System.out.println("Enter new password. Must be an integer password.");
                  int newPwd1 = sc.nextInt();
                  password = newPwd1;
                  System.out.println("Password updated.");
                  System.out.println("Enter new password to enter the system.");
                  
                  int testNewPwd1 = sc.nextInt();
                  if(testNewPwd1 == password)
                  {
                     System.out.println("Thank you for confirming.");
                     System.out.println("Would you like to remove or modify an entry in the dictionary?");
                     System.out.println("If you would like to remove an entry, type 1");
                     System.out.println("If you would like to modify an entry, type 2");
                     System.out.println("If you would like to exit the program, type 3 [Password will reset to first password]");
                     int testRemoveModifyExit1 = sc.nextInt();
                     
                     if(testRemoveModifyExit1 == 1)
                     {
                        Scanner sc2 = new Scanner(System.in);
                        System.out.println("For which Spanish word would you like to remove a translation?");
                        String spnWordToRemove1 = sc2.nextLine();
                        
                        if(spn2eng.containsKey(spnWordToRemove1))
                        {
                           Set<String> spnWordToRemove1Get = spn2eng.get(spnWordToRemove1);
                           System.out.println(spnWordToRemove1 + " " + spnWordToRemove1Get.toString());
                           System.out.println("Which translation would you like to remove?");
                           
                           String tranToRemove1 = sc2.nextLine();
                           if(spnWordToRemove1Get.contains(tranToRemove1))
                           {
                              Iterator<String> it = spnWordToRemove1Get.iterator();
                              while(it.hasNext())
                              {
                                 String value = it.next();
                                 if(value.equals(tranToRemove1))
                                 {
                                    spnWordToRemove1Get.remove(tranToRemove1);
                                    it = spnWordToRemove1Get.iterator();
                                 }
                              }
                              
                              spn2eng.put(spnWordToRemove1, spnWordToRemove1Get);
                              System.out.println("Translation removed.");
                              System.out.println(spnWordToRemove1 + " " + spnWordToRemove1Get.toString());
                              System.out.println("Exiting program. Password will reset to first password.");
                              System.out.println("Have a good day.");
                           }
                           
                           else
                           {
                              System.out.println("Translation not found. Exiting program. Password will reset to first password.");
                              System.out.println("Have a good day.");
                           }                           
                        }
                        
                        else
                        {
                           System.out.println("Dictionary does not contain that word. Exiting program. Password will reset to first password.");
                           System.out.println("Have a good day.");
                        }
                     }
                     
                     else if(testRemoveModifyExit1 == 2)
                     {
                        Scanner sc2 = new Scanner(System.in);
                        System.out.println("For which Spanish word would you like to modify a translation?");
                        String spnWordToRemove1 = sc2.nextLine();
                        
                        if(spn2eng.containsKey(spnWordToRemove1))
                        {
                           Set<String> spnWordToRemove1Get = spn2eng.get(spnWordToRemove1);
                           System.out.println(spnWordToRemove1 + " " + spnWordToRemove1Get.toString());
                           System.out.println("Which translation would you like to modify?");
                           
                           String tranToRemove1 = sc2.nextLine();
                           if(spnWordToRemove1Get.contains(tranToRemove1))
                           {
                              System.out.println("What translation would you like to replace it with?");
                              String tranToReplace1 = sc2.nextLine();
                              spnWordToRemove1Get.add(tranToReplace1);
                              Iterator<String> it = spnWordToRemove1Get.iterator();
                              while(it.hasNext())
                              {
                                 String value = it.next();
                                 if(value.equals(tranToRemove1))
                                 {
                                    spnWordToRemove1Get.remove(tranToRemove1);
                                    it = spnWordToRemove1Get.iterator();
                                 }
                              }
                              
                              spn2eng.put(spnWordToRemove1, spnWordToRemove1Get);
                              System.out.println("Translation replaced.");
                              System.out.println(spnWordToRemove1 + " " + spnWordToRemove1Get.toString());
                              System.out.println("Exiting program. Password will reset to first password.");
                              System.out.println("Have a good day.");
                           }
                           
                           else
                           {
                              System.out.println("Translation not found. Exiting program. Password will reset to first password.");
                              System.out.println("Have a good day.");
                           }                           
                        }
                        
                        else
                        {
                           System.out.println("Dictionary does not contain that word. Exiting program. Password will reset to first password.");
                           System.out.println("Have a good day.");
                        }
                     }
                     
                     else
                        System.out.println("Thank you for using the program. Have a good day.");
                  }
               }
               
               else
                  System.out.println("Incorrect password. Program will end now."); 
            }
         } 
         
         else
         {
            System.out.println("Current translation for this word.");
            System.out.println(spnWord + " " + spn2eng.get(spnWord).toString());
            System.out.println("Would you like to add a translation to the dicionary? Type 1 for yes, and 2 for no.");
            int optionAdd1 = sc.nextInt();
            
            if(optionAdd1 == 1)
            {
               System.out.println("This is an administrative action.");
               System.out.println("Enter the password to continue.");
               int passwordAttempt1 = sc.nextInt();
               
               if(passwordAttempt1 == password)
               {
                  System.out.println("You have entered the system.");
                  System.out.println("What translation would you like to add for: " + spnWord);
                  String tranToEnter = sc1.nextLine();
                  Set<String> toAdd = spn2eng.get(spnWord);
                  toAdd.add(tranToEnter);
                  spn2eng.put(spnWord, toAdd);
                  System.out.println("Translation added.");
                  
                  System.out.println("Would you like to enter a new password for the system?");
                  System.out.println("Type 1 for yes, and 2 for no.");
                  int newPwdChoice1 = sc.nextInt();
                  System.out.println("Enter new password. Must be an integer password.");
                  int newPwd1 = sc.nextInt();
                  password = newPwd1;
                  System.out.println("Password updated.");
                  System.out.println("Enter new password to enter the system.");
                  
                  int testNewPwd1 = sc.nextInt();
                  if(testNewPwd1 == password)
                  {
                     System.out.println("Thank you for confirming.");
                     System.out.println("Would you like to remove or modify an entry in the dictionary?");
                     System.out.println("If you would like to remove an entry, type 1");
                     System.out.println("If you would like to modify an entry, type 2");
                     System.out.println("If you would like to exit the program, type 3 [Password will reset to first password]");
                     int testRemoveModifyExit1 = sc.nextInt();
                     
                     if(testRemoveModifyExit1 == 1)
                     {
                        Scanner sc2 = new Scanner(System.in);
                        System.out.println("For which Spanish word would you like to remove a translation?");
                        String spnWordToRemove1 = sc2.nextLine();
                        
                        if(spn2eng.containsKey(spnWordToRemove1))
                        {
                           Set<String> spnWordToRemove1Get = spn2eng.get(spnWordToRemove1);
                           System.out.println(spnWordToRemove1 + " " + spnWordToRemove1Get.toString());
                           System.out.println("Which translation would you like to remove?");
                           
                           String tranToRemove1 = sc2.nextLine();
                           if(spnWordToRemove1Get.contains(tranToRemove1))
                           {
                              Iterator<String> it = spnWordToRemove1Get.iterator();
                              while(it.hasNext())
                              {
                                 String value = it.next();
                                 if(value.equals(tranToRemove1))
                                 {
                                    spnWordToRemove1Get.remove(tranToRemove1);
                                    it = spnWordToRemove1Get.iterator();
                                 }
                              }
                              
                              spn2eng.put(spnWordToRemove1, spnWordToRemove1Get);
                              System.out.println("Translation removed.");
                              System.out.println(spnWordToRemove1 + " " + spnWordToRemove1Get.toString());
                              System.out.println("Exiting program. Password will reset to first password.");
                              System.out.println("Have a good day.");
                           }
                           
                           else
                           {
                              System.out.println("Translation not found. Exiting program. Password will reset to first password.");
                              System.out.println("Have a good day.");
                           }                           
                        }
                        
                        else
                        {
                           System.out.println("Dictionary does not contain that word. Exiting program. Password will reset to first password.");
                           System.out.println("Have a good day.");
                        }
                     }
                     
                     else if(testRemoveModifyExit1 == 2)
                     {
                        Scanner sc2 = new Scanner(System.in);
                        System.out.println("For which Spanish word would you like to modify a translation?");
                        String spnWordToRemove1 = sc2.nextLine();
                        
                        if(spn2eng.containsKey(spnWordToRemove1))
                        {
                           Set<String> spnWordToRemove1Get = spn2eng.get(spnWordToRemove1);
                           System.out.println(spnWordToRemove1 + " " + spnWordToRemove1Get.toString());
                           System.out.println("Which translation would you like to modify?");
                           
                           String tranToRemove1 = sc2.nextLine();
                           if(spnWordToRemove1Get.contains(tranToRemove1))
                           {
                              System.out.println("What translation would you like to replace it with?");
                              String tranToReplace1 = sc2.nextLine();
                              spnWordToRemove1Get.add(tranToReplace1);
                              Iterator<String> it = spnWordToRemove1Get.iterator();
                              while(it.hasNext())
                              {
                                 String value = it.next();
                                 if(value.equals(tranToRemove1))
                                 {
                                    spnWordToRemove1Get.remove(tranToRemove1);
                                    it = spnWordToRemove1Get.iterator();
                                 }
                              }
                              
                              spn2eng.put(spnWordToRemove1, spnWordToRemove1Get);
                              System.out.println("Translation replaced.");
                              System.out.println(spnWordToRemove1 + " " + spnWordToRemove1Get.toString());
                              System.out.println("Exiting program. Password will reset to first password.");
                              System.out.println("Have a good day.");
                           }
                           
                           else
                           {
                              System.out.println("Translation not found. Exiting program. Password will reset to first password.");
                              System.out.println("Have a good day.");
                           }                           
                        }
                        
                        else
                        {
                           System.out.println("Dictionary does not contain that word. Exiting program. Password will reset to first password.");
                           System.out.println("Have a good day.");
                        }
                     }
                     
                     else
                        System.out.println("Thank you for using the program. Have a good day.");
                  }
               }
               
               else
                  System.out.println("Incorrect password. Program will end now.");              
            }
            
            else
            {
               System.out.println("Enter the password to continue.");
               int passwordAttempt1 = sc.nextInt();
               
               if(passwordAttempt1 == password)
               {
                  System.out.println("Would you like to enter a new password for the system?");
                  System.out.println("Type 1 for yes, and 2 for no.");
                  int newPwdChoice1 = sc.nextInt();
                  System.out.println("Enter new password. Must be an integer password.");
                  int newPwd1 = sc.nextInt();
                  password = newPwd1;
                  System.out.println("Password updated.");
                  System.out.println("Enter new password to enter the system.");
                  
                  int testNewPwd1 = sc.nextInt();
                  if(testNewPwd1 == password)
                  {
                     System.out.println("Thank you for confirming.");
                     System.out.println("Would you like to remove or modify an entry in the dictionary?");
                     System.out.println("If you would like to remove an entry, type 1");
                     System.out.println("If you would like to modify an entry, type 2");
                     System.out.println("If you would like to exit the program, type 3 [Password will reset to first password]");
                     int testRemoveModifyExit1 = sc.nextInt();
                     
                     if(testRemoveModifyExit1 == 1)
                     {
                        Scanner sc2 = new Scanner(System.in);
                        System.out.println("For which Spanish word would you like to remove a translation?");
                        String spnWordToRemove1 = sc2.nextLine();
                        
                        if(spn2eng.containsKey(spnWordToRemove1))
                        {
                           Set<String> spnWordToRemove1Get = spn2eng.get(spnWordToRemove1);
                           System.out.println(spnWordToRemove1 + " " + spnWordToRemove1Get.toString());
                           System.out.println("Which translation would you like to remove?");
                           
                           String tranToRemove1 = sc2.nextLine();
                           if(spnWordToRemove1Get.contains(tranToRemove1))
                           {
                              Iterator<String> it = spnWordToRemove1Get.iterator();
                              while(it.hasNext())
                              {
                                 String value = it.next();
                                 if(value.equals(tranToRemove1))
                                 {
                                    spnWordToRemove1Get.remove(tranToRemove1);
                                    it = spnWordToRemove1Get.iterator();
                                 }
                              }
                              
                              spn2eng.put(spnWordToRemove1, spnWordToRemove1Get);
                              System.out.println("Translation removed.");
                              System.out.println(spnWordToRemove1 + " " + spnWordToRemove1Get.toString());
                              System.out.println("Exiting program. Password will reset to first password.");
                              System.out.println("Have a good day.");
                           }
                           
                           else
                           {
                              System.out.println("Translation not found. Exiting program. Password will reset to first password.");
                              System.out.println("Have a good day.");
                           }                           
                        }
                        
                        else
                        {
                           System.out.println("Dictionary does not contain that word. Exiting program. Password will reset to first password.");
                           System.out.println("Have a good day.");
                        }
                     }
                     
                     else if(testRemoveModifyExit1 == 2)
                     {
                        Scanner sc2 = new Scanner(System.in);
                        System.out.println("For which Spanish word would you like to modify a translation?");
                        String spnWordToRemove1 = sc2.nextLine();
                        
                        if(spn2eng.containsKey(spnWordToRemove1))
                        {
                           Set<String> spnWordToRemove1Get = spn2eng.get(spnWordToRemove1);
                           System.out.println(spnWordToRemove1 + " " + spnWordToRemove1Get.toString());
                           System.out.println("Which translation would you like to modify?");
                           
                           String tranToRemove1 = sc2.nextLine();
                           if(spnWordToRemove1Get.contains(tranToRemove1))
                           {
                              System.out.println("What translation would you like to replace it with?");
                              String tranToReplace1 = sc2.nextLine();
                              spnWordToRemove1Get.add(tranToReplace1);
                              Iterator<String> it = spnWordToRemove1Get.iterator();
                              while(it.hasNext())
                              {
                                 String value = it.next();
                                 if(value.equals(tranToRemove1))
                                 {
                                    spnWordToRemove1Get.remove(tranToRemove1);
                                    it = spnWordToRemove1Get.iterator();
                                 }
                              }
                              
                              spn2eng.put(spnWordToRemove1, spnWordToRemove1Get);
                              System.out.println("Translation replaced.");
                              System.out.println(spnWordToRemove1 + " " + spnWordToRemove1Get.toString());
                              System.out.println("Exiting program. Password will reset to first password.");
                              System.out.println("Have a good day.");
                           }
                           
                           else
                           {
                              System.out.println("Translation not found. Exiting program. Password will reset to first password.");
                              System.out.println("Have a good day.");
                           }                           
                        }
                        
                        else
                        {
                           System.out.println("Dictionary does not contain that word. Exiting program. Password will reset to first password.");
                           System.out.println("Have a good day.");
                        }
                     }
                     
                     else
                        System.out.println("Thank you for using the program. Have a good day.");
                  }
               }
               
               else
                  System.out.println("Incorrect password. Program will end now."); 
            }
         }
      }
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