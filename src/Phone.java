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

    public void setLocation(Point location) {
        this.location = location;
    }

    public SimCard getSimCard() {
        return simCard;
    }

    public void setSimCard(SimCard simCard) {
        this.simCard = simCard;
    }

    public boolean canMakeCall(String currentLocation) {

        if (batteryLevel == 0) {
            return false;
        }


        if (this.simCard == null || !this.simCard.isActivated()) {
            return false;
        }


        if (!simCard.checkCredit()) {
            return false;
        }

        return true;
    }
}
