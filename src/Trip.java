import org.joda.time.DateTime;
import org.joda.time.Interval;

public class Trip {
    private DateTime startTime;//tiempo en que alquila el activo
    private DateTime endTime;//tiempo en que devuelve el activo
    private DateTime deliveryTime;//hora opcional para devolver el activo y obtener mas puntos
    private Interval durationOfTrip;//tramo recorrido en minutos

    public Trip() {//Se crea una instancia de viaje cada vez que un cliente inicie un viaje por pantalla (MoovMe)
        startTime = DateTime.now();
    }

    //setear hora de entrega opcional
    public void setDeliveryTime(int hour,int minute) {
        this.deliveryTime = new DateTime(DateTime.now().getYear(),DateTime.now().getMonthOfYear(),DateTime.now().getDayOfMonth(),hour,minute);
    }

    public DateTime getDeliveryTime() {
        return deliveryTime;
    }

    /*Cuando el cliente termine el viaje por pantalla(MoovMe) ejecutamos el metodo FinishTrip, pasándole un descuento si corresponde, si no encuentra será null.
    Al terminar el viaje,
    - obtenemos el tiempo actual y calculamos la duración del viaje, buscamos la tarifa correspondiente y creamos el nuevo consumo(con el costo del viaje)
    - se suman los puntos correspondientes al tipo de activo, zona y tiempo de duración a los puntos del cliente.
    - En caso de cumplir con la hora de entrega indicada anteriormente, se le agrega un 20% adicional a la suma de puntajes anterior.
    - En caso de DENUNCIA verificar si el cliente fue bloqueado y aplicarle una multa
    */
    public Invoice FinishTrip(Client aClient, Assets anAssets, ABM<Tariff> tariffs, Discount aDiscount){
        endTime=DateTime.now();
        durationOfTrip=new Interval(startTime,endTime);
        double finalPrice = 0;
        int pointsAcquired = 0;
        for (Tariff t: tariffs.getList()) {
            if (anAssets.sameZone(t.getZone()) && aClient.getStatus()) { //Verifica si el cliente no está bloqueado y busca la tarifa correspondiente
                //CONSUMOS
                if (aDiscount!=null){
                finalPrice = t.getPricePerMinute()*getDurationOfTrip()*aDiscount.getDiscount();
                aClient.addConsumption(new Consumption( finalPrice, endTime));
                }
                else {
                    finalPrice = t.getPricePerMinute()*getDurationOfTrip();
                    aClient.addConsumption(new Consumption( finalPrice, endTime));
                }
                //PUNTOS
                if (getDeliveryTime().isBefore(endTime) || getDeliveryTime().isEqual(endTime)){
                    pointsAcquired = Math.round(pointsSummary(aClient,anAssets)*1.2f);
                    aClient.setPoints(aClient.getPoints() + pointsAcquired);
                }
                else {
                    pointsAcquired = pointsSummary(aClient,anAssets);
                    aClient.setPoints(aClient.getPoints() + pointsAcquired);
                }
            }
            //MULTA
            if (anAssets.sameZone(t.getZone()) && !aClient.getStatus()){
                finalPrice = t.getPricePerMinute()*getDurationOfTrip()*2;
                aClient.addConsumption(new Consumption( finalPrice, endTime));
            }
        }
        return new Invoice(pointsAcquired, finalPrice, endTime);
    }

    //devolvemos el tiempo que se utilizo el activo en minutos
    public int getDurationOfTrip() {
        return durationOfTrip.toDuration().toStandardMinutes().getMinutes();
    }

    public int pointsSummary(Client aClient, Assets anAssets){
        return Math.toIntExact(Math.round(anAssets.getPoints()*anAssets.getZone().getIncrementPercent()*getDurationOfTrip()));
    }
}

