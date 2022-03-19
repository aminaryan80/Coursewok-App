package edu.sharif.courseworkapp.ui.panel;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import edu.sharif.courseworkapp.databinding.ActivityProfessorPanelBinding;
import edu.sharif.courseworkapp.model.Course;

public class ProfessorPanelActivity extends UserPanelActivity {
    private ActivityProfessorPanelBinding binding;

    private void setBinding() {
        binding = ActivityProfessorPanelBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBinding();
        setContentView(binding.getRoot());

        FloatingActionButton addButton = binding.fab;
        addButton.setOnClickListener(view -> goToCourseNewClass()
        );

        RecyclerView courseRecyclerView = binding.idRecyclerViewCourseList;
        addDivider(courseRecyclerView);
        courseListAdapter = getCourseListAdapter();
        LinearLayoutManager verticalLayoutManager = getVerticalLayoutManager();
        courseRecyclerView.setLayoutManager(verticalLayoutManager);
        courseRecyclerView.setAdapter(courseListAdapter);
        populate();
    }

    private void goToCourseNewClass() {
        Intent intent = new Intent(ProfessorPanelActivity.this, CreateNewCourseActivity.class);
        intent.putExtra("username", getUsername());
        startActivity(intent);
    }

    @Override
    protected List<Course> populateCourseList() {
        return Course.getProfessorCourses(getUsername());
    }
}