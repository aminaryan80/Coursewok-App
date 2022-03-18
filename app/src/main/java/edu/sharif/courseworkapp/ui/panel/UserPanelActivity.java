package edu.sharif.courseworkapp.ui.panel;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import edu.sharif.courseworkapp.R;
import edu.sharif.courseworkapp.adapter.RecyclerViewCourseListAdapter;
import edu.sharif.courseworkapp.model.Course;
import edu.sharif.courseworkapp.model.user.Professor;
import edu.sharif.courseworkapp.ui.LoginActivity;


public abstract class UserPanelActivity extends AppCompatActivity {
    protected RecyclerViewCourseListAdapter courseListAdapter;
    protected List<Course> courseList = new ArrayList<>();
    protected ArrayList<Course> allCourseList;
    protected ArrayAdapter<String> adapter;

    protected RecyclerViewCourseListAdapter getCourseListAdapter() {
        return new RecyclerViewCourseListAdapter(courseList, getApplicationContext(), getUsername());
    }

    protected String getUsername() {
        return getIntent().getStringExtra("username");
    }

    private void setAllCourseList() {
        Course potato = new Course("Potato", "1");
        Course onion = new Course("Onion", "2");
        Course cabbage = new Course("Cabbage", "3");
        Course cauliflower = new Course("Cauliflower", "4");
        allCourseList.add(potato);
        allCourseList.add(onion);
        allCourseList.add(cabbage);
        allCourseList.add(cauliflower);
        allCourseList.add(potato);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_course_page, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
////                searchView.clearFocus();
////                if (list.contains(query)){
////                    adapter.getFilter().filter(query);
////                } else{
////                    Toast.makeText(MainActivity.this, "No Match found",Toast.LENGTH_LONG).show();
////                }
////                return false;
//
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
////                    adapter.getFilter().filter(newText);
//                return false;
//            }
//        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            Intent intent = new Intent(UserPanelActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_search) {
            // pass
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("NotifyDataSetChanged")
    protected void populateCourseList() {
        new Professor("Prof1", "123", "Kaley", "Thomas", "Sharif");
        new Professor("Prof2", "123", "Jeffery", "Mercier", "Sharif");
        new Professor("Prof3", "123", "Brian", "Brooks", "Sharif");
        new Professor("Prof4", "123", "Robert", "Saenz", "Sharif");
        Course potato = new Course("Potato", "Prof1");
        Course onion = new Course("Onion", "Prof2");
        Course cabbage = new Course("Cabbage", "Prof3");
        Course cauliflower = new Course("Cauliflower", "Prof4");
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
