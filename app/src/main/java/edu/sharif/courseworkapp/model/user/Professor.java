package edu.sharif.courseworkapp.model.user;

public class Professor extends User {

    private String university;

    public Professor(
            String username, String password, String firstname, String lastname, String university
    ) {
        super(username, password, firstname, lastname);
        this.university = university;
        this.type = User.PROFESSOR;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
}
