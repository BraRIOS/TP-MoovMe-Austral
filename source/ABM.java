import java.util.ArrayList;
import java.util.HashSet;

public class ABM <T> {
    
    ArrayList<T> unT = new ArrayList<>();

    public void add(T instanceOfT) throws RuntimeException {
        for (int i = 0; i < unT.size(); i++) {
            if (unT.get(i).equals(instanceOfT)) {
                throw new RuntimeException("Duplicated Element at index " + i);
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
        if (founded == false) throw new RuntimeException("Object Not Exist");
        if (unT.isEmpty()) throw new RuntimeException("The List Is Empty");
    }
    public void clear() {unT.clear();}

    public ArrayList<T> getList() {return unT;}

    public int size(){
        return unT.size();
    }

    public T get(int position) {
        return unT.get(position);
    }


}

