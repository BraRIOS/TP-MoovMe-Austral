/*import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;


public class ConsumptionTest {

    @Test
    public void getEndTime() {
        DateTime finalizo = new DateTime(23);
        Consumption consumicion = new Consumption(243.5, finalizo);
        Assert.assertEquals(finalizo, consumicion.getEndTime());
    }

    @Test
    public void getConsumptionPrice() {
        DateTime finalizo = new DateTime(23);
        Consumption consumicion = new Consumption(243.5, finalizo);
        Assert.assertEquals(243.5, consumicion.getConsumptionPrice(),0);
    }
}