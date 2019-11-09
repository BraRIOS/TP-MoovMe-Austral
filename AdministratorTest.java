import org.junit.Assert;
import org.junit.Test;

public class AdministratorTest {

    @Test
    public void block() {
        Client pepe = new Client("Pepe", 1102034556);
        Administrator admin = new Administrator("Juan");
        admin.block(pepe);
        Assert.assertTrue(pepe.isBlocked());
    }

    @Test
    public void unblock() {
        Client pepe = new Client("Pepe", 1102034556);
        Administrator admin = new Administrator("Juan");
        admin.block(pepe);
        Assert.assertTrue(pepe.isBlocked());
        admin.unblock(pepe);
        Assert.assertFalse(pepe.isBlocked());
    }
}