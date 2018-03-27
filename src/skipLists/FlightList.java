package skipLists;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Date;


/** The class that represents the flight database using a skip list
 *  Stores several private data members
 *  Including:
 *  head of the list
 *  tail of the list
 *  dummy keys negInfi and posInfi
 *  dummy data dummyData
 *  height of the list
 */

public class FlightList {

	private FlightNode head;
	private FlightNode tail;
	private FlightKey negInfi;
	private FlightKey posInfi;
	private FlightData dummyData;
	private Integer height;


	/** Default constructor */
	public FlightList() {
		this.negInfi = new FlightKey("A", "A", "00/00", "00:00");
		this.posInfi = new FlightKey("Z", "Z", "32/13", "00:00");
		this.dummyData = new FlightData("0", 00);
		this.height = 1;
		this.head = new FlightNode(this.negInfi, this.dummyData);
		this.tail = new FlightNode(this.posInfi, this.dummyData);
		this.head.setNext(this.tail);
		this.tail.setPrevious(this.head);

	}

	/**
	 * Constructor.
	 * Reads flight data from the file and inserts it into the skip list.
	 * @param filename the name of the file
	 */
	public FlightList(String filename) {
		this.negInfi = new FlightKey("A", "A", "00/00", "00:00");
		this.posInfi = new FlightKey("Z", "Z", "32/13", "00:00");
		this.dummyData = new FlightData("0", 00);
		this.height = 1;
		this.head = new FlightNode(this.negInfi, this.dummyData);
		this.tail = new FlightNode(this.posInfi, this.dummyData);
		this.head.setNext(this.tail);
		this.tail.setPrevious(this.head);

		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {

			String currentLine;
			String origin;
			String dest;
			String date;
			String time;
			String flight;
			Integer price;

			while ((currentLine = reader.readLine()) != null) {
				String[] info = currentLine.split(" ");
				origin = info[0];
				dest = info[1];
				date = info[2];
				time = info[3];
				flight = info[4];
				price = Integer.parseInt(info[5]);
				FlightKey key = new FlightKey(origin, dest, date, time);
				FlightData data = new FlightData(flight, price);
				insert(key, data);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("File Input Unsuccessful");
		}
	}

	/**
	 * Returns true if the node with the given key exists in the skip list,
	 * false otherwise.
	 *
	 * @param key flight key
	 * @return true if the key is in the skip list, false otherwise
	 */
	public boolean find(FlightKey key) {

		FlightNode current = this.head;

		while(current != null && current.getNext() != null && current.getKey().compareTo(tail.getKey()) != 0) {

			FlightKey nextKey = current.getNext().getKey();

			if (nextKey.compareTo(key) == 0) {
				return true;
			}

			if(current.getNext() == this.tail || nextKey.compareTo(key) > 0) {
				current = current.getDown();
			} else if (nextKey.compareTo(key) < 0) {
				current = current.getNext();
			}
		}
		return false;
	}


	/**
	 * Private method checks the height of the newly created node
	 * @param node
	 * @return the height of the newly created node
	 */
	private int findHeight(FlightNode node) {

		int level = 0;

		while(node != null) {
			level++;
			node = node.getDown();
		}
		return level;
	}

	/**
	 * Private method used to calculate the height of the newly created node
	 * Probability of 0.5 used as a threshold to measure if we increase
	 * the height value
	 *
	 * Consecutive probabilities less than 0.5 adds to height
	 * Probability greater than 0.5 ends the process
	 * @return
	 */
	private int randLevel() {

		int result = 0;

		while(Math.random() < 0.5) {
			result++;
		}

		return result;
	}


	/**
	 * Private method creates the tower of the newly created node
	 * The height of the tower will depend on the randLevel() method
	 * @param node
	 * @return
	 */
	private FlightNode createTower(FlightNode node) {

		int levels = randLevel();
		FlightNode temp = node;

		while(levels > 0) {
			FlightNode newNode = new FlightNode(node.getKey(), node.getData());
			node.setDown(newNode);
			newNode.setUp(node);
			node = node.getDown();
			levels--;
		}
		node = temp;

		return node;
	}

	/**
	 * Private method called when needing to create a new level
	 * in the skip list
	 *
	 * Connects the new level with the existing level at the head
	 * and at the tail
	 * @param newHead
	 * @param newTail
	 */
	private void connectNewList(FlightNode newHead, FlightNode newTail) {

		this.head.setUp(newHead);
		this.tail.setUp(newTail);
		newHead.setDown(this.head);
		newTail.setDown(this.tail);
		newHead.setNext(newTail);
	}

	/**
	 * Private method adds a new node to the given position when
	 * found in the skip list
	 * @param current
	 * @param newNode
	 */
	private void addNew(FlightNode current, FlightNode newNode) {

		FlightNode next = current.getNext();

		newNode.setNext(next);
		next.setPrevious(newNode);
		current.setNext(newNode);
		newNode.setPrevious(current);

	}


	/**
	 * Private method finds the position in which to insert the new node
	 * @param current
	 * @param levels
	 * @param newNode
	 * @return true if it has been sucessfully added
	 */
	private boolean addNewNode(FlightNode current, int levels, FlightNode newNode) {

		while(current != null && current.getNext() != null && levels > 0) {

			FlightKey nextKey = current.getNext().getKey();

			if (nextKey.compareTo(newNode.getKey()) < 0) {
				current = current.getNext();
			} else if (nextKey.compareTo(newNode.getKey()) > 0) {
				addNew(current, newNode);
				newNode = newNode.getDown();
				current = current.getDown();
				levels--;
			}
		}
		return true;
	}



	/**
	 * Insert a (key, value) pair to the skip list. Returns true if it was able
	 * to insert.
	 *
	 * @param key flight key
	 * @param data associated flight data
	 * @return true if insertion was successful
	 */
	public boolean insert(FlightKey key, FlightData data) {

		FlightNode newNode = new FlightNode(key, data);
		newNode = createTower(newNode);
		int levels = findHeight(newNode);

		if (find(key) == true) {
			System.out.println("Key already exists");
			return false;
		}

		if (levels <= this.height) {
			int difference = this.height - levels;
			FlightNode current = this.head;

			if (difference > 0) {
				while (current != null && current.getNext() != null && difference > 0) {
					FlightKey nextKey = current.getNext().getKey();
					if (nextKey.compareTo(newNode.getKey()) < 0) {
						current = current.getNext();
					} else if (nextKey.compareTo(newNode.getKey()) > 0) {
						current = current.getDown();
						difference--;
					}
				}
			}
			boolean added = addNewNode(current, levels, newNode);
			return added;

		} else {
			int difference = levels - this.height;

			while(difference > 0) {
				FlightNode newHead = new FlightNode(this.negInfi, this.dummyData);
				FlightNode newTail = new FlightNode(this.posInfi, this.dummyData);
				connectNewList(newHead, newTail);
				this.head = newHead;
				this.height++;
				difference--;
			}

			int remainderLevels = this.height;
			FlightNode current = this.head;
			boolean added = addNewNode(current, remainderLevels, newNode);
			return added;
		}
	}


	/**
	 * Private method that adds the successors to a flight to an ArrayList
	 * @param result
	 * @param current
	 * @param key
	 * @return ArrayList of successor flights
	 */
	private ArrayList<FlightNode> successorsResult(ArrayList<FlightNode> result, FlightNode current, FlightKey key) {

		while (current.getNext().getKey().matchInfoS(key) == true && current.getNext() != null) {
			result.add(current.getNext());
			current = current.getNext();
		}

		return result;
	}


	/**
	 * Returns the list of nodes that are successors of a given key. Refer to
	 * the project pdf for a detailed description of the method.
	 *
	 * @param key flight key
	 * @return successors of the given key
	 */
	public ArrayList<FlightNode> successors(FlightKey key) {

		ArrayList<FlightNode> result = new ArrayList<FlightNode>();
		FlightNode current = this.head;
		int height = this.height;

		while (current != null && current.getNext() != null && current.getKey().compareTo(tail.getKey()) != 0) {

			FlightKey nextKey = current.getNext().getKey();

			if (nextKey.compareTo(key) < 0) {
				current = current.getNext();
				continue;
			}

			if (nextKey.compareTo(key) > 0 && height != 1) {
				current = current.getDown();
				height--;
				continue;
			}

			if (nextKey.compareTo(key) == 0) {
				current = current.getNext();
				while (height != 1) {
					current = current.getDown();
					height--;
				}
				break;
			}

			if (nextKey.compareTo(key) > 0 && height == 1) {
				break;
			}
		}
		return successorsResult(result, current, key);
	}

	/**
	 * Private method adds the predecessor flights to an ArrayList.
	 * @param result
	 * @param current
	 * @param key
	 * @return ArrayList of predecessor flights
	 */
	private ArrayList<FlightNode> predecessorsResult(ArrayList<FlightNode> result, FlightNode current, FlightKey key) {

		ArrayList<FlightNode> temp = new ArrayList<FlightNode>();

		while (current.getPrevious().getKey().matchInfoP(key) == true && current.getPrevious() != null) {
			temp.add(current.getPrevious());
			current = current.getPrevious();
		}

		for (int i = temp.size() - 1; i >= 0; i--) {
			result.add(temp.remove(temp.size()-1));
		}

		return result;
	}

	/**
	 * Returns the list of nodes that are predecessors of a given key
	 * (that corresponds to flights that are earlier than the given flight).
	 *  Refer to the project pdf for a detailed description of the method.
	 *
	 * @param key flight key
	 * @return predecessors of the given key
	 */
	public ArrayList<FlightNode> predecessors(FlightKey key, int timeFrame) {

		ArrayList<FlightNode> result = new ArrayList<FlightNode>();
		FlightNode current = this.head;
		int height = this.height;

		while (current != null && current.getNext() != null && current.getKey().compareTo(tail.getKey()) != 0) {

			FlightKey nextKey = current.getNext().getKey();

			if (nextKey.compareTo(key) < 0) {
				current = current.getNext();
				continue;
			}

			if (nextKey.compareTo(key) > 0 && height != 1) {
				current = current.getDown();
				height--;
				continue;
			}

			if (nextKey.compareTo(key) == 0) {
				current = current.getNext();
				while (height != 1) {
					current = current.getDown();
					height--;
				}
				break;
			}

			if (nextKey.compareTo(key) > 0 && height == 1) {
				current = current.getNext();
				break;
			}
		}
		return predecessorsResult(result, current, key);

	}

	/**
	 * Prints the SkipList (prints the elements on all levels starting at the
	 * top. Each level should be printed on a separate line.
	 */
	public void print() {

		FlightNode current = this.head;
		int count = this.height;

		while (count > 0) {
			FlightNode header = current;

			while (current != null && current.getNext() != null && current.getNext().getKey().compareTo(this.tail.getKey()) != 0) {
				System.out.print(current.getNext().getKey() + " " + current.getNext().getData() + "; ");
				current = current.getNext();
			}
			System.out.println();
			current = header;
			current = current.getDown();
			count--;
		}

	}

	/**
	 * Prints only the bottom level of the skip list
	 */
	public void printBottomLevel() {

		FlightNode current = this.head;
		FlightNode header = current;
		int count = this.height;

		while (count != 1) {
			current = current.getDown();
			count--;
		}

		while (current.getNext() != tail) {
			System.out.println(current.getNext().getKey() + " " + current.getNext().getData());
			current = current.getNext();
		}
	}

	/**
	 * Outputs the SkipList to a file
	 *(prints the elements on all levels starting at the top.
	 * Each level should be printed on a separate line.
	 * @param filename the name of the file
	 */
	public void print(String filename) {

		Path outPath = Paths.get(filename);
		outPath.getParent().toFile().mkdirs();

		try(BufferedWriter out = Files.newBufferedWriter(outPath)) {

			FlightNode current = this.head;
			int levels = this.height;

			while (levels > 0) {
				FlightNode header = current;

				while (current != null && current.getNext() != null && current.getNext().getKey().compareTo(this.tail.getKey()) != 0) {
					out.write(current.getNext().getKey() + " " + current.getNext().getData() + "; ");
					current = current.getNext();
				}
				out.write("");
				current = header;
				current = current.getDown();
				levels--;
			}

		} catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}

	/**
	 * Returns a list of nodes that have the same origin and destination cities
	 * and the same date as the key, with departure times within the given time
	 * frame of the departure time of the key.
	 *
	 * @param key flight key
	 * @param timeFrame interval of time
	 * @return list of flight nodes that have the same origin, destination and date
	 * as the key, and whose departure time is within a given timeframe
	 */
	public ArrayList<FlightNode> findFlights(FlightKey key, int timeFrame) {

		ArrayList<FlightNode> flights = new ArrayList<FlightNode>();
		ArrayList<FlightNode> predecessors = predecessors(key, 10);
		ArrayList<FlightNode> successors = successors(key);

		int keyHour = getHour(key.getTime());
		int upperThres = keyHour + timeFrame;
		int lowerThres = keyHour - timeFrame;

		for (int i = 0; i < predecessors.size(); i++) {
			FlightNode current = predecessors.get(i);
			int flightTime = getHour(current.getKey().getTime());
			if (flightTime >= lowerThres) {
				flights.add(current);
			}
		}

		if (find(key) == true) {
			flights.add(findFlight(key));
		}

		for (int i = 0; i < successors.size(); i++) {
			FlightNode current = successors.get(i);
			int flightTime = getHour(current.getKey().getTime());
			if (flightTime <= upperThres) {
				flights.add(current);
			}
		}
		return flights;
	}


	/**
	 * Private method returns find the flight node of the given key
	 * @param key
	 * @return
	 */
	private FlightNode findFlight(FlightKey key) {

		FlightNode current = this.head;

		while(current != null && current.getNext() != null && current.getKey().compareTo(tail.getKey()) != 0) {

			FlightKey nextKey = current.getNext().getKey();

			if (nextKey.compareTo(key) == 0) {
				return current.getNext();
			}

			if(current.getNext() == this.tail || nextKey.compareTo(key) > 0) {
				current = current.getDown();
			} else if (nextKey.compareTo(key) < 0) {
				current = current.getNext();
			}
		}
		return null;
	}

	/**
	 * Private method gets the hour of a particular flight time
	 * @param time
	 * @return
	 */
	private static int getHour(String time) {

		String[] parse = time.split(":");
		int hour = Integer.parseInt(parse[0]);
		return hour;
	}

}
