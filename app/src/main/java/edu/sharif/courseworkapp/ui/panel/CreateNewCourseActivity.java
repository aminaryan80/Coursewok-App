package edu.sharif.courseworkapp.ui.panel;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import edu.sharif.courseworkapp.R;
import edu.sharif.courseworkapp.model.Course;

public class CreateNewCourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_course);

        TextView courseNameTextView = findViewById(R.id.idCourseName);
        Button newCourseButton = findViewById(R.id.idNewCourseButton);

        newCourseButton.setOnClickListener(view -> createNewCourse(courseNameTextView.getText().toString()));
    }

    private String getUsername() {
        return getIntent().getStringExtra("username");
    }

    private void createNewCourse(String courseName) {
        String username = getUsername();
        List<Course> courses = Course.getProfessorCourses(username);
        for (Course course : courses) {
            if (course.getName().equals(courseName)) {
                Toast.makeText(CreateNewCourseActivity.this,
                        "Course with this name already exists for you!", Toast.LENGTH_LONG).show();
                return;
            }
        }
        Course newCourse = new Course(courseName, username);
        saveCourse(newCourse);
        finish();
    }

    private void saveCourse(Course course) {
        SharedPreferences.Editor sharedPreferencesEditor = getSharedPreferences("Courses", MODE_PRIVATE).edit();
        ;
        sharedPreferencesEditor.putString(course.getId(), course.encode());
        sharedPreferencesEditor.apply();
    }
}