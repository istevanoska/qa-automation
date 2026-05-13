package tests.user_account.automated_ai;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.AccountPage;
import pages.HomePage;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AIAutomatedUserAccount_TC25_TC26 extends BaseTest {

    private static final String EMAIL = "pub@prestashop.com";
    private static final String PASSWORD = "123456789";
    private static final String WRONG_PASSWORD = "WrongPassword123";

    // TC.25 - User Login with Valid Credentials
    @Test
    public void TC25_userLogin_withValidCredentials() {
        HomePage home = new HomePage(driver);
        LoginPage login = new LoginPage(driver);
        AccountPage account = new AccountPage(driver);

        home.switchToStoreFrame();
        home.clickSignIn();

        login.login(EMAIL, PASSWORD);

        assertTrue(account.isLoggedIn(),
                "TC.25 FAILED: User was not logged in with valid credentials");
    }

    // TC.26 - User Login with Invalid Credentials
    @Test
    public void TC26_userLogin_withInvalidCredentials() {
        HomePage home = new HomePage(driver);
        LoginPage login = new LoginPage(driver);

        home.switchToStoreFrame();
        home.clickSignIn();

        login.login(EMAIL, WRONG_PASSWORD);

        assertTrue(login.isErrorDisplayed(),
                "TC.26 FAILED: Error message was not displayed for invalid credentials");
    }
}