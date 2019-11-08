public class LeaderBoard {
    private ABM<Leader> leaders;
    private Zone zone;
    public LeaderBoard(Zone zone) {
        this.zone=zone;
        leaders = new ABM<>();
    }
    public void updateLeaders(Client aClient, int points){ //TEMPORAL
        //try{
            leaders.add(new Leader(aClient.getAlias(), points));
        /*}catch (RuntimeException r){
            String msg = r.getMessage();
            String msgSub = msg.substring(28);
            int index = Integer.parseInt(msgSub);
            Leader l = leaders.getList().get(index);
            l.setPoints(l.getPoints() + points);
        }*/
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

    public void clear(){ //El primer día de cada mes renueva la lista
        leaders.clear();
    }

    @Override
    public boolean equals(Object obj) {//Comparar zona de la tabla con zona especificada
        return this.getZone().getName().equals(((Zone) obj).getName());
    }
}