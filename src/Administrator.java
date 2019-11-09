public class Administrator extends User {

    public Administrator(String name, String password) {
        super(name, password);
    }

    public void block(Client cliente){
        cliente.block();
    }

    public void unblock(Client cliente){
        cliente.unBlock();
    }
}