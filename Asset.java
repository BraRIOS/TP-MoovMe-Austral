public class Asset {
    private TypeOfAsset type;
    private Zone zone;
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

    public boolean sameZone(Zone aZone) {
        return zone.getName().equals(aZone.getName());
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    @Override
    public boolean equals(Object obj) {
        return this.getId() == ((Asset) obj).getId();
    }

    public boolean equalsByType(Asset a) {
        return this.getType().equals(a.getType());
    }
}