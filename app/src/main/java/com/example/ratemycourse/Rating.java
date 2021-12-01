package com.example.ratemycourse;

public class Rating {

    private String ratingID;
    private int rating;
    private String gradeReceived;
    private String currentProf;
    private String ratingText;

    public Rating() {

    }

    public Rating(String ratingID, int rating, String gradeReceived, String currentProf, String ratingText) {
        this.ratingID = ratingID;
        this.rating = rating;
        this.gradeReceived = gradeReceived;
        this.currentProf = currentProf;
        this.ratingText = ratingText;
    }

    public String getRatingID() {
        return ratingID;
    }

    public int getRating() {
        return rating;
    }

    public String getGradeReceived() {
        return gradeReceived;
    }

    public String getCurrentProf() {
        return currentProf;
    }

    public String getRatingText() {
        return ratingText;
    }
 }
