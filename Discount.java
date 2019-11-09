public class Discount {
    private Asset asset;
    private int minPoints;
    private int discount;
    private Zone zone;

    public Discount(Asset asset, int minPoints, int discount, Zone zone){
        if(asset != null) this.asset = asset;
        if(minPoints >= 0) this.minPoints = minPoints;
        if(discount > 0 && discount <=100) this.discount = 1 - discount/100;
        if(zone != null) this.zone = zone;
    }

    public Asset getAsset() {
        return asset;
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