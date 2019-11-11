import java.io.Serializable;

public class Score implements Serializable {

    private static final long serialVersionUID = 1234567800L;

    private int totalPoints;
    private int spentPoints;//refleja los puntos gastados en los descuentos

    public int getTotalPoints() {return this.totalPoints;}
    public int getCurrentPoints() {return this.totalPoints-this.spentPoints;}//Utilizado para descuentos, indica los puntos disponibles a gastar

    public void addPoints(int points) {this.totalPoints += points;}
    public void spendPoints(int spentPoints) {this.spentPoints += spentPoints;}

    public void restartPoints() {
        totalPoints = 0;
    }
}
