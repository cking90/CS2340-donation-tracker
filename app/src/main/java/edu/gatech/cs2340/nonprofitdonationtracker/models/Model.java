package edu.gatech.cs2340.nonprofitdonationtracker.models;

import java.util.List;
import java.util.Map;

/**
 * The Model class handles user info updates and information retrieval
 */
public final class Model {

    /**
     * the facade maintains references to any required model classes
     */

    private List<String> currentUserInfo;

    /**
     * Singleton pattern
     */
    private static final Model instance = new Model();


    /**
     * private constructor for facade pattern
     */
    private Model() {

    }

    /**
     * Singleton pattern accessor for instance
     *
     *
     * @return the one and only one instance of this facade
     */
    public static Model getInstance() { return instance; }

    /**
     * Returns the list of information about a user from the
     * hash map of user information stored in the UserInfo class
     * @param email of the account
     * @return list of information associated with the email
     */
    private List<String> getUserInfo(String email) {
        Map<String, List<String>> loginInfo = UserInfo.getLoginInfo();
        return loginInfo.get(email);
    }

    /**
     * Sets the current user of the singleton to the list
     * of account information returned by the query
     * @param email of the account
     */
    public void setUserInfo(String email) {
        this.currentUserInfo = this.getUserInfo(email);
    }

    /**
     *
     * @return the email of the current logged in user
     */
    public String getCurrentUserEmail() {
        return currentUserInfo.get(0);
    }

    /**
     *
     * @return the type of the current user
     * in its String representation
     */
    public String getCurrentUserType() {
        return currentUserInfo.get(2);
    }


}
