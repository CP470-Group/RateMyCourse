package com.example.ratemycourse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewRating extends AppCompatActivity {

    private TextView courseNameRate;
    private TextView schoolNameRate;
    private Spinner spinnerGrades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_review);

        courseNameRate = (TextView) findViewById(R.id.courseNameRate);
        schoolNameRate = (TextView) findViewById(R.id.schoolNameRate);
        spinnerGrades = (Spinner) findViewById(R.id.spinnerGrades);

        Intent intent = getIntent();

        String courseName = intent.getStringExtra(CourseLanding.COURSE_NAME_RATING);
        String schoolName = intent.getStringExtra(CourseLanding.SCHOOL_NAME_RATING);

        courseNameRate.setText(courseName);
        schoolNameRate.setText(schoolName);

        List<String> grades = new ArrayList<>(Arrays.asList("A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F", "N/A"));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, grades);

        spinnerGrades.setAdapter(adapter);
    }
}