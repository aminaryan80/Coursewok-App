package edu.sharif.courseworkapp.data.model.course;

public class HomeWork {

    private String name;
    private final Course course;
    private final String question;

    public HomeWork(String name, Course course, String question) {
        this.name = name;
        this.course = course;
        this.question = question;
    }

    public String getName() {
        return name;
    }

    public Course getCourse() {
        return course;
    }

    public String getQuestion() {
        return question;
    }

    public void SetName(String name) {
        this.name = name;
    }
}