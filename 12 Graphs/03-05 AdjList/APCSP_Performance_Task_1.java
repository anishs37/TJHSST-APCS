/*

Prerequisites before running this program:

   1. Download this file
   2. Create two empty text files
      a. Namely highScores.txt and scores.txt in the same directory as this file is in
   3. Compile this file and run this program and it will work
*/

import java.io.*;
import java.awt.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.sql.Timestamp;

public class APCSP_Performance_Task_1
{
   public static PrintWriter outfile = null;
   public static PrintWriter outfile2 = null;
   
   /* Main Method
   
      1. Instantiates and declares input and output files
      2. Reads in data from input files and checks for highest scores and other statistics
      3. Calls checkOption method
   */
   
   public static void main(String[] args)
   {
      Scanner infile = null;
      Scanner infile2 = null;
      try
      {
         infile = new Scanner(new File("highScores.txt"));
         infile2 = new Scanner(new File("scores.txt"));
      }
      catch(Exception e)
      {
      }
      
      try
      {
         outfile = new PrintWriter(new FileWriter("highScores.txt", true));
         outfile2 = new PrintWriter(new FileWriter("scores.txt", true));
      }   
      catch(IOException e)
      {
         System.out.println("Text not appended.");
         System.exit(0);
      }
      
      JFrame frame = new JFrame("");
      String name = JOptionPane.showInputDialog(frame, "Welcome to the program! \nEnter your first name (Case sensitive to retrieve statistics).");
      int option = Integer.parseInt(JOptionPane.showInputDialog(frame, "Hello " + name + "! Welcome to the game!" + 
                                 "\nType -1 at any time to exit the game. Any invalid input will exit the program with no confirmation message." + 
                                 "\nType 1, 2, or 3 to continue to that option." + 
                                 "\n1. Display instructions for the game." + 
                                 "\n2. Start game." + 
                                 "\n3. View credits."));

      int highestScore = 0;
      int highestScoreEver = 0;
      
      while(infile.hasNextLine())
      {
         String currLine = infile.nextLine();
         String[] splitLine = currLine.split("\t");
         String nameToCheck = splitLine[0].trim();
         int score = Integer.parseInt(splitLine[1].trim());
         
         if(nameToCheck.equals(name))
            if(score > highestScore)
               highestScore = score;
         
         if(score > highestScoreEver)
            highestScoreEver = score;
      }
      
      infile.close();
      ArrayList<Integer> scores = new ArrayList<Integer>();
      
      while(infile2.hasNextLine())
      {
         String currLine = infile2.nextLine();
         String[] splitLine = currLine.split("\t");
         String nameToCheck = splitLine[0].trim();
         String timeStamp = splitLine[2].trim();
         String[] splitTimeStamp = timeStamp.split(" ");
         String dateStamp = splitTimeStamp[0].trim();
         
         Date date = new Date();
         String dateToCheck = new Timestamp(date.getTime()) + "";
         dateToCheck = dateToCheck.substring(0, 10);
         
         if((nameToCheck.equals(name)) && dateStamp.equals(dateToCheck))
         {
            int score = Integer.parseInt(splitLine[1].trim());
            scores.add(score);
         }
      }
      
      infile2.close();
      checkOption(option, name, highestScore, highestScoreEver, scores);
   }
   
   /* checkOption Method
   
      1. Calls one of three methods (showInstructions, startGame, or showCredits) based on what user inputs
  
   */
   
   public static void checkOption(int option, String name, int highestScore, int highestScoreEver, ArrayList<Integer> scores)
   {
      JFrame frame = new JFrame("");
      switch(option)
      {
         case -1:
            int confirmation = JOptionPane.showConfirmDialog(frame, "Are you sure that you would like to exit?");
         
            if(confirmation == JOptionPane.YES_OPTION)
            {
               JOptionPane.showMessageDialog(frame, "Thank you for playing. Have a good day! \nPress OK to exit the program.");
               System.exit(0);
            }
            
            else
            {
               int option2 = Integer.parseInt(JOptionPane.showInputDialog(frame, "Welcome back to the game!" + 
                                 "\nType -1 at any time to exit the game. Any invalid input will exit the program with no confirmation message." + 
                                 "\nType 1, 2, or 3 to continue to that option." + 
                                 "\n1. Display instructions for the game." + 
                                 "\n2. Start game." + 
                                 "\n3. View credits."));
               
               checkOption(option2, name, highestScore, highestScoreEver, scores);               
            }
         
         case 1: showInstructions(name, highestScore, highestScoreEver, scores);
         
         case 2: startGame(name, highestScore, highestScoreEver, scores);
         
         case 3: showCredits(name, highestScore, highestScoreEver, scores);
         
         default: System.exit(0);
      }
   }
   
   /* displayString Method
   
      1. Will display the string passed into this method in the JOptionPane
   */
   
