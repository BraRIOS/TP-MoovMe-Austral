import org.junit.Assert;
import org.junit.Test;

public class LeaderTest {

    @Test
    public void getPoints() {
        Leader lider = new Leader("Persona1", 545);
        Assert.assertEquals(545, lider.getPoints(),0);
    }
}