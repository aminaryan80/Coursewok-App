package edu.sharif.courseworkapp.ui.course;

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
import edu.sharif.courseworkapp.adapter.RecyclerViewHomeworkListAdapter;
import edu.sharif.courseworkapp.model.Homework;
import edu.sharif.courseworkapp.ui.account.LoginActivity;

public abstract class UserCoursePageActivity extends AppCompatActivity {
    protected RecyclerViewHomeworkListAdapter homeworkListAdapter;
    protected List<Homework> homeworkList = new ArrayList<>();
    private final List<Homework> allHomeworkList = new ArrayList<>();

    protected String getUsername() {
        return getIntent().getStringExtra("username");
    }

    protected String getCourseId() {
        return getIntent().getStringExtra("courseId");
    }

    protected RecyclerViewHomeworkListAdapter getHomeworkListAdapter() {
        return new RecyclerViewHomeworkListAdapter(
                homeworkList, getApplicationContext(),
                getUsername(), getCourseId());
    }

    protected LinearLayoutManager getVerticalLayoutManager() {
        return new LinearLayoutManager(
                UserCoursePageActivity.this,
                LinearLayoutManager.VERTICAL,
                false
        );
    }

    protected void addDivider(RecyclerView courseRecyclerView) {
        courseRecyclerView.addItemDecoration(
                new DividerItemDecoration(
                        UserCoursePageActivity.this,
                        LinearLayoutManager.VERTICAL
                )
        );
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume() {
        super.onResume();
        populate();
    }

    private void searchOnClose(SearchView searchView) {
        searchView.setOnCloseListener(() -> {
            homeworkListAdapter.setFilter(allHomeworkList);
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
        getMenuInflater().inflate(R.menu.menu_course_page, menu);
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
            Intent intent = new Intent(UserCoursePageActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void applyQuery(String query) {
        query = query.toLowerCase();
        ArrayList<Homework> filtered = new ArrayList<>();
        for (Homework homework : this.homeworkList) {
            if (homework.getId().toLowerCase().contains(query) ||
                    homework.getName().toLowerCase().contains(query)) {
                filtered.add(homework);
            }
        }
        homeworkListAdapter.setFilter(filtered);
    }

    @SuppressLint("NotifyDataSetChanged")
    protected void populate() {
        List<Homework> homeworks = populateHomeworkList();
        homeworkList.clear();
        allHomeworkList.clear();
        homeworkList.addAll(homeworks);
        allHomeworkList.addAll(homeworkList);
        homeworkListAdapter.notifyDataSetChanged();
    }

    private List<Homework> populateHomeworkList() {
        return Homework.getCourseHomeworks(getCourseId());
    }
}
