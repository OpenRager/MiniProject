import java.util.ArrayList;

public class Network {
    private ArrayList<Antenna> antennas;

    public Network() {
        this.antennas = new ArrayList<>();
    }

    public ArrayList<Antenna> getAntennas() {
        return antennas;
    }

    public void setAntennas(ArrayList<Antenna> antennas) {
        this.antennas = antennas;
    }


    public void addAntennas(Antenna a) {
        for (Antenna existingAntenna : antennas) {
            if (existingAntenna.getLocation().distanceTo(a.getLocation()) <= existingAntenna.getCoverageRadius()) {
                antennas.add(a);
            }
        }
        if (antennas.contains(a)) {
            System.out.println("Antenna is already part of the network");
        }
    }


    public Antenna findNearestAntenna(Phone phone) {
        Antenna nearestAntenna = null;
        double shortestDistance = Double.MAX_VALUE;

        for (Antenna antenna : antennas) {
            double distance = antenna.getLocation().distanceTo(phone.getLocation());
            if (distance <= antenna.getCoverageRadius() && antenna.canAcceptNewCall() && distance < shortestDistance) {
                nearestAntenna = antenna;
                shortestDistance = distance;
            }
        }

        return nearestAntenna; // Returns null if no antenna is found
    }
}



