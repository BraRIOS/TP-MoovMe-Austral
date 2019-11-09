public class Discount {
    private TypeOfAsset type;
    private int minPoints;
    private int discount;
    private Zone zone;

    public Discount(TypeOfAsset type, int minPoints, int discount, Zone zone){
        if(type != null) this.type = type;
        if(minPoints >= 0) this.minPoints = minPoints;
        if(discount > 0 && discount <=100) this.discount = 1 - discount/100;
        if(zone != null) this.zone = zone;
    }

    public TypeOfAsset getType() {
        return type;
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