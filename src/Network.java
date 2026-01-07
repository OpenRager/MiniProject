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
        // Reject duplicates (same location)
        for (Antenna antenna : antennas) {
            if (antenna.getLocation().distanceTo(newAntenna.getLocation()) == 0) {
                System.out.println("This antenna already exists in the network. Cannot add duplicate antennas.");
                return;
            }
        }

        // Allow the first antenna to be added (bootstrap), otherwise require overlap with at least one existing antenna
        if (!antennas.isEmpty()) {
            boolean overlapsAny = false;
            for (Antenna existing : antennas) {
                if (overlaps(existing, newAntenna)) {
                    overlapsAny = true;
                    break;
                }
            }
            if (!overlapsAny) {
                System.out.println("Isolated antennas are not allowed. New antenna must overlap coverage with at least one existing antenna.");
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
            // Must be within coverage and have available capacity
            if (antenna.phoneinRange(phone) && antenna.canAcceptNewCall()) {
                double distance = antenna.getLocation().distanceTo(phone.getLocation());
                if (distance < shortestDistance) {
                    shortestDistance = distance;
                    nearestAntenna = antenna;
                }
            }
        }

        return nearestAntenna;
    }

    /**
     * Returns true when two antennas' coverage areas overlap or touch.
     */
    private boolean overlaps(Antenna a1, Antenna a2) {
        double centerDist = a1.getLocation().distanceTo(a2.getLocation());
        return centerDist <= (a1.getCoverageRadius() + a2.getCoverageRadius());
    }

    @Override
    public String toString() {
        return "Network{" +
                "antennas=" + antennas +
                '}';
    }
}
