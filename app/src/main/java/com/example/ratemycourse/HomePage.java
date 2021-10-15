package com.example.ratemycourse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    //Class variables
    Button schoolSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //find buttons by id
        schoolSearch = findViewById(R.id.searchSchoolButton);

        schoolSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(HomePage.this, SchoolSearch.class);
                startActivityForResult(intent,10);
            }
        });
    }
}