   public static void displayString(int option, int optMsg)
   {
      JFrame frame = new JFrame("");
      switch(option)
      {
         case 1: 
            String str = "Congratulations, you just beat your highest score!" +
                         "\nYour name, score, and time was just added to the high scores list!" + 
                         "\nPress OK to continue.";
           
            JOptionPane.showMessageDialog(frame, str);
            break;
            
         case 2:
            String str2 = "Congratulations! You achieved the highest possible score of 10!" + 
                         "\nPress OK to continue.";
            
            JOptionPane.showMessageDialog(frame, str2);
            break;
            
         case 3:
            String str3 = "Congratulations, you just achieved the highest score ever out of anyone who has played!" +
                         "\nYou are the new champion! Press OK to continue.";
            
            JOptionPane.showMessageDialog(frame, str3);
            break;
            
         case 4:
            String str4 = "Your current score was " + optMsg + " points away from tying the all-time record." +
                         "\nKeep trying to get the highest score ever! Press OK to continue.";
            
            JOptionPane.showMessageDialog(frame, str4);
            break;
            
         case 5:
            String str5 = "You tied for the highest score ever! Keep trying to get the highest score ever!" + 
                         "\nPress OK to continue.";
            
            JOptionPane.showMessageDialog(frame, str5);
            break;
      }
   }
   
   /* showInstructions Method
   
      1. Displays instructions for game
      2. Calls checkOption method
   */
   
   public static void showInstructions(String name, int highestScore, int highestScoreEver, ArrayList<Integer> scores)
   {
      JFrame frame = new JFrame("Instructions");
      JOptionPane.showMessageDialog(frame, "Here is how to play the game:" + 
                                    "\n1. On the next screen, type 2" + 
                                    "\n2. Press OK on the confirmation screen to exit the dialog and enter the game" + 
                                    "\n3. Enter the numbers you see on the screen, separated by only spaces (no commas, etc)" + 
                                    "\n4. Enjoy the game!" + 
                                    "\nPress OK to continue.");
      
      int option = Integer.parseInt(JOptionPane.showInputDialog(frame,
                              "Type -1 at any time to exit the program. Any invalid input will exit the program with no confirmation message." + 
                              "\nType 1, 2, or 3 to continue to that option." + 
                              "\n1. Display instructions for the game." + 
                              "\n2. Start game." + 
                              "\n3. View credits."));
      
      checkOption(option, name, highestScore, highestScoreEver, scores);
   }
   
   public static void endAtTen(int scoreThisRound, int score, String name, int highestScore, int highestScoreEver, ArrayList<Integer> scores)
   {
      JFrame frame = new JFrame("");
      Collections.sort(scores);
               
      double sum = 0;
      int scoresSize = scores.size();
      
      for(int toAdd = 0; toAdd < scoresSize; toAdd++)
         sum += scores.get(toAdd);
         
      Date date = new Date();
      outfile2.println(name + "\t" + score + "\t" + new Timestamp(date.getTime()));
      
      if(scoreThisRound > highestScore)     
      {
         outfile.println(name + "\t" + score + "\t" + new Timestamp(date.getTime()));
         displayString(1, 0);
      }
      
      if(scoreThisRound > highestScoreEver)
         displayString(3, 0);
      
      else
      {
         int scoreDiff = highestScoreEver - scoreThisRound;
         
         if(scoreDiff != 0)
            displayString(4, scoreDiff);
         
         else
            displayString(5, 0);
      }
      
      int lowestScore = scores.get(0);
      double averageScore = sum / scoresSize;
      highestScore = scores.get(scoresSize - 1);
      
      NumberFormat toFormat = new DecimalFormat("#0.000");
      JOptionPane.showMessageDialog(frame, "Score this round: " + scoreThisRound +
                                 "\nLowest Score Today: " + lowestScore + 
                                 "\nAverage Score Today: " + toFormat.format(averageScore) + 
                                 "\nHighest Score Ever: " + highestScore + 
                                 "\nPress OK to continue.");
      
      
      int option = Integer.parseInt(JOptionPane.showInputDialog(frame,
                     "Type 2 to continue playing the game with your name." + 
                     "\nTyping any other number will exit the game."));
      
      switch(option)
      {
         case 2: startGame(name, highestScore, highestScoreEver, scores);
         
         default: 
            outfile.close();
            outfile2.close();
            System.exit(0);
      }
   }
   
   /* startGame Method (method that will run the game)
   
      1. Displays random numbers (keeps increasing by 1 for each level user is on) to user
      2. Checks if user enters the random numbers correctly
         a. If he/she does, score is incremented by 1
         b. If he/she does not, game exits, statistics are shown to user, and score + name is added to scores and/or highScores list
      3. Program continues until score = 10 or until user types random numbers incorrectly
      4. Will prompt user if he/she wants to play game again.
         If so, game will start over. If not, program exits.
   */
   
