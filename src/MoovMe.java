public class MoovMe {

    private ABM<Client> clients;
    private ABM<Administrator> admins;

    public MoovMe() {
        clients = new ABM<>();
        admins = new ABM<>();
    }

    public ABM<Client> getClients() {
        return clients;
    }

    public ABM<Administrator> getAdmins() {
        return admins;
    }
}