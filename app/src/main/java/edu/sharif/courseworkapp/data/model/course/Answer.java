package edu.sharif.courseworkapp.data.model.course;

import edu.sharif.courseworkapp.data.model.user.Student;

public class Answer {

    private final Student student;
    private final HomeWork homeWork;
    private String answer;

    public Answer(Student student, HomeWork homeWork, String answer) {
        this.student = student;
        this.homeWork = homeWork;
        this.answer = answer;
    }

    public Student getStudent() {
        return student;
    }

    public HomeWork getHomeWork() {
        return homeWork;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
