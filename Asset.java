public class Asset {
    private String type;
    private int points;
    private Zone zone;
    private int id;

    public Asset(String type, int points, int id){
        this.type = type;
        this.points = points;
        this.id = id;
    }

    public int getPoints() {
        return points;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public boolean sameZone(Zone aZone) {
        return zone.getName().equals(aZone.getName());
    }

    public void setPoints(int points) {
        this.points = points;
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