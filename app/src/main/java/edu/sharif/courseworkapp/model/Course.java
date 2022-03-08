package edu.sharif.courseworkapp.model;

import java.util.ArrayList;
import java.util.UUID;

public class Course {

    private final String id;
    private final String name;
    private final String professorId;
    private ArrayList<String> studentIds;

    public Course(String courseName, String professorId, ArrayList<String> studentIds) {
        this.id = UUID.randomUUID().toString().substring(0, 4);
        this.name = courseName;
        this.professorId = professorId;
        this.studentIds = studentIds;
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

    public ArrayList<String> getStudents() {
        return studentIds;
    }

    public void setStudents(ArrayList<String> studentIds) {
        this.studentIds = studentIds;
    }

    public void addStudent(String studentId) {
        this.studentIds.add(studentId);
    }
}
