import java.io.Serializable;

public class Asset implements Serializable {

    private static final long serialVersionUID = 1234567090L;

    private TypeOfAsset type;
    private int id;
    private String batchCode;

    public Asset(TypeOfAsset type,int id,String batchCode){
        this.type = type;
        this.id = id;
        this.batchCode = batchCode;
    }

    public int getId() {
        return id;
    }

    public TypeOfAsset getType() {
        return type;
    }

    public String getBatchCode() {
        return batchCode;
    }

    @Override
    public boolean equals(Object obj) {
        return id == ((Asset) obj).getId();
    }
}