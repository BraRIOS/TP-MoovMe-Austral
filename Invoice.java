import org.joda.time.DateTime;

import java.io.Serializable;

public class Invoice implements Serializable {

    private static final long serialVersionUID = 1230567890L;

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
