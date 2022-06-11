//Name: J2-24
//Date: 3/26/2021

import java.util.*;
import java.io.*;
public class deHuffman
{
   public static void main(String[] args) throws IOException
   {
      mainCaller("tj");
   }
   
   public static void mainCaller(String str) throws IOException
   {
      Scanner sc = new Scanner(new File("message."+str+".txt")); 
      String binaryCode = sc.next();
      Scanner huffLines = new Scanner(new File("scheme."+str+".txt")); 
      
      TreeNode root = huffmanTree(huffLines);
      String message = dehuff(binaryCode, root);
      System.out.println(message);
      	
      sc.close();
      huffLines.close();
   }
   
   public static TreeNode huffmanTree(Scanner huffLines)
   {
      TreeNode toRet = new TreeNode(null);
      
      while(huffLines.hasNextLine())
      {
         String schemeLine = huffLines.nextLine();
         String letter = schemeLine.substring(0, 1);
         String decode = schemeLine.substring(1);
         
         TreeNode tempRet = toRet;
         
         while(decode.length() > 0)
         {
            String indivDecode = decode.substring(0, 1);
            int indivDecodeVal = Integer.parseInt(indivDecode);
            
            if(indivDecodeVal == 0)
            {
               if(tempRet.getLeft() == null)
                  tempRet.setLeft(new TreeNode(""));
               
               tempRet = tempRet.getLeft();
            }
            
            else
            {
               if(tempRet.getRight() == null)
                  tempRet.setRight(new TreeNode(""));
               
               tempRet = tempRet.getRight();
            }
            
            decode = decode.substring(1);
         }
         
         tempRet.setValue(letter);
      }
      
      return toRet;
   }
   
   public static String dehuff(String text, TreeNode root)
   {
      String toRet = "";
      TreeNode tempRoot = root;
      
      while(text.length() > 0)
      {
         String indivDecode = text.substring(0, 1);
         int indivDecodeVal = Integer.parseInt(indivDecode);
         
         if(indivDecodeVal == 0)
            tempRoot = tempRoot.getLeft();
         
         else
            tempRoot = tempRoot.getRight();
         
         text = text.substring(1);
         
         if((tempRoot.getLeft() == null) && (tempRoot.getRight() == null))
         {
            toRet += tempRoot.getValue() + "";
            tempRoot = root;
         }
      }
      
      return toRet;
   }
}