package edu.sharif.courseworkapp.model;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import edu.sharif.courseworkapp.model.user.Student;
import edu.sharif.courseworkapp.model.user.User;
import edu.sharif.courseworkapp.utils.CourseImageUtils;

public class Course {
    public static final String NAME = "Courses";
    private static final ArrayList<Course> courses = new ArrayList<>();
    private final int image;
    private final String id;
    private final String name;
    private final String professorId;
    private ArrayList<String> studentIds;

    public Course(String id, String courseName, String professorId, int image, ArrayList<String> studentIds) {
        this.id = id;
        this.name = courseName;
        this.professorId = professorId;
        this.studentIds = studentIds;
        this.image = image;
        courses.add(this);
    }

    public Course(String courseName, String professorId) {
        this.id = UUID.randomUUID().toString().substring(0, 4);
        this.name = courseName;
        this.professorId = professorId;
        this.studentIds = new ArrayList<>();
        this.image = CourseImageUtils.getRandomImage();
        courses.add(this);
    }

    public static ArrayList<String> decodeArrayList(String value) {
        String[] items = value.split(",");
        return new ArrayList<>(Arrays.asList(items));
    }

    public static List<Course> getStudentCourses(String username) {
        ArrayList<Course> filtered = new ArrayList<>();
        for (Course course : courses) {
            ArrayList<String> students = course.getStudents();
            for (String studentId : students) {
                if (studentId.equals(username)) {
                    filtered.add(course);
                }
            }
        }
        return filtered;
    }

    public static List<Course> getProfessorCourses(String username) {
        ArrayList<Course> filtered = new ArrayList<>();
        for (Course course : courses) {
            if (course.professorId.equals(username)) {
                filtered.add(course);
            }
        }
        return filtered;
    }

    public static List<Course> getAllCourses() {
        return courses;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProfessorId() {
        return professorId;
    }

    public String getProfessorName() {
        return User.getProfessorByUsername(professorId).getDisplayName();
    }

    public int getImage() {
        return image;
    }

    public ArrayList<String> getStudents() {
        return studentIds;
    }

    public void setStudents(ArrayList<String> studentIds) {
        this.studentIds = studentIds;
    }

    public void addStudent(String studentId) {
        Student student = Student.getStudentByUsername(studentId);
        if (student == null) {
            throw new Resources.NotFoundException("Student not found.");
        }
        this.studentIds.add(studentId);
    }

    public static Course getCourseById(String courseId) {
        for (Course course : courses) {
            if (course.id.equals(courseId)) {
                return course;
            }
        }
        return null;
    }

    public static String[] decode(String value) {
        return value.split(":");
    }

    public String encode() {
        String studentIdsString = encodeArrayList(studentIds);
        return String.format("%s:%s:%s:%s", name, professorId, image, studentIdsString);
    }

    private String encodeArrayList(ArrayList<String> arrayList) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < arrayList.size(); i++) {
            result.append(arrayList.get(i));
            if (i + 1 != arrayList.size())
                result.append(",");
        }
        return result.toString();
    }
}