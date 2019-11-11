import java.io.Serializable;

public class Discount implements Serializable {

    private static final long serialVersionUID = 1234067890L;

    private TypeOfAsset type;
    private int minPoints;
    private double discount;
    private Zone zone;

    public Discount(TypeOfAsset type, int minPoints, int discount, Zone zone){
        if(type != null) this.type = type;
        if(minPoints >= 0) this.minPoints = minPoints;
        if(discount > 0 && discount <=100) this.discount = 1 - discount/100d;
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

    public double getDiscount() {
        return discount;
    }

    @Override
    public boolean equals(Object obj) {
        return zone.equals(((Discount) obj).getZone()) && type.equals(((Discount) obj).getType());
    }
}