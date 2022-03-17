package edu.sharif.courseworkapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.atomic.AtomicBoolean;

import edu.sharif.courseworkapp.R;
import edu.sharif.courseworkapp.controller.RegisterHandler;
import edu.sharif.courseworkapp.model.user.User;
import edu.sharif.courseworkapp.ui.panel.ProfessorPanelActivity;
import edu.sharif.courseworkapp.ui.panel.StudentPanelActivity;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AtomicBoolean isStudent = new AtomicBoolean(false);
        TextView username = findViewById(R.id.username);
        TextView password = findViewById(R.id.password);
        TextView firstname = findViewById(R.id.firstname);
        TextView lastname = findViewById(R.id.lastname);
        TextView extra = findViewById(R.id.extra);
        RadioGroup radio_group = findViewById(R.id.radio_group);

        Button login_button = findViewById(R.id.login_button);

        radio_group.setOnCheckedChangeListener((radioGroup, checkedId) -> {
            RadioButton radioButton = findViewById(checkedId);
            String string = radioButton.getText().toString();
            if (string.equals("Student")) {
                isStudent.set(true);
                extra.setHint(R.string.student_number);
            } else {
                isStudent.set(false);
                extra.setHint(R.string.university);
            }

        });

        login_button.setOnClickListener(v -> {
            RegisterHandler registerHandler = new RegisterHandler(
                    RegisterActivity.this,
                    username.getText().toString(),
                    password.getText().toString(),
                    firstname.getText().toString(),
                    lastname.getText().toString(),
                    extra.getText().toString(),
                    isStudent.get()
            );
            User user = registerHandler.register();
            String a = user.getDisplayName();
            if (a == null) {
                a = "null";
            }
            Toast.makeText(RegisterActivity.this, a, Toast.LENGTH_SHORT).show();
            handleNewActivity(user);
        });
    }

    private void handleNewActivity(User user) {
        Intent intent;
        if (user.isStudent()) {
            intent = new Intent(RegisterActivity.this,
                    StudentPanelActivity.class);
        } else {
            intent = new Intent(RegisterActivity.this,
                    ProfessorPanelActivity.class);
        }
        intent.putExtra("username", user.getUsername());
        startActivity(intent);
    }
}