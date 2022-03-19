package edu.sharif.courseworkapp.ui.course;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.sharif.courseworkapp.databinding.ActivityCreateNewHomeworkBinding;
import edu.sharif.courseworkapp.model.Homework;

public class CreateNewHomeworkActivity extends AppCompatActivity {
    private ActivityCreateNewHomeworkBinding binding;

    private void setBinding() {
        binding = ActivityCreateNewHomeworkBinding.inflate(getLayoutInflater());
    }

    private String getCourseId() {
        return getIntent().getStringExtra("courseId");
    }

    private void createNewHomework(String homeworkName, String question) {
        String courseId = getCourseId();
        if (Homework.checkExists(homeworkName, courseId)) {
            Toast.makeText(CreateNewHomeworkActivity.this,
                    "Homework with this name already exists for you!", Toast.LENGTH_LONG).show();
            return;
        }
        Homework newHomework = new Homework(homeworkName, courseId, question);
        saveHomework(newHomework);
        finish();
    }

    private void saveHomework(Homework homework) {
        SharedPreferences.Editor sharedPreferencesEditor = getSharedPreferences(Homework.NAME, MODE_PRIVATE).edit();
        sharedPreferencesEditor.putString(homework.getId(), homework.encode());
        sharedPreferencesEditor.apply();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBinding();
        setContentView(binding.getRoot());

        TextView homeworkName = binding.idHomeworkName;
        TextView homeworkQuestion = binding.idHomeworkQuestion;
        Button newHomeworkButton = binding.idNewHomeworkButton;

        newHomeworkButton.setOnClickListener(
                view -> createNewHomework(
                        homeworkName.getText().toString(),
                        homeworkQuestion.getText().toString()));
    }
}
