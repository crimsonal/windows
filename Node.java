
/**
 * Node class for LinkedList implementation.
 * @author C.Artis
 * @param <T> Generic node.
 */
class Node<T> {
	/**
	 * Generic data to hold.
	 */
	public T data;
	/**
	 * Next node.
	 */
	public Node<T> next;
	/**
	 * Previous node.
	 */
	public Node<T> prev;
	
	/**
	 * Default constructor.
	 */
	public Node() {
		
	}
	
	/**
	 * Constructor with data.
	 * @param data Generic data
	 */
	public Node(T data) {
		this.data = data;
	}
}
