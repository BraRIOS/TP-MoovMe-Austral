
public class ParkingTerminals {
    private ABM<Assets> actives;
    private Zone zone;

    public ParkingTerminals(Zone zone){
        this.zone = zone;
        actives = new ABM<>();
    }
    public void addActive(Assets assets){
        actives.add(assets);
    }
    public void pickUp(Assets assets){
        actives.remove(assets);
    }
}
