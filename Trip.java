import org.joda.time.DateTime;
import org.joda.time.Interval;

public class Trip {
    private Active anActive;
    private Client aClient;
    private DateTime startTime;
    private DateTime endTime;
    private DateTime deliveryTime;
    private Interval durationOfTrip;

    public Trip(DateTime deliveryTime) {
        startTime = DateTime.now();
        this.deliveryTime = deliveryTime;
    }

    public DateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void FinishTrip(){
        endTime=DateTime.now();
        durationOfTrip=new Interval(startTime,endTime);
    }

    public int getDurationOfTrip() {
        return durationOfTrip.toDuration().toStandardMinutes().getMinutes();
    }
}

