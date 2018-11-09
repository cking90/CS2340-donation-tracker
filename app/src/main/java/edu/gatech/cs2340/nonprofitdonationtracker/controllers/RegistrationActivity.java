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
import edu.gatech.cs2340.nonprofitdonationtracker.models.UserInfo;
import edu.gatech.cs2340.nonprofitdonationtracker.models.UserType;

import android.widget.Toast;

/**
 * Controller for the activity_registration layout file.
 * Allows the user to create a new account.
 */
public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //populating spinner with values from enum
        Spinner userType = findViewById(R.id.user_type_spinner_id);
        userType.setAdapter(new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, UserType.values()));
    }


    /**
     * Controller for the cancel button. Returns the
     * user back to the OpeningScreenActivity
     * @param view the button being clicked
     */
    public void onClickCancel(View view) {
        Intent intent = new Intent(this, OpeningScreen.class);
        startActivity(intent);
    }

    private boolean updateUserInformation(View view) {
        //getting user entered values from the edit text entry boxes
        EditText name = findViewById(R.id.name_edit_text_id);
        Editable name_value = name.getText();
        EditText email = findViewById(R.id.email_edit_text_id);
        Editable email_value = email.getText();
        EditText password = findViewById(R.id.password_edit_text_id);
        Editable password_value = password.getText();
        //get user type
        Spinner userType = findViewById(R.id.user_type_spinner_id);

        Object userTypeField = userType.getSelectedItem();
        String selected_user_type = userTypeField.toString();
        if (("").equals(email_value.toString() + "")
                || ("").equals(name_value.toString() + "")
                || ("").equals(password_value.toString() + "")) {
            Toast toast = Toast.makeText(getApplicationContext()
                    , "All fields are required to register", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        if (("Guest User").equals(selected_user_type)) {
            Toast toast = Toast.makeText(getApplicationContext()
                    , "Cannot register as a guest user", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        if (!UserInfo.containsKey(email_value.toString())) {
            UserInfo.addNewUser(name_value.toString(), email_value.toString(),
                    password_value.toString(), selected_user_type);
            return true;
        } else {
            Toast toast = Toast.makeText(getApplicationContext()
                        , "Email already registered to an account.", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }

    }

    /**
     * Creates the new account depending if the information is
     * deemed to be valid. If the account is not valid or available for
     * creation, the user is notified. If the account is successfully, the
     * user is brought to the LoginActivity.
     *
     * @param view the register button being selected
     */
    public void onClickRegister(View view) {
        if (updateUserInformation(view)) {
            //takes user back to login page so that they can login with their credentials
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

    }
}
