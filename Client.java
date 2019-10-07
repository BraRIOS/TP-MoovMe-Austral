public class Client extends User {
    private boolean status;
    private boolean logged;
    private int phone;
    private int points;
    private String alias;

    public Client(String alias) {
        super(alias);
        this.status = true;
        this.logged = false;
        this.points = 0;
    }

    public String getStatus(){
        if(status){
            return "You are free to order";
        }
        return "You have been blocked";
    }

    void login(int newPhone){
        this.phone = newPhone;
        logged = true;
    }

    public void use(Active activo){
        points += activo.getPoints();
    }

    void setStatus(boolean newStatus){
        status = newStatus;
    }

    public int getPhone() {
        return phone;
    }

    public boolean getLogged(){
        return logged;
    }

    public String getAlias() {
        return alias;
    }

    public int getPoints() { return points; }

    public void setPoints(int points) {
        this.points = points;
    }
}
