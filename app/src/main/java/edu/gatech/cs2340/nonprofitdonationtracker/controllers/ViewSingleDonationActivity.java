package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

import edu.gatech.cs2340.nonprofitdonationtracker.R;

public class ViewSingleDonationActivity extends AppCompatActivity {

    private TextView name;
    private TextView shortDescription;
    private TextView longDescription;
    private TextView price;
    private TextView category;
    private TextView date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_single_donation);
        Bundle extras = getIntent().getExtras();

        name = findViewById(R.id.nameTextView);
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

    public void onBackPressed(View view) {
        Bundle extras = getIntent().getExtras();
        Intent intent = new Intent(this, ViewAllDonationsActivity.class);
        intent.putExtra("location_id", extras.getInt("location_id"));
        intent.putExtra("user_type", extras.getString("user_type"));
        startActivity(intent);
    }
}