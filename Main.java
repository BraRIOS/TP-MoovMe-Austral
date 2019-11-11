import org.joda.time.DateTime;

import javax.management.InstanceNotFoundException;
import java.io.*;
import java.util.EmptyStackException;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        FileInputStream fis = new FileInputStream("dataRestore.file");
        ObjectInputStream ois = new ObjectInputStream(fis);

        MoovMe system = (MoovMe) ois.readObject();

        /*MoovMe system = new MoovMe();

        FileOutputStream fos = new FileOutputStream("dataRestore.file");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(system);*/

        Screen.print("\n***** WELCOME TO MOOVME *****\n");

        try {
            Screen.mainScreen(system);
        }catch (InstanceNotFoundException e){
            System.out.println("otaku");
        }
        catch (EmptyStackException e){
            System.out.println("nazi");
        }
    }
}
