package task2.v2.BusinessObjects;

public class User {

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String email) {
        this.username = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(final String email, final String password) {
        this.username = email;
        this.password = password;
    }
}
