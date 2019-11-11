import org.joda.time.DateTime;

import javax.management.InstanceNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EmptyStackException;

public class Screen {

    private static String line = "----------------------------------------";
    private static User activeUser = new User("-----");
    private static Client clientInUse = null;
    private static DateTime time = DateTime.now();

    public static void mainScreen(MoovMe m) throws InstanceNotFoundException, EmptyStackException {
        startScreen();
        print("\t0. Salir.\n\t1. " + (activeUser.getAlias().equals("-----")? "Iniciar":"Cambiar") + " usuario.\n\t2. Cambiar hora.");
        print("\t3. Ver rankings.");
        print("\t4. Entregar premios mensuales.");
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
            else if (choose == 1) time = new DateTime(year, Scanner.getInt("Mes: "), day, hour, minute);
            else if (choose == 2) time = new DateTime(year, month, Scanner.getInt("Dia: "), hour, minute);
            else if (choose == 3) time = new DateTime(year, month, day, Scanner.getInt("Hora: "), minute);
            else if (choose == 4) time = new DateTime(year, month, day, hour, Scanner.getInt("Minuto: "));
            else if (choose == 5) time = new DateTime(Scanner.getInt("Año: "),Scanner.getInt("Mes: "),Scanner.getInt("Dia: "),Scanner.getInt("Hora: "),Scanner.getInt("Minuto: "));
            else System.out.println("ENTRADA INVÁLIDA");
            mainScreen(m);
        }
        if (entry == 3) {
            boolean stateTrue=true;
            Zone zoneChoose=null;
            if (m.getZones().size() > 0) {
                do {
                    int zoneCounter = 0;
                    print("Elija una Zona: ");
                    for (Zone z : m.getZones().getList()){ print("\t" + zoneCounter++ + ". " + z.getName());}
                    int choose = Scanner.getInt("Entrada: ");
                    if (choose >= 0 && choose < zoneCounter) {
                        zoneChoose = m.getZones().getList().get(choose);
                        stateTrue = false;
                    } else print("Entrada invalida");
                } while (stateTrue);
                printRanking(m, zoneChoose);
            }
            else print("No hay zonas disponibles");mainScreen(m);

        }
        if (entry == 4) {m.getScoring().monthlyAwards(m.getClients());print("Se entregaron los premios del mes");mainScreen(m); }

