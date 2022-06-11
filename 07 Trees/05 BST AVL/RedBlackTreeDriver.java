//Name: J2-24
//Date: 2/26/2021
 
//Driver file that can be used to test RedBlackTreeImplementation.java (BST AVL Extension #1)
 
public class RedBlackTreeDriver
{ 
   public static void main(String[] args)
   {
      RedBlackTreeImplementation tree = new RedBlackTreeImplementation();
      tree.add("D");
      tree.add("B");
      tree.add("A");
      tree.add("F");
      tree.add("E");
      tree.add("C");
      tree.add("K");
      tree.add("H");
      tree.add("J");
      tree.add("I");
      tree.remove("A");
      tree.remove("B");
      tree.remove("F");
      tree.remove("D");  
   }
}