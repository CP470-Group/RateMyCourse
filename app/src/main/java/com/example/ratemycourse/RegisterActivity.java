package com.example.ratemycourse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    Button signUpPageButton;
    EditText usernameText;
    EditText fullNameText;
    EditText emailText;
    EditText majorText;
    EditText passwordText;

    List<User> userList;

    DatabaseReference databaseUsers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        databaseUsers = FirebaseDatabase.getInstance().getReference("users");

        usernameText = (EditText) findViewById(R.id.userNameField);
        fullNameText = (EditText) findViewById(R.id.fullNameField);
        emailText = (EditText) findViewById(R.id.emailField);
        majorText = (EditText) findViewById(R.id.majorField);
        passwordText = (EditText) findViewById(R.id.passwordField);
        signUpPageButton = findViewById(R.id.signUpPageButton);

        userList = new ArrayList<>();

        signUpPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, HomePage.class);
                createUser();
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void createUser(){
        String username = usernameText.getText().toString().trim();
        String fullName = fullNameText.getText().toString().trim();
        String email = emailText.getText().toString().trim();
        String major = majorText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();

        if(!TextUtils.isEmpty(fullName)){
            String id = databaseUsers.push().getKey();
            User user = new User(username, email, fullName, major , 0, 0, 0, null);
            databaseUsers.child(id).setValue(user);
            Toast.makeText(this, "Account Created.", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "Please fill all of the feilds", Toast.LENGTH_LONG).show();
        }

    }
}