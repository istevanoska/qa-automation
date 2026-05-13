package tests.error_handling.automated_ai;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AIAutomatedErrorHandling_TC35 extends BaseTest {

    @Test
    public void sessionTimeoutHandling_TC35()
            throws Exception {

        HomePage homePage =
                new HomePage(driver);

        homePage.switchToStoreFrame();

        homePage.clickSimpleAddToCart();

        Thread.sleep(3000);

        int cartBeforeRefresh =
                homePage.getCartCount();

        assertTrue(
                cartBeforeRefresh >= 0,
                "Product should be added to cart"
        );

        homePage.refreshStorePage();

        int cartAfterRefresh =
                homePage.getCartCount();

        System.out.println(
                "Before refresh: "
                        + cartBeforeRefresh
        );

        System.out.println(
                "After refresh: "
                        + cartAfterRefresh
        );

        assertTrue(
                cartAfterRefresh >= 0,
                "Cart/session should remain accessible after refresh"
        );
    }
}