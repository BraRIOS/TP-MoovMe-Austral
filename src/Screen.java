import org.joda.time.DateTime;
import java.io.IOException;
import java.util.ArrayList;

public class Screen {

    private static String line = "----------------------------------------";
    private static User activeUser = new User("-----");
    private static Client clientInUse = null;
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
        if (entry == 2) {
            print("\t0. Cambiar Año.\n\t1. Cambiar Mes.\n\t2. Cambiar Dia.\n\t3. Cambiar Hora.\n\t4. Cambiar Minuto.\n\t5. Cambio Completo.");
            int choose = Scanner.getInt("Entrada: ");
            int year = time.getYear();
            int month = time.getMonthOfYear();
            int day = time.getDayOfMonth();
            int hour = time.getHourOfDay();
            int minute = time.getMinuteOfHour();
            if (choose == 0) time = new DateTime(Scanner.getInt("Año: "), month, day, hour, minute);
            if (choose == 1) time = new DateTime(year, Scanner.getInt("Mes: "), day, hour, minute);
            if (choose == 2) time = new DateTime(year, month, Scanner.getInt("Dia: "), hour, minute);
            if (choose == 3) time = new DateTime(year, month, day, Scanner.getInt("Hora: "), minute);
            if (choose == 4) time = new DateTime(year, month, day, hour, Scanner.getInt("Minuto: "));
            if (choose == 5) time = new DateTime(Scanner.getInt("Año: "),Scanner.getInt("Mes: "),Scanner.getInt("Dia: "),Scanner.getInt("Hora: "),Scanner.getInt("Minuto: "));
            mainScreen(m);
        }
        if (entry == 0) exit();
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
                clientInUse = client;
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
        print("\t4. Ver Terminales.\n\t5. Bloquear o Desbloquear Cliente.\n\t6. Agregar Descuento.");
        print("\t7. Ver Descuentos.\n\t8. Volver a Inicio.");
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
            double price = Scanner.getDouble("Ingrese el precio de la Tarifa de la Zona: ");
            m.getTariffs().add(new Tariff(price, zone));
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
        if (entry == 6) {
            int typeCounter = 0;
            ArrayList<TypeOfAsset> typeOfAssets = new ArrayList<>();
            ArrayList<Zone> zones = new ArrayList<>();
            print("Elija un tipo de Activo: ");
            for(Zone z: m.getZones().getList()) {
                if (m.getTerminals().get(z).size() > 0) {
                    if(!typeOfAssets.contains(m.getTerminals().get(z).get(0).getTypeOfAsset())) {
                        typeOfAssets.add(m.getTerminals().get(z).get(0).getTypeOfAsset());
                        typeCounter++;
                    }
                }
            }
            if (typeCounter == 0) {
                print("\tNo existen tipos de Activos, por favor cree lotes.");
            } else {
                typeCounter = 0;
                for(TypeOfAsset t: typeOfAssets)
                    print("\t" + typeCounter++ + ". " + t.getName());
                int choose = Scanner.getInt("Entrada: ");
                if (choose >= 0 && choose < typeCounter) {
                    int zoneWithAsset = 0;
                    print("Elija una Zona para el Descuento:");
                    for(Zone z: m.getZones().getList()) {
                        for(ParkingTerminal p: m.getTerminals().get(z)) {
                            if (p.getTypeOfAsset().equals(typeOfAssets.get(choose))) {
                                print("\t" + zoneWithAsset++ + ". " + z.getName());
                                zones.add(z);
                                break;
                            }
                        }
                    }
                    int zoneChoose = Scanner.getInt("Entrada: ");
                    m.getScoring().getDiscounts().add(new Discount(typeOfAssets.get(choose), Scanner.getInt("Puntos para aplicar Descuento: "), Scanner.getInt("Porcentaje: "), zones.get(zoneChoose)));
                }
            }
            adminScreen(m);
        }
        if (entry == 7) {
            for(Discount d: m.getScoring().getDiscounts().getList())
                print("Zona: " + d.getZone().getName() + "   Activo: " + d.getType().getName() + "   Puntos: " + d.getMinPoints() + "   Porcentaje: " + (Math.round((1 - d.getDiscount())*100)) + "%");
            adminScreen(m);
        }
        if (entry == 8) mainScreen(m);
    }

    public static void clientScreen(MoovMe m) {
        startScreen();
        print("\t0. Salir.\n\t1. Cambiar usuario.\n\t2. Iniciar Viaje.\n\t3. Finalizar Viaje.");
        int entry = Scanner.getInt("Entrada: ");
        if (entry == 0) exit();
        if (entry == 1) usersScreen(m);
        if (entry == 2) {
            print("Elija una zona:");
            int zoneCounter = 0;
            int assetCounter = 0;
            ArrayList<Asset> assets = new ArrayList<>();
            for(Zone z: m.getZones().getList())
                print("\t" + zoneCounter++ + ". " + z.getName());
            int choose = Scanner.getInt("Entrada: ");
            Zone zoneChoose = m.getZones().getList().get(choose);
            for(ParkingTerminal p: m.getTerminals().get(zoneChoose)) {
                if (p.getActives().size() > 0)
                    if (!assets.contains(p.getActives().get(0)))
                        assets.add(p.getActives().get(0));
            }
            for(Asset a: assets) {
                print("\t" + assetCounter++ + ". " + a.getType().getName());
            }
            int typeChoose = Scanner.getInt("Elija un Activo: ");
            Asset assetChoose = assets.get(typeChoose);
            ((Client) activeUser).alquilar(new Trip(zoneChoose, time));
            m.getAssetsInUse().put(((Client) activeUser), assetChoose);
            for(ParkingTerminal p: m.getTerminals().get(zoneChoose)) {
                if (p.getActives().contains(assetChoose)) {
                    p.getActives().remove(assetChoose);
                    m.getClients().modify(clientInUse, (Client) activeUser);
                    break;
                }
            }
        }
        if (entry == 4) {
            Client manageClient = m.getClients().getList().get(m.getClients().getList().indexOf(activeUser));
            if(manageClient.isInTrip())
                manageClient.getActualTrip().FinishTrip(manageClient,m.getAssetsInUse().get(manageClient),m.getTariffs(), null);
            activeUser = manageClient;
        }
    }

    private static void startScreen() {
        clrCmd();
        print("Usuario: " + activeUser.getAlias() + "\t\t Tiempo: " + printDateTime());
    }

    private static String printDateTime() {
        return time.toString().substring(0, 10) + " " + time.toString().substring(11,16);
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
                (new ProcessBuilder("cmd", "/c", "cls")).inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (InterruptedException | IOException var1) {
        }
    }
}
