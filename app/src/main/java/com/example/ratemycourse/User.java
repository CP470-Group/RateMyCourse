package com.example.ratemycourse;

import android.media.Image;

public class User {
    String username;
    String email;
    String userFullName;
    String userMajor;
    float userRating;
    int userNumberOfReviews;
    int userNumberOfEndorsements;
    Image userProfilePicture;
    //MAYBE MAKE THESE OPTIONAL DISCUSS AT SOME POINT
    String interests;

    public User(String username, String email, String userFullName, String userMajor, float userRating, int userNumberOfReviews, int userNumberOfEndorsements, Image userProfilePicture){
        this.username = username;
        this.email = email;
        this.userFullName = userFullName;
        this.userMajor = userMajor;
        this.userRating = userRating;
        this.userNumberOfReviews = userNumberOfReviews;
        this.userNumberOfEndorsements = userNumberOfEndorsements;
        this.userProfilePicture = userProfilePicture;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserMajor() {
        return userMajor;
    }

    public void setUserMajor(String userMajor) {
        this.userMajor = userMajor;
    }

    public float getUserRating() {
        return userRating;
    }

    public void setUserRating(float userRating) {
        this.userRating = userRating;
    }

    public int getUserNumberOfReviews() {
        return userNumberOfReviews;
    }

    public void setUserNumberOfReviews(int userNumberOfReviews) {
        this.userNumberOfReviews = userNumberOfReviews;
    }

    public int getUserNumberOfEndorsements() {
        return userNumberOfEndorsements;
    }

    public void setUserNumberOfEndorsements(int userNumberOfEndorsements) {
        this.userNumberOfEndorsements = userNumberOfEndorsements;
    }

    public Image getUserProfilePicture() {
        return userProfilePicture;
    }

    public void setUserProfilePicture(Image userProfilePicture) {
        this.userProfilePicture = userProfilePicture;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

}
