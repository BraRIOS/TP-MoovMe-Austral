import java.io.Serializable;

public class Zone implements Serializable {

    private static final long serialVersionUID = 1230567890L;

    private String name;
    private double incrementPercent;

    public Zone(String name, int incrementPercent){
        this.name=name;
        if(incrementPercent>=0 && incrementPercent<=100) this.incrementPercent = 1 + incrementPercent/100d;
    }

    public String getName() {
        return name;
    }

    public double getIncrementPercent() {
        return incrementPercent;
    }

    @Override
    public boolean equals(Object obj) {
        return name.equals(((Zone) obj).getName());
    }
}
