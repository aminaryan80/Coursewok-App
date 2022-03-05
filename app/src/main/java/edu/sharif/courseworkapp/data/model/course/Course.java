package edu.sharif.courseworkapp.data.model.course;

import java.util.ArrayList;

import edu.sharif.courseworkapp.data.model.user.Professor;
import edu.sharif.courseworkapp.data.model.user.Student;

public class Course {

    private final String name;
    private final Professor professor;
    private ArrayList<Student> students;

    public Course(String courseName, Professor professor, ArrayList<Student> students) {
        this.name = courseName;
        this.professor = professor;
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public Professor getProfessor() {
        return professor;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }
}
