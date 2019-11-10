import org.joda.time.DateTime;

import javax.management.InstanceNotFoundException;
import java.io.*;

public class Main {

    public static void main(String[] args) {

        MoovMe system = new MoovMe();
        Screen.setTime(new DateTime(DateTime.now()));

        Screen.print("\n***** WELCOME TO MOOVME *****\n");

        Screen.firstScreen(system);
    }
}
