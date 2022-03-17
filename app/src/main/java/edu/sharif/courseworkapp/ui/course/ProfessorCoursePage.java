package edu.sharif.courseworkapp.ui.course;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import edu.sharif.courseworkapp.databinding.ActivityProfessorCoursePageBinding;

public class ProfessorCoursePage extends UserCoursePage {
    private ActivityProfessorCoursePageBinding binding;

    private void setBinding() {
        binding = ActivityProfessorCoursePageBinding.inflate(getLayoutInflater());
        System.out.println(binding);
    }

    protected LinearLayoutManager getVerticalLayoutManager() {
        return new LinearLayoutManager(
                ProfessorCoursePage.this,
                LinearLayoutManager.VERTICAL,
                false
        );
    }

    protected void addDivider(RecyclerView courseRecyclerView) {
        courseRecyclerView.addItemDecoration(
                new DividerItemDecoration(
                        ProfessorCoursePage.this,
                        LinearLayoutManager.VERTICAL
                )
        );
    }

    private void handleFab() {
        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(view ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show());
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

        handleFab();
        handleToolbar();
        handleViews();
        handleRecyclerView();
    }
}
