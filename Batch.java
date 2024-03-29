import java.io.Serializable;
import java.util.ArrayList;

public class Batch implements Serializable {

    private static final long serialVersionUID = 1234560890L;

    private String code;
    private ArrayList<Asset> listOfAssets;

    public Batch(int quantity,TypeOfAsset type) {
        code=Code.getCode();
        listOfAssets=new ArrayList<>();
        for (int i=0;i<quantity;i++){
            listOfAssets.add(new Asset(type,(int)(Math.random()*100000),code));}
    }

    public ArrayList<Asset> getListOfAssets(){
        return listOfAssets;
    }

    public String getCode() {
        return code;
    }
}
