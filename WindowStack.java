//TODO: Complete java docs and code in missing spots.
//Missing spots are marked by < YOUR_CODE_HERE >.
//Do NOT edit any other parts of the code.

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.awt.Color;
import java.util.Comparator;

/**
 *  A stack of windows within the window.
 *  
 *  <p>Adapterion of Nifty Assignment (http://nifty.stanford.edu/) by
 *  Mike Clancy in 2001. Original code by Mike Clancy. Updated Fall
 *  2022 by K. Raven Russell.</p>
 * @author C.Artis & K. Raven Russel.
 */
public class WindowStack {
	//You'll need some instance variables probably...
	//< YOUR_CODE_HERE >

	/**
	 * Head window.
	 */
	Node<Window> head;
	/**
	 * Tail window.
	 */
	Node<Window> tail;
	/**
	 * Number of windows.
	 */
	int count;
	
	/**
	 * Default constructor for Window Stack.
	 */
	public WindowStack() {
		//Any initialization code you need.
		
		//O(1)
		
		//< YOUR_CODE_HERE >
		this.head = null;
		this.tail = null;
		this.count = 0;
	}
	
	/**
	 * Returns the head (top) of the stack of windows.
	 * @return head
	 */
	public Node<Window> getHead() {
		//Returns the head (top) of the stack of windows.
		
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
	 * Returns the tail (bottom) of the stack of windows.
	 * @return tail window
	 */
	public Node<Window> getTail() {
		//Returns the tail (bottom) of the stack of windows.
		
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
	 * Gets the number of windows in the stack.
	 * @return number of windows
	 */
	public int numWindows() {
		//Gets the number of windows in the stack.
		
		//O(1)
		
		return this.count;
	}
	/**
	 * Add a window at the top of the stack.
	 * @param r Window to be added
	 */
	public void add(Window r) {
		//Add a window at the top of the stack.
		
		//O(1)
		
		//throw IllegalArgumentException for invalid windows
		
		//Note: the "top" of the stack should
		//be the head of your linked list.

		//< YOUR_CODE_HERE >
		if (r == null)
			throw new IllegalArgumentException();

		Node<Window> n = new Node<>(r);

		if (this.head != null)
			this.getHead().data.setSelected(false);

		if (this.count == 0) {
			head = n;
			tail = n;
		} else {
			head.prev = n;
			n.next = head;
			head = n;
		}

		this.count++;
		this.getHead().data.setSelected(true);

		
		
	}
	/**
	 * Find & perform  mouse click at pos (x,y).
	 * @param x x position
	 * @param y y position
	 * @param leftClick boolean flag for left mouse click
	 * @return true if a window was handled
	 */
	public boolean handleClick (int x, int y, boolean leftClick) {
		//The mouse has been clicked at position (x,y).
		//Left clicks are move windows to the top of the
		//stack or pass the click on to the window at the
		//top. Right clicks remove windows.
		
		//Returns true if the click was handled, false
		//if no window was found.
		
		//O(n) where n is the number of windows in the stack
		
		
		//Details:
		
		//Find the top-most window on the stack (if any)
		//that contains the given coordinate.
		
		
		//For a left click:
		
		//If the window is not at the top of the stack,
		//move it to the top of the stack without
		//disturbing the order of the other windows.
		//Mark this window as the "selected" window (and
		//ensure the previous selected window is no longer
		//selected).
		
		//If the window is at the top of the stack already,
		//ask the window to handle a click-event (using the
		//Window's handleClick() method).
		
		//If none of the windows on the stack were clicked
		//on, just return.
		
		
		//For a right click:
		
		//Remove the window from the stack completely. The
		//window at the top of the stack should be the 
		//selected window.
		
		
		//Hint #1: This would be a great time to use helper
		//methods! If you just write one giant method...
		//it'll be much harder to debug...
		
		//Hint #2: Make sure to use the methods you wrote
		//in the Window class. Don't write those again!

		
		//< YOUR_CODE_HERE >

		Node<Window> current = this.head;
		//boolean handled = false;
		while (current != null) {
			Window w = current.data; 
			if (w.contains(x, y)) {
				if (leftClick) {
					if (current == this.head) { // current window is the top of stack
						w.handleClick(x, y);
						return true;
					} else { 
						this.getHead().data.setSelected(false);
						if (current == this.tail) { // special case, end of list
							// new tail
							current.prev.next = null;
							this.tail = current.prev;
							
						} else {
							//re-arrange
							current.prev.next = current.next;
							current.next.prev = current.prev;

						}
						// we want to move the node to the top.
						this.head.prev = current; // make the current head the second node
						current.prev = null;
						current.next = this.head;
						this.head = current; // clarification
						this.getHead().data.setSelected(true);
						return true;
					}
				} else { // right click, so, delete
					if (current == this.head) { // special case, the node we are deleting is the head (top of stack)
						this.getHead().data.setSelected(false);
						
						if (this.head.next != null) { // make sure stack won't be empty after updating
							this.head.next.prev = null;
							this.head = this.head.next;

						} else { // stack is empty now
							this.head = null;
							this.tail = null;
						}

						if (this.head != null) { // select new head.
							this.getHead().data.setSelected(true);
						}
						
					} else if (current == this.tail){ // special case, the node we are deleting is the tail (bottom of stack)
						current.prev.next = null;
						this.tail = current.prev;
					} else { // middle node
						current.prev.next = current.next;
						current.next.prev = current.prev;
					}
					count--;
					return true;
					
				}
			}

			current = current.next;
		}
		
		return false; //dummy return, replace this!
	}

	/**
	 *  Gets an iterator for the stack of windows.
	 *  Windows are returned from bottom to top.
	 *  
	 *  @return the iterator requested
	 */
	public Iterator<Window> windows() {
		//Note that this method uses your linked list!
		//so if the iterator doesn't work, that's on you...
		
		return new Iterator<>() {
			/**
			 *  The current node pointed to by the
			 *  iterator (containing the next value
			 *  to be returned).
			 */
			private Node<Window> current = getTail();
			
			/**
			 * {@inheritDoc}
			 */
			@Override
			public Window next() {
				if(!hasNext()) {
					throw new NoSuchElementException();
				}
				Window ret = current.data;
				current = current.prev;
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
	 * Sorts the windows in the stack by their area (length x width).
	 */
	public void sortSize() {
		//Sorts the windows in the stack by their area (length x width).
		//MOST of this is done for you, but you still need to assign
		//the returned head and tail back.
		
		//unselect the top window
		this.getHead().data.setSelected(false);
		
		//create a way to compare windows by area
		Comparator<Window> comp = new Comparator<>() {
			public int compare(Window w1, Window w2) {
				return (w1.getWidth()*w1.getHeight())-(w2.getWidth()*w2.getHeight());
			}
		};
		
		//create a pair of nodes to pass into the sort function
		ThreeTenLinkedList.NodePair<Window> pair = new ThreeTenLinkedList.NodePair<>(getHead(), getTail());
		
		//call the sort function with the comparator
		ThreeTenLinkedList.NodePair<Window> ret = ThreeTenLinkedList.sort(pair, comp);
		
		//make the returned list the head and tail of this list
		//this is for PART 5 of the project... don't try to do this
		//before you complete ThreeTenLinkedList.sort()!
		//< YOUR_CODE_HERE >
		this.head = ret.head;
		this.tail = ret.tail;
		
		
		//re-select the top of the stack
		this.getHead().data.setSelected(true);
	}
	/**
	 * Sorts the windows in the stack by their upper left corner loction.
	 */
	public void sortLoc() {
		//Sorts the windows in the stack by their upper left
		//corner loction. Left things (bigger-X) are on top
		//of right things (smaller-X). Tie-breaker: lower
		//things (bigger-Y) top of  higher things (smaller-Y).

		//This should use your ThreeTenLinkedList.sort() method you
		//write in Part 5, so don't do this until you have (a) read
		//part 5, (b) looked at the example in sortSize() above, and
		//(c) are sure you understand comparators.
		
		//O(n^2)
		
		//< YOUR_CODE_HERE >

		

		this.getHead().data.setSelected(false);

		Comparator<Window> comp = new Comparator<>() {
			public int compare(Window w1, Window w2) {

				if (w1.getUpperLeftX() == w2.getUpperLeftX() && w1.getUpperLeftY() == w2.getUpperLeftY())
					return 0;
				if (w1.getUpperLeftX() != w2.getUpperLeftX()) {
					if (w1.getUpperLeftX() < w2.getUpperLeftX()) {
						return 1;
					} else {
						return -1;
					}
				} else {
					if (w1.getUpperLeftY() < w2.getUpperLeftY()) {
						return 1;
					} else {
						return -1;
					}
				}
				
				//return (w1.getUpperLeftX()+w1.getUpperLeftY())-(w2.getUpperLeftX()+w2.getUpperLeftY());
			}
		};

		ThreeTenLinkedList.NodePair<Window> pair = new ThreeTenLinkedList.NodePair<>(getHead(), getTail());
		ThreeTenLinkedList.NodePair<Window> ret = ThreeTenLinkedList.sort(pair, comp);

		this.head = ret.head;
		this.tail = ret.tail;

		this.getHead().data.setSelected(true);
	}

	/**
	 * Main method.
	 * @param args N/A
	 */
	public static void main(String args[]) {
		Window w0 = new Window(80, 80, 40,40, Color.BLACK);
		Window w1 = new Window(0, 0, 25, 25, Color.BLACK);
		Window w2 = new Window(0, 0, 30, 30, Color.RED);
		Window w3 = new Window(0, 0, 13, 13, Color.BLACK);
		Window w4 = new Window(0, 0, 23, 23, Color.BLACK);
		Window w5 = new Window(3, 0, 10, 10, Color.BLACK);
		Window w6 = new Window(3, 1, 15, 15, Color.BLACK);

		WindowStack ws = new WindowStack();
		ws.add(w0);
		ws.add(w1);
		ws.add(w2);
		ws.add(w3);
		ws.add(w4);
		ws.add(w5);
		ws.add(w6);

		System.out.println("before sort:");
		Iterator<Window> it = ws.windows();
		while (it.hasNext()) {
			Window thing = it.next();
			if (thing != null)
				System.out.println(thing.getHeight());
		}
		System.out.println("after sort:");
		ws.handleClick(3, 0, true);

		Iterator<Window> it2 = ws.windows();
		while (it2.hasNext()) {
			Window thing = it2.next();
			if (thing != null)
				System.out.println(thing.getHeight());
		}
	}
}

