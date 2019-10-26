public class Scoring{
    private ABM<Discount> discounts;
    private ABM<MonthlyLeadersPerZone> rankings;
    public Scoring(){
        discounts= new ABM<>();
        rankings = new ABM<>();
    }
    public void addDiscount(Assets assets, int minPoints, int discount, Zone zone){
        discounts.add(new Discount(assets, minPoints, discount, zone));
    }
    public void removeDiscount(Assets assets, Zone zone){
        for( Discount d : discounts.getList()){
            if (d.getAssets().equals(assets) && d.getZone().equals(zone))
                discounts.remove(d);
        }
    }
    /*public Discount findDiscount(Client aClient, Assets anActive, Zone aZone){ //Viaje le pasa a Scoring el cliente para ver sus puntos, el activo que pidio el cliente y la zona indicada
       Iterator<Discount> it = discounts.iterator();
       while(it.hasNext()) {
           Discount aDiscount= it.next();
           if(aClient.getPoints() >= aDiscount.getMinPoints() && anActive.g.equals(descuento.getZone()) && activo.equals(descuento.getActivo())) {
               activo.setTarifa(activo.getTarifa() * descuento.getDiscount() / 100);
           }
       }
   }*/

    public void createRankingPerZone(ABM<Zone> zones){ //Por cada zona en la lista de zonas del ABM creamos un ranking, cuando se agregue una zona (zones.addZones()) tambien se debe ejecutar este método
        for (Zone aZone: zones.getList()) {
            rankings.add(new MonthlyLeadersPerZone(aZone));
        }

    }

    public void updateRanking(Zone zone, Client aClient, int points){//Al momento de alquilar un activo, Viaje le pasa a ranking los puntos sumados por el cliente en la zona indicada
        for(int i=0; i< rankings.getList().size(); i++){
            if(rankings.getList().get(i).getZone() == zone)
                rankings.getList().get(i).updateLeaders(aClient, points);
        }
    }
}
