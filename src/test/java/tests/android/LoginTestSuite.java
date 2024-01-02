package tests.android;

import org.junit.jupiter.api.Test;
import tests.base.BaseFactoryAndroidTest;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class LoginTestSuite extends BaseFactoryAndroidTest {


    @Test
    public void testUserRegisterAndLogin() {
        String tempUser = userData.getUserName();

        chromePage.goRegisterPage();
        // Fill out the registration form
        registrationPage.fillRegistrationForm(userData.generateRandomNameString(8), userData.generateRandomNameString(8), tempUser, userData.getPassword());

        // Check the CAPTCHA checkbox  and submit the registration
        registrationPage.checkAndRegister();

        // Confirm any dialog if necessary
        registrationPage.confirmAction();

        // Go to login page
        registrationPage.goToLogin();

        // Log in with the new user credentials
        registrationPage.login(userData.getUserName(), userData.getPassword());

        // Click logout
        registrationPage.logout();

    }

    @Test
    public void testUserLogin() {

        chromePage.goLoginPage();

        // Log in with the pre-created user credentials
        registrationPage.login("AndroidTestUserDemo", "randomPass123!");

        // Click logout
        registrationPage.logout();

    }
    @Test
    public void testUserLoginFailure() {

        chromePage.goLoginPage();

        // Log in Failure with the random user credentials
        registrationPage.login(userData.generateRandomString(8), userData.getPassword());

        // Checks Cannot Login into System
        assertFalse(registrationPage.logoutIsVisible());

    }
}
