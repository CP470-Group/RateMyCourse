package com.example.ratemycourse;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;


public class UserProfile extends AppCompatActivity {
    TextView fullName;
    TextView username;
    TextView userRating;
    TextView numberOfEndorsements;
    TextInputEditText fullNameField;
    TextInputEditText emailField;
    TextInputEditText majorField;
    TextInputEditText interestsField;
    Button updateProfileButton;
    Button deleteProfileButton;

    DatabaseReference databaseUsers;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        //Getting all the users information from the db and email used to login
        databaseUsers = FirebaseDatabase.getInstance().getReference("users");
        user = (User)getIntent().getSerializableExtra("user");
        String fullNameText = user.getUserFullName();
        String userNameText = user.getUsername();
        String userRatingText = String.valueOf(user.getUserRating());
        String numberOfEndorsementsText = String.valueOf(user.getUserNumberOfEndorsements());
        String emailText = user.getEmail();
        String majorText = user.getUserMajor();
        String interestsText = user.getInterests();


        fullName = findViewById(R.id.userFullNameTitle);
        username = findViewById(R.id.userNameTitle);
        userRating = findViewById(R.id.userRating);
        numberOfEndorsements = findViewById(R.id.numberOfEndorsements);
        fullNameField = findViewById(R.id.fullNameFieldProfile);
        emailField = findViewById(R.id.emailFieldProfile);
        majorField = findViewById(R.id.majorFieldProfile);
        interestsField = findViewById(R.id.interestsFieldProfile);
        updateProfileButton = findViewById(R.id.updateProfileButton);
        deleteProfileButton = findViewById(R.id.deleteProfileButton);

        //Populating all of the information onto the layout
        fullName.setText(fullNameText);
        username.setText(userNameText);
        userRating.setText(userRatingText);
        numberOfEndorsements.setText(numberOfEndorsementsText);
        fullNameField.setText(fullNameText);
        emailField.setText(emailText);
        majorField.setText(majorText);
        interestsField.setText(interestsText);

        updateProfileButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String fullNameUpdate = fullNameField.getText().toString().trim();
                String emailUpdate = emailField.getText().toString().trim();
                String majorUpdate = majorField.getText().toString().trim();
                String interestsUpdate = interestsField.getText().toString().trim();
                updateUser(user.getId(), user.getUsername(), emailUpdate, fullNameUpdate, majorUpdate, user.getPassword(), user.getUserRating(), user.getUserNumberOfReviews(), user.getUserNumberOfEndorsements(), interestsUpdate );

            }
        });

        deleteProfileButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                deleteUser(user.getId());
                Intent intent = new Intent(UserProfile.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean updateUser(String id, String username, String email, String userFullName, String userMajor, String password, float userRating, int userNumberOfReviews, int userNumberOfEndorsements, String interests ){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users").child(id);
        User user = new User(id, username, email , userFullName , userMajor , password, userRating, userNumberOfReviews , userNumberOfEndorsements, interests);
        databaseReference.setValue(user);
        Toast.makeText(this, "User Updated", Toast.LENGTH_LONG).show();
        return true;
    }

    public void deleteUser(String id){
        DatabaseReference deleteUser = FirebaseDatabase.getInstance().getReference("users").child(id);
        deleteUser.removeValue();
        Toast.makeText(this, "User is deleted", Toast.LENGTH_LONG).show();
    }
}