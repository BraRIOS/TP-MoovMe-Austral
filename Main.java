
import javax.management.InstanceNotFoundException;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InstanceNotFoundException {

        MoovMe system = new MoovMe();

        while (true) {
            int entry = Scanner.getInt("Como desea Iniciar MoovMe:\n\t0. Cargar datos existentes.\n\t1. Empezar con nuevos datos.\nEntrada: ");
            if (entry == 0) {
                FileInputStream fis = new FileInputStream("dataRestore.file");
                ObjectInputStream ois = new ObjectInputStream(fis);
                system = (MoovMe) ois.readObject();
                break;
            }
            if (entry == 1) {
                FileOutputStream fos = new FileOutputStream("dataRestore.file");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(system);
                break;
            }
            if (entry != 0 && entry != 1) Screen.print("Entrada invalida: ");
        }

        Screen.print("\n***** WELCOME TO MOOVME *****\n");

        Screen.mainScreen(system);
    }
}
