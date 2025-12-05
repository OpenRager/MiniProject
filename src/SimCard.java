public class SimCard {
    private double phoneNumber;
    private double creditBalance;
    private boolean isActivated;

    public SimCard(double phoneNumber, double creditBalance, boolean isActivated) {
        this.phoneNumber = phoneNumber;
        this.creditBalance = creditBalance;
        this.isActivated = isActivated;
    }

    public double getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(double phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getCreditBalance() {
        return creditBalance;
    }

    public void setCreditBalance(double creditBalance) {
        this.creditBalance = creditBalance;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }
}
