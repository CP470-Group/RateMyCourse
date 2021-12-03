package com.example.ratemycourse;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class RatingList extends ArrayAdapter<Rating> {

    private Activity context;
    private List<Rating> ratingList;

    public RatingList(Activity context, List<Rating> ratingList) {
        super(context, R.layout.list_layout, ratingList);
        this.context = context;
        this.ratingList = ratingList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.rating_list_layout, null, true);

        TextView prof = (TextView) listViewItem.findViewById(R.id.prof);
        RatingBar rate = (RatingBar) listViewItem.findViewById(R.id.rate);
        TextView grade = (TextView) listViewItem.findViewById(R.id.grade);
        TextView ratingText = (TextView) listViewItem.findViewById(R.id.rating);

        Rating rating = ratingList.get(position);

        prof.setText(rating.getCurrentProf());
        rate.setRating(rating.getRating());
        grade.setText(rating.getGradeReceived());
        ratingText.setText(rating.getRatingText());

        return listViewItem;
    }
}
