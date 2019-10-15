
public class ParkingTerminals {
    private ABM<Active> actives;
    private Zone zone;

    public ParkingTerminals(Zone zone){
        this.zone = zone;
        actives = new ABM<>();
    }
    public void addActive(Active active){
        actives.add(active);
    }
    public void pickUp(Active active){
        actives.remove(active);
    }
}
