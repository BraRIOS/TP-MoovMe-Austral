import org.joda.time.DateTime;

import javax.management.InstanceNotFoundException;
import java.util.Date;
import java.util.EmptyStackException;

public class Main {

    public static void main(String[] args) {

        MoovMe system = new MoovMe();
        Screen.setTime(new DateTime(DateTime.now()));

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
