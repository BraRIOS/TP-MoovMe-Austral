import java.io.Serializable;

public class Tariff implements Serializable {

    private static final long serialVersionUID = 1234560890L;

    private double pricePerMinute;
    private Zone zone;

    public Tariff(double price, Zone zone) {
        pricePerMinute = price;
        this.zone = zone;
    }

    public double getPricePerMinute() {
        return pricePerMinute;
    }

    public Zone getZone() {
        return zone;
    }


}
