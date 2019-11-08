import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Repositories {

    public static ObjectOutputStream users;

    static {
        try {
            users = new ObjectOutputStream(new FileOutputStream("usersABM.file"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
