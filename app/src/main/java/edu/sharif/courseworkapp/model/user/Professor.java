package edu.sharif.courseworkapp.model.user;

public class Professor extends User {

    private String university;

    public Professor(
            String userId, String firstname, String lastname, String password, String university
    ) {
        super(userId, firstname, lastname, password);
        this.university = university;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
}
