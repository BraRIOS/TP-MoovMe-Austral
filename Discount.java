public class Discount {
    private Active active;
    private int minPoints;
    private int discount;
    private Zone zone;

    public Discount(Active active, int minPoints, int discount, Zone zone){
        if(active != null) this.active = active;
        if(minPoints >= 0) this.minPoints = minPoints;
        if(discount > 0 && discount <=100) this.discount = discount;
        if(zone != null) this.zone = zone;
    }

    public Active getActive() {
        return active;
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