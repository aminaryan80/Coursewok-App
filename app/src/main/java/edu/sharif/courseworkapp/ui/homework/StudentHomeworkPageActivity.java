package edu.sharif.courseworkapp.ui.homework;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.sharif.courseworkapp.R;
import edu.sharif.courseworkapp.databinding.ActivityStudentHomeworkPageBinding;
import edu.sharif.courseworkapp.model.Answer;
import edu.sharif.courseworkapp.model.Homework;

public class StudentHomeworkPageActivity extends AppCompatActivity {
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

        TextView questionNameTextView = binding.textViewHomeworkName;
        TextView questionTextTextView = binding.textViewHomeworkQuestion;
        TextView gradeTextView = binding.textViewLastGrade;
        EditText toSubmitAnswerTextView = binding.editTextAnswer;

        handleImages();
        setQA(questionNameTextView, questionTextTextView, gradeTextView, toSubmitAnswerTextView);

        Button submitButton = findViewById(R.id.submit_button);

        submitButton.setOnClickListener(view -> {
            String answerT = toSubmitAnswerTextView.getText().toString();
            if (answerT.isEmpty()) {
                handleEmptyInput();
                return;
            }
            Answer answer;
            if (Answer.checkExists(getUsername(), getHomeworkId())) {
                answer = Answer.getUniqueAnswer(getUsername(), getHomeworkId());
                answer.setAnswer(answerT);
            } else {
                answer = new Answer(getUsername(), getHomeworkId(), answerT);
            }
            answer.SetGrade("NG");
            toSubmitAnswerTextView.setText(answer.getAnswer());
            saveAnswer(answer);
            finish();
        });
    }

    private void handleImages() {
        Homework homework = Homework.getHomeworkById(getHomeworkId());
        assert homework != null;
        binding.idHomeworkImage.setImageResource(homework.getImage());
    }

    private void handleEmptyInput() {
        Toast.makeText(
                StudentHomeworkPageActivity.this,
                "Your answer can't be empty!",
                Toast.LENGTH_SHORT).show();
    }

    private void saveAnswer(Answer answer) {
        SharedPreferences.Editor sharedPreferencesEditor = getSharedPreferences(Answer.NAME, MODE_PRIVATE).edit();
        sharedPreferencesEditor.putString(answer.getId(), answer.encode());
        sharedPreferencesEditor.apply();
    }

    private void setQA(
            TextView questionNameTextView, TextView questionTextTextView,
            TextView gradeTextView, TextView toSubmitAnswerTextView
    ) {
        Homework homework = Homework.getHomeworkById(getHomeworkId());
        questionNameTextView.setText(homework.getName());
        questionTextTextView.setText(homework.getQuestion());
        if (Answer.checkExists(getUsername(), getHomeworkId())) {
            Answer answer = Answer.getUniqueAnswer(getUsername(), getHomeworkId());
            toSubmitAnswerTextView.setText(answer.getAnswer());
            if (answer.getGrade().equals("NG")) {
                gradeTextView.setText("not graded");
            } else {
                gradeTextView.setText(answer.getGrade());
            }
        } else {
            gradeTextView.setText("not graded");
        }
    }
}