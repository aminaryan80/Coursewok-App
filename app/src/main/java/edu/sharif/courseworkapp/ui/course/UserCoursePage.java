package edu.sharif.courseworkapp.ui.course;

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
import edu.sharif.courseworkapp.adapter.RecyclerViewHomeworkListAdapter;
import edu.sharif.courseworkapp.model.Homework;
import edu.sharif.courseworkapp.ui.LoginActivity;

public abstract class UserCoursePage extends AppCompatActivity {
    protected RecyclerViewHomeworkListAdapter homeworkListAdapter;
    protected List<Homework> homeworkList = new ArrayList<>();

    protected String getUsername() {
        return getIntent().getStringExtra("username");
    }

    protected String getCourseId() {
        return getIntent().getStringExtra("courseId");
    }

    protected RecyclerViewHomeworkListAdapter getCourseListAdapter() {
        return new RecyclerViewHomeworkListAdapter(
                homeworkList, getApplicationContext(),
                getUsername(), getCourseId());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_course_page, menu);
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
            Intent intent = new Intent(UserCoursePage.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void applyQuery(String query) {
        boolean hasMatch = filterQuery(query);
        if (!hasMatch) {
            Toast.makeText(UserCoursePage.this, "No Match found",Toast.LENGTH_LONG).show();
        }
    }

    private boolean filterQuery(String query) {
        ArrayList<Homework> filtered = new ArrayList<>();
        for (Homework homework : this.homeworkList) {
            if (homework.getId().contains(query) || homework.getName().toLowerCase().contains(query)) {
                filtered.add(homework);
            }
        }
        homeworkListAdapter.setFilter(filtered);
        return homeworkList.size() != 0;
    }

    @SuppressLint("NotifyDataSetChanged")
    protected void populateHomeworkList(){
        Homework potato = new Homework("Potato", "1");
        Homework onion = new Homework("Onion", "2");
        Homework cabbage = new Homework("Cabbage", "3");
        Homework cauliflower = new Homework("Cauliflower", "4");
        homeworkList.add(potato);
        homeworkList.add(onion);
        homeworkList.add(cabbage);
        homeworkList.add(cauliflower);
        homeworkList.add(potato);
        homeworkList.add(onion);
        homeworkList.add(cabbage);
        homeworkList.add(cauliflower);
        homeworkList.add(potato);
        homeworkList.add(onion);
        homeworkList.add(cabbage);
        homeworkList.add(cauliflower);
        homeworkListAdapter.notifyDataSetChanged();
    }
}
