package edu.sharif.courseworkapp.model;

import java.util.UUID;

public class Answer {

    private final String id;
    private final String studentId;
    private final String homeworkId;
    private String answer;

    public Answer(String studentId, String homeworkId, String answer) {
        this.id = UUID.randomUUID().toString().substring(0, 4);
        this.studentId = studentId;
        this.homeworkId = homeworkId;
        this.answer = answer;
    }

    public String getId() {
        return id;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getHomeworkId() {
        return homeworkId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
