package com.example.ratemycourse;

public class Rating {

    private String courseID;
    private int rating;
    private String gradeReceived;
    private String currentProf;
    private String ratingText;

    public Rating() {

    }

    public Rating(String ratingID, int rating, String gradeReceived, String currentProf, String ratingText) {
        this.courseID = ratingID;
        this.rating = rating;
        this.gradeReceived = gradeReceived;
        this.currentProf = currentProf;
        this.ratingText = ratingText;
    }

    public String getCourseID() {
        return courseID;
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
