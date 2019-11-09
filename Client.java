import java.util.HashMap;

public class Client extends User {
    private boolean blocked;
    private int phone;
    private HashMap<Zone,Score> points;
    private ABM<Consumption> travelHistory;
    private boolean winnerOfTheMonth;

    public Client(String alias, int phone) {
        super(alias);
        this.blocked = false;
        this.points = new HashMap<>();
        this.phone = phone;
        travelHistory= new ABM<>();
        winnerOfTheMonth = false;
    }

    public boolean getStatus(){
        return blocked;
    }

    public void setStatus(boolean newStatus){
        blocked = newStatus;
    }


    public boolean isBlocked(){return blocked;}

    public int getPhone() {return phone;}

    public HashMap<Zone,Score> getPoints() { return points; }

    public ABM<Consumption> getTravelHistory() {return travelHistory;}

    public void block(){blocked = true; }

    public void unBlock(){blocked = false;}

    public void addConsumption(Consumption c){travelHistory.add(c);}

    public void setWinnerOfTheMonth(boolean winnerOfTheMonth) {
        this.winnerOfTheMonth = winnerOfTheMonth;
    }
}
