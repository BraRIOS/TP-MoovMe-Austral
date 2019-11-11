import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 1234567800L;

    private String alias;

    public User(String alias){
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    @Override
    public boolean equals(Object obj) {
        return alias.equals(((User) obj).getAlias());
    }
}
