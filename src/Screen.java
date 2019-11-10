import org.joda.time.DateTime;
import java.io.IOException;
import java.util.ArrayList;

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

    public static void mainScreen(MoovMe m) {
        startScreen();
        print("\t0. Salir.\n\t1. " + (activeUser.getAlias().equals("-----")? "Iniciar":"Cambiar") + " usuario.\n\t2. Cambiar hora.");
        print("\t3. Ver rankings. [NOT YET]");
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
            print("\t1. Administrador.\n\t2. Cliente.");
            int type = Scanner.getInt("Entrada: ");
            if (type == 1) {
                Administrator admin = new Administrator(Scanner.getString("Alias: "));
                m.getAdmins().add(admin);
                activeUser = admin;
                adminScreen(m);
            }
            if (type == 2) {
                Client client = new Client(Scanner.getString("Alias: "), Scanner.getInt("Telefono: "));
                m.getClients().add(client);
                activeUser = client;
                clientScreen(m);
            }
        }
        if (entry > 0 && entry <= adminCounter) {
            activeUser = m.getAdmins().getList().get(adminCounter-1);
            adminScreen(m);
        }
        if (entry > adminCounter && entry <= (adminCounter+clientCounter)) {
            activeUser = m.getClients().getList().get(clientCounter-1);
            clientScreen(m);
        }
        mainScreen(m);
    }

    public static void adminScreen(MoovMe m) {
        startScreen();
        print("\t0. Salir.\n\t1. Cambiar usuario.\n\t2. Crear zona.\n\t3. Crear terminal y asignar lote.");
        print("\t4. Ver Terminales.\n\t5. Bloquear o Desbloquear Cliente.\n\t6. Volver a Inicio.");
        int entry = Scanner.getInt("Entrada: ");
        if (entry == 0) exit();
        if (entry == 1) usersScreen(m);
        if (entry == 2) {
            if (m.getZones().size() > 0) {
                print("Zonas existentes:");
                for (Zone z : m.getZones().getList())
                    print("\t" + z.getName());
            } else print("\tNo hay Zonas existentes.");
            Zone zone = new Zone(Scanner.getString("Nombre de la Zona: "), Scanner.getInt("Incremento: "));
            m.getZones().add(zone);
            m.getTerminals().put(zone, new ArrayList<>());
            adminScreen(m);
        };
        if (entry == 3) {
            int zoneCounter = 0;
            if (m.getZones().size() > 0) {
                print("Elija una Zona para agregar terminal:");
                for(Zone z: m.getZones().getList())
                    print("\t" + zoneCounter++ + ". " + z.getName());
                int choose = Scanner.getInt("Entrada: ");
                if (choose >= 0 && choose < zoneCounter) {
                    Zone zoneChoose = m.getZones().getList().get(choose);
                    ParkingTerminal terminal = new ParkingTerminal(zoneChoose, new Batch(Scanner.getInt("Cantidad de Activos: "), new TypeOfAsset(Scanner.getString("Nombre del Activo: "), Scanner.getInt("Puntos Base del Activo: "))));
                    if (!m.getTerminals().containsKey(zoneChoose))
                        m.getTerminals().put(zoneChoose, new ArrayList<>());
                    m.getTerminals().get(zoneChoose).add(terminal);
                }
            } else print("\tNo hay Zonas existentes.");
            adminScreen(m);
        }
        if (entry == 4) {
            print("Terminales Existentes:");
            for(Zone z: m.getZones().getList()){
                print("Zona: " + z.getName() + "   Incremento: " + (Math.round((z.getIncrementPercent()-1)*100)) + "%");
                if (m.getTerminals().get(z).size() > 0) {
                    for (ParkingTerminal p : m.getTerminals().get(z))
                        print("\tActivo: " + p.getTypeOfAsset().getName() + "\tPuntos Base: " + p.getTypeOfAsset().getPoints() + "\tCantidad: " + p.getActives().size());
                } else print("\tNo hay terminales existentes.");
            }
            adminScreen(m);
        }
        if (entry == 5) {
            print("Lista de Clientes:");
            int clientCounter = 0;
            for(Client c: m.getClients().getList())
                print("\t" + clientCounter++ + ". " + c.getAlias() + "   [" + (c.isBlocked()? "Bloqueado":"Desbloqueado") + "]");
            int clientChoose = Scanner.getInt("Elija un Cliente: ");
            Client client = m.getClients().getList().get(clientChoose);
            if (clientChoose >= 0 && clientChoose < clientCounter) {
                print("\t0. Bloquear.\n\t1. Desbloquear.");
                int choose = Scanner.getInt("Entrada: ");
                if (choose == 0) client.block();
                if (choose == 1) client.unBlock();
            }
            adminScreen(m);
        }
        if (entry == 6) mainScreen(m);
    }

    public static void clientScreen(MoovMe m) {
        startScreen();
        print("\t0. Salir.\n\t1. Cambiar usuario.\n\t2. ");
        int entry = Scanner.getInt("Entrada: ");
        if (entry == 0) exit();
        if (entry == 1) usersScreen(m);
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
