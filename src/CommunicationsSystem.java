public class CommunicationsSystem {
    public static void main(String[] args) {
        Network network = new Network();

        System.out.println("Adding antennas to the network...");
        Antenna antenna1 = new Antenna(0, 0, 50, 2,0);
        Antenna antenna2 = new Antenna(60, 60, 50, 1,0);
        network.addAntennas(antenna1);
        network.addAntennas(antenna2);

        SimCard simCard1 = new SimCard(1234567890, 50.0, true);

        Phone phone = new Phone(10, 10, 40, simCard1);

        System.out.println("\n--- Call Test ---");
        System.out.println("Phone attempting to call...");
        if (phone.makeCall(network)) {
            System.out.println(" -> Phone: 📞 Call successfully started!");
        } else {
            System.out.println(" -> Phone: ❌ Call failed.");
        }

        System.out.println("\n--- Moving Phone Out of Range ---");
        phone.setLocation(new Point(200, 200), network);
        System.out.println("Phone attempting to call again while out of range...");
        if (phone.makeCall(network)) {
            System.out.println(" -> Phone: 📞 Call successfully started!");
        } else {
            System.out.println(" -> Phone: 🚫 Call failed.");
        }

        System.out.println("\n--- Moving Phone Back Into Range ---");
        phone.setLocation(new Point(10, 10), network);
        System.out.println("Phone attempting to call again after moving back into range...");
        if (phone.makeCall(network)) {
            System.out.println(" -> Phone: 📞 Call successfully started!");
        } else {
            System.out.println(" -> Phone: ❌ Call failed.");
        }

        System.out.println("\n--- Final Antenna Status ---");
        System.out.println("Active connections on Antenna 1: " + antenna1.getActiveComms() + "/" + antenna1.getCapacityLimit());
        System.out.println("Active connections on Antenna 2: " + antenna2.getActiveComms() + "/" + antenna2.getCapacityLimit());
    }
}