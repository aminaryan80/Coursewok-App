package edu.sharif.courseworkapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.sharif.courseworkapp.utils.AnswerImageUtils;

public class Answer {
    public static final String NAME = "Answers";
    private static final ArrayList<Answer> answers = new ArrayList<>();
    private final String id;
    private final String studentId;
    private final String homeworkId;
    private final int image;
    private String answer;
    private String grade;


    public Answer(String id, String studentId, String homeworkId, String answer, String grade, int image) {
        this.id = id;
        this.studentId = studentId;
        this.homeworkId = homeworkId;
        this.answer = answer;
        this.grade = grade;
        this.image = image;
        answers.add(this);
    }

    public Answer(String studentId, String homeworkId, String answer) {
        this.id = UUID.randomUUID().toString().substring(0, 4);
        this.studentId = studentId;
        this.homeworkId = homeworkId;
        this.answer = answer;
        this.grade = "NG";
        this.image = AnswerImageUtils.getRandomImage();
        answers.add(this);
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getImage() {
        return image;
    }

    public static Answer getAnswerById(String answerId) {
        for (Answer answer : answers) {
            if (answer.id.equals(answerId)) {
                return answer;
            }
        }
        return null;
    }

    public static List<Answer> getHomeworkAnswers(String homeworkId) {
        ArrayList<Answer> filtered = new ArrayList<>();
        for (Answer answer : answers) {
            if (answer.homeworkId.equals(homeworkId)) {
                filtered.add(answer);
            }
        }
        return filtered;
    }

    public static boolean checkExists(String studentId, String homeworkId) {
        List<Answer> homeworkAnswerList = getHomeworkAnswers(homeworkId);
        for (Answer answer : answers)
            if (answer.homeworkId.equals(homeworkId) && answer.studentId.equals(studentId))
                return true;

        return false;
    }

    public static Answer getUniqueAnswer(String studentId, String homeworkId) {
        List<Answer> homeworkAnswerList = getHomeworkAnswers(homeworkId);
        for (Answer answer : answers)
            if (answer.homeworkId.equals(homeworkId) && answer.studentId.equals(studentId))
                return answer;

        return null;
    }

    public String encode() {
        return String.format("%s:%s:%s:%s:%s", studentId, homeworkId, answer, grade, image);
    }

    public static String[] decode(String value) {
        return value.split(":");
    }
}
