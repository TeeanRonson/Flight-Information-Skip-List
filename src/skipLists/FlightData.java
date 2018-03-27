package skipLists;

/**
 * Represents data in the FlightNode. Contains the flight number and the flight price
 * Private data members for both
 */
public class FlightData {

	private String flightNum;
	private Integer price;

	/**
	 * Constructor for FlightData
	 * @param flightNum flight number
	 * @param price price of the flight
	 */
	public FlightData(String flightNum, Integer price) {
		this.flightNum = flightNum;
		this.price = price;
	}

	/**
	 * Returns the flight number
	 * @return flight number
	 */
	public String getFlightNumber() {
		return this.flightNum;
	}

	/**
	 * Returns the flight price
	 * @return price
	 */
	public double getPrice() {
		return this.price;
	}

	/**
	 * Returns a string representation of the flight number
	 * and price
	 * @return
	 */
	public String toString() {
		String dataResult = this.flightNum + " " + this.price;
		return dataResult;
	}
}
