package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

/**
 * Created by Neha on 9/29/2018.
 */

public enum UserType {
    ADMIN("Admin"),
    MANAGER("Manager"),
    LOCATION_EMPLOYEE("Location Employee"),
    GUEST_USER("Guest User");

    private String userTypeInString;

    private UserType(String userTypeInString) {
        this.userTypeInString = userTypeInString;
    }

    @Override
    public String toString() {
        return userTypeInString;
    }
}

