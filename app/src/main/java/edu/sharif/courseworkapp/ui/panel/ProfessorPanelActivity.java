package edu.sharif.courseworkapp.ui.panel;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import edu.sharif.courseworkapp.databinding.ActivityProfessorPanelBinding;

public class ProfessorPanelActivity extends UserPanelActivity {
    private ActivityProfessorPanelBinding binding;

    private void setBinding() {
        binding = ActivityProfessorPanelBinding.inflate(getLayoutInflater());
    }

    private LinearLayoutManager getVerticalLayoutManager() {
        return new LinearLayoutManager(
                ProfessorPanelActivity.this,
                LinearLayoutManager.VERTICAL,
                false
        );
    }

    private void addDivider(RecyclerView courseRecyclerView) {
        courseRecyclerView.addItemDecoration(
                new DividerItemDecoration(
                        ProfessorPanelActivity.this,
                        LinearLayoutManager.VERTICAL
                )
        );
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setBinding();
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        // TODO: add new course
        binding.fab.setOnClickListener(view ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        RecyclerView courseRecyclerView = binding.idRecyclerViewCourseList;
        addDivider(courseRecyclerView);
        courseListAdapter = getCourseListAdapter();
        LinearLayoutManager verticalLayoutManager = getVerticalLayoutManager();
        courseRecyclerView.setLayoutManager(verticalLayoutManager);
        courseRecyclerView.setAdapter(courseListAdapter);
        populateCourseList();
    }
}
