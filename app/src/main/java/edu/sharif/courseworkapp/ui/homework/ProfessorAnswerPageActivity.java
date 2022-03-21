package edu.sharif.courseworkapp.ui.homework;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.sharif.courseworkapp.R;
import edu.sharif.courseworkapp.databinding.ActivityProfessorAnswerPageBinding;
import edu.sharif.courseworkapp.model.Answer;
import edu.sharif.courseworkapp.model.Homework;
import edu.sharif.courseworkapp.model.user.User;

public class ProfessorAnswerPageActivity extends AppCompatActivity {
    private ActivityProfessorAnswerPageBinding binding;

    private void setBinding() {
        binding = ActivityProfessorAnswerPageBinding.inflate(getLayoutInflater());
    }

    protected String getUsername() {
        return getIntent().getStringExtra("username");
    }

    protected String getAnswerId() {
        return getIntent().getStringExtra("answerId");
    }

    private void handleImages() {
        Answer answer = Answer.getAnswerById(getAnswerId());
        assert answer != null;
        int image = answer.getHomework().getImage();
        binding.idHomeworkImage.setImageResource(image);
        binding.idStudentImage.setImageResource(answer.getImage());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBinding();
        setContentView(binding.getRoot());

        TextView homeworkNameTextView = findViewById(R.id.textViewHomeworkName);
        TextView homeworkQuestionTextView = findViewById(R.id.textViewHomeworkQuestion);
        TextView submittedAnswerTextView = findViewById(R.id.textViewSubmittedAnswer);
        TextView lastGradeTextView = findViewById(R.id.textViewLastGrade);
        TextView studentNameTextView = findViewById(R.id.textViewStudentName);
        TextView toSubmitGradeTextView = findViewById(R.id.editTextGrade);

        setQA(homeworkNameTextView, homeworkQuestionTextView, lastGradeTextView, submittedAnswerTextView, studentNameTextView);
        handleImages();

        Button gradeButton = findViewById(R.id.grade_button);
        gradeButton.setOnClickListener(view -> {
            String gradeT = toSubmitGradeTextView.getText().toString();
            if (gradeT.isEmpty()) {
                handleEmptyInput();
                return;
            }
            Answer answer = Answer.getAnswerById(getAnswerId());
            answer.SetGrade(gradeT);
            saveAnswer(answer);
            lastGradeTextView.setText("grade:\n" + answer.getGrade());
            toSubmitGradeTextView.setText("");
            finish();
        });
    }

    private void handleEmptyInput() {
        Toast.makeText(
                ProfessorAnswerPageActivity.this,
                "Set grade first!",
                Toast.LENGTH_SHORT).show();
    }

    private void saveAnswer(Answer answer) {
        SharedPreferences.Editor sharedPreferencesEditor = getSharedPreferences(Answer.NAME, MODE_PRIVATE).edit();
        sharedPreferencesEditor.putString(answer.getId(), answer.encode());
        sharedPreferencesEditor.apply();
    }

    private void setQA(TextView homeworkNameTextView, TextView homeworkQuestionTextView, TextView lastGradeTextView,
                       TextView submittedAnswerTextView, TextView studentNameTextView) {
        Answer answer = Answer.getAnswerById(getAnswerId());
        Homework homework = Homework.getHomeworkById(answer.getHomeworkId());
        homeworkNameTextView.setText(homework.getName());
        homeworkQuestionTextView.setText(homework.getQuestion());
        if (answer.getGrade().equals("NG")) {
            lastGradeTextView.setText("not graded");
        } else {
            lastGradeTextView.setText(answer.getGrade());
        }
        submittedAnswerTextView.setText(answer.getAnswer());
        studentNameTextView.setText(User.getUserByUsername(answer.getStudentId()).getDisplayName());
    }

}