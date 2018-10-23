package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import java.util.List;

public class Model {

    /**
     * the facade maintains references to any required model classes
     */

    private List<String> currentUserInfo;

    /**
     * Singleton pattern
     */
    private static Model instance = new Model();


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

    public List<String> getUserInfo(String email) {
        return UserInfo.getLoginInfo().get(email);
    }

    public void setUserInfo(String email) {
        this.currentUserInfo = this.getUserInfo(email);
    }

    public String getCurrentUserEmail() {
        return currentUserInfo.get(0);
    }

    public String getCurrentUserType() {
        return currentUserInfo.get(2);
    }


}
