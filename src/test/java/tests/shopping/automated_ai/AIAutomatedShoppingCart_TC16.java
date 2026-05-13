package tests.shopping.automated_ai;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.ProductPage;
import utils.WaitUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AIAutomatedShoppingCart_TC16 extends BaseTest {

    @Test
    public void updateQuantity_endToEnd() {

        HomePage homePage =
                new HomePage(driver);

        homePage.switchToStoreFrame();

        ProductPage productPage =
                new ProductPage(driver);

        productPage.clickAddToCart();

        productPage.waitForCartModal();

        productPage.clickProceedToCheckout();

        assertEquals(
                "1",
                productPage.getQuantityValue(),
                "Initial quantity should be 1"
        );

        productPage.clickPlusButton(2);

        WaitUtils.waitForCondition(
                driver,
                d -> productPage.getQuantityValue()
                        .equals("3")
        );

        assertEquals(
                "3",
                productPage.getQuantityValue(),
                "Quantity should update to 3"
        );

        productPage.clickMinusButton();

        WaitUtils.waitForCondition(
                driver,
                d -> productPage.getQuantityValue()
                        .equals("2")
        );

        assertEquals(
                "2",
                productPage.getQuantityValue(),
                "Quantity should update to 2"
        );

        assertTrue(
                Integer.parseInt(
                        productPage.getQuantityValue()
                ) > 0,
                "Quantity should remain positive"
        );
    }
}