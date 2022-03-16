package edu.sharif.courseworkapp.ui.panel;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import edu.sharif.courseworkapp.R;
import edu.sharif.courseworkapp.databinding.ActivityProfessorPanelBinding;

public class ProfessorPanelActivity extends UserPanelActivity {

    private ActivityProfessorPanelBinding getBinding() {
        return ActivityProfessorPanelBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityProfessorPanelBinding binding = getBinding();
        setSupportActionBar(binding.toolbar);

        // TODO: add new course
        binding.fab.setOnClickListener(view ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        RecyclerView courseRecyclerView = findViewById(R.id.idRecyclerViewCourseList);
        addDivider(courseRecyclerView);
        courseListAdapter = getCourseListAdapter();
        LinearLayoutManager verticalLayoutManager = getVerticalLayoutManager();
        courseRecyclerView.setLayoutManager(verticalLayoutManager);
        courseRecyclerView.setAdapter(courseListAdapter);
        populateCourseList();
    }
}
