// Name: J2-24    
// Date: 4/17/2021

import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.*;
public class HuffmanPix
{
   public static int WIDTH = 50;   // 500 x 500 is too big
   public static int HEIGHT = 50;

   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("Encoding using Huffman codes");
      System.out.print("\nWhat image (including extension)? ");
      String pixName = keyboard.nextLine();
      ImageIcon i = new ImageIcon(pixName);
      BufferedImage bufImg = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
   
      JFrame f = new JFrame("HuffmanPix");
      f.setSize(500,500);    // width, height
      f.setLocation(100,50);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setContentPane(new DisplayPix(bufImg, i));
      f.setVisible(true);
   
      System.out.print("\nEnter middle part of filename:  ");
      String middlePart = keyboard.nextLine();
   
      huffmanize( bufImg, middlePart );
      
      System.exit(0);
   }

   
   public static void huffmanize(BufferedImage bufImg, String middlePart) throws IOException
   {
      /*   your Huffman code goes  here  */
      
      Map<Integer, Integer> freqTable = new HashMap<Integer, Integer>();
      
      for(int w = 0; w < WIDTH; w++)
      {
         for(int h = 0; h < HEIGHT; h++)
         {
            int rgb = bufImg.getRGB(w, h);
            
            if(freqTable.containsKey(rgb))
            {
               int freq = freqTable.get(rgb);
               freqTable.put(rgb, freq + 1);
            }
         
            else
               freqTable.put(rgb, 1);
         }
      }
      
      PriorityQueue<HuffmanNode> rgbFreq = new PriorityQueue<HuffmanNode>();
      Iterator<Integer> it = freqTable.keySet().iterator();
      
      while(it.hasNext())
      {
         int rgb = it.next();
         int freq = freqTable.get(rgb);
         
         HuffmanNode toAdd = new HuffmanNode(rgb, freq);
         rgbFreq.add(toAdd);
      }  
      
      Map<Integer, String> blankValCopy = new HashMap<Integer, String>();
      
      for(int rgb : freqTable.keySet())
         blankValCopy.put(rgb, "");
            
      while(rgbFreq.size() > 1)
      {
         HuffmanNode tree1 = rgbFreq.remove();
         HuffmanNode tree2 = rgbFreq.remove();
         
         int tree1Freq = tree1.getFreq();
         int tree2Freq = tree2.getFreq();
         
         HuffmanNode tree3 = new HuffmanNode(-2147483647, tree1Freq + tree2Freq, tree1, tree2);
         
         recurse(tree1, blankValCopy, '0');
         recurse(tree2, blankValCopy, '1');
         
         rgbFreq.add(tree3);
      }
      
      HuffmanNode lastToRemove = rgbFreq.remove();
      
      String code = "";
      
      for(int w = 0; w < WIDTH; w++)
         for(int h = 0; h < WIDTH; h++)
            code += blankValCopy.get(bufImg.getRGB(w, h));
 				
      String binaryFileName = "pix." + middlePart + ".txt";
      PrintWriter outfile = new PrintWriter(new FileWriter(binaryFileName));
      outfile.print(code);
      System.out.println("Pix done");
            			
      Map<String, Integer> huffmanScheme = new TreeMap<String, Integer>();
      String schemeFile = "schemePix."+ middlePart + ".txt";
      PrintWriter outfile2 = new PrintWriter(new FileWriter(schemeFile));
      outfile2.println(""+ WIDTH +" " + HEIGHT);    //outputs the width x height
      
      for(int freq : blankValCopy.keySet())
         huffmanScheme.put(blankValCopy.get(freq), freq);
      
      for(String scheme : huffmanScheme.keySet())
         outfile2.println(huffmanScheme.get(scheme) + " " + scheme);
      
      System.out.println("Scheme done");
      
      outfile.close(); 
      outfile2.close();  
   }
   
   /*  several Huffman methods go here  */
   
   public static void recurse(HuffmanNode toRecurse, Map<Integer, String> map, char num)
   {
      if(toRecurse.getRGB() != -2147483647)
      {
         int rgb = toRecurse.getRGB();
         map.put(rgb, num + map.get(rgb));
         return;
      }
      
      recurse(toRecurse.getLeft(), map, num);
      recurse(toRecurse.getRight(), map, num);
   }  
}


  /*
  * This node stores two values.  
  * The compareTo method must ensure that the lowest frequency has the highest priority.
  */ 

//Modified HuffmanTreeNode for purpose of lab  
class HuffmanNode implements Comparable<HuffmanNode>
{
   private int value, frequency; 
   private HuffmanNode left, right;
   
   public HuffmanNode(int initValue, int initFreq)
   { 
      value = initValue;
      frequency = initFreq;
      left = null; 
      right = null; 
   }
   
   public HuffmanNode(int initValue, int initFreq, HuffmanNode initLeft, HuffmanNode initRight)
   { 
      value = initValue; 
      frequency = initFreq;
      left = initLeft; 
      right = initRight; 
   }
   
   public int getRGB()
   { 
      return value; 
   }
   
   public int getFreq()
   { 
      return frequency; 
   }
   
   public HuffmanNode getLeft() 
   { 
      return left; 
   }
   
   public HuffmanNode getRight() 
   { 
      return right; 
   } 

   public void setValue(int theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(HuffmanNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(HuffmanNode theNewRight)
   { 
      right = theNewRight;
   }
   
   public int compareTo(HuffmanNode other)
   {
      int freqDiff = frequency - other.getFreq();
      return freqDiff;
   }
   
   public String toString()
   {
      String str = this.getRGB() + ":" + this.getFreq();
      return str;
   }
}
  /*
  * Minimum code necessary to display a BufferedImage.    
  */ 
class DisplayPix extends JPanel
{
   private BufferedImage img;
   private Graphics g;
   public DisplayPix(BufferedImage bufImg, ImageIcon i)   //for Huffman
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