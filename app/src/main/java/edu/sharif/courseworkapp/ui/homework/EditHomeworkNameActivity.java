package edu.sharif.courseworkapp.ui.homework;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import edu.sharif.courseworkapp.R;
import edu.sharif.courseworkapp.model.Course;
import edu.sharif.courseworkapp.model.Homework;

public class EditHomeworkNameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_homework_name);

        TextView newNameTextView = findViewById(R.id.idNewHomeworkName);
        Button editNameButton = findViewById(R.id.idEditHomeworkNameButton);

        editNameButton.setOnClickListener(view -> editHomeworkName(newNameTextView.getText().toString()));
    }

    private String getUsername() {
        return getIntent().getStringExtra("username");
    }

    protected String getHomeworkId() {
        return getIntent().getStringExtra("homeworkId");
    }

    private void editHomeworkName(String newName) {
        String username = getUsername();
        Homework oldHomework = Homework.getHomeworkById(getHomeworkId());
        List<Homework> homeworks = Homework.getCourseHomeworks(oldHomework.getCourseId());
        for (Homework homework : homeworks) {
            if (homework.getName().equals(newName)) {
                Toast.makeText(EditHomeworkNameActivity.this,
                        "Homework with this name already exists for you!", Toast.LENGTH_LONG).show();
                return;
            }
        }
        Homework editedHomework = new Homework(oldHomework.getId(), newName, oldHomework.getCourseId(), oldHomework.getQuestion(), oldHomework.getImage());
        saveHomework(editedHomework);
        finish();
    }

    private void saveHomework(Homework homework) {
        SharedPreferences.Editor sharedPreferencesEditor = getSharedPreferences(Homework.NAME, MODE_PRIVATE).edit();
        sharedPreferencesEditor.putString(homework.getId(), homework.encode());
        sharedPreferencesEditor.apply();
    }
}