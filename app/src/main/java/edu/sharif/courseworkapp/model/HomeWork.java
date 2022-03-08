package edu.sharif.courseworkapp.model;

import java.util.UUID;

public class HomeWork {

    private final String id;
    private final String courseId;
    private final String question;
    private String name;

    public HomeWork(String name, String courseId, String question) {
        this.id = UUID.randomUUID().toString().substring(0, 4);
        this.name = name;
        this.courseId = courseId;
        this.question = question;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getQuestion() {
        return question;
    }

    public void SetName(String name) {
        this.name = name;
    }
}