import org.joda.time.DateTime;
import org.joda.time.Interval;

public class Trip {
    private DateTime startTime;//tiempo en que alquila el activo
    private DateTime endTime;//tiempo en que devuelve el activo
    private DateTime deliveryTime;//hora opcional para devolver el activo y obtener mas puntos
    private Interval durationOfTrip;//tramo recorrido en minutos
    private ABM<Consumption> consumptions;
//tarifas van a crearse con un precio base y a la hora de finalizar el viaje se va a realizar el calculo total(puntos y precio)
    public Trip() {
        startTime = DateTime.now();
        consumptions= new ABM<>();
    }

    public void setDeliveryTime(int hour,int minute) {
        this.deliveryTime = new DateTime(DateTime.now().getYear(),DateTime.now().getMonthOfYear(),DateTime.now().getDayOfMonth(),hour,minute);
    }//setear hora de entrega opcional

    public DateTime getDeliveryTime() {
        return deliveryTime;
    }

    //cuando alguien termine el viaje por pantalla(MoovMe) ejecutamos el metodo FinishTrip
    public void FinishTrip(Client aClient, Assets anAssets,ABM<Tariff> tariffs){
        endTime=DateTime.now();
        durationOfTrip=new Interval(startTime,endTime);
        for (Tariff t: tariffs.getList()) {
            if (anAssets.isInTheZone(t.getZone())) {
                consumptions.add(new Consumption(t.getPricePerMinute()*getDurationOfTrip()));
                if (getDeliveryTime().isBefore(endTime) || getDeliveryTime().isEqual(endTime))
                aClient.setPoints(Math.round(addPoints(aClient,anAssets)*0.8f));
            }
        }
    }//al terminar el viaje, obtenemos el tiempo actual, buscamos la tarifa correspondiente y creamos el nuevo consumo

    public int getDurationOfTrip() {
        return durationOfTrip.toDuration().toStandardMinutes().getMinutes();
    }
    //devolvemos el tiempo que se utilizo el activo en minutos

    public int addPoints(Client aClient,Assets anAssets){
        return aClient.getPoints()+Math.toIntExact(Math.round(anAssets.getPoints()*anAssets.getZone().getIncrementPercent()*getDurationOfTrip()));
    }
}

