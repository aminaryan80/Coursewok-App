package edu.sharif.courseworkapp.model.user;

public class Student extends User {

    private String studentNumber;

    public Student(
            String userId, String firstname, String lastname, String password, String studentNumber
    ) {
        super(userId, firstname, lastname, password);
        this.studentNumber = studentNumber;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }
}
