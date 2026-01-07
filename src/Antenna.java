/**
 * Represents a cellular antenna providing radio coverage.
 */
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

    /**
     * Returns true if the given phone is within the antenna's coverage area.
     */
    public boolean phoneinRange(Phone p) {
        return location.distanceTo(p.getLocation()) <= coverageRadius;
    }

    /**
     * Increments active calls if capacity allows; otherwise logs a message.
     */
    public void incrementActiveCalls() {
        if (activeComms < capacityLimit) {
            activeComms++;
        } else {
            System.out.println("Antenna capacity exceeded.");
        }
    }

    /**
     * Decrements active calls if any are active; otherwise logs a message.
     */
    public void decrementActiveCalls() {
        if (activeComms > 0) {
            activeComms--;
        } else {
            System.out.println("There are no calls being made right now.");
        }
    }

    /**
     * Returns true if the antenna can accept at least one more call.
     */
    public boolean canAcceptNewCall() {
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
