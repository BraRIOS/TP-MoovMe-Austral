public class RepositoryZones {

    private ABM<Zone> zones;

    public RepositoryZones(){
        zones = new ABM<>();
    }

    public void addZone(Zone aZone){
        zones.add(aZone);
    }
    public void removeZone(Zone aZone){
        zones.remove(aZone);
    }
}
