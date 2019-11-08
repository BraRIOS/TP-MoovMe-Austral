import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

public class ABM <T> implements Serializable {
    
    private HashSet<T> memory = new HashSet<>();
    private long serialVersionUID;

    public ABM(int counter) {
        serialVersionUID = 1000L + counter;
    }

    public ABM() {}

    public void add(T instanceOfT) {
        memory.add(instanceOfT);
    }

    public void remove(T instanceOfT){
        memory.remove(instanceOfT);
    }

    public void clear() {
        memory.clear();
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

    public long getSerialVersionUID() {
        return serialVersionUID;
    }
}