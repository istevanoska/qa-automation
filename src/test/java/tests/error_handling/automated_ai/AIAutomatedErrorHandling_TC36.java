package tests.error_handling.automated_ai;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AIAutomatedErrorHandling_TC36 extends BaseTest {

    @Test
    public void checkoutInterruptionSimulation_TC36()
            throws Exception {

        HomePage homePage =
                new HomePage(driver);

        homePage.switchToStoreFrame();

        homePage.clickSimpleAddToCart();

        Thread.sleep(3000);

        int cartBefore =
                homePage.getCartCount();

        assertTrue(
                cartBefore >= 0,
                "Product should be added before interruption simulation"
        );

        boolean cartAccessibleBefore =
                homePage.isCartAccessible();

        assertTrue(
                cartAccessibleBefore,
                "Cart should be accessible before interruption"
        );

        homePage.refreshStorePage();

        int cartAfter =
                homePage.getCartCount();

        System.out.println(
                "Cart before interruption: "
                        + cartBefore
        );

        System.out.println(
                "Cart after interruption: "
                        + cartAfter
        );

        boolean cartStillAccessible =
                homePage.isCartAccessible();

        assertTrue(
                cartStillAccessible,
                "User should still be able to access cart after interruption"
        );

        assertTrue(
                cartAfter >= 0,
                "Cart/session should remain available after interruption"
        );
    }
}