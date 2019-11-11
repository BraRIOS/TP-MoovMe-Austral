import javax.management.InstanceNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashMap;

public class Scoring implements Serializable {

    private static final long serialVersionUID = 1234567090L;

    private ABM<Discount> discounts;
    private HashMap<Zone, ArrayList<Leader>> rankings;
    public Scoring(){
        discounts= new ABM<>();
        rankings = new HashMap<>();
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
    //El primer dia de cada mes se ejecuta este codigo para premiar a los tres primeros.
    public void monthlyAwards(ABM<Client> clientsABM) {
        for (Client client : clientsABM.getList()) {
            for (Zone zone : rankings.keySet()) {
                for (int i = 0; i < rankings.get(zone).size(); i++) {
                    if (i<3 && rankings.get(zone).get(i).equals(client))
                        client.setWinnerOfTheMonth(true);
                    if (i==3) break;
                }
            }
        }
    }
    public Discount findDiscount(Client client, Asset asset, Zone zone) throws EmptyStackException, InstanceNotFoundException {
        int i = 0;
        if (discounts.size() == 0) throw new EmptyStackException();
        for (int j = 0; j < discounts.getList().size(); j++) {
            if (foundValidDiscount(client, asset, zone, j)) {
                i = j;
                break;
            }
            if (j==discounts.getList().size()-1) throw new InstanceNotFoundException("No hay descuento para esta zona y/o activo o el cliente no tiene los puntos suficientes");
        }
        return discounts.getList().get(i);
    }

    private boolean foundValidDiscount(Client client, Asset asset, Zone zone, int j) {
        return discounts.getList().get(j).getZone().equals(zone) && discounts.getList().get(j).getMinPoints() <= client.getPoints().getCurrentPoints() && discounts.getList().get(j).getType().equals(asset.getType());
    }
}
