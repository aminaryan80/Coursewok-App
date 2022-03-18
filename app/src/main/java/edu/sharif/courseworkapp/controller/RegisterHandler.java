package edu.sharif.courseworkapp.controller;

import edu.sharif.courseworkapp.model.user.Professor;
import edu.sharif.courseworkapp.model.user.Student;
import edu.sharif.courseworkapp.model.user.User;

public class RegisterHandler {

    private final String username;
    private final String firstname;
    private final String lastname;
    private final String password;
    private final String extra;
    private final boolean isStudent;

    public RegisterHandler(
            String username, String password, String firstname,
            String lastname, String extra, boolean isStudent
    ) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.extra = extra;
        this.isStudent = isStudent;
    }

    public User register() {
        User result;
        if (this.isStudent) {
            result = register_student();
        } else {
            result = register_professor();
        }
        return result;
    }

    private User register_professor() {
        if (User.getProfessorByUsername(this.username) != null) {
            return null;
        }
        return new Professor(
                this.username,
                this.password,
                this.firstname,
                this.lastname,
                this.extra
        );
    }


    private User register_student() {
        if (User.getStudentByUsername(this.username) != null) {
            return null;
        }
        return new Student(
                this.username,
                this.password,
                this.firstname,
                this.lastname,
                this.extra
        );
    }
}
