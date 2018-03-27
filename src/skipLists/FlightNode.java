package skipLists;

/**
 * The class that represents a node in a flight skip list.
 * Contains the key of type FlightKey and the data of type FlightData.
 * Also stores the following pointers to FlightNode(s): next, down, prev and up.
 */
public class FlightNode {

	private FlightKey key;
	private FlightData data;
	private FlightNode next;
	private FlightNode previous;
	private FlightNode up;
	private FlightNode down;

	/**
	 * Copy constructor for FlightNode
	 * @param node FlightNode
	 */
	public FlightNode(FlightNode node) {
		this.key = node.getKey();
		this.data = node.getData();
		this.next = null;
		this.previous = null;
		this.up = null;
		this.down = null;
	}

	/**
	 * FlightNode Constructor
	 * @param key flight key
	 * @param data fight daya
	 */
	public FlightNode(FlightKey key, FlightData data) {
		this.key = key;
		this.data = data;
		this.next = null;
		this.previous = null;
		this.up = null;
		this.down = null;
	}

	/**
	 * Sets the key for a node
	 * @param key
	 */
	public void setKey(FlightKey key) {
		this.key = key;
	}

	/**
	 * Sets the data for a node
	 * @param data
	 */
	public void setData(FlightData data) {
		this.data = data;
	}

	/**
	 * Sets the node given as parameter as the next node
	 * @param node
	 */
	public void setNext(FlightNode node) {
		this.next = node;
	}

	/**
	 * Sets the node given as parameter as the previous node
	 * @param node
	 */
	public void setPrevious(FlightNode node) {
		this.previous = node;
	}

	/**
	 * Sets the node given as paramater as the above node
	 * @param node
	 */
	public void setUp(FlightNode node) {
		this.up = node;
	}

	/**
	 * Sets the node given as parameter as the below node
	 * @param node
	 */
	public void setDown(FlightNode node) {
		this.down = node;
	}

	/**
	 * Returns the next node
	 * @return
	 */
	public FlightNode getNext() {
		return this.next;
	}

	/**
	 * Returns the previous node
	 * @return
	 */
	public FlightNode getPrevious() {
		return this.previous;
	}

	/**
	 * Returns the above node
	 * @return
	 */
	public FlightNode getUp() {
		return this.up;
	}

	/**
	 * Returns the node below
	 * @return
	 */
	public FlightNode getDown() {
		return this.down;
	}

	/**
	 * A getter for the key
	 * @return key
	 */
	public FlightKey getKey() {
		return this.key;
	}

	/**
	 * A getter for the data
	 * @return
	 */
	public FlightData getData() {
		return this.data;
	}

}
