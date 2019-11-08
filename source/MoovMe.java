
public class MoovMe {
    public static void main(String[] args) {
        Zone tuquito=new Zone("Holi",20);
        LeaderBoard m=new LeaderBoard(tuquito);
        Client bruno=new Client("tuquito",777);
        m.updateLeaders(bruno,100);
    }
}