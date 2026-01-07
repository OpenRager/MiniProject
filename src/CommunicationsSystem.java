public class CommunicationsSystem {
    public static void main(String[] args) {
        Network network = new Network();

        System.out.println("Adding antennas to the network...");
        Antenna antenna1 = new Antenna(0, 0, 50, 2,0);
        Antenna antenna2 = new Antenna(60, 60, 50, 1,0);
        network.addAntennas(antenna1);
        network.addAntennas(antenna2);

        SimCard simCard1 = new SimCard(1234567890, 50.0, true);

        Phone phone1 = new Phone(10, 10, 40, simCard1);

        System.out.println("\n--- Outgoing Call Test (Phone1) ---");
        System.out.println("Phone1 attempting to call...");
        if (phone1.makeCall(network)) {
            System.out.println(" -> Phone1: 📞 Call successfully started!");
        } else {
            System.out.println(" -> Phone1: ❌ Call failed.");
        }

        System.out.println("\n--- Moving Phone1 Within Coverage (possible handoff) ---");
        phone1.setLocation(new Point(55, 55), network); // near antenna2; may handoff if capacity is available

        System.out.println("\n--- Incoming Call Rejection While Busy (Phone1) ---");
        boolean received = phone1.receiveCall(network);
        System.out.println("Phone1 receiveCall while in call -> " + (received ? "unexpectedly accepted" : "rejected as expected"));

        System.out.println("\n--- Capacity Limit Test (Antenna2 cap=1) ---");
        SimCard simCard2 = new SimCard(5550002, 20.0, true);
        Phone phone2 = new Phone(60, 60, 50, simCard2); // closer to antenna2
        boolean call2 = phone2.makeCall(network);
        System.out.println("Phone2 attempting to call near Antenna2 -> " + (call2 ? "started" : "failed"));

        System.out.println("Attempting third call at Antenna2 with Phone3 (should fail due to capacity)");
        SimCard simCard3 = new SimCard(5550003, 20.0, true);
        Phone phone3 = new Phone(62, 62, 50, simCard3); // very close to antenna2
        boolean call3 = phone3.makeCall(network);
        System.out.println("Phone3 attempting to call -> " + (call3 ? "started" : "failed as expected"));

        System.out.println("\n--- Moving Phone1 Out of All Coverage (disconnect rule) ---");
        phone1.setLocation(new Point(200, 200), network);

        System.out.println("\n--- Phone1 Attempts New Call Out of Range ---");
        boolean call1Again = phone1.makeCall(network);
        System.out.println("Phone1 attempting to call out of range -> " + (call1Again ? "started" : "failed as expected"));

        System.out.println("\n--- Moving Phone1 Back Into Range and Call ---");
        phone1.setLocation(new Point(10, 10), network);
        boolean call1Back = phone1.makeCall(network);
        System.out.println("Phone1 attempting to call after moving back -> " + (call1Back ? "started" : "failed"));

        System.out.println("\n--- Final Antenna Status ---");
        System.out.println("Active connections on Antenna 1: " + antenna1.getActiveComms() + "/" + antenna1.getCapacityLimit());
        System.out.println("Active connections on Antenna 2: " + antenna2.getActiveComms() + "/" + antenna2.getCapacityLimit());
    }
}