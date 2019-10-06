import java.util.HashSet;

public class Zone {
    private String name;
    private double area;
    private HashSet<Zone> zones;

    public Zone(String name, double area){
        this.name=name;
        this.area=area;
    }

    public void addZone(String name, double area){
        zones.add(new Zone(name, area));
    }
}
