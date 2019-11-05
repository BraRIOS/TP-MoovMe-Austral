public abstract class User {
    private String alias;

    public User(String alias){
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }
    @Override
    public boolean equals(Object obj) {
        return this.getAlias().equals(((User) obj).getAlias());
    }
}
