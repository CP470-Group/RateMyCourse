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
    Button reviewButton;
    Button databaseSample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //find buttons by id
        schoolSearch = findViewById(R.id.searchSchoolButton);
        profileButton = findViewById(R.id.editProfileButton);
        reviewButton = findViewById(R.id.addRatingButton);
        databaseSample = findViewById(R.id.databaseSample);

        schoolSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(HomePage.this, SchoolSearch.class);
                startActivity(intent);
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, UserProfile.class);//
                User user = (User)getIntent().getSerializableExtra("user");
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, new_review.class);
                startActivity(intent);
            }
        });

        databaseSample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, DatabaseSample.class);
                startActivity(intent);
            }
        });
    }
}