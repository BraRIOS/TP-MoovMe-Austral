import java.util.ArrayList;
import java.util.List;

class ABM <T> {
    
    ArrayList<T> unT;

    public ABM() {
        unT = new ArrayList<>();
    }
    
    public void add(T instanceOfT) throws RuntimeException{
        for (T t: unT) {
            if (t.equals(instanceOfT)) unT.add(instanceOfT);
            else throw new RuntimeException("Duplicated Element");
        }
    }

    public void remove(T instanceOfT) throws  RuntimeException{
        for (T t: unT) {
            if (t.equals(instanceOfT)) unT.remove(instanceOfT);
            else throw new RuntimeException("Object Not Exist");
        }
    }

    public void clear() {unT.clear();}

    public void edit() {}

    public ArrayList<T> getList() {return unT;}
}
