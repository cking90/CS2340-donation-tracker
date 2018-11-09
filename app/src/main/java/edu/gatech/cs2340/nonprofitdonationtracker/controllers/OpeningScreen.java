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

import java.util.Date;
import java.util.Objects;

import edu.gatech.cs2340.nonprofitdonationtracker.R;

/**
 * Controller for the launch activity of the application.
 * Provides the user with the option to either sign in or create
 * a new account.
 *
 */
public class OpeningScreen extends AppCompatActivity {

    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_screen);

        model = Model.getInstance();

        //readCSVFile();
        Log.d("Locations", Location.getLocationList().toString());
        initData();

    }

//    private void readCSVFile() {
//        Log.d("Help1", getResources().openRawResource(R.raw.location_data).toString());
//        InputStream csvStream = getResources().openRawResource(R.raw.location_data);
//        Location.parseCSV(csvStream);
//    }

    private void initData() throws NullPointerException {
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
                    double latitude = (double) snap.child("latitude").getValue();
                    double longitude = (double) snap.child("longitude").getValue();
                    String street = (String) snap.child("street").getValue();
                    String state = (String) snap.child("state").getValue();
                    String name = (String) snap.child("name").getValue();
                    String website = (String) snap.child("website").getValue();
                    int key = ((Long) Objects.requireNonNull(snap.child("key").getValue())).intValue();
                    int zipcode = ((Long) Objects.requireNonNull(snap.child("zipcode").getValue())).intValue();
                    LocationType type = LocationType.valueOf(
                            (String) snap.child("type").getValue());
                    long phone = (long) snap.child("phone").getValue();

                    Location location = new Location(key, name, latitude, longitude, street, city,
                                state, zipcode, type, phone, website);

                    for (DataSnapshot donationsSnap : snap.child("donations").getChildren()) {
                        String donationName = (String) donationsSnap.child("name").getValue();
                        long timestamp = (long) donationsSnap.child("date").getValue();
                        String shortDescription = (String) donationsSnap.child(
                                "shortDescription").getValue();
                        String longDescription = (String) donationsSnap.child(
                                "longDescription").getValue();
                        float value = ((Long) Objects.requireNonNull(donationsSnap.child("value").getValue())).floatValue();
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

    /**
     * Controller for the Login Button within
     * the activity_login_screen layout file
     * @param view the button
     */
    public void onClickLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Controller for the register button within the
     * activity_login_screen layout file
     * @param view the button
     */
    public void onClickRegister(View view) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    /**
     * Controller for the guest button within the
     * activity_login_screen layout file
     * @param view the button
     */
    public void onClickGuest(View view) {
        Intent intent = new Intent(this, HomePageActivity.class);
        String email = "guest";
        model.setUserInfo(email);
        intent.putExtra("user_email", email);
        intent.putExtra("user_type", UserInfo.getUserType(email));
        startActivity(intent);
    }
}
