public class TypeOfAsset {
    private String type;
    private int points;

    public TypeOfAsset(String type, int points) {
        this.type = type;
        this.points = points;
    }

    public String getType() {
        return type;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object obj) {
        return this.getType().equals(((Asset) obj).getType());
    }
}
