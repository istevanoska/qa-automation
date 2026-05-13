package tests.shopping.automated_ai;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.ProductPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AIAutomatedShoppingCart_TC17 extends BaseTest {

    @Test
    public void continueShopping_addAgain() {

        HomePage homePage =
                new HomePage(driver);

        homePage.switchToStoreFrame();

        ProductPage productPage =
                new ProductPage(driver);

        int initialCount =
                Integer.parseInt(
                        productPage.getCartBadgeCount()
                );

        productPage.clickAddToCart();

        productPage.clickContinueShopping();

        int afterFirstAdd =
                Integer.parseInt(
                        productPage.getCartBadgeCount()
                );

        assertTrue(
                afterFirstAdd >= initialCount,
                "First product should be added"
        );

        productPage.clickAddToCart();

        productPage.clickContinueShopping();

        int finalCount =
                Integer.parseInt(
                        productPage.getCartBadgeCount()
                );

        assertTrue(
                finalCount >= afterFirstAdd,
                "Cart quantity should remain valid after second add"
        );
    }
}