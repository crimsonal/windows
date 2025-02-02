//TODO: Linked list implementation (optional)
//      JavaDocs (not optional)
//      Static sorting methods (not optional)

import java.util.Comparator;
/**
 * Linked List class for sorting.
 * @author C.Artis & K. Raven Russel.
 * @param <T> Generic parameter
 */
class ThreeTenLinkedList<T> {
	//You may, but are not required to, implement some or
	//all of this generic linked list class to help you with
	//this project. If you do, you MUST use the provided
	//Node class for this linked list class. This means
	//that it must be a doubly linked list (and links in
	//both directions MUST work).
	
	//Alternatively, you may implement this project using
	//only the Node class itself (i.e. use "bare nodes"
	//in the classes that require linked lists).
	
	//Either way, you MUST do all your own work. Any other
	//implementations you have done in the past, anything
	//from the book, etc. should not be in front of you,
	//and you certainly shouldn't copy and paste anything
	//from any other source.
	
	//This is the only class where you are allowed to
	//implement public methods.
	
	//In "Part 5" of the project, you will also be implementing
	//the following static methods:


	/**
	 * Determine if the provided list is sorted based on the comparator.
	 * @param <X> generic linked list
	 * @param pairs head/tail node pair
	 * @param comp comparator to be used
	 * @return true if list is sorted
	 */
	static <X> boolean isSorted(NodePair<X> pairs, Comparator<X> comp) {
		//Determine if the provided list is sorted based on the comparator.
		
		//For an empty linked list (e.g. the head-tail pair contains two nulls)
		//return true (an empty list is sorted).
		
		//For a null comparator or null pairs, throw an IllegalArgumentException.
		
		//O(n)
		
		//< YOUR_CODE_HERE >


		if (pairs == null || comp == null)
			throw new IllegalArgumentException();
		
		if (pairs.head == null || pairs.tail == null)
			return true;
		
		Node<X> current = pairs.head;
		Node<X> nextNode;
		while ( (current != null)) {
			nextNode = current.next;

			if (nextNode != null) { // do we have another node to compare the current node to?
				if (comp.compare(current.data, nextNode.data) > 0) { // is the the current node is greater than the next node?
					return false; // no need to check anymore, the list is not sorted.
				}
			}
			current = nextNode;
		}
		return true; //replace this!
	}
	
	/**
	 * Using the comparator, sort the linked list.
	 * @param <X> generic linked list
	 * @param pairs Head and Tail node pairs
	 * @param comp Comparator function 
	 * @return sorted pairs
	 */
	static <X> NodePair<X> sort(NodePair<X> pairs, Comparator<X> comp) {
		
		//Using the comparator, sort the linked list. It is recommended that
		//you sort by moving *values* around rather than moving nodes.
		//Two simple sorting algorithms which will work well here (and
		//meet the big-O requirements if implemented correctly) are the
		//insertion sort (see textbook Ch8.3) and the selection sort.

		//Insertion sort quick summary: Go to each element in the linked list,
		//shift it "left" into the correct position.
		//Example: 1,3,0,2
		// 1 is at the start of the list, leave it alone
		// 3 is bigger than 1, leave it alone
		// 0 is smaller than 3, move it left: 1,0,3,2
		// 0 is smaller than 1, move it to the left: 0,1,3,2
		// 0 is at the start of the list, stop moving it left
		// 2 is smaller than 3, move it to the left: 0,1,2,3
		// 2 is bigger than 1, stop moving it to the left

		//Selection sort quick summary: Go to each index in the linked list,
		//find the smallest thing from that index and to the "right",
		//and swap it into that index.
		//Example: 1,3,0,2
		// index 0: the smallest thing from index 0 to the end is 0, swap it into the right place: 0,3,1,2
		// index 1: the smallest thing from index 1 to the end is 1, swap it into the right place: 0,1,3,2
		// index 2: the smallest thing from index 2 to the end is 2, swap it into the right place: 0,1,2,3
		// index 3: there is only one item from index 3 to the end, so this is in the right places
		
		//Regardless of the method you choose, your sort should be a stable sort.
		//This means that if there are two equal values, they do not change their
		//order relative to each other.
		//Example: 1, 2, 1
		//The first "1" (which I'll call "1a") should be sorted before
		//the second "1" (1b), so that the output is "1a, 1b, 2" and
		//never "1b, 1a, 2". The easiest way to test this is to put two
		//equal items in the list, sort, and confirm using == that the
		//correct object is in the correct place.
		
		//For an empty linked list (e.g. the head-tail pair contains two nulls)
		//return the original pairs back to the user.
		
		//For a null comparator or null pairs, throw an IllegalArgumentException.
		
		//O(n^2)
		
		//< YOUR_CODE_HERE >
		if (pairs == null || comp == null)
			throw new IllegalArgumentException();

		if (pairs.head == null || pairs.tail == null)
			return pairs;
		
		Node<X> current = pairs.head; 

		while (current != null) {

			Node<X> j = current.next;
			Node<X> swap = current;
			
			while (j != null) { // loop through node+1
				if (comp.compare(swap.data, j.data) > 0) {  // is current node data greater than node+1 data? 
					swap = j; // swap
				}
				j = j.next;
			}

			// swap node data
			X oldData = current.data;
			current.data = swap.data;
			swap.data = oldData;
			current = current.next;
		}
		return pairs; //replace this!
	}
	
	//Which uses the following nested class:
	/**
	 * NodePair class (head and tail).
	 * @param <Y> Generic type
	 */
	public static class NodePair<Y> {
		/**
		 * NodePair head.
		 */
		public Node<Y> head;
		/**
		 * NodePair tail.
		 */
		public Node<Y> tail;
		/**
		 * Constructor for NodePair with head and tail.
		 * @param head generic head
		 * @param tail generic tail
		 */
		public NodePair(Node<Y> head, Node<Y> tail) {
			this.head = head;
			this.tail = tail;
		}
	}

	
	//You may also use the above nested class elsewhere in your
	//project if you'd find that helpful.
}