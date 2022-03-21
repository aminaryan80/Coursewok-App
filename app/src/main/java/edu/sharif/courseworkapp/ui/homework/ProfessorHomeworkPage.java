package edu.sharif.courseworkapp.ui.homework;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import edu.sharif.courseworkapp.R;
import edu.sharif.courseworkapp.adapter.RecyclerViewProfessorHomeworkAnswersListAdapter;
import edu.sharif.courseworkapp.databinding.ActivityProfessorHomeworkPageBinding;
import edu.sharif.courseworkapp.model.Answer;
import edu.sharif.courseworkapp.model.Homework;
import edu.sharif.courseworkapp.ui.account.LoginActivity;

public class ProfessorHomeworkPage extends AppCompatActivity {
    protected RecyclerViewProfessorHomeworkAnswersListAdapter homeworkAnswerListAdapter;
    private ActivityProfessorHomeworkPageBinding binding;
    protected List<Answer> answerList = new ArrayList<>();

    private void setBinding() {
        binding = ActivityProfessorHomeworkPageBinding.inflate(getLayoutInflater());
    }

    private void handleFab() {
        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(view -> goToEditHomeworkName());
    }

    protected String getUsername() {
        return getIntent().getStringExtra("username");
    }

    protected String getHomeworkId() {
        return getIntent().getStringExtra("homeworkId");
    }

    protected RecyclerViewProfessorHomeworkAnswersListAdapter getHomeworkAnswerListAdapter() {
        return new RecyclerViewProfessorHomeworkAnswersListAdapter(
                answerList, getApplicationContext(),
                getUsername(), getHomeworkId());
    }

    private void handleToolbar() {
        Toolbar toolbar = binding.toolbar;
        //setSupportActionBar(toolbar);
        CollapsingToolbarLayout collToolbar = binding.toolbarLayout;

        Homework homework = Homework.getHomeworkById(getHomeworkId());
        collToolbar.setTitle(homework.getName());
        collToolbar.setCollapsedTitleTextColor(Color.WHITE);
        collToolbar.setExpandedTitleColor(Color.WHITE);
    }

    private void handleRecyclerView() {
        RecyclerView courseRecyclerView = binding.idRecyclerViewHomeworkAnswerList;
        addDivider(courseRecyclerView);
        homeworkAnswerListAdapter = getHomeworkAnswerListAdapter();
        LinearLayoutManager verticalLayoutManager = getVerticalLayoutManager();
        courseRecyclerView.setLayoutManager(verticalLayoutManager);
        courseRecyclerView.setAdapter(homeworkAnswerListAdapter);
        populate();
    }

    protected LinearLayoutManager getVerticalLayoutManager() {
        return new LinearLayoutManager(
                ProfessorHomeworkPage.this,
                LinearLayoutManager.VERTICAL,
                false
        );
    }

    protected void addDivider(RecyclerView answersRecyclerView) {
        answersRecyclerView.addItemDecoration(
                new DividerItemDecoration(
                        ProfessorHomeworkPage.this,
                        LinearLayoutManager.VERTICAL
                )
        );
    }

    private void goToEditHomeworkName() {
        Intent intent = new Intent(ProfessorHomeworkPage.this, EditHomeworkNameActivity.class);
        intent.putExtra("username", getUsername());
        intent.putExtra("homeworkId", getHomeworkId());
        startActivity(intent);
        Homework homework = Homework.getHomeworkById(getHomeworkId());
        binding.toolbarLayout.setTitle(homework.getName());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBinding();
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        handleFab();
        handleToolbar();
        handleRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_homework_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            Intent intent = new Intent(ProfessorHomeworkPage.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("NotifyDataSetChanged")
    protected void populate() {
        List<Answer> answers = populateHomeworkAnswerList();
        answerList.clear();
        answerList.addAll(answers);
        homeworkAnswerListAdapter.notifyDataSetChanged();
    }

    private List<Answer> populateHomeworkAnswerList() {
        return Answer.getHomeworkAnswers(getHomeworkId());
    }
}