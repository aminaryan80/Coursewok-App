package edu.sharif.courseworkapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.sharif.courseworkapp.utils.HomeworkImageUtils;

public class Homework {
    public static final String NAME = "Homeworks";
    private static final ArrayList<Homework> homeworks = new ArrayList<>();
    private final String id;
    private final int image;
    private final String courseId;
    private final String question;
    private String name;

    public Homework(String id, String name, String courseId, String question, int image) {
        this.id = id;
        this.name = name;
        this.courseId = courseId;
        this.question = question;
        this.image = image;
        homeworks.add(this);
    }

    public Homework(String name, String courseId, String question) {
        this.id = UUID.randomUUID().toString().substring(0, 4);
        this.name = name;
        this.courseId = courseId;
        this.question = question;
        this.image = HomeworkImageUtils.getRandomImage();
        homeworks.add(this);
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

    public int getImage() {
        return image;
    }

    public Course getCourse() {
        return Course.getCourseById(courseId);
    }

    public static Homework getHomeworkById(String homeworkId) {
        for (Homework homework : homeworks) {
            if (homework.id.equals(homeworkId)) {
                return homework;
            }
        }
        return null;
    }

    public static List<Homework> getCourseHomeworks(String courseId) {
        ArrayList<Homework> filtered = new ArrayList<>();
        for (Homework homework : homeworks) {
            if (homework.courseId.equals(courseId)) {
                filtered.add(homework);
            }
        }
        return filtered;
    }

    public static boolean checkExists(String homeworkName, String courseId) {
        List<Homework> homeworkList = getCourseHomeworks(courseId);
        for (Homework homework : homeworkList)
            if (homework.courseId.equals(courseId) && homework.name.equals(homeworkName))
                return true;

        return false;
    }

    public String encode() {
        return String.format("%s:%s:%s:%s", name, courseId, question, image);
    }

    public static String[] decode(String value) {
        return value.split(":");
    }
}