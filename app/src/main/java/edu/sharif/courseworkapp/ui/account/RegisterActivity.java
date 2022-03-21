package edu.sharif.courseworkapp.ui.account;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.sharif.courseworkapp.R;
import edu.sharif.courseworkapp.controller.RegisterHandler;
import edu.sharif.courseworkapp.model.user.User;
import edu.sharif.courseworkapp.ui.homework.ProfessorAnswerPageActivity;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        String username = "";
        String password = "";

        Intent intent = getIntent();
        if (intent.hasExtra("username")) {
            username = intent.getStringExtra("username");
        }
        if (intent.hasExtra("password")) {
            password = intent.getStringExtra("password");
        }

        TextView usernameTextView = findViewById(R.id.username);
        TextView passwordTextView = findViewById(R.id.password);
        TextView firstnameTextView = findViewById(R.id.firstname);
        TextView lastnameTextView = findViewById(R.id.lastname);
        TextView extraTextView = findViewById(R.id.extra);
        RadioGroup radio_group = findViewById(R.id.radio_group);

        Button login_button = findViewById(R.id.login_button);
        Button register_button = findViewById(R.id.register_button);

        usernameTextView.setText(username);
        passwordTextView.setText(password);

        radio_group.setOnCheckedChangeListener((radioGroup, checkedId) -> {
            RadioButton radioButton = findViewById(checkedId);
            String string = radioButton.getText().toString();
            if (string.equals("Student")) {
                extraTextView.setText(null);
                extraTextView.setHint(R.string.student_number);
            } else {
                extraTextView.setText(null);
                extraTextView.setHint(R.string.university);
            }
        });

        register_button.setOnClickListener(v -> {
            int selectedId = radio_group.getCheckedRadioButtonId();
            RadioButton radioButton = radio_group.findViewById(selectedId);
            boolean isStudent = radioButton.getText().toString().equals("Student");
            if (usernameTextView.getText().toString().isEmpty() || passwordTextView.getText().toString().isEmpty()) {
                handleEmptyInput();
                return;
            }
            RegisterHandler registerHandler = new RegisterHandler(
                    usernameTextView.getText().toString(),
                    passwordTextView.getText().toString(),
                    firstnameTextView.getText().toString(),
                    lastnameTextView.getText().toString(),
                    extraTextView.getText().toString(),
                    isStudent
            );
            User user = registerHandler.register();
            if (user == null) {
                Toast.makeText(RegisterActivity.this, "Username already exists!", Toast.LENGTH_SHORT).show();
                return;
            }
            String userDisplayName = user.getDisplayName();
            String response = userDisplayName + " Registered Successfully.";
            Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
            saveUser(user);
            goToLogin(user.getUsername(), user.getPassword());
        });

        login_button.setOnClickListener(view -> goToLogin(
                usernameTextView.getText().toString(),
                passwordTextView.getText().toString()
        ));
    }

    private void handleEmptyInput() {
        Toast.makeText(
                RegisterActivity.this,
                "Username or password can't be empty!",
                Toast.LENGTH_SHORT).show();
    }

    private void saveUser(User user) {
        SharedPreferences.Editor sharedPreferencesEditor;
        String type = user.getType();
        if (type.equals(User.STUDENT)) {
            sharedPreferencesEditor = getSharedPreferences("Students", MODE_PRIVATE).edit();
        } else {
            sharedPreferencesEditor = getSharedPreferences("Professors", MODE_PRIVATE).edit();
        }
        sharedPreferencesEditor.putString(user.getUsername(), user.encode());
        sharedPreferencesEditor.apply();
    }

    private void goToLogin(String username, String password) {
        Intent myIntent = new Intent(RegisterActivity.this, LoginActivity.class);
        myIntent.putExtra("username", username);
        myIntent.putExtra("password", password);
        RegisterActivity.this.startActivity(myIntent);
    }

    public void onBackPressed() {
        super.onBackPressed();
        this.finishAffinity();
    }
}