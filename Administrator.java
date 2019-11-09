public class Administrator extends User {

    public Administrator(String name) {
        super(name);
    }

    public void block(Client cliente){
        cliente.block();
    }

    public void unblock(Client cliente){
        cliente.unBlock();
    }
}