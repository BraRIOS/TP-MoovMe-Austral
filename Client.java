import java.util.HashMap;

public class Client extends User {
    private boolean blocked;
    private int phone;
    private HashMap<Zone,Integer> pointsPerZone;
    private Score points;
    private ABM<Consumption> travelHistory;

    public void alquilar(Trip t) {
        actualTrip = t;
        inTrip = true;
    }

    public Trip getActualTrip() {
        return actualTrip;
    }

    public boolean isInTrip() {
        return inTrip;
    }

    public void finishTrip() {
        this.inTrip = false;
    }

    private Trip actualTrip;
    private boolean inTrip;
    private boolean winnerOfTheMonth;

    public Client(String alias, int phone) {
        super(alias);
        blocked = false;
        points = new Score();
        this.phone = phone;
        travelHistory= new ABM<>();
        winnerOfTheMonth = false;
        pointsPerZone = new HashMap<>();
        actualTrip = null;
        inTrip = false;
    }

    public boolean isBlocked(){return blocked;}

    public void block(){blocked = true; }

    public void unBlock(){blocked = false;}

    public int getPhone() {return phone;}

    public Score getPoints() {
        points.restartPoints();
        for (Integer value : pointsPerZone.values()) {
            points.addPoints(value);
        }
        return points;
    }

    public HashMap<Zone, Integer> getPointsPerZone() {
        return pointsPerZone;
    }

    public void addPointsToZone(Zone aZone, int points){
        if(pointsPerZone.containsKey(aZone)) pointsPerZone.put(aZone,pointsPerZone.get(aZone)+points);
        else pointsPerZone.put(aZone,points);
    }

    public ABM<Consumption> getTravelHistory() {return travelHistory;}

    public void addConsumption(Consumption c){travelHistory.add(c);}

    public boolean isWinnerOfTheMonth() {
        return winnerOfTheMonth;
    }

    public void setWinnerOfTheMonth(boolean winnerOfTheMonth) {
        this.winnerOfTheMonth = winnerOfTheMonth;
    }
}
