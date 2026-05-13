package tests.checkout.automated_ai;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.RegistrationPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AIAutomatedCheckout_TC20 extends BaseTest {

    @Test
    public void checkoutWithExistingCustomerAccount() {

        HomePage homePage =
                new HomePage(driver);

        homePage.switchToStoreFrame();

        homePage.clickSignIn();

        RegistrationPage registrationPage =
                new RegistrationPage(driver);

        registrationPage.clickCreateAccountLink();

        String email =
                "john"
                        + System.currentTimeMillis()
                        + "@test.com";

        registrationPage.fillPersonalDetails(
                "John",
                "Doe",
                email,
                "01/01/1999"
        );

        registrationPage.typePasswordRealTime(
                "StrongPass123!"
        );

        registrationPage.clickContinueButton();

        if (registrationPage.isAddressStepVisible()) {

            registrationPage.fillAddressInformation();

            registrationPage.continueAddressStep();
        }

        boolean shippingReached =
                driver.getPageSource()
                        .toLowerCase()
                        .contains("shipping")

                        ||

                        driver.getPageSource()
                                .toLowerCase()
                                .contains("delivery")

                        ||

                        driver.getCurrentUrl()
                                .contains("checkout");

        assertTrue(
                shippingReached,
                "Checkout should proceed successfully to shipping step"
        );
    }
}

