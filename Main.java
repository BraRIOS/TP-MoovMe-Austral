import org.joda.time.DateTime;

import javax.management.InstanceNotFoundException;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InstanceNotFoundException {

       // ObjectInputStream usersAMB = new ObjectInputStream(new FileInputStream("usersABM.file"));

       // MoovMe system = new MoovMe((ABM<User>) usersAMB.readObject());

       // system.addUser(new User("Mike"));

        //system.getUsers().clear();

        //Screen.printABM(system.getUsers());

        /*ABM<User> test = new ABM<>(0);
        ABM<Score> test1 = new ABM<>(1);

        FileOutputStream fos0 = new FileOutputStream("usersABM.file");
        ObjectOutputStream oos0 = new ObjectOutputStream(fos0);
        oos0.writeObject(test);

        FileOutputStream fos1 = new FileOutputStream("scoresABM.file");
        ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
        oos1.writeObject(test1);*/

        Zone tuquito=new Zone("Holi",20);

        Client bruno=new Client("Bruno",777);
        Client juan = new Client("juan",888);

        TypeOfAsset bici=new TypeOfAsset("Bicicleta",1000);
        Asset asset = new Asset(bici,1250);
        //bruno.addPointsToZone(tuquito,1000);
        //juan.addPointsToZone(tuquito,12000);

        System.out.println(bruno.getAlias() + "\t" + bruno.getPointsPerZone().get(tuquito));
        System.out.println(juan.getAlias() + "\t" + juan.getPointsPerZone().get(tuquito) + "\n");

        Scoring scoring = new Scoring();
        ABM<Client> clientABM = new ABM<>();
        clientABM.add(bruno);
        clientABM.add(juan);
        //for (Leader leader : scoring.getRankings(clientABM).get(tuquito)) {
          //  System.out.println(leader.getAlias() + "\t" + leader.getPoints());
       // }

       // bruno.addPointsToZone(tuquito,1000);
        juan.addPointsToZone(tuquito,12000);

        System.out.println("\n" + bruno.getAlias() + "\t" + bruno.getPointsPerZone().get(tuquito));
        System.out.println(juan.getAlias() + "\t" + juan.getPointsPerZone().get(tuquito) + "\n");

       // for (Leader leader : scoring.getRankings(clientABM).get(tuquito)) {
        //    System.out.println(leader.getAlias() + "\t" + leader.getPoints());
       // }

        scoring.getDiscounts().add(new Discount(new TypeOfAsset("Carreta",1000),100,20,tuquito));
        scoring.getDiscounts().add(new Discount(new TypeOfAsset("Dragones",10000),500,60,tuquito));
        scoring.getDiscounts().add(new Discount(bici,100,10,tuquito));
        System.out.println(juan.getPoints().getCurrentPoints());

        ABM<Tariff> tariffABM=new ABM<>();
        tariffABM.add(new Tariff(777,tuquito));
        Trip narnia=new Trip(tuquito);
        narnia.setEndTime(new DateTime(2019,11,10,23,00));
        narnia.FinishTrip(juan,asset,tariffABM,scoring.findDiscount(juan,asset,tuquito));
        System.out.println(juan.getPoints().getCurrentPoints());
        for (Discount discount : scoring.getDiscounts().getList()) {
            System.out.println(discount.getDiscount()+" "+discount.getMinPoints()+discount.getZone().getName()+discount.getType().getName());
        }

        System.out.println(asset.getType().getName()+" "+tuquito.getName()+" "+juan.getPoints().getCurrentPoints());

        System.out.println(scoring.findDiscount(juan,asset,tuquito).getDiscount());







    }
}
