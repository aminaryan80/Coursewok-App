package edu.sharif.courseworkapp.ui.course;

import android.annotation.SuppressLint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.sharif.courseworkapp.adapter.RecyclerViewHomeworkListAdapter;
import edu.sharif.courseworkapp.model.Homework;

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
