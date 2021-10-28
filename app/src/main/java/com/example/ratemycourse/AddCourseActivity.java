package com.example.ratemycourse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddCourseActivity extends AppCompatActivity {

    TextView textViewSchoolName;
    EditText editTextCourseName;
    EditText editTextCourseInstructor;
    EditText editTextCourseDepartment;
    EditText editTextCourseYear;
    SeekBar seekbarRating;
    Button buttonAddCourse;

    ListView listViewCourses;
    List<Course> courseList;

    DatabaseReference databaseCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        textViewSchoolName = (TextView) findViewById(R.id.textViewSchoolName);
        editTextCourseName = (EditText) findViewById(R.id.editTextCourseName);
        editTextCourseInstructor = (EditText) findViewById(R.id.editTextCourseInstructor);
        editTextCourseDepartment = (EditText) findViewById(R.id.editTextCourseDepartment);
        editTextCourseYear = (EditText) findViewById(R.id.editTextCourseYear);
        seekbarRating = (SeekBar) findViewById(R.id.seekBarRating);
        buttonAddCourse = (Button) findViewById(R.id.buttonAddCourse);

        listViewCourses = (ListView) findViewById(R.id.listViewCourses);
        courseList = new ArrayList<>();

        Intent intent = getIntent();

        String id = intent.getStringExtra(DatabaseSample.SCHOOL_ID);
        String name = intent.getStringExtra(DatabaseSample.SCHOOL_NAME);

        textViewSchoolName.setText(name);

        databaseCourses = FirebaseDatabase.getInstance().getReference("courses").child(id);

        buttonAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveCourse();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseCourses.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                courseList.clear();
                for (DataSnapshot courseSnapshot: dataSnapshot.getChildren()) {
                    Course course = courseSnapshot.getValue(Course.class);
                    courseList.add(course);
                }
                CourseList courseListAdapter = new CourseList(AddCourseActivity.this, courseList);
                listViewCourses.setAdapter(courseListAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void saveCourse() {
        String courseName = editTextCourseName.getText().toString().trim();
        int rating = seekbarRating.getProgress();
        String currentInstructor = editTextCourseInstructor.getText().toString().trim();
        String department = editTextCourseDepartment.getText().toString().trim();
        String year = editTextCourseYear.getText().toString().trim();

        if (!TextUtils.isEmpty(courseName)) {
            String id = databaseCourses.push().getKey();
            Course course = new Course(id, courseName, rating, currentInstructor, department, year);
            databaseCourses.child(id).setValue(course);
            Toast.makeText(this, "Course saved successfully.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Please add a course name.", Toast.LENGTH_LONG).show();
        }
    }
}