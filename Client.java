public class Client extends User {
    private boolean status;
    private boolean loged;
    private int phone;
    private double points;
    private String alias;

    public Client(String alias) {
        super(alias);
        this.status = true;
        this.loged = false;
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
        loged = true;
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

    public boolean getLoged(){
        return loged;
    }

    public double getPoints() { return points; }
}
