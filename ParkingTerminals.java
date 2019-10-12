import java.util.ArrayList;
import java.util.List;

public class ParkingTerminals {
    private List<Active> actives;
    private Zone zone;
    private String pickUpHour;
    private String returnHour;
    private boolean inStorage;

    public ParkingTerminals(Zone zone){
        this.zone = zone;
        actives = new ArrayList<>();
        inStorage = true;
    }

    public void pickUp(Client client, Active active, String pickUpHour, String returnHour){
        this.pickUpHour = pickUpHour;
        this.returnHour = returnHour;
        for (int i = 0; i < actives.size(); i++) {
            if(actives.get(i).equals(active)){
                System.out.println("Return at "+ returnHour);
                inStorage = false;
            }
        }
    }

    public void returnActive(Client client, Active active, String hour){
        inStorage = true;
        if(returnHour.equals(hour)){
            active.setPoints(active.getPoints() + active.getPoints() * (20 / 100));
        }
    }
}
