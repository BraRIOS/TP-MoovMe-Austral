import org.joda.time.DateTime;

import java.io.Serializable;

public class Consumption implements Serializable {

    private static final long serialVersionUID = 1234507890L;

    private double consumptionPrice;
    private DateTime endTime;

    public Consumption(double consumptionPrice, DateTime endTime) {
        this.consumptionPrice = consumptionPrice;
        this.endTime = endTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public double getConsumptionPrice() {
        return consumptionPrice;
    }
}