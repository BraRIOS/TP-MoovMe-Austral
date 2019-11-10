import java.util.ArrayList;

public class ParkingTerminals {
    private ArrayList<Asset> actives;
    private Zone zone;

    public ParkingTerminals(Zone zone, Batch batch){
        this.zone = zone;
        actives = batch.getListOfAssets();
    }
    public void deliveryActive(Asset asset){ actives.add(asset); }
    public void pickUp(Asset asset){
        actives.remove(asset);
    }
}
