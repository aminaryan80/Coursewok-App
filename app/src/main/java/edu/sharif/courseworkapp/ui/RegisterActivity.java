package edu.sharif.courseworkapp.ui;

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

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


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
                extra.setHint(R.string.student_number);
            } else {
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
                    true
            );
            User user = registerHandler.register();
            String a = user.getDisplayName();
            if (a == null) {
                a = "null";
            }
            Toast.makeText(RegisterActivity.this, a, Toast.LENGTH_SHORT).show();
        });
    }
}