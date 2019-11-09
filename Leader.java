
public class Leader extends User{
    private int points;

    public Leader(String alias, int points) {
        super(alias);
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}