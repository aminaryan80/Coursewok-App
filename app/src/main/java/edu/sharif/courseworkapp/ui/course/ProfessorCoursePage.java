package edu.sharif.courseworkapp.ui.course;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import edu.sharif.courseworkapp.R;
import edu.sharif.courseworkapp.databinding.ActivityProfessorCoursePageBinding;

public class ProfessorCoursePage extends UserCoursePage {

    private ActivityProfessorCoursePageBinding getBinding() {
        return ActivityProfessorCoursePageBinding.inflate(getLayoutInflater());
    }

    private void handleFab(ActivityProfessorCoursePageBinding binding) {
        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(view ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }

    private void handleToolbar(ActivityProfessorCoursePageBinding binding) {
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle("Course Name");
    }

    private void handleViews(ActivityProfessorCoursePageBinding binding) {
        TextView profName = binding.idProfessorName;
//        TextView courseName = binding.idCourseName;
        profName.setText("Prof Name");
//        courseName.setText("Course Name");
    }

    private void handleRecyclerView() {
        RecyclerView courseRecyclerView = findViewById(R.id.idRecyclerViewCourseList);
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

        ActivityProfessorCoursePageBinding binding = getBinding();
        setSupportActionBar(binding.toolbar);

        handleFab(binding);
        handleToolbar(binding);
        handleViews(binding);
        handleRecyclerView();
    }
}
