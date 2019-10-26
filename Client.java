public class Client extends User {
    private boolean status;
    private int phone;
    private int points;
    private ABM<Consumption> consumptions;
    private boolean winnerOfTheMonth;

    public Client(String alias, int phone) {
        super(alias);
        this.status = true;
        this.points = 0;
        this.phone = phone;
        consumptions = new ABM<>();
        winnerOfTheMonth = false;
    }

    public boolean getStatus(){
        return status;
    }
    public void addConsumption(Consumption c){
        consumptions.add(c);
    }

    public void setStatus(boolean newStatus){
        status = newStatus;
    }

    public int getPhone() {
        return phone;
    }

    public int getPoints() { return points; }

    public void setPoints(int points) {
        this.points = points;
    }

    public ABM<Consumption> getConsumptions() {
        return consumptions;
    }

    public boolean isWinnerOfTheMonth() {
        return winnerOfTheMonth;
    }

    public void setWinnerOfTheMonth(boolean winnerOfTheMonth) {
        this.winnerOfTheMonth = winnerOfTheMonth;
    }
}
