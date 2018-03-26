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
		// FILL IN CODE

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

	// FILL IN CODE: write getters and setters for all private variables


	public void setKey(FlightKey key) {
		this.key = key;
	}

	public void setData(FlightData data) {
		this.data = data;
	}

	public void setNext(FlightNode node) {
		this.next = node;
	}

	public void setPrevious(FlightNode node) {
		this.previous = node;
	}

	public void setUp(FlightNode node) {
		this.up = node;
	}

	public void setDown(FlightNode node) {
		this.down = node;
	}


	public FlightNode getNext() {
		return this.next;
	}

	public FlightNode getPrevious() {
		return this.previous;
	}

	public FlightNode getUp() {
		return this.up;
	}

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

	public FlightData getData() {
		return this.data;
	}

}
