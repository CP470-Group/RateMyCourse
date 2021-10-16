package com.example.ratemycourse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    //Class variables
    Button schoolSearch;
    Button profileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //find buttons by id
        schoolSearch = findViewById(R.id.searchSchoolButton);
        profileButton = findViewById(R.id.editProfileButton);

        schoolSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(HomePage.this, SchoolSearch.class);
                startActivityForResult(intent,10);
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, UserProfile.class);
                startActivityForResult(intent, 10);
            }
        });
    }
}