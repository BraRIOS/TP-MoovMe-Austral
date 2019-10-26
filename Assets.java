public class Assets {
    private String type;
    private int points;
    private Zone zone;
    private int id;

    public Assets(String type, int points,int id){
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

    public boolean isInTheZone(Zone aZone) {
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
        return this.getId() == ((Assets) obj).getId();
    }
}