package edu.sharif.courseworkapp.data.model.user;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class User {

    private final String userId;
    private String firstname;
    private String lastname;
    private String password;

    public User(String userId, String firstname, String lastname, String password) {
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
    }

    public String getUserId() {
        return userId;
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