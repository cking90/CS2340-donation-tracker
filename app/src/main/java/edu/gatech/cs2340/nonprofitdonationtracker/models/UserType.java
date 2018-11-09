package edu.gatech.cs2340.nonprofitdonationtracker.models;

/**
 * Enumeration of UserTypes used to
 * delegate access rights of various
 * users
 */
public enum UserType {
    ADMIN("Admin"),
    MANAGER("Manager"),
    LOCATION_EMPLOYEE("Location Employee"),
    GUEST_USER("Guest User");

    private final String userTypeInString;

    /**
     * Constructor for the UserType Enum that provides
     * a formatted string instance variable that represents
     * the enum type
     * @param userTypeInString UserType String representation
     */
    UserType(String userTypeInString) {
        this.userTypeInString = userTypeInString;
    }

    @Override
    public String toString() {
        return userTypeInString;
    }
}