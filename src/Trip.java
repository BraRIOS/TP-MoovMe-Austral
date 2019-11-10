import org.joda.time.DateTime;
import org.joda.time.Interval;

public class Trip {
    private DateTime startTime;//tiempo en que alquila el activo
    private DateTime endTime;//tiempo en que devuelve el activo
    private DateTime deliveryTime;//hora opcional para devolver el activo y obtener mas puntos
    private Interval durationOfTrip;//tramo recorrido en minutos
    private float bonus;
    private Zone zone;

    public Trip(Zone aZone, DateTime dateTime) {//Se crea una instancia de viaje cada vez que un cliente inicie un viaje por pantalla (MoovMe)
        startTime = dateTime;
        deliveryTime = null;
        bonus=0;
        zone=aZone;

    }

    //setear hora de entrega opcional
    public void setDeliveryTime(DateTime dateTime) {
        this.deliveryTime = dateTime;
        bonus=0.2f;
    }

    public DateTime getDeliveryTime() {
        return deliveryTime;
    }

    /*Cuando el cliente termine el viaje por pantalla(MoovMe?) ejecutamos el metodo FinishTrip, pasándole un descuento si corresponde, si no encuentra será null.
    Al terminar el viaje,
    - obtenemos el tiempo actual y calculamos la duración del viaje, buscamos la tarifa correspondiente y creamos el nuevo consumo(con el costo del viaje)
    - se suman los puntos correspondientes al tipo de activo, zona y tiempo de duración a los puntos del cliente.
    - En caso de cumplir con la hora de entrega indicada anteriormente, se le agrega un 20% adicional a la suma de puntajes anterior.
    - En caso de DENUNCIA verificar si el cliente fue bloqueado y aplicarle una multa
    - El método retornara una factura que indica el precio a pagar, los puntos ganados y la hora en que finalizó el viaje
    */
    public Invoice FinishTrip(Client aClient, Asset anAsset, ABM<Tariff> tariffs, Discount aDiscount){
        //endTime=DateTime.now(); //para funcionamiento en tiempo real!!!!
        durationOfTrip=new Interval(startTime,endTime);
        double finalPrice = 0;
        int pointsAcquired = 0;
        for (Tariff t: tariffs.getList()) {
            if (zone.equals(t.getZone()) && !aClient.isBlocked()) { //Verifica si el cliente no está bloqueado y busca la tarifa correspondiente
                //PUNTOS
                pointsAcquired = Math.round(pointsSummary(aClient, anAsset) * (1 + (isAtTime() ? bonus : 0)));
                aClient.addPointsToZone(zone, pointsAcquired);
                //CONSUMOS
                if (aDiscount != null) aClient.getPoints().spendPoints(aDiscount.getMinPoints());
                finalPrice = t.getPricePerMinute() * getDurationOfTrip() * (aDiscount != null ? aDiscount.getDiscount() : 1);
                aClient.addConsumption(new Consumption(finalPrice, endTime));
            }
            //MULTA
            if (zone.equals(t.getZone()) && aClient.isBlocked()){
                finalPrice = t.getPricePerMinute()*getDurationOfTrip()*2;
                aClient.addConsumption(new Consumption( finalPrice, endTime));
            }
        }
        return new Invoice(pointsAcquired, finalPrice, endTime);
    }

    //devolvemos el tiempo que se utilizo el activo en minutos
    private int getDurationOfTrip() {
        return durationOfTrip.toDuration().toStandardMinutes().getMinutes();
    }

    private int pointsSummary(Client aClient, Asset anAsset){
        return Math.toIntExact(Math.round(anAsset.getType().getPoints()* zone.getIncrementPercent()*getDurationOfTrip()));
    }

    private boolean isAtTime(){
        return deliveryTime!=null && (deliveryTime.isBefore(endTime) || deliveryTime.isEqual(endTime));
    }

    public void setEndTime(DateTime endTime) {//para demostracion de funcionamiento correcto
        this.endTime = endTime;
    }
}

