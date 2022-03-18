package edu.sharif.courseworkapp.ui.panel;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import edu.sharif.courseworkapp.R;
import edu.sharif.courseworkapp.adapter.RecyclerViewCourseListAdapter;
import edu.sharif.courseworkapp.model.Course;
import edu.sharif.courseworkapp.ui.LoginActivity;


public abstract class UserPanelActivity extends AppCompatActivity {
    protected RecyclerViewCourseListAdapter courseListAdapter;
    protected List<Course> courseList = new ArrayList<>();

    protected RecyclerViewCourseListAdapter getCourseListAdapter() {
        return new RecyclerViewCourseListAdapter(courseList, getApplicationContext(), getUsername());
    }

    protected String getUsername() {
        return getIntent().getStringExtra("username");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_panel, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

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

    private void applyQuery(String query) {
        boolean hasMatch = filterQuery(query);
        if (!hasMatch) {
            Toast.makeText(UserPanelActivity.this, "No Match found",Toast.LENGTH_LONG).show();
        }
    }

    private boolean filterQuery(String query) {
        ArrayList<Course> filtered = new ArrayList<>();
        for (Course course : this.courseList) {
            if (course.getId().contains(query) || course.getName().toLowerCase().contains(query)) {
                filtered.add(course);
            }
        }
        courseListAdapter.setFilter(filtered);
        return filtered.size() != 0;
    }

    @SuppressLint("NotifyDataSetChanged")
    protected void populateCourseList() {
        Course potato = new Course("Potato", "1");
        Course onion = new Course("Onion", "2");
        Course cabbage = new Course("Cabbage", "3");
        Course cauliflower = new Course("Cauliflower", "4");
        courseList.add(potato);
        courseList.add(onion);
        courseList.add(cabbage);
        courseList.add(cauliflower);
        courseList.add(potato);
        courseList.add(onion);
        courseList.add(cabbage);
        courseList.add(cauliflower);
        courseList.add(potato);
        courseList.add(onion);
        courseList.add(cabbage);
        courseList.add(cauliflower);
        courseListAdapter.notifyDataSetChanged();
    }
}
