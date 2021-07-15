package contas_bancarias;

public class Manager {
    private final String name;
    private final String password;

    public Manager(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
