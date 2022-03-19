package edu.sharif.courseworkapp.ui.panel;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import edu.sharif.courseworkapp.R;
import edu.sharif.courseworkapp.model.Course;

public class ProfessorPanelActivity extends UserPanelActivity {
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
        setContentView(R.layout.activity_professor_panel);

        FloatingActionButton addButton = findViewById(R.id.fab);
        addButton.setOnClickListener(view ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
        );

        RecyclerView courseRecyclerView = findViewById(R.id.idRecyclerViewCourseList);
        addDivider(courseRecyclerView);
        courseListAdapter = getCourseListAdapter();
        LinearLayoutManager verticalLayoutManager = getVerticalLayoutManager();
        courseRecyclerView.setLayoutManager(verticalLayoutManager);
        courseRecyclerView.setAdapter(courseListAdapter);
        populateCourseList();
    }

    @Override
    protected ArrayList<Course> filterUser(ArrayList<Course> courses) {
        ArrayList<Course> filtered = new ArrayList<>();
        for (Course course : courses) {
            if (course.getProfessorId().equals(getUsername())) {
                filtered.add(course);
            }
        }
        return filtered;
    }
}
