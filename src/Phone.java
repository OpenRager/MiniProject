public class Phone {
    private int batteryLevel;
    private Point location;
    private SimCard simCard;

    public Phone(double x, double y, int batteryLevel, SimCard simCard) {
        this.location = new Point(x, y);
        this.batteryLevel = batteryLevel;
        this.simCard = simCard;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point newLocation, Network network) {
        this.location = newLocation;

        // Handle automatic call disconnection if moved out of network range
        Antenna nearestAntenna = network.findNearestAntenna(this);
        if (nearestAntenna == null) {
            System.out.println("Out of range. Call disconnected.");
        }
    }

    public SimCard getSimCard() {
        return simCard;
    }

    public void setSimCard(SimCard simCard) {
        this.simCard = simCard;
    }

    public boolean canAcceptNewCall(Network network) {
        if (batteryLevel <= 0) {
            return false;
        }

        if (!simCard.isActivated()) {
            return false;
        }

        if (!simCard.checkCredit()) {
            return false;
        }

        Antenna nearestAntenna = network.findNearestAntenna(this);
        if (nearestAntenna == null) {
            return false;
        }

        if (!nearestAntenna.canAcceptNewCall()) {
            return false;
        }

        return true;
    }

    public boolean makeCall(Network network) {
        if (!canAcceptNewCall(network)) {
            return false;
        }

        Antenna nearestAntenna = network.findNearestAntenna(this);
        nearestAntenna.incrementActiveCalls();
        simCard.deductCredit();
        return true;
    }

    public boolean receiveCall(Network network) {
        if (batteryLevel <= 0) {
            return false;
        }

        Antenna nearestAntenna = network.findNearestAntenna(this);
        if (nearestAntenna == null) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "batteryLevel=" + batteryLevel +
                ", location=" + location +
                ", simCard=" + simCard +
                '}';
    }
}