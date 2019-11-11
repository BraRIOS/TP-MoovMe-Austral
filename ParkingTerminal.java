import java.io.Serializable;
import java.util.ArrayList;

public class ParkingTerminal implements Serializable {

    private static final long serialVersionUID = 1034567890L;

    private ArrayList<Asset> actives;
    private Zone zone;
    private TypeOfAsset typeOfAsset;

    public ParkingTerminal(Zone zone, Batch batch){
        this.zone = zone;
        actives = batch.getListOfAssets();
        typeOfAsset = batch.getListOfAssets().get(0).getType();
    }
    public void deliveryActive(Asset asset){ actives.add(asset); }
    public void pickUp(Asset asset){
        actives.remove(asset);
    }

    public TypeOfAsset getTypeOfAsset() {
        return typeOfAsset;
    }

    public ArrayList<Asset> getActives() {
        return actives;
    }

    public Zone getZone() {
        return zone;
    }
}
