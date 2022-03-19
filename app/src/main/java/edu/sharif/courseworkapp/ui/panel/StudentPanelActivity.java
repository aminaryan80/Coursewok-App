package edu.sharif.courseworkapp.ui.panel;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import edu.sharif.courseworkapp.databinding.ActivityStudentPanelBinding;
import edu.sharif.courseworkapp.model.Course;

public class StudentPanelActivity extends UserPanelActivity {
    private ActivityStudentPanelBinding binding;

    private void setBinding() {
        binding = ActivityStudentPanelBinding.inflate(getLayoutInflater());
    }

    private void handleFab() {
        FloatingActionButton joinButton = binding.fab;
        joinButton.setOnClickListener(view -> goToJoinCourse());
    }

    private void handleRecyclerView() {
        RecyclerView courseRecyclerView = binding.idRecyclerViewCourseList;
        addDivider(courseRecyclerView);
        courseListAdapter = getCourseListAdapter();
        LinearLayoutManager verticalLayoutManager = getVerticalLayoutManager();
        courseRecyclerView.setLayoutManager(verticalLayoutManager);
        courseRecyclerView.setAdapter(courseListAdapter);
        populate();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBinding();
        setContentView(binding.getRoot());

        handleFab();
        handleRecyclerView();
    }

    private void goToJoinCourse() {
        Intent intent = new Intent(StudentPanelActivity.this, StudentJoinCourseActivity.class);
        intent.putExtra("username", getUsername());
        startActivity(intent);
    }

    @Override
    protected List<Course> populateCourseList() {
        return Course.getStudentCourses(getUsername());
    }
}