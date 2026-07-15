package p;

import java.util.*;

public abstract class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
 
    public String getPassword() {
        return password;
    }

    public abstract String getRole();

    @Override
    public String toString() {
        return getRole() + "{" +
                "username='" + username + '\'' +
                '}';
    }
}
