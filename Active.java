public class Active {
    private String name;
    private int points;
    private Zone zone;
    boolean isInTheZone;

    public Active(String name, int points, Zone zone){
        this.name = name;
        this.points = points;
        this.zone = zone;
    }

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

    public void setInTheZone(boolean state){
        isInTheZone=state;
    }
}