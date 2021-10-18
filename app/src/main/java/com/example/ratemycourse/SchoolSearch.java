package com.example.ratemycourse;

//Imports
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import java.util.ArrayList;

//This activity is where the user is going to search for the school they want to find a course for
public class SchoolSearch extends AppCompatActivity {

    //Class variables
    SearchView searchView;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String > adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_search);

        //Find the views by there ids
        searchView = (SearchView) findViewById(R.id.searchSchools);
        listView = (ListView) findViewById(R.id.listOfSchools);

        //This is temporary data while setting up the visual aspects of the activity.
        //The final product will be searching the db for the required data
        list = new ArrayList<>();
        list.add("WLU");
        list.add("Queens");
        list.add("MUN");

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
                    Toast.makeText(SchoolSearch.this, "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}
