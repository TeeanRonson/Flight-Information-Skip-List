package skipLists;

/**
 * Represents the key in the FlightNode. Stores origin, destination, date and
 * time. Implements Comparable<FlightKey>.
 */
public class FlightKey implements Comparable<FlightKey> {

	private String orig;
	private String dest;
	private String date;
	private String time;

	/**
	 *  FlightKey constructor
	 * @param orig origin
	 * @param dest destination
	 * @param date date
	 * @param time time
	 */
	public FlightKey(String orig, String dest, String date, String time) {
		this.orig = orig;
		this.dest = dest;
		this.date = date;
		this.time = time;
	}

	/**
	 * FlightKey - copy constructor
	 * @param other the other FlightKey
	 */
	public FlightKey(FlightKey other) {
		this.orig = other.orig;
		this.dest = other.dest;
		this.date = other.date;
		this.time = other.time;

	}


	public String getOrigin() {
		return this.orig;
	}

	public String getDest() {
		return this.dest;
	}

	public String getDate() {
		return this.date;
	}

	public String getTime() {
		return this.time;
	}


	/**
	 * Compares a given flight key with the one given as a parameter.
	 * @param other
	 * @return -1, if this key is < other, > -1 if the opposite, and 0 if equal.  </>
	 */
	public int compareTo(FlightKey other) {


		int result0 = this.orig.compareTo(other.orig);
		int result1 = this.dest.compareTo(other.dest);
		int result2 = this.date.compareTo(other.date);

		if (result0 == 0 && result1 == 0 && result2 == 0) {
			return this.time.compareTo(other.time);
		}

		if (result0 == 0 && result1 == 0) {
			return result2;
		}

		if (result0 == 0) {
			return result1;
		}

		return result0; // don't forget to change it
	}

	public boolean matchInfoS(FlightKey other) {

		int result0 = this.orig.compareTo(other.orig);
		int result1 = this.dest.compareTo(other.dest);
		int result2 = this.date.compareTo(other.date);
		int result3 = this.getTime().compareTo(other.getTime());

		if (result0 == 0 && result1 == 0 && result2 == 0 && result3 >= 0) {
			return true;
		}

		return false;
	}

	public boolean matchInfoP(FlightKey other) {

		int result0 = this.orig.compareTo(other.orig);
		int result1 = this.dest.compareTo(other.dest);
		int result2 = this.date.compareTo(other.date);
		int result3 = this.getTime().compareTo(other.getTime());

		if (result0 == 0 && result1 == 0 && result2 == 0 && result3 <= 0) {
			return true;
		}

		return false;
	}

	/**
	 * Returns a string representation of the key
	 * @return String
	 */
	public String toString() {
		String keyResult = this.orig + " " + this.dest + " " + this.date + " " + this.time;
		return keyResult;
	}
}
