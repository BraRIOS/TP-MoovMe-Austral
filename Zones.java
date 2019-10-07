import java.util.ArrayList;
import java.util.List;

public class Zones {
    private List<Zone> zones;
    public Zones(){
        zones= new ArrayList<>();
    }
    public void addZone(String name, double area){
        for(int i=0; i< zones.size(); i++){
            if(zones.get(i).getName().equals(name))
                throw new RuntimeException("Ya existe esta zona");
            else zones.add(new Zone(name, area));
        }
    }

    public List<Zone> getZones() {
        return zones;
    }
}
