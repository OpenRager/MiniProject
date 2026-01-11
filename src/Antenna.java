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

    
    
    
    public boolean phoneinRange(Phone p) {
        return location.distanceTo(p.getLocation()) <= coverageRadius; // Returns true if the given phone is within the antenna's coverage area.
    }

  
    public void incrementActiveCalls() {
        if (activeComms < capacityLimit) {
            activeComms++; // Increments active calls if capacity allows
        } else {
            System.out.println("Antenna capacity exceeded."); 
        }
    }
     
    public void decrementActiveCalls() {
        if (activeComms > 0) {
            activeComms--; // Decrements active calls if any are active
        } else {
            System.out.println("There are no calls being made right now.");
        }
    }

    public boolean canAcceptNewCall() {
        return activeComms < capacityLimit; // Returns true if the antenna can accept at least one more call.
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
