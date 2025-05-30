package nl.han.oose.dea.mitchell.domain.dto.login;

public class Credentials {
    private String user;
    private String password;

    public Credentials(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public Credentials() {
        user = "";
        password = "";
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
