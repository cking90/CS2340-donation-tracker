package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import edu.gatech.cs2340.nonprofitdonationtracker.R;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //populating spinner with values from enum
        Spinner userType = (Spinner)findViewById(R.id.user_type_spinner_id);
        userType.setAdapter(new ArrayAdapter<UserType>(this, android.R.layout.simple_spinner_item, UserType.values()));
    }



    public void onClickCancel(View view) {
        Intent intent = new Intent(this, OpeningScreen.class);
        startActivity(intent);
    }

    private boolean updateUserInformation(View view) {
        //getting user entered values from the edit text entry boxes
        EditText name = (EditText)findViewById(R.id.name_edit_text_id);
        Editable name_value = name.getText();
        EditText email = (EditText)findViewById(R.id.email_edit_text_id);
        Editable email_value = email.getText();
        EditText password = (EditText)findViewById(R.id.password_edit_text_id);
        Editable password_value = password.getText();
        //get user type
        Spinner userType = (Spinner)findViewById(R.id.user_type_spinner_id);
        String selected_user_type = userType.getSelectedItem().toString();
        if ((email_value.toString() + "").equals("")
                || (name_value.toString() + "").equals("")
                || (password_value.toString() + "").equals("")) {
            Toast toast = Toast.makeText(getApplicationContext()
                    , "All fields are required to register", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        if (selected_user_type.equals("Guest User")) {
            Toast toast = Toast.makeText(getApplicationContext()
                    , "Cannot register as a guest user", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        if (!UserInfo.containsKey(email_value.toString())) {
            UserInfo.addNewUser(name_value.toString(), email_value.toString(), password_value.toString(), selected_user_type);
            return true;
        } else {
            Toast toast = Toast.makeText(getApplicationContext()
                        , "Email already registered to an account.", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }

    }

    public void onClickRegister(View view) {
        if (updateUserInformation(view)) {
            //takes user back to login page so that they can login with their credentials
            //TODO: set up validation in the LoginActivity class
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

    }
}
