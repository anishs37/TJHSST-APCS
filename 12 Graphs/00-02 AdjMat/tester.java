import java.io.*;
import java.util.*;

public class tester
{
   public static void main(String[] args)
   {
      longestStreak("CCAAAAATTT!");
   }

public static void longestStreak(String str)
{    int longestLen = 1;    int currentLen =  1;    String longSubst = str.substring(0, 1);    String currentSubst = longSubst;    for(int i = 1; i < str.length(); i++)    {        currentSubst = str.substring(i, i + 1);        if(currentSubst .equals(str.substring(i - 1, i)))            currentLen++;        else        {            if(currentLen > longestLen)            {                longestLen = currentLen;                longSubst = str.substring(i - 1, i);            }            currentLen = 1;        }    }    if(currentLen > longestLen)    {        longestLen = currentLen;        longSubst = currentSubst;    }    System.out.println(longSubst + " " + longestLen);}
}