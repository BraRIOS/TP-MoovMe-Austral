import java.util.ArrayList;
import java.util.HashMap;

public class Scoring{
    private ABM<Discount> discounts;
    private Tariff tariff;
   // private HashMap<Zone,>;
    private HashMap<Zone, ArrayList<Client>> rankings;
    public Scoring(){
        discounts= new ABM<>();
        rankings = new ABM<>();
    }
    public void addDiscount(Assets assets, int minPoints, int discount, Zone zone){
        discounts.add(new Discount(assets, minPoints, discount, zone));
    }
    public void removeDiscount(Assets assets, Zone zone){
        for( Discount d : discounts.getList()){
            if (d.getAssets().equalsByType(assets) && d.getZone().equals(zone))
                discounts.remove(d);
        }
    }

    public void startRanking()

    public void updateRanking(Zone zone, Client aClient, int points){//Al momento de finalizar el viaje, MoovMe le pasa a Scoring los puntos sumados por el cliente (mediante el invoice) en la zona indicada
        for(int i=0; i< rankings.getList().size(); i++){
            if(rankings.getList().get(i).getZone().equals(zone))
                rankings.getList().get(i).updateLeaders(aClient, points);
        }
    }

    public void monthlyAwards(ABM<Zone> zones, ABM<Client> clients) {
        for (Zone z: zones.getList()) {
            for(Client c: clients.getList()){
                for(LeaderBoard m : rankings.getList()){
                    if(m.getZone().equals(z)) {
                        for (int i = 0; i < 3; i++) {
                            if (m.getLeaders().getList().get(i).equalsByName(c))
                                c.setWinnerOfTheMonth(true);
                        }
                    }
                }
            }
        }
    }
    public Discount discountFinder(){              //Es un metodo en general para encontrar los descuentos de la lista
        int position = 0;
        for(int i = 0; i < discounts.size(); i++){
             position = i;
        }
        return discounts.getList().get(position);
    }

    public boolean validateDiscount(Client client, Zone zone) {
        if (!discountFinder().getZone().equals(zone))
            throw new IllegalArgumentException("We did not found the Zone specified");
       // if (client.getPoints() >= discountFinder().getMinPoints())
            //return true;
        return false;
     }

//Este metodo aplica el descuento sobre la tarifa a partir de la validacion del descuento, si el descuento no es valido la tarifa permanece igual
    public double applyDiscount(Client client, Zone zone){
        double newTariff;
        if(validateDiscount(client, zone) == true) {
            newTariff = tariff.pricePerMinute * discountFinder().getDiscount();
        }
        else{
            newTariff = tariff.pricePerMinute;
        }
        return newTariff;
    }
}
