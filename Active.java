public class Active {
    private String type;
    private int points;
    private Zone zone;
    private int id;

    public Active(String type, int points, Zone zone, int id){
        this.type = type;
        this.points = points;
        this.zone = zone;
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

    @Override
    public boolean equals(Object obj) {
        return this.getId() == ((Active) obj).getId();
    }
}