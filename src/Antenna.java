public class Antenna {
    private Point location;
    private double coverageRadius;
    private int capacityLimit;
    private int activeComms;

    public Antenna(double x, double y, double coverageRadius, int capacityLimit, int activeComms) {
        this.location = new Point(x, y);
        this.coverageRadius = coverageRadius;
        this.capacityLimit = capacityLimit;
        this.activeComms = activeComms;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public double getCoverageRadius() {
        return coverageRadius;
    }

    public void setCoverageRadius(double coverageRadius) {
        this.coverageRadius = coverageRadius;
    }

    public int getCapacityLimit() {
        return capacityLimit;
    }

    public void setCapacityLimit(int capacityLimit) {
        this.capacityLimit = capacityLimit;
    }

    public int getActiveComms() {
        return activeComms;
    }

    public void setActiveComms(int activeComms) {
        this.activeComms = activeComms;
    }

    public boolean phoneinRange (Phone p) {
        // Logically checking to see if phone is in range
        return location.distanceTo(p.getLocation()) <= coverageRadius;
    }

    public void incrementActiveCalls() {
        if (activeComms + 1 < capacityLimit) {
            activeComms++;
        }
        else
            System.out.println("Antenna capacity exceeded.");
    }
    public void decrementActiveCalls () {
        if (activeComms > 0) {
            activeComms--;
        }
        else
            System.out.println("There are no calls being made right now.");
    }

    public boolean canAcceptNewCall() {
        // Logically checking to see if a call can be made
        return activeComms < capacityLimit;
    }

    @Override
    public String toString() {
        return "Antenna{" +
                "location=" + location +
                ", coverageRadius=" + coverageRadius +
                ", capacityLimit=" + capacityLimit +
                ", activeComms=" + activeComms +
                '}';
    }
}
