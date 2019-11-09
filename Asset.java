public class Asset {
    private TypeOfAsset type;
    private int id;

    public Asset(TypeOfAsset type,int id){
        this.type = type;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public TypeOfAsset getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        return id == ((Asset) obj).getId();
    }
}