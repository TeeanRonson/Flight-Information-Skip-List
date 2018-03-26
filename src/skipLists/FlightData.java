package skipLists;

/**
 * Represents data in the FlightNode. Contains the flight number and the price
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
	 * Returns the number of the flight
	 * @return flight number
	 */
	public String getFlightNumber() {
		return this.flightNum;
	}

	/**
	 * Returns the price of the flight
	 * @return price
	 */
	public double getPrice() {
		return this.price;
	}

	public String toString() {
		String dataResult = this.flightNum + " " + this.price;
		return dataResult;
	}
}
