package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

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

    public boolean isValidLogin(String email, String password) {
        if (!loginInfo.containsKey(email))
            return false;
        else {
            if (!password.equals(loginInfo.get(email).get(1))) {
                return false;
            }
        }
        return true;
    }

    public void addNewUser(String name, String email, String password, String userType) {
        ArrayList<String> info = new ArrayList<>();
        info.add(name);
        info.add(password);
        info.add(userType);
        loginInfo.put(email, info);

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
