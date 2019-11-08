import java.util.ArrayList;
import java.util.HashSet;

public class ABM <T> {
    
    private HashSet<T> memory = new HashSet<>();

    public void add(T instanceOfT){ memory.add(instanceOfT); }

    public void remove(T instanceOfT){ memory.remove(instanceOfT); }

    public void clear() {memory.clear();}

    public ArrayList<T> getList() {
        ArrayList<T> list = new ArrayList<>();
        for (T unT : memory) {
            list.add(unT);
        }
        return list;
    }

    public int size(){ return memory.size(); }

    public void modify(T aT,T otherT){
        memory.remove(aT);
        memory.add(otherT);
    }
}

