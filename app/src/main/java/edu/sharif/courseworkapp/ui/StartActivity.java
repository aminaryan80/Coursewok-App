package edu.sharif.courseworkapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Map;

import edu.sharif.courseworkapp.model.Answer;
import edu.sharif.courseworkapp.model.Course;
import edu.sharif.courseworkapp.model.Homework;
import edu.sharif.courseworkapp.model.user.Professor;
import edu.sharif.courseworkapp.model.user.Student;
import edu.sharif.courseworkapp.ui.account.LoginActivity;

public class StartActivity extends AppCompatActivity {
    static boolean checkFirstTime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (checkFirstTime) {
            loadData();
            checkFirstTime = false;
        }

        Intent intent = new Intent(StartActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void loadData() {
        loadStudentData();
        loadProfessorData();
        loadCourseData();
        loadHomeworkData();
        loadAnswerData();
    }

    private void loadStudentData() {
        SharedPreferences studentSharedPreferences = getSharedPreferences(Student.NAME, MODE_PRIVATE);
        Map<String, ?> map = studentSharedPreferences.getAll();
        for (String key : map.keySet()) {
            String[] data = Student.decode(studentSharedPreferences.getString(key, ""));
            new Student(key, data[0], data[1], data[2], data[3]);
        }
    }

    private void loadProfessorData() {
        SharedPreferences professorSharedPreferences = getSharedPreferences(Professor.NAME, MODE_PRIVATE);
        Map<String, ?> map = professorSharedPreferences.getAll();
        for (String key : map.keySet()) {
            String[] data = Professor.decode(professorSharedPreferences.getString(key, ""));
            new Professor(key, data[0], data[1], data[2], data[3]);
        }
    }

    private void loadCourseData() {
        SharedPreferences courseSharedPreferences = getSharedPreferences(Course.NAME, MODE_PRIVATE);
        Map<String, ?> map = courseSharedPreferences.getAll();
        for (String key : map.keySet()) {
            String[] data = Course.decode(courseSharedPreferences.getString(key, ""));
            String studentIdsString = "";
            if (data.length == 4)
                studentIdsString = data[3];
            ArrayList<String> studentIds = Course.decodeArrayList(studentIdsString);
            new Course(key, data[0], data[1], Integer.parseInt(data[2]), studentIds);
        }
    }

    private void loadHomeworkData() {
        SharedPreferences homeworkSharedPreferences = getSharedPreferences(Homework.NAME, MODE_PRIVATE);
        Map<String, ?> map = homeworkSharedPreferences.getAll();
        for (String key : map.keySet()) {
            String[] data = Homework.decode(homeworkSharedPreferences.getString(key, ""));
            new Homework(key, data[0], data[1], data[2], Integer.parseInt(data[3]));
        }
    }

    private void loadAnswerData() {
        SharedPreferences answerSharedPreferences = getSharedPreferences(Answer.NAME, MODE_PRIVATE);
        Map<String, ?> map = answerSharedPreferences.getAll();
        for (String key : map.keySet()) {
            String[] data = Answer.decode(answerSharedPreferences.getString(key, ""));
            new Answer(key, data[0], data[1], data[2]);
        }
    }
}