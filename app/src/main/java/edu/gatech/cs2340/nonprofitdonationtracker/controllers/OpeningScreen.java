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

import edu.gatech.cs2340.nonprofitdonationtracker.R;

public class OpeningScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_screen);
        readCSVFile();
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
}
