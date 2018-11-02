package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

public enum UserType {
    ADMIN("Admin"),
    MANAGER("Manager"),
    LOCATION_EMPLOYEE("Location Employee"),
    GUEST_USER("Guest User");

    private final String userTypeInString;

    UserType(String userTypeInString) {
        this.userTypeInString = userTypeInString;
    }

    @Override
    public String toString() {
        return userTypeInString;
    }
}