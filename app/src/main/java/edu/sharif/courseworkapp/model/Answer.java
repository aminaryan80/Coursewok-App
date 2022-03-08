package edu.sharif.courseworkapp.model;

import java.util.UUID;

public class Answer {

    private final String id;
    private final String studentId;
    private final String homeWorkId;
    private String answer;

    public Answer(String studentId, String homeWorkId, String answer) {
        this.id = UUID.randomUUID().toString().substring(0, 4);
        this.studentId = studentId;
        this.homeWorkId = homeWorkId;
        this.answer = answer;
    }

    public String getId() {
        return id;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getHomeWorkId() {
        return homeWorkId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
