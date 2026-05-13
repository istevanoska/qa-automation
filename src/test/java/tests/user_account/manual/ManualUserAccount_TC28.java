package tests.user_account.manual;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.AccountPage;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ManualUserAccount_TC28 extends BaseTest {

    private static final String TEST_EMAIL = "pub@prestashop.com";
    private static final String TEST_PASS = "123456789";
    private static final String NEW_FIRSTNAME = "Jane";
    private static final String NEW_LASTNAME = "Doe";
    private static final String NEW_EMAIL = "jane.doe.test@gmaill.com";

    @Test
    public void TC28_editAccountInformation() {
        HomePage home = new HomePage(driver);
        LoginPage login = new LoginPage(driver);
        AccountPage account = new AccountPage(driver);
        loginUser(home, login, TEST_EMAIL, TEST_PASS);
        stabilizeFrameContext(home);
        home.clickUserProfile();
        account.openInformationSection();
        account.updatePersonalInfo(NEW_FIRSTNAME, NEW_LASTNAME, NEW_EMAIL, TEST_PASS);
        String successMsg = account.getSuccessMessage();
        assertTrue(successMsg.contains("Information successfully updated"),
                "TC.28 FAILED: Success message not displayed or incorrect");
    }

    private void loginUser(HomePage home, LoginPage login, String email, String pass) {
        home.switchToStoreFrame();
        home.clickSignIn();
        login.login(email, pass);
    }

    private void stabilizeFrameContext(HomePage home) {
        driver.switchTo().defaultContent();
        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        home.switchToStoreFrame();
    }
}