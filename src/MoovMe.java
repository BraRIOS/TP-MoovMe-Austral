import org.joda.time.DateTime;

public class MoovMe {
    public static void main(String[] args) {

        ABM<Client> test = new ABM<>();
        test.add(new Client("Ignacio", 48372910));
        test.add(new Client("Miguel", 48826197));
        test.add(new Client("Juan", 48165267));
        test.add(new Client("Braian", 48862512));

        Screen.printABM(test);
    }
}