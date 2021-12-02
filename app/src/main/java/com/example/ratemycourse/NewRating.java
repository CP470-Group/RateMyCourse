package com.example.ratemycourse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewRating extends AppCompatActivity {

    private TextView courseNameRate;
    private TextView schoolNameRate;

    private String courseID;
    private String courseName;
    private String schoolName;
    private String username;
    private String userid;
    private int reviewNum;
    private int ratingCount;

    // rating input fields
    private Spinner spinnerGrades;
    private RatingBar rateBar;
    private EditText profName;
    private EditText ratingText;

    private Button submitRating;

    DataSnapshot dataSnapshot;

    DatabaseReference databaseRatings;
    DatabaseReference databaseUsers;

    public static final String COURSE_ID = "courseID";
    public static final String COURSE_NAME = "courseNameRate";
    public static final String SCHOOL_NAME = "schoolNameRate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_review);

        databaseRatings = FirebaseDatabase.getInstance().getReference("ratings");
        databaseUsers = FirebaseDatabase.getInstance().getReference("users");

        courseNameRate = (TextView) findViewById(R.id.courseNameRate);
        schoolNameRate = (TextView) findViewById(R.id.schoolNameRate);

        // initialize rating input fields
        spinnerGrades = (Spinner) findViewById(R.id.spinnerGrades);
        rateBar = (RatingBar) findViewById(R.id.rateBar);
        profName = (EditText) findViewById(R.id.profName);
        ratingText = (EditText) findViewById(R.id.ratingText);

        submitRating = (Button) findViewById(R.id.submitRating);

        Intent intent = getIntent();

        courseID = intent.getStringExtra(CourseLanding.COURSE_ID);
        courseName = intent.getStringExtra(CourseLanding.COURSE_NAME_RATING);
        schoolName = intent.getStringExtra(CourseLanding.SCHOOL_NAME_RATING);

        SharedPreferences sp = getSharedPreferences("login", MODE_PRIVATE);
        username = sp.getString("username", "");
        userid = sp.getString("userid", "");
        reviewNum = sp.getInt("reviewNum", 0);

        courseNameRate.setText(courseName);
        schoolNameRate.setText(schoolName);

        List<String> grades = new ArrayList<>(Arrays.asList("A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F", "N/A"));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, grades);

        spinnerGrades.setAdapter(adapter);

        submitRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRating();
            }
        });
    }

    private void addRating() {
        String grade = spinnerGrades.getSelectedItem().toString();
        int rate = (int) rateBar.getRating();
        String prof = profName.getText().toString().trim();
        String text = ratingText.getText().toString().trim();

        if (!TextUtils.isEmpty(grade) || !TextUtils.equals(Integer.toString(rate), "0") || !TextUtils.isEmpty(prof) || !TextUtils.isEmpty(text)) {
            String id = databaseRatings.push().getKey();
            Rating rating = new Rating(courseID, rate, grade, prof, text, username);
            databaseRatings.child(id).setValue(rating);

            // update user review count
            databaseUsers.child(userid).child("userNumberOfReviews").setValue(reviewNum + 1);

            // redirect user back to course landing page after rating is created
            Intent intent = new Intent(NewRating.this, HomePage.class);
            startActivity(intent);

            Toast.makeText(this, "Rating added.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Please fill all fields.", Toast.LENGTH_LONG).show();
        }
    }
}