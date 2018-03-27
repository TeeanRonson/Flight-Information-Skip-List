package skipLists;

public class Driver {


    public static void main(String[] args) {

        FlightList fl = new FlightList();

        FlightKey A = new FlightKey("FRA", "JFK", "05/05/2019", "07:00");
        FlightData Aa = new FlightData("LH122", 300);

        FlightKey B = new FlightKey("FRA", "JFK", "01/03/2019", "05:50");
        FlightData Ba = new FlightData("LH113", 400);

        FlightKey C = new FlightKey("FRA", "JFK", "01/03/2019", "07:00");
        FlightData Ca = new FlightData("LH123", 400);

        FlightKey D = new FlightKey("FRA", "JFK", "01/03/2019", "16:00");
        FlightData Da = new FlightData("LH143", 500);

        FlightKey E = new FlightKey("FRA", "JFK", "01/03/2019", "17:00");
        FlightData Ea = new FlightData("AA123", 400);

        FlightKey F = new FlightKey("FRA", "JFK", "01/03/2019", "22:00");
        FlightData Fa = new FlightData("DL324", 400);

//        FlightKey B = new FlightKey("S", "HKG", "21/03", "22:00");
//        FlightData Ba = new FlightData("CX514", 1200);
//
//        FlightKey C = new FlightKey("LHW", "HKG", "29/04", "18:00");
//        FlightData Ca = new FlightData("BA123", 1900);



//        FlightNode one = new FlightNode(A, Aa);
//        FlightNode two = new FlightNode(B, Ba);
        fl.insert(A, Aa);
        fl.insert(B, Ba);
        fl.insert(C, Ca);
        fl.insert(D, Da);
        fl.insert(E, Ea);
        fl.insert(F, Fa);


//        System.out.println(fl.find(B));
//        System.out.println(fl.find(A));
//        System.out.println(fl.find(C));

        System.out.println("************************");
        fl.print();
        System.out.println("************************");


        System.out.println(fl.findFlights(A, 2));
    }
}
