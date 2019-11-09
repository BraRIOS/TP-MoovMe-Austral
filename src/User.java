import java.io.Serializable;

public class User implements Serializable {

    private String alias;
    private String password;
    private static final long serialVersionUID = 1234233342333L;

    public User(String alias, String password){
        this.alias = alias;
        this.password = password;
    }

    public String getAlias() {
        return alias;
    }

    public String getPassword() { return password; }

    public void changePassword(String password) { this.password = password; }

    @Override
    public boolean equals(Object obj) {
        if (!this.getAlias().equals(((User) obj).getAlias()))
            return false;
        return this.getPassword().equals(((User) obj).getPassword());
    }
}
