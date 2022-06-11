import java.util.*;
import java.io.*;
/*  
Test your AVL tree with this driver.
*/

public class BST_AVL_driver
{
   public static void main( String[] args ) throws Exception
   {
      BST balancedTree = new BST();  
      //Scanner in = new Scanner(System.in);
      //System.out.print("Type in a line: ");  
      //String line = in.nextLine();
      
      //String line = "5 3 2";  //left-left case (right rotation) 
      //String line = "3 5 7";  //right-right case (left rotation)
      //String line = "5 3 4";  //left-right case (left rotation then right rotation)
      //String line = "3 5 4";  //right-left case (right rotation then left rotation)
      //String line = "a b c d e f g";   
      //String line = "g f e d c b a";
      //String line = "J E H B F G D"; //doubleright, doubleright, doubleright
      //String line = "L P N U R O"; //doubleleft, doubleleft, doubleleft
      //String line = "M H D F E J U L X I P K"; //left, doubleleft, doubleright  
      String line = "9 8 6 4 0 2";
      
      String[] str = line.split(" ");
      for(String item : str)
      {
         balancedTree.addBalanced( item );  //implement addBalanced() in your BST class
         System.out.println(balancedTree.display());
         System.out.println("------------------------------");
      }
      
      /*balancedTree.removeBalanced("H");
      balancedTree.removeBalanced("M");
      balancedTree.removeBalanced("P");
      balancedTree.removeBalanced("J");
      balancedTree.removeBalanced("L");
      balancedTree.removeBalanced("F");
      balancedTree.removeBalanced("X");*/
   }
}

/***************************************************
     ----jGRASP exec: java BST_self_balancing_Teacher
 a
 
 ------------------------------
 	b
 a
 
 ------------------------------
 	c
 b
 	a
 
 ------------------------------
 		d
 	c
 b
 	a
 
 ------------------------------
 		e
 	d
 		c
 b
 	a
 
 ------------------------------
 		f
 	e
 d
 		c
 	b
 		a
 
 ------------------------------
 		g
 	f
 		e
 d
 		c
 	b
 		a
 
 ------------------------------
 			h
 		g
 	f
 		e
 d
 		c
 	b
 		a
 
 ------------------------------
 			i
 		h
 			g
 	f
 		e
 d
 		c
 	b
 		a
 
 ------------------------------
 			j
 		i
 	h
 			g
 		f
 			e
 d
 		c
 	b
 		a
 
 ------------------------------
 			k
 		j
 			i
 	h
 			g
 		f
 			e
 d
 		c
 	b
 		a
 
 ------------------------------
 			l
 		k
 	j
 		i
 h
 			g
 		f
 			e
 	d
 			c
 		b
 			a
 
 ------------------------------
 			m
 		l
 			k
 	j
 		i
 h
 			g
 		f
 			e
 	d
 			c
 		b
 			a
 
 ------------------------------
 			n
 		m
 	l
 			k
 		j
 			i
 h
 			g
 		f
 			e
 	d
 			c
 		b
 			a
 
 ------------------------------
 			o
 		n
 			m
 	l
 			k
 		j
 			i
 h
 			g
 		f
 			e
 	d
 			c
 		b
 			a
 
 ------------------------------
 				p
 			o
 		n
 			m
 	l
 			k
 		j
 			i
 h
 			g
 		f
 			e
 	d
 			c
 		b
 			a
 
 ------------------------------
 				q
 			p
 				o
 		n
 			m
 	l
 			k
 		j
 			i
 h
 			g
 		f
 			e
 	d
 			c
 		b
 			a
 
 ------------------------------
 				r
 			q
 		p
 				o
 			n
 				m
 	l
 			k
 		j
 			i
 h
 			g
 		f
 			e
 	d
 			c
 		b
 			a
 
 ------------------------------
 				s
 			r
 				q
 		p
 				o
 			n
 				m
 	l
 			k
 		j
 			i
 h
 			g
 		f
 			e
 	d
 			c
 		b
 			a
 
 ------------------------------
 				t
 			s
 		r
 			q
 	p
 				o
 			n
 				m
 		l
 				k
 			j
 				i
 h
 			g
 		f
 			e
 	d
 			c
 		b
 			a
 
 ------------------------------
 				u
 			t
 				s
 		r
 			q
 	p
 				o
 			n
 				m
 		l
 				k
 			j
 				i
 h
 			g
 		f
 			e
 	d
 			c
 		b
 			a
 
 ------------------------------
 				v
 			u
 		t
 				s
 			r
 				q
 	p
 				o
 			n
 				m
 		l
 				k
 			j
 				i
 h
 			g
 		f
 			e
 	d
 			c
 		b
 			a
 
 ------------------------------
 				w
 			v
 				u
 		t
 				s
 			r
 				q
 	p
 				o
 			n
 				m
 		l
 				k
 			j
 				i
 h
 			g
 		f
 			e
 	d
 			c
 		b
 			a
 
 ------------------------------
 				x
 			w
 		v
 			u
 	t
 			s
 		r
 			q
 p
 				o
 			n
 				m
 		l
 				k
 			j
 				i
 	h
 				g
 			f
 				e
 		d
 				c
 			b
 				a
 
 ------------------------------
 				y
 			x
 				w
 		v
 			u
 	t
 			s
 		r
 			q
 p
 				o
 			n
 				m
 		l
 				k
 			j
 				i
 	h
 				g
 			f
 				e
 		d
 				c
 			b
 				a
 
 ------------------------------
 				z
 			y
 		x
 				w
 			v
 				u
 	t
 			s
 		r
 			q
 p
 				o
 			n
 				m
 		l
 				k
 			j
 				i
 	h
 				g
 			f
 				e
 		d
 				c
 			b
 				a
 
 ------------------------------
 ************************************/