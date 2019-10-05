public class Discount {
    private Active activo;
    private int minPoints;
    private String zone;
    private int discount;

    public Discount(Active activo, int minPoints, String zone,int discount){
        this.activo = activo;
        this.minPoints = minPoints;
        this.discount = discount;
        this.zone = zone;
    }

    public Active getActivo() {
        return activo;
    }

    public int getMinPoints() {
        return minPoints;
    }

    public String getZone() {
        return zone;
    }

    public int getDiscount() {
        return discount;
    }
}