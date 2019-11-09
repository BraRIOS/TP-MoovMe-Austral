public class Tariff {
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
