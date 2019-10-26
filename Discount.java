public class Discount {
    private Assets assets;
    private int minPoints;
    private int discount;
    private Zone zone;

    public Discount(Assets assets, int minPoints, int discount, Zone zone){
        if(assets != null) this.assets = assets;
        if(minPoints >= 0) this.minPoints = minPoints;
        if(discount > 0 && discount <=100) this.discount = discount;
        if(zone != null) this.zone = zone;
    }

    public Assets getAssets() {
        return assets;
    }

    public int getMinPoints() {
        return minPoints;
    }

    public Zone getZone() {
        return zone;
    }

    public int getDiscount() {
        return discount;
    }
}