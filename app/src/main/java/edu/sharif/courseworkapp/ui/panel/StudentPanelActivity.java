package edu.sharif.courseworkapp.ui.panel;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import edu.sharif.courseworkapp.R;
import edu.sharif.courseworkapp.databinding.ActivityStudentPanelBinding;
import edu.sharif.courseworkapp.model.Course;

public class StudentPanelActivity extends UserPanelActivity {
    private ActivityStudentPanelBinding binding;

    private void setBinding() {
        binding = ActivityStudentPanelBinding.inflate(getLayoutInflater());
    }

    private LinearLayoutManager getVerticalLayoutManager() {
        return new LinearLayoutManager(
                StudentPanelActivity.this,
                LinearLayoutManager.VERTICAL,
                false
        );
    }

    private void addDivider(RecyclerView courseRecyclerView) {
        courseRecyclerView.addItemDecoration(
                new DividerItemDecoration(
                        StudentPanelActivity.this,
                        LinearLayoutManager.VERTICAL
                )
        );
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setBinding();
        setContentView(binding.getRoot());

        FloatingActionButton joinButton = findViewById(R.id.fab);
        joinButton.setOnClickListener(view -> goToJoinCourse());

        RecyclerView courseRecyclerView = binding.idRecyclerViewCourseList;
        addDivider(courseRecyclerView);
        courseListAdapter = getCourseListAdapter();
        LinearLayoutManager verticalLayoutManager = getVerticalLayoutManager();
        courseRecyclerView.setLayoutManager(verticalLayoutManager);
        courseRecyclerView.setAdapter(courseListAdapter);
        populate();
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