        if (entry == 0) exit();
    }

    public static void usersScreen(MoovMe m) throws InstanceNotFoundException, EmptyStackException {
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

    public static void adminScreen(MoovMe m) throws InstanceNotFoundException, EmptyStackException {
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
        }
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
            if (m.getClients().size()==0) {print("\tPrimero se deben ingresar clientes al sistema");}
            else {
                for (Client c : m.getClients().getList())
                    print("\t" + clientCounter++ + ". " + c.getAlias() + "   [" + (c.isBlocked() ? "Bloqueado" : "Desbloqueado") + "]");
                int clientChoose = Scanner.getInt("Elija un Cliente: ");
                Client client = m.getClients().getList().get(clientChoose);
                if (clientChoose >= 0 && clientChoose < clientCounter) {
                    print("\t0. Bloquear.\n\t1. Desbloquear.");
                    int choose = Scanner.getInt("Entrada: ");
                    if (choose == 0) client.block();
                    if (choose == 1) client.unBlock();
                }
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

    public static void clientScreen(MoovMe m) throws InstanceNotFoundException, EmptyStackException {
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
            print("¿Desea definir una hora de entrega? (Se otorgara un bonus de puntos si se cumple con el plazo de entrega)\n\t0. Si.\n\t1. No.");
            int selection = Scanner.getInt("Entrada: ");
            if (selection==0) {
                ((Client) activeUser).getActualTrip().setDeliveryTime(new DateTime(time.getYear(),time.getMonthOfYear(),time.getDayOfMonth(),Scanner.getInt("Hora: "),Scanner.getInt("Minutos: ")));
                printDateTime(((Client) activeUser).getActualTrip().getDeliveryTime());
            }
            m.getAssetsInUse().put(((Client) activeUser), assetChoose);
            for(ParkingTerminal p: m.getTerminals().get(zoneChoose)) {
                if (p.getActives().contains(assetChoose)) {
                    p.getActives().remove(assetChoose);
                    m.getClients().modify(clientInUse, (Client) activeUser);
                    break;
                }
            }
            clientScreen(m);
        }
        if (entry == 3) {
            if(!((Client)activeUser).isInTrip()) print("Por favor inicie un viaje");
            else {
                print("Indique el tiempo de finalización");
                boolean state=true;
                do {
                   int hora= Scanner.getInt("Hora: ");
                   if (hora==0|hora==00)print("Se debe entregar el mismo dia");
                   else {state=true;
                setTime(new DateTime(time.getYear(),time.getMonthOfYear(),time.getDayOfMonth(),Scanner.getInt("Hora: "),Scanner.getInt("Minutos: ")));
                ((Client)activeUser).getActualTrip().setEndTime(time);}}while (state);

                try{
                    Discount discount = m.getScoring().findDiscount(((Client)activeUser),m.getAssetsInUse().get(((Client)activeUser)),((Client)activeUser).getActualTrip().getZone());
                    print("¿Hay un descuento disponible, desea usarlo?" + "Puntos requeridos: " +discount.getMinPoints() +"\t\tPuntos actuales: "+((Client)activeUser).getPoints().getCurrentPoints() + "\nDescuento: " + (Math.round((1 - discount.getDiscount())*100)) + "%\n\t0. Si.\n\t1. No.");
                    int selection = Scanner.getInt("Entrada: ");
                    if(selection==0){
                        Invoice invoice=((Client)activeUser).getActualTrip().FinishTrip((Client)activeUser,m.getAssetsInUse().get(((Client)activeUser)),m.getTariffs(), discount);
                        print("Hora de finalización: " + printDateTime(invoice.getEndTime()) +"\nPuntos adquiridos: " + invoice.getPointsAcquired() + "\nTotal: " + invoice.getFinalPrice());
                    }
                    if(selection==1){
                        Invoice invoice = ((Client)activeUser).getActualTrip().FinishTrip((Client)activeUser,m.getAssetsInUse().get(((Client)activeUser)),m.getTariffs(), null);
                        print("Hora de finalización: " + printDateTime(invoice.getEndTime()) +"\nPuntos adquiridos: " + invoice.getPointsAcquired() + "\nTotal: " + invoice.getFinalPrice());
                    }
                }
                catch (EmptyStackException | InstanceNotFoundException in){
                    Invoice invoice = ((Client)activeUser).getActualTrip().FinishTrip((Client)activeUser,m.getAssetsInUse().get(((Client)activeUser)),m.getTariffs(), null);
                    print("Hora de finalización: " + printDateTime(invoice.getEndTime()) +"\nPuntos adquiridos: " + invoice.getPointsAcquired() + "\nTotal: " + invoice.getFinalPrice());
                }
                for(ParkingTerminal p: m.getTerminals().get(((Client)activeUser).getActualTrip().getZone())) {
                    if (p.getActives().contains(m.getAssetsInUse().get(((Client)activeUser)))) {
                        p.getActives().add(m.getAssetsInUse().get(((Client)activeUser)));
                        m.getClients().modify(clientInUse, (Client) activeUser);
                        break;
                    }
                }
            }
        }
    }

    private static void startScreen() {
        clrCmd();
        print("Usuario: " + activeUser.getAlias() + "\t\t Tiempo: " + printTime());
    }

    private static String printTime() {
        return time.toString().substring(0, 10) + " " + time.toString().substring(11,16);
    }
    private static String printDateTime(DateTime time) {
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

    public static void printRanking(MoovMe m,Zone aZone){

            print(aZone.getName()+"\n");
        ArrayList<Leader> rankByZone = m.getScoring().getRankings(m.getClients()).get(aZone);
        for (int i = 0; i < rankByZone.size(); i++) {
                    if (i<10)print(i+"."+" "+ rankByZone.get(i).getAlias()+"\tPuntos: "+rankByZone.get(i).getPoints());
                    else break;
                }
            }

}
