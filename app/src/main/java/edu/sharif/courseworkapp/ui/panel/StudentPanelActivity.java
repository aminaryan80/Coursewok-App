package edu.sharif.courseworkapp.ui.panel;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import edu.sharif.courseworkapp.R;
import edu.sharif.courseworkapp.databinding.ActivityStudentPanelBinding;

public class StudentPanelActivity extends UserPanelActivity {

    private ActivityStudentPanelBinding getBinding() {
        return ActivityStudentPanelBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityStudentPanelBinding binding = getBinding();
        setSupportActionBar(binding.toolbar);

        RecyclerView courseRecyclerView = findViewById(R.id.idRecyclerViewCourseList);
        addDivider(courseRecyclerView);
        courseListAdapter = getCourseListAdapter();
        LinearLayoutManager verticalLayoutManager = getVerticalLayoutManager();
        courseRecyclerView.setLayoutManager(verticalLayoutManager);
        courseRecyclerView.setAdapter(courseListAdapter);
        populateCourseList();
    }
}
