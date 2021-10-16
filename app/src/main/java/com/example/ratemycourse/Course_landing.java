package com.example.ratemycourse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Course_landing extends AppCompatActivity {

    //button for the new review
    // this button will bring us to the new Review page
    //user can enter a new review based on the course selected.
    private Button new_review;
    private TextView scroll;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_landing);
        //reference to my button.
        new_review=(Button) findViewById(R.id.newRev);

        //next need to add listener that will take new to the new review activity
        //need to add a layout in order to constraint the buttons.

        //this should make my text view scroll
        //scroll=(TextView) findViewById(R.id.scroll);
        //scroll.setMovementMethod(new ScrollingMovementMethod());


        //this should take me to the new review page where you can add your review for the course.
        new_review.setOnClickListener(new View.OnClickListener() {
            @Override
            //open the new review page.
            public void onClick(View view) {
                Intent intent= new Intent(Course_landing.this,new_review.class);
                startActivity(intent);

            }
        });




    }



}