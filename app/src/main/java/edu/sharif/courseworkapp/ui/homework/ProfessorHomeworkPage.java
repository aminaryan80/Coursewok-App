package edu.sharif.courseworkapp.ui.homework;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

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

import edu.sharif.courseworkapp.adapter.RecyclerViewHomeworkListAdapter;
import edu.sharif.courseworkapp.adapter.RecyclerViewProfessorHomeworkAnswersListAdapter;
import edu.sharif.courseworkapp.adapter.RecyclerViewProfessorHomeworkListAdapter;
import edu.sharif.courseworkapp.databinding.ActivityProfessorCoursePageBinding;
import edu.sharif.courseworkapp.databinding.ActivityProfessorHomeworkPageBinding;
import edu.sharif.courseworkapp.model.Answer;
import edu.sharif.courseworkapp.model.Homework;
import edu.sharif.courseworkapp.model.user.Professor;
import edu.sharif.courseworkapp.model.user.User;
import edu.sharif.courseworkapp.ui.course.CreateNewHomeworkActivity;
import edu.sharif.courseworkapp.ui.course.UserCoursePage;

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
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBinding();
        setContentView(binding.getRoot());

        handleFab();
        handleToolbar();
        handleRecyclerView();
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