import java.util.Comparator;

public class Leader {
    private String alias;
    private int points;

    public Leader(String alias, int points) {
        this.alias = alias;
        this.points = points;
    }

    public String getAlias() {
        return alias;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public static Comparator<Leader> LeaderPointsComparator = (l1, l2) -> {
        int points1 = l1.getPoints();
        int points2 = l2.getPoints();

        return points2 - points1;
    };
}