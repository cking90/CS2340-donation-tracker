package edu.gatech.cs2340.nonprofitdonationtracker;

import org.junit.Test;


import edu.gatech.cs2340.nonprofitdonationtracker.models.UserInfo;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertNull;

/**
 * @author Neha
 * J unit test to check if usertype is correctly returned
 */
public class GetUserTypeWithEmailTest {

    /**
     * Checks if return type is correct
     */
    @Test
    public void checkReturnCorrectUserType() {
        //should return null if trying to get user type of null email
        assertNull(UserInfo.getUserType(null));
        UserInfo user = new UserInfo();
        UserInfo.addUserToLocal("name", "thisIsATestUser", "password", "Admin");
        //checks to see if the user type is returned correctly as a string - if user found
        assertEquals(UserInfo.getUserType("thisIsATestUser"), "Admin");

        //if user is not found it should return null
        assertNull(UserInfo.getUserType("thisUserDoesNotExist"));

    }
}
