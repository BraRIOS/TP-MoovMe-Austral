
public class ParkingTerminals {
    private ABM<Asset> actives;
    private Zone zone;

    public ParkingTerminals(Zone zone){
        this.zone = zone;
        actives = new ABM<>();
    }
    public void deliveryActive(Asset asset){
        actives.add(asset);
    }
    public void pickUp(Asset asset){
        actives.remove(asset);
    }
}
