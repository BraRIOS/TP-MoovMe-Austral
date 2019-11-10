public class Administrator extends User {

    public Administrator(String name) {
        super(name);
    }

    public void block(Client client){
        client.block();
    }

    public void unblock(Client client){
        client.unBlock();
    }
}