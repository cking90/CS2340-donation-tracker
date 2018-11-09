//Made by Tyler
package edu.gatech.cs2340.nonprofitdonationtracker;

import org.junit.Test;

import edu.gatech.cs2340.nonprofitdonationtracker.controllers.UserInfo;

import static junit.framework.Assert.assertTrue;

public class correctPasswordTest {

    @Test
    public void correctPassword() {
        UserInfo user = new UserInfo();
        UserInfo.addUserToLocal("name", "email", "password", "Admin");

        //email and password valid
        assertTrue(UserInfo.correctPassword("email", "password"));
    }
}
