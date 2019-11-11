public class TypeOfAsset {
    private String name;
    private int points;

    public TypeOfAsset(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object obj) {
        return name.equals(((TypeOfAsset) obj).getName());
    }
}
