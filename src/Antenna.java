public class Antenna {
    private String location;
    private double coverageRadius;
    private int capacityLimit;
    private int activeComms;


    public Antenna(String location, double coverageRadius, int capacityLimit, int activeComms) {
        this.location = location;
        this.coverageRadius = coverageRadius;
        this.capacityLimit = capacityLimit;
        this.activeComms = activeComms;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
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
}
