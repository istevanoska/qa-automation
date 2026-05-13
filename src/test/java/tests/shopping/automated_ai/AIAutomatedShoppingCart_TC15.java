package tests.shopping.automated_ai;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.ProductPage;
import utils.WaitUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AIAutomatedShoppingCart_TC15 extends BaseTest {

    @Test
    public void removeItemFromCart() {

        HomePage homePage =
                new HomePage(driver);

        homePage.switchToStoreFrame();

        ProductPage productPage =
                new ProductPage(driver);

        productPage.clickAddToCart();

        assertTrue(
                productPage.waitForCartModal().isDisplayed(),
                "Cart modal should appear"
        );

        productPage.clickProceedToCheckout();

        assertEquals(
                "1",
                productPage.getQuantityValue(),
                "Cart should initially contain 1 product"
        );

        productPage.clickMinusButton();

        WaitUtils.waitForCondition(
                driver,
                d -> productPage.getCartBadgeCount()
                        .equals("0")
        );

        assertEquals(
                "0",
                productPage.getCartBadgeCount(),
                "Cart should be empty after removing item"
        );
    }
}