public class Client extends User {
    private boolean status;
    private int phone;
    private int points;

    public Client(String alias, int phone) {
        super(alias);
        this.status = true;
        this.points = 0;
        this.phone = phone;
    }

    public String getStatus(){
        if(status){
            return "You are free to order";
        }
        return "You have been blocked";
    }

    public void use(Active active){
        points += active.getPoints();
    }

    void setStatus(boolean newStatus){
        status = newStatus;
    }

    public int getPhone() {
        return phone;
    }

    public int getPoints() { return points; }

    public void setPoints(int points) {
        this.points = points;
    }
}
