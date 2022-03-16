package edu.sharif.courseworkapp.model.user;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class User {
    public static final String PROFESSOR = "professor";
    public static final String STUDENT = "student";

    static ArrayList<User> users = new ArrayList<User>();
    private final String id;
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    protected String type;

    public User(String username, String password, String firstname, String lastname) {
        this.id = UUID.randomUUID().toString().substring(0, 4);
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        users.add(this);
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

    public boolean isStudent() {
        return this.type.equals(STUDENT);
    }

    public static Professor getProfessorByUsername(String username) {
        return (Professor) getUserByUsername(username, User.PROFESSOR);
    }

    public static Student getStudentByUsername(String username) {
        return (Student) getUserByUsername(username, User.STUDENT);
    }

    public static User getUserByUsername(String username, String type) {
        for (User user : users) {
            if (user.type.equals(type) && user.username.equals(username)) {
                return user;
            }
        }
        return null;
    }
}