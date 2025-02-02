//TODO: Complete java docs and code in missing spots.
//Missing spots are marked by < YOUR_CODE_HERE >.
//Do NOT edit any other parts of the code.

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Comparator;
import java.awt.Color;
import java.awt.Graphics;

/**
 *  A list of squares within a single window.
 *  
 *  <p>Adapterion of Nifty Assignment (http://nifty.stanford.edu/) by
 *  Mike Clancy in 2001. Original code by Mike Clancy. Updated Fall
 *  2022 by K. Raven Russell.</p>
 * @author C.Artis & K. Raven Russel.
 */
public class SquareList {
	//You'll need some instance variables probably...

	/**
	 * Square Node head.
	 */
	Node<Square> head;
	/**
	 * Square Node tail.
	 */
	Node<Square> tail;
	/**
	 * Number of squares.
	 */
	int count;

	/**
	 *  Initialize an empty list of squares.
	 */
	public SquareList() {
		//Any initialization code you need.
		
		//O(1)
		
		this.tail = null;
		this.head = null;
		this.count = 0;
	}
	
	/**
	 * Returns the head of the list of squares.
	 * @return the head of the list of squares
	 */
	public Node<Square> getHead() {
		//Returns the head of the list of squares.
		
		//O(1)
		
		//We will use this method to test your
		//linked list implementaiton of this
		//class, so whether or not you are using
		//the generic linked list class or bare
		//nodes, you must still be able to return
		//the appropriate head of the list.
		
		//< YOUR_CODE_HERE >
		
		return this.head; //dummy return, replace this!
	}
	/**
	 * Returns the tail of the list of squares.
	 * @return the tail
	 */
	public Node<Square> getTail() {
		//Returns the tail of the list of squares.
		
		//O(1)
		
		//We will use this method to test your
		//linked list implementaiton of this
		//class, so whether or not you are using
		//the generic linked list class or bare
		//nodes, you must still be able to return
		//the appropriate tail of the list.
		
		//< YOUR_CODE_HERE >
		
		
		return this.tail; //dummy return, replace this!
	}
	/**
	 * Gets the number of squares in the list.
	 * @return number of squares in the list
	 */
	public int numSquares() {
		//Gets the number of squares in the list.
		
		//O(1)
		
		//< YOUR_CODE_HERE >
		
		return this.count;
	}
	/**
	 * Add a square to the list.
	 * @param sq square to be added
	 */
	public void add(Square sq) {
		//Add a square to the list. Newly added squares
		//should be at the back end of the list.
		
		//O(1)

		//throw IllegalArgumentException for invalid sqares
		
		//< YOUR_CODE_HERE >
		if (sq == null)
			throw new IllegalArgumentException();

		Node<Square> n = new Node<>(sq);

		if (this.count == 0) {
			head = n;
			tail = n;
		} else {

			if (tail != null) {
				tail.next = n;
				n.prev = tail;
				tail = n;
			}
			
		}

		this.count++;
		
	}
	/**
	 * Deletes all squares from the list that contain the position (x,y).
	 * @param x x position
	 * @param y y posiiton
	 * @return true if any squares were deleted, false otherwise
	 */
	public boolean handleClick (int x, int y) {
		//Deletes all squares from the list that contain the 
		//position (x,y). Returns true if any squares get
		//deleted and returns false otherwise.
		
		//Returns true if any squares were deleted.
		
		//O(n) where n is the size of the list of squares
		
		//< YOUR_CODE_HERE >
		
		Node<Square> current = this.head;
		boolean flag = false;
		while (current != null) {
			Square s = current.data;
			if (s.contains(x, y)) {
				flag = true; // we deleted at least one square
				Node<Square> prev = current.prev;
				Node<Square> next = current.next;
				if (current == this.head) {
					this.head = current.next; // set new head
					if (next != null) 
						next.prev = null; // set new head's prev to null
						
				} else if (current == this.tail) {
					
					if (prev != null) {
						prev.next = null; // set new tail's next to null
					} 
						
					this.tail = prev; // set new tail


					
				} else {				
					prev.next = next;
					next.prev = prev;
				}
				
				
				this.count--;
				if (this.count == 1) {
					if (next != null) {
						head = next;
						tail = next;
					} else {
						head = prev;
						tail = prev;
					}
					
					
				}

				if (this.count == 0) {
					head = tail = null;
				}
					

				// current.prev.next = current.next; // square's prev should point to square's next
				// current.next.prev = current.prev; // square's next should point to square's prev
				// // now square is no longer in the linked list
				// current.next = null;
				// current.prev = null;
				
			}
			current = current.next;
		}
		
		return flag;
	}

