package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

import java.util.Objects;

import edu.gatech.cs2340.nonprofitdonationtracker.R;

/**
 * Provides user with information about a donation
 */
public class ViewSingleDonationActivity extends AppCompatActivity {

    private Model model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = Model.getInstance();
        setContentView(R.layout.activity_view_single_donation);
        Bundle extras = getIntent().getExtras();

        TextView name;
        TextView shortDescription;
        TextView longDescription;
        TextView price;
        TextView category;
        TextView date;

        name = findViewById(R.id.nameTextView);
        assert extras != null;
        name.setText(extras.getString("donation_Name"));
        shortDescription = findViewById(R.id.shortDescriptionTextView);
        shortDescription.setText(extras.getString("donation_ShortDescription"));
        longDescription = findViewById(R.id.longDescriptionTextView);
        longDescription.setText(extras.getString("donation_LongDescription"));
        price = findViewById(R.id.priceTextView);
        price.setText(extras.getString("donation_Value"));
        category = findViewById(R.id.categoryTextView);
        category.setText(extras.getString("donation_Category"));
        date = findViewById(R.id.dateTextView);
        date.setText(extras.getString("donation_Date"));
    }

    /**
     * Controller for the back button on the associated
     * layout file. Brings the user back to the location
     * that currently holds the ite,
     * @param view the back button
     */
    public void onBackPressed(View view) {
        Bundle extras = getIntent().getExtras();
        Intent intent = new Intent(this, ViewSingleLocationActivity.class);
        intent.putExtra("location_id", Objects.requireNonNull(extras).getInt("location_id"));
        intent.putExtra("user_type", model.getCurrentUserType());
        startActivity(intent);
    }
}