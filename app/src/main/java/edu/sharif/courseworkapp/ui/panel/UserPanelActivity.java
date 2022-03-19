package edu.sharif.courseworkapp.ui.panel;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.sharif.courseworkapp.R;
import edu.sharif.courseworkapp.adapter.RecyclerViewCourseListAdapter;
import edu.sharif.courseworkapp.adapter.RecyclerViewNewCourseListAdapter;
import edu.sharif.courseworkapp.model.Course;
import edu.sharif.courseworkapp.ui.account.LoginActivity;


public abstract class UserPanelActivity extends AppCompatActivity {
    protected RecyclerViewCourseListAdapter courseListAdapter;
    protected List<Course> courseList = new ArrayList<>();
    protected List<Course> allCourseList = new ArrayList<>();

    protected RecyclerViewCourseListAdapter getCourseListAdapter() {
        return new RecyclerViewCourseListAdapter(
                courseList, getApplicationContext(), getUsername()
        );
    }

    protected RecyclerViewNewCourseListAdapter getNewCourseListAdapter() {
        return new RecyclerViewNewCourseListAdapter(
                courseList, UserPanelActivity.this, getUsername()
        );
    }

    protected LinearLayoutManager getVerticalLayoutManager() {
        return new LinearLayoutManager(
                UserPanelActivity.this,
                LinearLayoutManager.VERTICAL,
                false
        );
    }

    protected void addDivider(RecyclerView courseRecyclerView) {
        courseRecyclerView.addItemDecoration(
                new DividerItemDecoration(
                        UserPanelActivity.this,
                        LinearLayoutManager.VERTICAL
                )
        );
    }

    protected String getUsername() {
        return getIntent().getStringExtra("username");
    }

    private void searchOnClose(SearchView searchView) {
        searchView.setOnCloseListener(() -> {
            courseListAdapter.setFilter(allCourseList);
            return false;
        });
    }

    private void searchOnQueryTextChanged(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                applyQuery(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                applyQuery(query);
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_panel, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchOnQueryTextChanged(searchView);
        searchOnClose(searchView);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            Intent intent = new Intent(UserPanelActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume() {
        super.onResume();
        populate();
    }

    private void applyQuery(String query) {
        query = query.toLowerCase();
        ArrayList<Course> filtered = new ArrayList<>();
        for (Course course : this.courseList) {
            if (course.getId().toLowerCase().contains(query) ||
                    course.getName().toLowerCase().contains(query)) {
                filtered.add(course);
            }
        }
        courseListAdapter.setFilter(filtered);
    }

    @SuppressLint("NotifyDataSetChanged")
    protected void populate() {
        List<Course> courses = populateCourseList();
        courseList.clear();
        allCourseList.clear();
        courseList.addAll(courses);
        allCourseList.addAll(courseList);
        courseListAdapter.notifyDataSetChanged();
    }

    protected abstract List<Course> populateCourseList();
}