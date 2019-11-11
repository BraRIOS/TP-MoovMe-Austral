import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

public class InvoiceTest {

    @Test
    public void getPointsAcquired() {
        DateTime finalizo = new DateTime(23);
        Invoice invoice = new Invoice(38, 234.5, finalizo);
        Assert.assertEquals(38, invoice.getPointsAcquired(), 0);
    }

    @Test
    public void getFinalPrice() {
        DateTime finalizo = new DateTime(23);
        Invoice invoice = new Invoice(38, 234.5, finalizo);
        Assert.assertEquals(234.5, invoice.getFinalPrice(), 0);
    }

    @Test
    public void getEndTime() {
        DateTime finalizo = new DateTime(23);
        Invoice invoice = new Invoice(38, 234.5, finalizo);
        Assert.assertEquals(finalizo, invoice.getEndTime());
    }
}