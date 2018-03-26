package skipLists;

public class Driver {


    public static void main(String[] args) {

        FlightList fl = new FlightList();

        FlightKey A = new FlightKey("FRA", "JFK", "05/05/2019", "07:00");
        FlightData Aa = new FlightData("LH122", 300);

        FlightKey A1 = new FlightKey("FRA", "JFK", "01/03/2019", "05:50");
        FlightData A1a = new FlightData("LH113", 400);

        FlightKey B = new FlightKey("SFO", "HKG", "21/03", "22:00");
        FlightData Ba = new FlightData("CX514", 1200);

        FlightKey C = new FlightKey("LHW", "HKG", "29/04", "18:00");
        FlightData Ca = new FlightData("BA123", 1900);



//        FlightNode one = new FlightNode(A, Aa);
//        FlightNode two = new FlightNode(B, Ba);
        fl.insert(A, Aa);
        fl.insert(B, Ba);
        fl.insert(C, Ca);
        fl.insert(A1, A1a);


//        System.out.println(fl.find(B));
//        System.out.println(fl.find(A));
//        System.out.println(fl.find(C));

        System.out.println("************************");
        fl.print();
        System.out.println("************************");


        System.out.println(fl.findFlights(A, 2));
    }
}
