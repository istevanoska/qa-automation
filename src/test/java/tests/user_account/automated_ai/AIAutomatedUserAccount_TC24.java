package tests.user_account.automated_ai;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.RegistrationPage;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AIAutomatedUserAccount_TC24 extends BaseTest {

    //TC.24 - Password Requirements Visibility During Registration
    @Test
    public void TC24_fullPasswordComplexityTest() {
        HomePage home = new HomePage(driver);
        RegistrationPage registration = new RegistrationPage(driver);

        home.switchToStoreFrame();
        home.clickSignIn();
        home.switchToStoreFrame();
        registration.clickCreateAccountLink();

        registration.fillPersonalDetails(
                "T",
                "TT",
                "tamarastojanoska4@gmaill.com",
                "05/31/1970"
        );

        System.out.println("\n--- Testing with: Tamara12 ---");
        registration.typePasswordRealTime("Tamara12");

        assertTrue(registration.areRequirementsVisible(), "Password hint did not appear!");

        String strength1 = registration.getPasswordStrengthResult();
        System.out.println("Total Strength: " + strength1);
        registration.printFailedRequirements();

        System.out.println("\n--- Testing with: Tamara#2026 ---");
        registration.typePasswordRealTime("Tamara#2026");

        String strength2 = registration.getPasswordStrengthResult();
        System.out.println("Total Strength: " + strength2);
        registration.printFailedRequirements();

        assertTrue(strength2.equalsIgnoreCase("Strong"),
                "ERROR: Tamara#2026 should be Strong, but the site did not accept it!");

        System.out.println("\nTC.24 SUCCESSFULLY COMPLETED.");
    }
}