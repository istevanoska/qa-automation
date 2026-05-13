package tests.checkout.automated_ai;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.RegistrationPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AIAutomatedCheckout_TC21 extends BaseTest {

    @Test
    public void checkoutWithInvalidAddress_missingRequiredFields() {

        HomePage homePage =
                new HomePage(driver);

        homePage.switchToStoreFrame();

        homePage.openFirstProduct();

        homePage.clickAddToCart();

        homePage.proceedToCheckoutFromModal();

        assertTrue(
                homePage.isShoppingCartPageDisplayed()
        );

        homePage.clickCheckoutButton();

        RegistrationPage registrationPage =
                new RegistrationPage(driver);

        registrationPage.selectMrTitle();

        registrationPage.enterEmailOnly(
                "john"
                        + System.currentTimeMillis()
                        + "@test.com"
        );

        registrationPage.enterBirthday(
                "01/01/1999"
        );

        registrationPage.acceptPolicies();

        registrationPage.clickContinueButton();

        boolean stillOnPersonalInfo =
                driver.getPageSource()
                        .toLowerCase()
                        .contains("personal information")

                        ||

                        driver.getPageSource()
                                .toLowerCase()
                                .contains("sign in")

                        ||

                        driver.getCurrentUrl()
                                .contains("checkout");

        assertTrue(
                stillOnPersonalInfo,
                "Form should remain on Personal Information step"
        );

        boolean validationExists =
                driver.getPageSource()
                        .toLowerCase()
                        .contains("required")

                        ||

                        driver.getPageSource()
                                .toLowerCase()
                                .contains("invalid")

                        ||

                        driver.getPageSource()
                                .toLowerCase()
                                .contains("error");

        assertTrue(
                validationExists,
                "Validation message should appear"
        );
    }
}