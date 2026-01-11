public class Phone {
    private int batteryLevel;
    private Point location;
    private SimCard simCard;
    private boolean inCall;
    private Antenna connectedAntenna;

    public Phone(double x, double y, int batteryLevel, SimCard simCard) {
        this.location = new Point(x, y);
        this.batteryLevel = batteryLevel;
        this.simCard = simCard;
        this.inCall = false;
        this.connectedAntenna = null;
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
        // Handle moving calls
        if (inCall) {
            if (connectedAntenna != null && connectedAntenna.phoneinRange(this)) {
                return; 
            }

            Antenna best = network.findNearestAntenna(this); // ensures capacity and range
            if (best != null) {
                // Handoff to the new antenna
                if (connectedAntenna != null && connectedAntenna != best) {
                    connectedAntenna.decrementActiveCalls();
                }
                best.incrementActiveCalls();
                connectedAntenna = best;
                System.out.println("Handoff performed to nearest antenna at " + best.getLocation());
            } else {
                // Out of coverage
                if (connectedAntenna != null) {
                    connectedAntenna.decrementActiveCalls();
                }
                connectedAntenna = null;
                inCall = false;
                System.out.println("Out of range. Call disconnected.");
            }
        }
    }

    public SimCard getSimCard() {
        return simCard;
    }

    public void setSimCard(SimCard simCard) {
        this.simCard = simCard;
    }

    public boolean canAcceptNewCall(Network network) {
        if (inCall) return false; 
        if (batteryLevel <= 0) return false;
        if (simCard == null || !simCard.isActivated()) return false;
        if (!simCard.checkCredit()) return false;

        Antenna nearestAntenna = network.findNearestAntenna(this);
        return nearestAntenna != null; // already filtered by capacity and range
    }

    public boolean makeCall(Network network) {
        if (!canAcceptNewCall(network)) return false;
        Antenna nearestAntenna = network.findNearestAntenna(this);
        if (nearestAntenna == null) return false;
        nearestAntenna.incrementActiveCalls();
        simCard.deductCredit();
        inCall = true;
        connectedAntenna = nearestAntenna;
        return true;
    }

    public boolean receiveCall(Network network) {
        if (inCall) return false; 
        if (batteryLevel <= 0) return false;
        Antenna nearestAntenna = network.findNearestAntenna(this);
        if (nearestAntenna == null) return false; // out of range or no capacity
        nearestAntenna.incrementActiveCalls();
        inCall = true;
        connectedAntenna = nearestAntenna;
        return true;
    }

         
    public void endCall() {
        if (inCall) {
            if (connectedAntenna != null) {
                connectedAntenna.decrementActiveCalls(); 
            }
            connectedAntenna = null;
            inCall = false; // Ends the active call, releasing antenna capacity.
        }
    }

    public boolean isInCall() {
        return inCall;
    }

    public Antenna getConnectedAntenna() {
        return connectedAntenna;
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
