//Made by Tyler
package edu.gatech.cs2340.nonprofitdonationtracker;

import org.junit.Test;

import edu.gatech.cs2340.nonprofitdonationtracker.models.UserInfo;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Class that performs tests to check if the password is correct
 */
public class CorrectPasswordTest {

    /**
     * asserts true if the password is correct
     */
    @Test
    public void correctPassword() {
        UserInfo user = new UserInfo();
        UserInfo.addUserToLocal("name", "email", "password", "Admin");

        //email is not valid
        assertFalse(UserInfo.correctPassword("f", null));

        //email valid, password not
        assertFalse(UserInfo.correctPassword("email", "incorrect"));

        //email and password valid
        assertTrue(UserInfo.correctPassword("email", "password"));
    }
}
