import java.io.*;

public class MoovMe {

    private ABM<Client> clients;
    private ABM<Administrator> admins;

    public ABM<Client> getClients() {
        return clients;
    }

    public ABM<Administrator> getAdmins() {
        return admins;
    }

    public ABM<User> getUsers() {
        return users;
    }

    private ABM<User> users;

    public MoovMe(ABM<User> users) {
        this.users = users;
    }

    public void addUser(User u) throws IOException {
        users.add(u);
        Repositories.users.writeObject(users);
    }
}