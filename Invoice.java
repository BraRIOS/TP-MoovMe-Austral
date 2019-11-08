import org.joda.time.DateTime;

public class Invoice {
    private int pointsAcquired;
    private double finalPrice;
    private DateTime endTime;

    public Invoice(int pointsAcquired, double finalPrice, DateTime endTime) {
        this.pointsAcquired = pointsAcquired;
        this.finalPrice = finalPrice;
        this.endTime = endTime;
    }
    public int getPointsAcquired() {
        return pointsAcquired;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public DateTime getEndTime() {
        return endTime;
    }
}
