package edu.sharif.courseworkapp.ui.panel;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.sharif.courseworkapp.R;
import edu.sharif.courseworkapp.model.Course;

public class StudentJoinCourseActivity extends UserPanelActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_join_course);

        RecyclerView courseRecyclerView = findViewById(R.id.idRecyclerViewCourseList);
        addDivider(courseRecyclerView);
        courseListAdapter = getNewCourseListAdapter();
        LinearLayoutManager verticalLayoutManager = getVerticalLayoutManager();
        courseRecyclerView.setLayoutManager(verticalLayoutManager);
        courseRecyclerView.setAdapter(courseListAdapter);
        populate();
    }

    @Override
    protected List<Course> populateCourseList() {
        List<Course> allCourses = Course.getAllCourses();
        List<Course> studentCourses = Course.getStudentCourses(getUsername());
        ArrayList<Course> newCourses = new ArrayList<>(allCourses);
        newCourses.removeAll(studentCourses);
        return newCourses;
    }
}