public class Score {
    private int totalPoints;
    private int spentPoints;

    public Score() {
        totalPoints = 0;
        spentPoints = 0;
    }

    public int getTotalPoints() {return this.totalPoints;}
    public int getCurrentPoints() {return this.totalPoints-this.spentPoints;}

    public void addPoints(int points) {this.totalPoints += points;}
    public void spendPoints(int spentPoints) {this.spentPoints += spentPoints;}
}
