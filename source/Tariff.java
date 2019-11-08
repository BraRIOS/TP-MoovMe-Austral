public class Tariff {
    double pricePerMinute;
    Zone zone;

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