   public static void startGame(String name, int highestScore, int highestScoreEver, ArrayList<Integer> scores)
   {
      JFrame frame = new JFrame("Memorization Game");
      ArrayList<Integer> nums = new ArrayList<Integer>();
      ArrayList<Integer> randNums = new ArrayList<Integer>();  
      int score = 0;
      
      for(int i = 10; i <= 99; i++)
         nums.add(i);
      
      int numsSize = nums.size();
      
      for(int iter = 1; iter <= 10; iter++)
      {
         String strToDisplay = "";    
         for(int innerIter = 1; innerIter <= iter; innerIter++)
         {
            int randIndex = ((int)(Math.random() * numsSize));
            int randNumToAdd = nums.get(randIndex);
            randNums.add(randNumToAdd);
            strToDisplay += randNumToAdd + " ";
         }
         
         JOptionPane.showMessageDialog(frame, "" + strToDisplay + "\nPress OK to continue.");
         String input = JOptionPane.showInputDialog(frame, "Enter the order of the numbers on the previous screen, separated by spaces.");
                                 
         strToDisplay = strToDisplay.trim();
         input = input.trim();
         
         if(stringsAreEqual(strToDisplay, input))
         {
            if(iter == 10)
            {
               score++;
               displayString(2, 0);                            
               int scoreThisRound = score;
               scores.add(scoreThisRound);   
               endAtTen(scoreThisRound, score, name, highestScore, highestScoreEver, scores);
            }
            
            else
               score++;
         }
         
         else
         {
            int scoreThisRound = iter - 1;
            Date date = new Date();
            outfile2.println(name + "\t" + score + "\t" + new Timestamp(date.getTime()));
            Collections.sort(scores);
            int scoresSize = scores.size();
            highestScore = scores.get(scoresSize - 1);
            
            if(scoreThisRound > highestScore)     
            {
               outfile.println(name + "\t" + score + "\t" + new Timestamp(date.getTime()));
               JOptionPane.showMessageDialog(frame, "Congratulations, you just beat your highest score!" +
                                          "\nYour name, score, and time was just added to the high scores list!" + 
                                          "\nPress OK to continue.");
            }
            
            if(scoreThisRound > highestScoreEver)
               JOptionPane.showMessageDialog(frame, "Congratulations, you just achieved the highest score ever out of anyone who has played!" +
                                             "\nYou are the new champion! Press OK to continue.");
               
            else
            {
               int scoreDiff = highestScoreEver - scoreThisRound;
               
               if(scoreDiff != 0)
                  JOptionPane.showMessageDialog(frame, "Your current score was " + (highestScoreEver - scoreThisRound) + " points away from tying the all-time record." +
                                             "\nKeep trying to get the highest score ever! Press OK to continue.");
               
               else
                  JOptionPane.showMessageDialog(frame, "You tied for the highest score ever! Keep trying to get the highest score ever!" + 
                                             "\nPress OK to continue.");
            }

            scores.add(scoreThisRound);
            Collections.sort(scores);
            
            double sum = 0;
            
            for(int toAdd = 0; toAdd < scoresSize; toAdd++)
               sum += scores.get(toAdd);

            int lowestScore = scores.get(0);
            double averageScore = sum / scoresSize;
            
            NumberFormat toFormat = new DecimalFormat("#0.000");
            JOptionPane.showMessageDialog(frame, "Score this round: " + scoreThisRound +
                                          "\nLowest Score Today: " + lowestScore + 
                                          "\nAverage Score Today: " + toFormat.format(averageScore) + 
                                          "\nHighest Score Ever: " + highestScore + 
                                          "\nPress OK to continue.");
            
            int option = Integer.parseInt(JOptionPane.showInputDialog(frame,
                              "Type 2 to continue playing the game with your name." + 
                              "\nTyping any other number will exit the game."));
               
            switch(option)
            {
               case 2: startGame(name, highestScore, highestScoreEver, scores);
               
               default: 
                  outfile.close();
                  outfile2.close();
                  System.exit(0);
            }
         }
      }
   }
   
   /* stringsAreEqual Method
   
      1. Checks if two strings are equal.
         a. If they are, return true
         b. If they are not, return false
   */
   
   public static boolean stringsAreEqual(String str1, String str2)
   {
      int str1Length = str1.length();
      int str2Length = str2.length();
      
      if(str1Length != str2Length)
         return false;
      
      else
      {
         for(int i = 0; i < str1Length; i++)
         {
            String str1Substring = str1.substring(i, i + 1);
            String str2Substring = str2.substring(i, i + 1);
            
            if(!str1Substring.equals(str2Substring))
               return false;          
         }
      }
      
      return true;
   }
   
   /* showCredits Method
   
      1. Displays credits for the program
      2. Calls checkOption method
   */
   
   public static void showCredits(String name, int highestScore, int highestScoreEver, ArrayList<Integer> scores)
   {
      JFrame frame = new JFrame("Credits");
      JOptionPane.showMessageDialog(frame, "1. apclassroom.collegeboard.org" + 
                                    "\n 2. Java" + 
                                    "\n Press OK to continue.");
      
      int option = Integer.parseInt(JOptionPane.showInputDialog(frame,
                              "Type -1 at any time to exit the program. Any invalid input will exit the program with no confirmation message." + 
                              "\nType 1, 2, or 3 to continue to that option." + 
                              "\n1. Display instructions for the game." + 
                              "\n2. Start game." + 
                              "\n3. View credits."));
      
      checkOption(option, name, highestScore, highestScoreEver, scores);
   }
}