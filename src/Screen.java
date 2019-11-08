public class Screen {

    private static String line = "----------------------------------------";
    private static boolean firstStart = false;
    public static int counterABM = 0;

    public static void printABMClient(ABM<Client> toPrint) {
        print(line);
        for (Client aClient : toPrint.getList())
            print("Alias: " + aClient.getAlias() + "\t| Phone: " + aClient.getPhone());
        print(line);
    }

    public static  void printABM(ABM<User> toPrint) {
        print(line);
        for (User u: toPrint.getList())
            printUser(u);
        print(line);
    }

    public static void printUser(User u) {
        print("Alias: " + u.getAlias() + "\t| Password: " + u.getPassword());
    }

    public static void print(Object o) { System.out.println(o); }
}
