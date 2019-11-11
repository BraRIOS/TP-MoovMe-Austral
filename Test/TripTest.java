import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

public class TripTest {

    @Test
    public void getDeliveryTime() {
        Zone pilar = new Zone("Pilar", 20);
        DateTime ya=DateTime.now();
        Trip viaje = new Trip(pilar,ya);
        viaje.setDeliveryTime(new DateTime(DateTime.now().getYear(),DateTime.now().getMonthOfYear(),DateTime.now().getDayOfMonth(),1,0));
        DateTime entrega = new DateTime(DateTime.now().getYear(),DateTime.now().getMonthOfYear(),DateTime.now().getDayOfMonth(),1,0);
        Assert.assertEquals(entrega, viaje.getDeliveryTime());
    }

    @Test
    public void finishTrip() {
        Zone pilar = new Zone("Pilar", 20);
        Trip viaje = new Trip(pilar,DateTime.now());
        viaje.setDeliveryTime(new DateTime(DateTime.now().getYear(),DateTime.now().getMonthOfYear(),DateTime.now().getDayOfMonth(),1,0));

        Client pepe = new Client("Pepe", 11234567);

        TypeOfAsset auto = new TypeOfAsset("Auto", 30);
        Asset vehiculo = new Asset(auto, 3456,"tuquito,Bruno Caselli presente en nuestros corazones");

        Tariff tarifa = new Tariff(20, pilar);
        ABM<Tariff> precios = new ABM<>();
        precios.add(tarifa);

        Discount descuento = new Discount(auto, 50, 10, pilar);

        DateTime entrega = new DateTime();
        DateTime entregado = entrega;
        viaje.setEndTime(entrega);

        Assert.assertEquals(entregado, viaje.FinishTrip(pepe, vehiculo, precios, descuento).getEndTime());
        Assert.assertEquals(0, viaje.FinishTrip(pepe, vehiculo, precios, descuento).getPointsAcquired());
    }

}