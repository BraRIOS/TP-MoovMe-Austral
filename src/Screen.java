import org.joda.time.DateTime;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class Screen {

    private static String line = "----------------------------------------";
    private static User activeUser = new User("-----");
    private static DateTime time = DateTime.now();

    public static void printABMClient(ABM<Client> toPrint) {
        print(line);
        for (Client aClient : toPrint.getList())
            print("Alias: " + aClient.getAlias() + "\t| Phone: " + aClient.getPhone());
        print(line);
    }

    public static void firstScreen(MoovMe m) {
        startScreen();
        print("\t1. " + (activeUser.getAlias().equals("-----")? "Iniciar":"Cambiar") + " usuario.\n\t2. Cambiar hora.\n\t0. Salir.");
        int entry = Scanner.getInt("Entrada: ");
        if (entry == 1) usersScreen(m);
        if (entry == 2) changeTimeScreen(m);
        if (entry == 0) exit();
    }

    private static void startScreen() {
        clrCmd();
        print("Usuario: " + activeUser.getAlias() + "\t\t Tiempo: " + printDateTime());
    }

    private static String printDateTime() {
        return time.toString().substring(0, 10) + " " + time.toString().substring(11,16);
    }

    public static void usersScreen(MoovMe m) {
        startScreen();
        int adminCounter = 0;
        int clientCounter = 0;
        print("\t0. Nuevo Usuario.");
        for(Administrator a: m.getAdmins().getList())
            print("\t" + ++adminCounter + ". [Admin] " + a.getAlias());
        for(Client c: m.getClients().getList())
            print("\t" + (++clientCounter+adminCounter) + ". [Client] " + c.getAlias() + "\tTelefono: " + c.getPhone());
        int entry = Scanner.getInt("Entrada: ");
        if (entry == 0) {
            print("\n\t1. Administrador.\n\t2. Cliente.");
            int type = Scanner.getInt("Entrada: ");
            if (type == 1) {
                Administrator admin = new Administrator(Scanner.getString("Alias: "));
                m.getAdmins().add(admin);
                activeUser = admin;
            }
            if (type == 2) {
                Client client = new Client(Scanner.getString("Alias: "), Scanner.getInt("Telefono: "));
                m.getClients().add(client);
                activeUser = client;
            }
        }
        if (entry > 0 && entry <= adminCounter) {
            activeUser = m.getAdmins().getList().get(adminCounter-1);
        }
        if (entry > adminCounter && entry <= (adminCounter+clientCounter)) {
            activeUser = m.getClients().getList().get(clientCounter-1);
        }
        firstScreen(m);
    }

    public static void changeTimeScreen(MoovMe m) {

    }

    public static void setTime(DateTime dateTime) {
        time = dateTime;
    }

    public static void print(Object o) {
        System.out.println(o);
    }

    public static void exit() {
        System.out.close();
    }

    public static void clrCmd() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                (new ProcessBuilder(new String[]{"cmd", "/c", "cls"})).inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (InterruptedException | IOException var1) {
        }
    }
}
