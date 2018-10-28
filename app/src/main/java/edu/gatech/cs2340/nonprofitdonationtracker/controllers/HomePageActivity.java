package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import edu.gatech.cs2340.nonprofitdonationtracker.R;

public class HomePageActivity extends AppCompatActivity {

    private String userEmail;
    Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        model = Model.getInstance();
        userEmail = model.getCurrentUserEmail();

        TextView welcomeText = this.findViewById(R.id.welcomeTxtView);
        String welcomeMsg = String.format("Welcome %s!", userEmail.split("@")[0]);
        welcomeText.setText(welcomeMsg);

    }

    public void onClickViewLocations(View view) {
        Bundle extras = getIntent().getExtras();
        Intent intent = new Intent(this, ViewLocationsActivity.class);
        intent.putExtra("user_email", userEmail);
        intent.putExtra("user_type", model.getCurrentUserType());
        startActivity(intent);
    }
    public void onClickLogout(View view) {
        Intent intent = new Intent(this, OpeningScreen.class);
        startActivity(intent);
    }

    public void onClickSearchDonations(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
}
