package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStream;
import java.util.Date;

import edu.gatech.cs2340.nonprofitdonationtracker.R;

public class OpeningScreen extends AppCompatActivity {

    Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_screen);

        model = Model.getInstance();

        //readCSVFile();
        Log.d("Locations", Location.locationList.toString());
        initData();

    }

    //Broken, need to fix
    private void readCSVFile() {
        Log.d("Help1", getResources().openRawResource(R.raw.location_data).toString());
        InputStream csvStream = getResources().openRawResource(R.raw.location_data);
        Location.parseCSV(csvStream);
    }

    private void initData() {
        DatabaseReference dbr = FirebaseDatabase.getInstance().getReference();
        dbr.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snap : dataSnapshot.child("users").getChildren()) {
                    String email = (String) snap.child("email").getValue();
                    String name = (String) snap.child("name").getValue();
                    String password = (String) snap.child("password").getValue();
                    String type = (String) snap.child("userType").getValue();
                    UserInfo.addUserToLocal(name, email, password, type);
                }

                for (DataSnapshot snap : dataSnapshot.child("locations").getChildren()) {
                    String city = (String) snap.child("city").getValue();
                    double latitude = (Double) snap.child("latitude").getValue();
                    double longitude = (Double) snap.child("longitude").getValue();
                    String street = (String) snap.child("street").getValue();
                    String state = (String) snap.child("state").getValue();
                    String name = (String) snap.child("name").getValue();
                    String website = (String) snap.child("website").getValue();
                    int key = ((Long) snap.child("key").getValue()).intValue();
                    int zipcode = ((Long) snap.child("zipcode").getValue()).intValue();
                    LocationType type = LocationType.valueOf(
                            (String) snap.child("type").getValue());
                    long phone = (Long) snap.child("phone").getValue();

                    Location location = new Location(key, name, latitude, longitude, street, city,
                                street, zipcode, type, phone, website);

                    for (DataSnapshot donationsSnap : snap.child("donations").getChildren()) {
                        String donationName = (String) donationsSnap.child("name").getValue();
                        long timestamp = (Long) donationsSnap.child("date").getValue();
                        String shortDescription = (String) donationsSnap.child(
                                "shortDescription").getValue();
                        String longDescription = (String) donationsSnap.child(
                                "longDescription").getValue();
                        float value = ((Long) donationsSnap.child("value").getValue()).floatValue();
                        Category category = Category.valueOf(
                                (String) donationsSnap.child("category").getValue());
                        Donation donation = new Donation(donationName,
                                shortDescription, longDescription
                                , value, category, new Date(timestamp));
                        location.addDonationLocal(donation);
                    }
                    Log.d("blue", location.toString());
                    Location.addLocationLocal(location);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("InitError", databaseError.getMessage());
            }
        });
    }
    public void onClickLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void onClickRegister(View view) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    public void onClickGuest(View view) {
        Intent intent = new Intent(this, HomePageActivity.class);
        String email = "guest";
        model.setUserInfo(email);
        intent.putExtra("user_email", email);
        intent.putExtra("user_type", UserInfo.getUserType(email));
        startActivity(intent);
    }
}
