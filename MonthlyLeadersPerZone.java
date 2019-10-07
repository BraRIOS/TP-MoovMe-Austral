import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MonthlyLeadersPerZone {
    private List<Leader> leaders;
    private Zone zone;
    public MonthlyLeadersPerZone(Zone zone) {
        this.zone=zone;
        leaders = new ArrayList<>();
    }
    public void updateLeaders(String alias, int points){
        boolean founded=false;
        for(int i=0; i< leaders.size(); i++) {//iteración para buscar lider existente por nombre
            if (leaders.get(i).getAlias().equals(alias)) {
                leaders.get(i).setPoints(leaders.get(i).getPoints() + points);
                founded=true;
            }
        }
        if(founded==false){//Si no encuentra el lider lo agrega y le suma los puntos
            leaders.add(new Leader(alias, points));
        }
        Collections.sort(leaders, Leader.LeaderPointsComparator);
    }
    public List<Leader> getLeaders() {
        return leaders;
    }

    public Zone getZone() {
        return zone;
    }
}