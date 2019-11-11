import org.junit.Assert;
import org.junit.Test;


public class DiscountTest {

    @Test
    public void getType() {
        TypeOfAsset auto = new TypeOfAsset("Auto", 10);
        Zone pilar = new Zone("Pilar", 20);
        Discount disc = new Discount(auto, 30, 5, pilar);
        Assert.assertEquals(auto, disc.getType());
    }

    @Test
    public void getMinPoints() {
        TypeOfAsset auto = new TypeOfAsset("Auto", 10);
        Zone pilar = new Zone("Pilar", 20);
        Discount disc = new Discount(auto, 30, 5, pilar);
        Assert.assertEquals(30, disc.getMinPoints(), 0);
    }

    @Test
    public void getZone() {
        TypeOfAsset auto = new TypeOfAsset("Auto", 10);
        Zone pilar = new Zone("Pilar", 20);
        Discount disc = new Discount(auto, 30, 5, pilar);
        Assert.assertEquals(pilar, disc.getZone());
    }

    @Test
    public void getDiscount() {
        TypeOfAsset auto = new TypeOfAsset("Auto", 10);
        Zone pilar = new Zone("Pilar", 20);
        Discount disc = new Discount(auto, 30, 5, pilar);
        Assert.assertEquals(0.95, disc.getDiscount(), 0);
    }

    @Test
    public void testEquals() {
        TypeOfAsset auto = new TypeOfAsset("Auto", 10);
        Zone pilar = new Zone("Pilar", 20);
        Discount disc = new Discount(auto, 30, 5, pilar);
        Discount disc2 = new Discount(auto, 40, 5, pilar);
        Assert.assertTrue(disc.equals(disc2));
    }
}