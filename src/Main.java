import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ObjectInputStream usersAMB = new ObjectInputStream(new FileInputStream("usersABM.file"));

        MoovMe system = new MoovMe((ABM<User>) usersAMB.readObject());

        system.addUser(new User("Mike", "hell-o"));

        //system.getUsers().clear();

        Screen.printABM(system.getUsers());

        /*ABM<User> test = new ABM<>(0);
        ABM<Score> test1 = new ABM<>(1);

        FileOutputStream fos0 = new FileOutputStream("usersABM.file");
        ObjectOutputStream oos0 = new ObjectOutputStream(fos0);
        oos0.writeObject(test);

        FileOutputStream fos1 = new FileOutputStream("scoresABM.file");
        ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
        oos1.writeObject(test1);*/
    }
}
