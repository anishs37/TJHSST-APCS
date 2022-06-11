// Name: J2-24
// Date: 4/17/2021
 
import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
public class deHuffmanPix
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nWhat binary picture file (middle part) ? ");
      String middlePart = keyboard.next();
      Scanner sc = new Scanner(new File("pix."+middlePart+".txt")); 
      String binaryCode = sc.next();
      Scanner huffScheme = new Scanner(new File("schemePix."+middlePart+".txt")); 
      int width = huffScheme.nextInt();   //  read the size of the image
      int height = huffScheme.nextInt();    
      
      TreeNode root = huffmanTree(huffScheme);
      BufferedImage bufImg = dehuff(binaryCode, root, height, width);
   	
      JFrame f = new JFrame("HuffmanPix");
      f.setSize(500,500);    // width, height
      f.setLocation(100,50);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setContentPane(new DisplayPix(bufImg));
      f.setVisible(true);
      
      sc.close();
      huffScheme.close();
      keyboard.nextLine();  //press 'enter'
      keyboard.nextLine(); 
      System.exit(0);
   }
   
   public static TreeNode huffmanTree(Scanner huffScheme)
   {
      TreeNode toRet = new TreeNode("");
      
      while(huffScheme.hasNext())
      {
         int schemeLine = Integer.parseInt(huffScheme.next());
         String decode = huffScheme.next();
         
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
         
         tempRet.setValue(schemeLine);
      }
      
      return toRet;
   }
   
   public static BufferedImage dehuff(String text, TreeNode root, int height, int width)
   {
      BufferedImage bufImg = new BufferedImage( width, height, BufferedImage.TYPE_INT_RGB);
      
      /*  your code goes here */
  
      TreeNode tempRoot = root;
      
      for(int w = 0; w < width; w++)
      {
         for(int h = 0; h < height; h++)
         {
            int check = 0;
            while((text.length() > 0) && (check == 0))
            {
               String indivDecode = text.substring(0, 1);
               int indivDecodeVal = Integer.parseInt(indivDecode);
               
               if(indivDecodeVal == 0)
                  tempRoot = tempRoot.getLeft();
               
               else
                  tempRoot = tempRoot.getRight();
               
               text = text.substring(1);
               
               if((tempRoot.getLeft() == null) && (tempRoot.getRight() == null) && (tempRoot.getValue() != null))
               {
                  bufImg.setRGB(w, h, ((int) tempRoot.getValue()));
                  tempRoot = root;
                  check = 1;
               }
            }            
         }
      }
      
      return bufImg;
   }
}
   
 
 /* normal AP-style TreeNode class  */
class TreeNode
{
   private Object value; 
   private TreeNode left, right;
   public TreeNode(Object initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }  
   public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   } 
   public Object getValue()
   { 
      return value; 
   }  
   public TreeNode getLeft() 
   { 
      return left; 
   }  
   public TreeNode getRight() 
   { 
      return right; 
   }
   public void setValue(Object theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(TreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(TreeNode theNewRight)
   { 
      right = theNewRight;
   }
}
 
  /*
  * Minimum code necessary to show a BufferedImage.   
  * 
  */ 
class DisplayPix extends JPanel
{
   private BufferedImage img;
   private Graphics g;
 
   public DisplayPix(BufferedImage bufImg, ImageIcon i)   //for Huffman Coding
   {
      int w = bufImg.getWidth();
      int h = bufImg.getHeight();
      img = bufImg;
      g = bufImg.getGraphics();
      g.drawImage( i.getImage() , 0 , 0 , w , h, null );
   }
   public DisplayPix(BufferedImage bufImg)              //for deHuffman
   {
      img = bufImg;
   }
   public void paintComponent(Graphics g)
   {
      g.drawImage( img , 0 , 0 , getWidth() , getHeight() , null );
   }
}