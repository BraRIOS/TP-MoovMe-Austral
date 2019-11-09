
public class MoovMe {
    public static void main(String[] args) {
        Zone tuquito=new Zone("Holi",20);

        Client bruno=new Client("Bruno",777);
        Client juan = new Client("juan",888);

        bruno.addPointsToZone(tuquito,1000);
        juan.addPointsToZone(tuquito,1200);

        System.out.println(bruno.getAlias() + "\t" + bruno.getPointsPerZone().get(tuquito));
        System.out.println(juan.getAlias() + "\t" + juan.getPointsPerZone().get(tuquito) + "\n");

        Scoring scoring = new Scoring();
        ABM<Client> clientABM = new ABM<>();
        clientABM.add(bruno);
        clientABM.add(juan);
        for (Leader leader : scoring.getRankings(clientABM).get(tuquito)) {
            System.out.println(leader.getAlias() + "\t" + leader.getPoints());
        }

        bruno.addPointsToZone(tuquito,1000);
        juan.addPointsToZone(tuquito,1200);

        System.out.println("\n" + bruno.getAlias() + "\t" + bruno.getPointsPerZone().get(tuquito));
        System.out.println(juan.getAlias() + "\t" + juan.getPointsPerZone().get(tuquito) + "\n");

        for (Leader leader : scoring.getRankings(clientABM).get(tuquito)) {
            System.out.println(leader.getAlias() + "\t" + leader.getPoints());
        }
    }
}