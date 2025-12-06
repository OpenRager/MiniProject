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
}
