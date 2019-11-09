import java.util.ArrayList;
import java.util.HashMap;

public class Scoring{
    private ABM<Discount> discounts;
    private HashMap<Zone, ArrayList<Leader>> rankings;
    public Scoring(){
        discounts= new ABM<>();
        rankings = new HashMap<>();
    }
    public void addDiscount(Asset asset, int minPoints, int discount, Zone zone){
        discounts.add(new Discount(asset, minPoints, discount, zone));
    }
    public void removeDiscount(Asset asset, Zone zone){
        for( Discount d : discounts.getList()){
            if (d.getAsset().equalsByType(asset) && d.getZone().equals(zone))
                discounts.remove(d);
        }
    }

    public ABM<Discount> getDiscounts() {
        return discounts;
    }

    private void updateRanking(ABM<Client> clientsABM){
        for (Client client : clientsABM.getList()) {
            for (Zone zone : client.getPointsPerZone().keySet()) {
                if (rankings.containsKey(zone)) {
                    if(!rankings.get(zone).contains(client))rankings.get(zone).add(new Leader(client.getAlias(), client.getPointsPerZone().get(zone)));
                    else rankings.get(zone).get(rankings.get(zone).indexOf(client)).setPoints(client.getPointsPerZone().get(zone));

                }else {
                    rankings.put(zone, new ArrayList<>());
                    rankings.get(zone).add(new Leader(client.getAlias(), client.getPointsPerZone().get(zone)));
                }
                rankings.get(zone).sort((l1, l2) -> {
                    int points1 = l1.getPoints();
                    int points2 = l2.getPoints();

                    return points2 - points1;
                });
            }
        }
    }

    public HashMap<Zone, ArrayList<Leader>> getRankings(ABM<Client> clientsABM) {
        updateRanking(clientsABM);
        return rankings;
    }

    public void monthlyAwards(ABM<Client> clientsABM) {
        for (Client client : clientsABM.getList()) {
            for (Zone zone : rankings.keySet()) {
                for (int i = 0; i < rankings.get(zone).size(); i++) {
                    if (i<3 && rankings.get(zone).get(i).equals(client))
                        client.setWinnerOfTheMonth(true);
                    else break;
                }
            }
        }
    }
    /*public Discount findDiscount(Client client, Asset asset, Zone zone){
        for (Discount discount : discounts.getList()) {
            if(discount.getZone().equals(zone) && discount.getMinPoints()<=client.getPoints().getCurrentPoints())
        }
    }*/
}
