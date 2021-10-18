package com.example.ratemycourse;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import java.util.ArrayList;

//This activity is where the user is going to search for the course they want to write or read reviews for
public class ClassSearch extends AppCompatActivity {

    //Class variables
    SearchView searchView;
    ListView listView;
    Button newReview;
    Button viewReviews;
    ArrayList<String> list;
    ArrayAdapter<String > adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_search);

        //Find the views by there ids
        searchView = (SearchView) findViewById(R.id.searchCourses);
        listView = (ListView) findViewById(R.id.listOfCourses);
        newReview = (Button) findViewById(R.id.writeNewReview);
        viewReviews = (Button) findViewById(R.id.viewReviews);

        //This is temporary data while setting up the visual aspects of the activity.
        //The final product will be searching the db for the courses offered by the chosen school
        list = new ArrayList<>();
        list.add("CP104");
        list.add("CP164");
        list.add("CP213");
        list.add("CP264");

        //Using simple_list_item_1 as it is all we need for the basic layout
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

        //Adding the listener to the searchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //Setting up some basic logic for searching for each school.
            //This is not the finished product of course as we don't have the db setup and navigation to each school setup
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(list.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(ClassSearch.this, "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        //Adding the listener for write new review button for selected course
        newReview.setOnClickListener(new View.OnClickListener(){
            @Override
            //Sends user to write new review activity
            public void onClick(View view) {
                //Will be implemented later
            }
        });

        //Adding the listener for view reviews button
        viewReviews.setOnClickListener(new View.OnClickListener(){
            @Override
            //Sends user to view reviews activity for selected course
            public void onClick(View view) {
                //Will be implemented later
            }
        });
    }
}
