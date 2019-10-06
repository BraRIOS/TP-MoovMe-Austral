import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Scoring{
    private List<Discount> discounts;
    public Scoring(){
        discounts= new ArrayList<>();
    }
    public void addDiscount(Active active, int minPoints, int discount, Zone zone){
        discounts.add(new Discount(active, minPoints, discount, zone));
    }
    public void removeDiscount(Active active, Zone zone){
        Iterator<Discount> it = discounts.iterator();
        while(it.hasNext()) {
            Discount aDiscount= it.next();
            if (aDiscount.getActive().equals(active) && aDiscount.getZone().equals(zone))
                it.remove();
        }
    }
    public Discount findDiscount(Client aClient, Active anActive, Zone aZone){
        Iterator<Discount> it = discounts.iterator();
        while(it.hasNext()) {
            Discount aDiscount= it.next();
            if(aClient.getPoints() >= aDiscount.getMinPoints() && anActive.g.equals(descuento.getZone()) && activo.equals(descuento.getActivo())) {
                activo.setTarifa(activo.getTarifa() * descuento.getDiscount() / 100);
            }
    }
}
