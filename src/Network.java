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


    public void addAntennas(Antenna newAntenna) {
        for (Antenna antenna : antennas) {
            if (antenna.getLocation().distanceTo(newAntenna.getLocation()) == 0) {
                System.out.println("This antenna already exists in the network. Cannot add duplicate antennas.");
                return;
            }
        }

        antennas.add(newAntenna);
        System.out.println("Antenna added successfully at location: " + newAntenna.getLocation());
    }


    public Antenna findNearestAntenna(Phone phone) {
        Antenna nearestAntenna = null;
        double shortestDistance = Double.MAX_VALUE;

        for (Antenna antenna : antennas) {
            if (antenna.phoneinRange(phone)) {
                double distance = antenna.getLocation().distanceTo(phone.getLocation());
                if (distance < shortestDistance) {
                    shortestDistance = distance;
                    nearestAntenna = antenna;
                }
            }
        }

        return nearestAntenna;
    }

    @Override
    public String toString() {
        return "Network{" +
                "antennas=" + antennas +
                '}';
    }
}