	/**
	 *  Gets an iterator for the list of squares.
	 *  Squares are returned in the order added.
	 *  
	 *  @return the iterator requested
	 */
	public Iterator<Square> elements() {
		//Note that this method uses your linked list!
		//so if the iterator doesn't work, that's on you...
		
		return new Iterator<>() {
			/**
			 *  The current node pointed to by the
			 *  iterator (containing the next value
			 *  to be returned).
			 */
			private Node<Square> current = getHead();
			
			/**
			 * {@inheritDoc}
			 */
			@Override
			public Square next() {
				if(!hasNext()) {
					throw new NoSuchElementException();
				}
				Square ret = current.data;
				current = current.next;
				return ret;
			}
			
			/**
			 * {@inheritDoc}
			 */
			@Override
			public boolean hasNext() {
				return (current != null);
			}
		};
	}
	
	/**
	 * Sorts the squares in the window by their creation time.
	 */
	public void sortCreation() {
		//Sorts the squares in the window by their creation time
		//(lower ids were created first). This should use your
		//ThreeTenLinkedList.sort() method you write in Part 5,
		//so don't do this until you have (a) read part 5,
		//(b) looked at the example in WindowStack, and (c) are 
		//sure you understand comparators.
		
		//O(n^2)
		
		//< YOUR_CODE_HERE >

		Comparator<Square> comp = new Comparator<>() {
			public int compare(Square s1, Square s2) {
				return (s2.id()-s1.id());
			}
		};

		ThreeTenLinkedList.NodePair<Square> pair = new ThreeTenLinkedList.NodePair<>(getHead(), getTail());
		ThreeTenLinkedList.NodePair<Square> ret = ThreeTenLinkedList.sort(pair, comp);

		this.head = ret.head;
		this.tail = ret.tail;
	}
	/**
	 * Sorts the squares in the window by their location in the window.
	 */
	public void sortLoc() {
		//Sorts the squares in the window by their location
		//in the window. Same rules as sorting the windows
		//in WindowStack. This should use your
		//ThreeTenLinkedList.sort() method you write in Part 5,
		//so don't do this until you have (a) read part 5,
		//(b) looked at the example in WindowStack, and (c) are 
		//sure you understand comparators
		
		//O(n^2)
		
		//< YOUR_CODE_HERE >

		Comparator<Square> comp = new Comparator<>() {
			public int compare(Square s1, Square s2) {

				
				if (s1.getUpperLeftX() == s2.getUpperLeftX() && s1.getUpperLeftY() == s2.getUpperLeftY())
					return 0;
				if (s1.getUpperLeftX() != s2.getUpperLeftX()) {
					if (s1.getUpperLeftX() > s2.getUpperLeftX()) {
						return 1;
					} else {
						return -1;
					}
				} else {
					if (s1.getUpperLeftY() > s2.getUpperLeftY()) {
						return 1;
					} else {
						return -1;
					}
				}
				
				//return (s1.getUpperLeftX()+s1.getUpperLeftY())-(s2.getUpperLeftX()+s2.getUpperLeftY());
			}
		};

		ThreeTenLinkedList.NodePair<Square> pair = new ThreeTenLinkedList.NodePair<>(getHead(), getTail());
		ThreeTenLinkedList.NodePair<Square> ret = ThreeTenLinkedList.sort(pair, comp);

		this.head = ret.head;
		this.tail = ret.tail;
	}

	/**
	 * Main method for testing.
	 * @param args N/A
	 */
	public static void main(String[] args) {

		SquareList l = new SquareList();
		Square s = new Square(2, 2, 2, Color.BLACK); // 0
		Square s2 = new Square(4, 4, 2, Color.RED); // 1
		Square s3 = new Square(6, 6, 2, Color.BLUE); // 2
		// 0 2 1
		l.add(s);
		l.add(s2);
		l.add(s3);
		
		
		
		
		// use iterator
		

		// System.out.println(l.handleClick(2, 2));
		// Iterator<Square> it = l.elements();
		// System.out.println("remaining squares:");
		// while (it.hasNext()) {
		// 	Square thing = it.next();
		// 	if (thing != null)
		// 		System.out.println(thing.id());
			
		// }

		Iterator<Square> it = l.elements();
		while (it.hasNext()) {
			Square thing = it.next();
			if (thing != null)
				System.out.println(thing.id());
		}
		
		//l.sortLoc();
		l.handleClick(6, 6);
		Iterator<Square> it2 = l.elements();
		System.out.println("after sort:");
		while (it2.hasNext()) {
			Square thing = it2.next();
			if (thing != null)
				System.out.println(thing.id());
			
		}
		

	}
}

