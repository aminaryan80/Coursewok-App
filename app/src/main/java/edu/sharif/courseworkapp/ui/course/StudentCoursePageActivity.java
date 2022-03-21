package edu.sharif.courseworkapp.ui.course;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import edu.sharif.courseworkapp.databinding.ActivityStudentCoursePageBinding;
import edu.sharif.courseworkapp.model.Course;

public class StudentCoursePageActivity extends UserCoursePageActivity {
    private ActivityStudentCoursePageBinding binding;

    private void setBinding() {
        binding = ActivityStudentCoursePageBinding.inflate(getLayoutInflater());
    }

    private void handleToolbar() {
        String courseId = getCourseId();
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout collToolbar = binding.toolbarLayout;

        Course course = Course.getCourseById(courseId);
        assert course != null;
        collToolbar.setTitle(course.getName());
        collToolbar.setCollapsedTitleTextColor(Color.WHITE);
        collToolbar.setExpandedTitleColor(Color.WHITE);
    }

    private void handleRecyclerView() {
        RecyclerView courseRecyclerView = binding.idRecyclerViewHomeworkList;
        addDivider(courseRecyclerView);
        homeworkListAdapter = getHomeworkListAdapter();
        LinearLayoutManager verticalLayoutManager = getVerticalLayoutManager();
        courseRecyclerView.setLayoutManager(verticalLayoutManager);
        courseRecyclerView.setAdapter(homeworkListAdapter);
        populate();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBinding();
        setContentView(binding.getRoot());

        handleToolbar();
        handleRecyclerView();
    }
}