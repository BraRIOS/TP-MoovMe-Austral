public class Active {
    private String name;
    private int points;
    private String location;
    private int minutes;
    private double tarifa;

    public Active(String name, int points, String location, int minutes, double tarifa){
        this.name = name;
        this.points = points;
        this.location = location;
        this.minutes = minutes;
        this.tarifa = tarifa;
    }

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getMinutes() {
        return minutes;
    }

    public double getTarifa() {
        return tarifa;
    }

    public void setTarifa(double tarifa) {
        this.tarifa = tarifa;
    }
}