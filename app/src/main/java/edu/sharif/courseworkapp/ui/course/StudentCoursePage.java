package edu.sharif.courseworkapp.ui.course;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import edu.sharif.courseworkapp.databinding.ActivityStudentCoursePageBinding;

public class StudentCoursePage extends UserCoursePage {
    private ActivityStudentCoursePageBinding binding;

    private void setBinding() {
        binding = ActivityStudentCoursePageBinding.inflate(getLayoutInflater());
    }

    private LinearLayoutManager getVerticalLayoutManager() {
        return new LinearLayoutManager(
                StudentCoursePage.this,
                LinearLayoutManager.VERTICAL,
                false
        );
    }

    private void addDivider(RecyclerView courseRecyclerView) {
        courseRecyclerView.addItemDecoration(
                new DividerItemDecoration(
                        StudentCoursePage.this,
                        LinearLayoutManager.VERTICAL
                )
        );
    }

    private void handleToolbar() {
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle("Course Name");
    }

    private void handleViews() {
        TextView profName = binding.idProfessorName;
        profName.setText("Prof Name");
    }

    private void handleRecyclerView() {
        RecyclerView courseRecyclerView = binding.idRecyclerViewHomeworkList;
        addDivider(courseRecyclerView);
        homeworkListAdapter = getCourseListAdapter();
        LinearLayoutManager verticalLayoutManager = getVerticalLayoutManager();
        courseRecyclerView.setLayoutManager(verticalLayoutManager);
        courseRecyclerView.setAdapter(homeworkListAdapter);
        populateHomeworkList();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setBinding();
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        handleToolbar();
        handleViews();
        handleRecyclerView();
    }
}
