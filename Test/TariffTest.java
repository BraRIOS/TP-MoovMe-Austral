import org.junit.Assert;
import org.junit.Test;


public class TariffTest {
    @Test
    public void tariff(){
        Zone z1 = new Zone("Pilar", 10);
        Tariff t1 = new Tariff(25, z1);
        Assert.assertEquals(25, t1.getPricePerMinute(), 0);
        Assert.assertEquals(z1, t1.getZone());
    }

}