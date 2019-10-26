import org.joda.time.DateTime;

public class Consumption{
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