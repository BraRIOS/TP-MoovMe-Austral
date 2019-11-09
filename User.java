import java.io.Serializable;

public class User implements Serializable {

    private String alias;
    private static final long serialVersionUID = 1234233342333L;

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
