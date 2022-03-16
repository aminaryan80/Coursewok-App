package edu.sharif.courseworkapp.controller;

import android.app.Activity;
import android.widget.Toast;

import edu.sharif.courseworkapp.model.user.Professor;
import edu.sharif.courseworkapp.model.user.Student;
import edu.sharif.courseworkapp.model.user.User;

public class RegisterHandler {

    private final Activity current_activity;
    private final String username;
    private final String firstname;
    private final String lastname;
    private final String password;
    private final String extra;
    private final boolean isStudent;

    public RegisterHandler(
            Activity activity, String username, String password, String firstname,
            String lastname, String extra, boolean isStudent
    ) {
        this.current_activity = activity;
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
        if (result != null) {
            Toast.makeText(current_activity, "REGISTER SUCCESSFUL", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(current_activity, "REGISTER FAILED!", Toast.LENGTH_SHORT).show();
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
