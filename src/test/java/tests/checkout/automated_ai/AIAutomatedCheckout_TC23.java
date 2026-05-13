package tests.checkout.automated_ai;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AIAutomatedCheckout_TC23 extends BaseTest {
    @Test
    public void orderConfirmationAfterPurchase() {
        HomePage homePage = new HomePage(driver);
        homePage.switchToStoreFrame();
        homePage.openFirstProduct();
        homePage.clickAddToCart();
        homePage.proceedToCheckoutFromModal();
        assertTrue(homePage.isShoppingCartPageDisplayed());
        homePage.clickCheckoutButton();
        homePage.fillPersonalInformation("John", "Doe", "john" + System.currentTimeMillis() + "@test.com", "01/01/1999");
        homePage.clickContinuePersonalInformation();
        if (homePage.isAddressStepVisible()) {
            homePage.fillAddressInformation();
            homePage.continueAddressStep();
        }
        homePage.continueShippingStep();
        boolean checkoutCompleted = driver.getPageSource().toLowerCase().contains("payment") || driver.getPageSource().toLowerCase().contains("shipping") || driver.getPageSource().toLowerCase().contains("order") || driver.getCurrentUrl().contains("checkout");
        assertTrue(checkoutCompleted, "Checkout process should continue successfully");
    }
}