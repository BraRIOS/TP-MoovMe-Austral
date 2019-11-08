public class Screen {

    private static String line = "----------------------------------------";

    public static void printABM(ABM<Client> toPrint) {
        print(line);
        for (Client aClient: toPrint.getList())
            print("Alias: " + aClient.getAlias() + "\t| Phone: " + aClient.getPhone());
        print(line);
    }

    public static void print(Object o) { System.out.println(o); }
}
