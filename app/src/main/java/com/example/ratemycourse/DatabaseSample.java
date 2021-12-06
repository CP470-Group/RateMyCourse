package com.example.ratemycourse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DatabaseSample extends AppCompatActivity {

    public static final String SCHOOL_ID = "schoolid";
    public static final String SCHOOL_NAME = "schoolname";

    EditText editTextName;
    Spinner spinnerProvinces;
    EditText editTextCity;
    EditText editTextEmail;
    EditText editTextAddress;
    EditText editTextPhone;
    EditText editTextPostalCode;
    Button buttonAddSchool;

    List<School> schoolList;

    DatabaseReference databaseSchools;

    ListView listViewSchools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_sample);

        databaseSchools = FirebaseDatabase.getInstance().getReference("schools");

        editTextName = (EditText) findViewById(R.id.editTextName);
        spinnerProvinces = (Spinner) findViewById(R.id.spinnerProvinces);
        editTextCity = (EditText) findViewById(R.id.editTextCity);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextAddress = (EditText) findViewById(R.id.editTextAddress);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextPostalCode = (EditText) findViewById(R.id.editTextPostalCode);

        buttonAddSchool = (Button) findViewById(R.id.buttonAddSchool);

        schoolList = new ArrayList<>();

        buttonAddSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSchool();
            }
        });

//        listViewSchools.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                School school = schoolList.get(i);
//                Intent intent = new Intent(getApplicationContext(), AddCourseActivity.class);
//                intent.putExtra(SCHOOL_ID, school.getSchoolID());
//                intent.putExtra(SCHOOL_NAME, school.getSchoolName());
//                startActivity(intent);
//            }
//        });

//        listViewSchools.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                School school = schoolList.get(i);
//                showUpdateDialog(school.getSchoolID(), school.getSchoolName() );
//                return true;
//            }
//        });
    }

    @Override
    protected void onStart() {
        super.onStart();

//        databaseSchools.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                schoolList.clear();
//                for (DataSnapshot schoolSnapshot: dataSnapshot.getChildren()) {
//                    School school = schoolSnapshot.getValue(School.class);
//                    schoolList.add(school);
//                }
//                SchoolList adapter = new SchoolList(DatabaseSample.this, schoolList);
//                listViewSchools.setAdapter(adapter);
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }

    private void addSchool() {

        String name = editTextName.getText().toString().trim();
        String province = spinnerProvinces.getSelectedItem().toString();
        String city = editTextCity.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String postalCode = editTextPostalCode.getText().toString().trim();

        if (!TextUtils.isEmpty(name)) {
            String id = databaseSchools.push().getKey();
            School school = new School(id, name, province, city, email, address, phone, postalCode);
            databaseSchools.child(id).setValue(school);



            Toast.makeText(this, "School added.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Please enter name.", Toast.LENGTH_LONG).show();
        }
    }

    private void showUpdateDialog(String schoolID, String schoolName) {

        // create alert dialog builder
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        // inflate xml layout
        LayoutInflater inflater = getLayoutInflater();

        // create view for dialog and inflate
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);

        // set view for dialog builder as the inflated view
        dialogBuilder.setView(dialogView);

        final EditText updateSchoolName = (EditText) dialogView.findViewById(R.id.updateSchoolName);
        final Spinner updateSpinner = (Spinner) dialogView.findViewById(R.id.updateSpinnerSchool);
        final EditText updateSchoolCity = (EditText) dialogView.findViewById(R.id.updateSchoolCity);
        final EditText updateSchoolEmail = (EditText) dialogView.findViewById(R.id.updateSchoolEmail);
        final EditText updateSchoolAddress = (EditText) dialogView.findViewById(R.id.updateSchoolAddress);
        final EditText updateSchoolPhone = (EditText) dialogView.findViewById(R.id.updateSchoolPhone);
        final EditText updateSchoolPostalCode = (EditText) dialogView.findViewById(R.id.updateSchoolPostalCode);

        final Button updateButton = (Button) dialogView.findViewById(R.id.buttonUpdateSchool);
        final Button deleteButton = (Button) dialogView.findViewById(R.id.buttonDelete);

        dialogBuilder.setTitle("Updating School: " + schoolName);

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = updateSchoolName.getText().toString().trim();
                String province = updateSpinner.getSelectedItem().toString();
                String city = updateSchoolCity.getText().toString().trim();
                String email = updateSchoolEmail.getText().toString().trim();
                String address = updateSchoolAddress.getText().toString().trim();
                String phone = updateSchoolPhone.getText().toString().trim();
                String postalCode = updateSchoolPostalCode.getText().toString().trim();
                if (!TextUtils.isEmpty(name)) {
                    updateSchool(schoolID, name, province, city, email, address, phone, postalCode);
                    alertDialog.dismiss();
                } else {
                    updateSchoolName.setError("Name is required");
                    return;
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteSchool(schoolID);
            }
        });
    }

    private void deleteSchool(String id) {
        DatabaseReference deleteSchools = FirebaseDatabase.getInstance().getReference("schools").child(id);
        DatabaseReference deleteCourses = FirebaseDatabase.getInstance().getReference("courses").child(id);

        deleteSchools.removeValue();
        deleteCourses.removeValue();

        Toast.makeText(this, "School is deleted", Toast.LENGTH_LONG).show();
    }

    public boolean updateSchool(String id, String name, String province, String city, String email, String address, String phone, String postalCode) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("schools").child(id);
        School school = new School(id, name, province, city, email, address, phone, postalCode);
        databaseReference.setValue(school);
        Toast.makeText(this, "School Updated", Toast.LENGTH_LONG).show();
        return true;
    }

}