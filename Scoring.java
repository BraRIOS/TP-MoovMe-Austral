import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Scoring{
    private List<Discount> discounts;
    private int points;
    private List<MonthlyLeadersPerZone> rankings;
    public Scoring(){
        discounts= new ArrayList<>();
        rankings = new ArrayList<>();
    }
    public void addDiscount(Active active, int minPoints, int discount, Zone zone){
        discounts.add(new Discount(active, minPoints, discount, zone));
    }
    public void removeDiscount(Active active, Zone zone){
        Iterator<Discount> it = discounts.iterator();
        while(it.hasNext()) {
            Discount aDiscount= it.next();
            if (aDiscount.getActive().equals(active) && aDiscount.getZone().equals(zone))
                it.remove();
        }
    }
    /*public Discount findDiscount(Client aClient, Active anActive, Zone aZone){ //MoovMe le pasa a Scoring el cliente para ver sus puntos, el activo que pidio el cliente y la zona indicada
       Iterator<Discount> it = discounts.iterator();
       while(it.hasNext()) {
           Discount aDiscount= it.next();
           if(aClient.getPoints() >= aDiscount.getMinPoints() && anActive.g.equals(descuento.getZone()) && activo.equals(descuento.getActivo())) {
               activo.setTarifa(activo.getTarifa() * descuento.getDiscount() / 100);
           }
       }
   }*/

    public void createRankingPerZone(ABM<Zone> zones){ //Por cada zona en la base de datos creamos un ranking, cuando se agregue una zona (zones.addZones()) tambien se debe ejecutar este método

        for (Zone aZone: zones.getList()) {
            rankings.add(new MonthlyLeadersPerZone(aZone));
        }

    }

    public void updateRanking(Zone zone, String alias, int points){//Al momento de alquilar un activo, MoovMe le pasa a ranking los puntos sumados por el cliente en la zona indicada
        for(int i=0; i< rankings.size(); i++){
            if(rankings.get(i).getZone() == zone)
                rankings.get(i).updateLeaders(alias, points);
        }
    }
}
