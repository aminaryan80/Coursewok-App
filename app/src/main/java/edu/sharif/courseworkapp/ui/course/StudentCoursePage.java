package edu.sharif.courseworkapp.ui.course;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import edu.sharif.courseworkapp.R;
import edu.sharif.courseworkapp.databinding.ActivityStudentCoursePageBinding;

public class StudentCoursePage extends UserCoursePage {

    private ActivityStudentCoursePageBinding getBinding() {
        return ActivityStudentCoursePageBinding.inflate(getLayoutInflater());
    }

    private void handleToolbar(ActivityStudentCoursePageBinding binding) {
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle("Course Name");
    }

    private void handleViews(ActivityStudentCoursePageBinding binding) {
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

        ActivityStudentCoursePageBinding binding = getBinding();
        setSupportActionBar(binding.toolbar);

        handleToolbar(binding);
        handleViews(binding);
        handleRecyclerView();
    }
}
