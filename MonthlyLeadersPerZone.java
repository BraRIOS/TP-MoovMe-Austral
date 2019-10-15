
public class MonthlyLeadersPerZone {
    private ABM<Leader> leaders;
    private Zone zone;
    public MonthlyLeadersPerZone(Zone zone) {
        this.zone=zone;
        leaders = new ABM<>();
    }
    public void updateLeaders(Client aClient, int points){ //TEMPORAL
        boolean founded = false;
        for (Leader l : leaders.getList()) {
            if (l.getAlias().equals(aClient.getAlias())) {
                l.setPoints(l.getPoints() + points);
                founded = true;
            }
        }
        if (founded == false)
            leaders.getList().add(new Leader(aClient.getAlias(), points));

        leaders.getList().sort((l1, l2) -> {
            int points1 = l1.getPoints();
            int points2 = l2.getPoints();

            return points2 - points1;
        });
    }
    public ABM<Leader> getLeaders() {
        return leaders;
    }

    public Zone getZone() {
        return zone;
    }

    /*public void clear(){ //El primer día de cada mes renueva la lista
        leaders.clear();
    }*/

}