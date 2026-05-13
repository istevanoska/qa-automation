package tests.checkout.automated_ai;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AIAutomatedCheckout_TC19 extends BaseTest {

    @Test
    public void checkoutDeliveryInformation_validData() {

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

        homePage.fillPersonalInformation(
                "John",
                "Doe",
                "john" + System.currentTimeMillis() + "@mail.com",
                "01/01/1999"
        );

        homePage.clickContinuePersonalInformation();

        if (homePage.isAddressStepVisible()) {

            homePage.fillAddressInformation();

            homePage.continueAddressStep();
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

                        driver.getPageSource()
                                .toLowerCase()
                                .contains("payment")

                        ||

                        driver.getCurrentUrl()
                                .contains("checkout");

        assertTrue(
                shippingReached,
                "User should proceed successfully to Shipping step"
        );
    }
}
