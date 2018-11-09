package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Model class representing a user within the
 * application
 */
public class UserInfo {
    private static final Map<String, List<String>> loginInfo = new HashMap<>();

    /**
     * Creates a new User with the passed in information
     * and adds the user to the database and local static
     * copy of all users
     *
     * @param name the user's name
     * @param email the user's email address
     * @param password the user's password
     * @param userType the user's UserType used to
     *                 decide access to application features
     */
    public static void addNewUser(String name, String email, String password, String userType) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        List<String> userInfo = new ArrayList<>();
        userInfo.add(name);
        userInfo.add(password);
        userInfo.add(userType);
        String email2 = email.replace(".",",");
        loginInfo.put(email, userInfo);
        database.child("users").child(email2).child("name").setValue(name);
        database.child("users").child(email2).child("email").setValue(email);
        database.child("users").child(email2).child("password").setValue(password);
        database.child("users").child(email2).child("userType").setValue(userType);
    }

    /**
     * Adds a new user to local reference to all users.
     * Used when reading data from the fire base
     *
     * @param name the user's name
     * @param email the user's email address
     * @param password the user's password
     * @param userType the user's UserType used to
     *                 decide access to application features
     */
    public static void addUserToLocal(String name, String email, String password, String userType) {
            List<String> userInfo = new ArrayList<>();
            userInfo.add(name);
            userInfo.add(password);
            userInfo.add(userType);
            loginInfo.put(email, userInfo);
    }

    /**
     * Gets the list of users and their login credentials
     * @return a hash map mapping a user's email to a list of
     * the user's instance variables
     */
    public static Map<String, List<String>> getLoginInfo() {
        return UserInfo.loginInfo;
    }

    /**
     * Determines if a user email exists in the set
     * of current users
     * @param email to be checked
     * @return boolean representing if user email
     * is currently registered as an account
     */
    public static boolean containsKey(String email) {
        return loginInfo.containsKey(email);
    }

    /**
     * Determines if the passed in password is within the
     * values associated with the passed in email
     *
     * @param email of the user account
     * @param password candidate provided by user
     * @return boolean whether password is correct
     *         given the passed in email
     */
    public static boolean correctPassword(String email, String password) {
        if (containsKey(email)) {
            List<String> temp = loginInfo.get(email);
            return password.equals(temp.get(1));
        }
        return false;

    }

    /**
     * getter for the userType given the passed in email.
     * 
     * @param email of the user account
     * @return the UserType associated with the User with
     * the passed in email
     */
    public static String getUserType(String email) {
        if (containsKey(email)) {
            List<String> temp = loginInfo.get(email);
            return temp.get(2);
        }
        return null;
    }


}
