package edu.sharif.courseworkapp.ui.panel;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.sharif.courseworkapp.databinding.ActivityStudentJoinCourseBinding;
import edu.sharif.courseworkapp.model.Course;

public class StudentJoinCourseActivity extends UserPanelActivity {
    private ActivityStudentJoinCourseBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setBinding();
        setContentView(binding.getRoot());

        RecyclerView courseRecyclerView = binding.idRecyclerViewCourseList;
        addDivider(courseRecyclerView);
        courseListAdapter = getNewCourseListAdapter();
        LinearLayoutManager verticalLayoutManager = getVerticalLayoutManager();
        courseRecyclerView.setLayoutManager(verticalLayoutManager);
        courseRecyclerView.setAdapter(courseListAdapter);
        populate();
    }

    private void setBinding() {
        binding = ActivityStudentJoinCourseBinding.inflate(getLayoutInflater());
    }

    private LinearLayoutManager getVerticalLayoutManager() {
        return new LinearLayoutManager(
                StudentJoinCourseActivity.this,
                LinearLayoutManager.VERTICAL,
                false
        );
    }

    private void addDivider(RecyclerView courseRecyclerView) {
        courseRecyclerView.addItemDecoration(
                new DividerItemDecoration(
                        StudentJoinCourseActivity.this,
                        LinearLayoutManager.VERTICAL
                )
        );
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