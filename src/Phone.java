public class Phone {
    private int batteryLevel;
    private String location;
    private SimCard simCard;

    public Phone(String location, int batteryLevel, SimCard simCard) {
        this.location = location;
        this.batteryLevel = batteryLevel;
        this.simCard = simCard;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public SimCard getSimCard() {
        return simCard;
    }

    public void setSimCard(SimCard simCard) {
        this.simCard = simCard;
    }
}
