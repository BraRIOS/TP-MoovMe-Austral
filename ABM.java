import java.util.ArrayList;

public class ABM <T> {
    
    ArrayList<T> unT = new ArrayList<>();

    public void add(T instanceOfT) throws RuntimeException {
        for (int i = 0; i < unT.size(); i++) {
            if (unT.get(i).equals(instanceOfT)) {
                throw new RuntimeException("Duplicated Element at index ");
            }
        }
            unT.add(instanceOfT);

        if (unT.isEmpty()) unT.add(instanceOfT);
    }

    public void remove(T instanceOfT) throws  RuntimeException{
        boolean founded = false;
        for (int i = 0; i < unT.size(); i++) {
            if (unT.get(i).equals(instanceOfT)){
                unT.remove(instanceOfT);
                founded = true;
            }
        }
        if (!founded) throw new RuntimeException("Object Not Exist");
        if (unT.isEmpty()) throw new RuntimeException("The List Is Empty");
    }
    public void clear() {unT.clear();}

    public ArrayList<T> getList() {return unT;}
}
