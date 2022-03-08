package edu.sharif.courseworkapp.model.user;

import java.util.UUID;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class User {

    private final String id;
    private String username;
    private String firstname;
    private String lastname;
    private String password;

    public User(String username, String firstname, String lastname, String password) {
        this.id = UUID.randomUUID().toString().substring(0, 4);
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return firstname + " " + lastname;
    }
}