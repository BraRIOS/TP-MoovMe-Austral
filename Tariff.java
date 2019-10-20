public class Tariff {
    double price;
    Zone zone;

    public Tariff(double price, Zone zone) {
        this.price = price;
        this.zone = zone;
    }

    public double getPrice() {
        return price;
    }

    public Zone getZone() {
        return zone;
    }
}
