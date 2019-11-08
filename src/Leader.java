
public class Leader extends User{
    private int points;

    public Leader(String alias, String password, int points) {
        super(alias, password);
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean equalsByName(Client c){
        return this.getAlias().equals(c.getAlias());
    }
}