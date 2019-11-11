import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

public class ABM <T> implements Serializable {

    private static final long serialVersionUID = 1234567890L;
    
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