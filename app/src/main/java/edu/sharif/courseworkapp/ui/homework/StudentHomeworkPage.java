package edu.sharif.courseworkapp.ui.homework;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import edu.sharif.courseworkapp.R;
import edu.sharif.courseworkapp.databinding.ActivityProfessorCoursePageBinding;
import edu.sharif.courseworkapp.databinding.ActivityStudentHomeworkPageBinding;
import edu.sharif.courseworkapp.model.Answer;
import edu.sharif.courseworkapp.model.Course;
import edu.sharif.courseworkapp.model.Homework;
public class StudentHomeworkPage extends AppCompatActivity {
    private ActivityStudentHomeworkPageBinding binding;

    private void setBinding() {
        binding = ActivityStudentHomeworkPageBinding.inflate(getLayoutInflater());
    }


    protected String getUsername() {
        return getIntent().getStringExtra("username");
    }

    protected String getHomeworkId() {
        return getIntent().getStringExtra("homeworkId");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBinding();
        setContentView(binding.getRoot());

        TextView questionNameTextView = findViewById(R.id.textViewQuestionName);
        TextView questionTextTextView = findViewById(R.id.textViewQuestionText);
        TextView latestAnswerTextView = findViewById(R.id.textViewLastSubmittedAnswer);
        TextView gradeTextView = findViewById(R.id.textViewGrade);
        TextView toSubmitAnswerTextTextView = findViewById(R.id.editTextAnswer);

        setQA(questionNameTextView, questionTextTextView, gradeTextView, latestAnswerTextView);

        Button submitButton = findViewById(R.id.submit_button);

        submitButton.setOnClickListener(view -> {
            String answerT = toSubmitAnswerTextTextView.getText().toString();
            Answer answer;
            if (Answer.checkExists(getUsername(), getHomeworkId())) {
                answer = Answer.getUniqueAnswer(getUsername(), getHomeworkId());
                answer.setAnswer(answerT);
            } else {
                answer = new Answer(getUsername(), getHomeworkId(), answerT);
            }
            latestAnswerTextView.setText("last submitted answer:\n" + answer.getAnswer());
            saveAnswer(answer);
            toSubmitAnswerTextTextView.setText("");
        });

    }

    private void saveAnswer(Answer answer) {
        SharedPreferences.Editor sharedPreferencesEditor = getSharedPreferences(Answer.NAME, MODE_PRIVATE).edit();
        sharedPreferencesEditor.putString(answer.getId(), answer.encode());
        sharedPreferencesEditor.apply();
    }

    private void setQA(TextView questionNameTextView, TextView questionTextTextView, TextView gradeTextView, TextView latestAnswerTextView) {
        Homework homework = Homework.getHomeworkById(getHomeworkId());
        questionNameTextView.setText(homework.getName());
        questionTextTextView.setText(homework.getQuestion());
        if (Answer.checkExists(getUsername(), getHomeworkId())) {
            Answer answer = Answer.getUniqueAnswer(getUsername(), getHomeworkId());
            if (answer.getGrade().equals("NG")) {
                gradeTextView.setText("grade:\nnot graded.");
            } else {
                gradeTextView.setText("grade:\n" + answer.getGrade());
            }
            latestAnswerTextView.setText("last submitted answer:\n" + answer.getAnswer());
        } else {
            gradeTextView.setText("grade:\nnot graded.");
            latestAnswerTextView.setText("last submitted answer:\nhaven't submitted anything yet.");
        }
    }

}