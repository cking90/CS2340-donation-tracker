package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Neha on 9/29/2018.
 * This class is TEMPORARY
 * It stores User info in a HashMap where the key = email id
 * and the values = List containing name, password & user account type
 */
public class UserInfo {
    private static Map<String, List<String>> loginInfo = new HashMap<>();

    private static DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public static void addNewUser(String name, String email, String password, String userType) {
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

    public static void addUserToLocal(String name, String email, String password, String userType) {
            List<String> userInfo = new ArrayList<>();
            userInfo.add(name);
            userInfo.add(password);
            userInfo.add(userType);
            loginInfo.put(email, userInfo);
    }

    public static Map<String, List<String>> getLoginInfo() {
        return UserInfo.loginInfo;
    }

    public static boolean containsKey(String email) {
        return loginInfo.containsKey(email);
    }

    public static boolean correctPassword(String email, String password) {
        if (containsKey(email)) {
            List<String> temp = loginInfo.get(email);
            return password.equals(temp.get(1));
        }
        return false;

    }

    public static String getUserType(String email) {
        if (containsKey(email)) {
            List<String> temp = loginInfo.get(email);
            return temp.get(2);
        }
        return null;
    }


}
