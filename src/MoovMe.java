import java.util.ArrayList;
import java.util.HashMap;

public class MoovMe {

    private ABM<Client> clients;
    private ABM<Administrator> admins;
    private Scoring scoring;
    private ABM<Tariff> tariffs;
    private ABM<Zone> zones;
    private HashMap<Zone, ArrayList<ParkingTerminal>> terminals;

    public MoovMe() {
        clients = new ABM<>();
        admins = new ABM<>();
        scoring = new Scoring();
        tariffs = new ABM<>();
        zones = new ABM<>();
        terminals = new HashMap<>();

    }

    public ABM<Client> getClients() {
        return clients;
    }

    public ABM<Administrator> getAdmins() {
        return admins;
    }

    public Scoring getScoring() {
        return scoring;
    }

    public ABM<Tariff> getTariffs() {
        return tariffs;
    }

    public ABM<Zone> getZones() {
        return zones;
    }

    public HashMap<Zone, ArrayList<ParkingTerminal>> getTerminals() {
        return terminals;
    }
}