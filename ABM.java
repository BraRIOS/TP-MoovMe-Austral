import java.util.ArrayList;
import java.util.HashSet;

public class ABM <T> {
    
    private HashSet<T> memory = new HashSet<>();

    public void add(T instanceOfT) {
        memory.add(instanceOfT);
    }

    public void remove(T instanceOfT){
        memory.remove(instanceOfT);
    }

    public ArrayList<T> getList() {
        return new ArrayList<>(memory);
    }

    public int size(){
        return memory.size();
    }

    public void modify(T aT,T otherT){
        memory.remove(aT);
        memory.add(otherT);
    }
}