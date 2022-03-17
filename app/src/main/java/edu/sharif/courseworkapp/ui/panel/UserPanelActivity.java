package edu.sharif.courseworkapp.ui.panel;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import edu.sharif.courseworkapp.R;
import edu.sharif.courseworkapp.adapter.RecyclerViewCourseListAdapter;
import edu.sharif.courseworkapp.model.Course;


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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_course_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // TODO: logout
        if (id == R.id.action_logout) {
            return true;
        }

        // TODO: other courses page (include courses that student doesn't have and search courses)
        if (id == R.id.action_other_courses) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("NotifyDataSetChanged")
    protected void populateCourseList(){
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
