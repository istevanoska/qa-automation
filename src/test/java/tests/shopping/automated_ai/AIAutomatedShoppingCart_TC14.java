package tests.shopping.automated_ai;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.ProductPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AIAutomatedShoppingCart_TC14 extends BaseTest {

    @Test
    public void addMultipleQuantities_sameProduct() {
        HomePage homePage = new HomePage(driver);
        homePage.switchToStoreFrame();

        ProductPage productPage = new ProductPage(driver);
        productPage.clickIncrementQuantity(4);
        productPage.clickAddToCart();

        assertTrue(
                productPage.waitForCartModal().isDisplayed(),
                "Cart modal should appear"
        );

        String cartCount = productPage.getCartBadgeCount();
        System.out.println("Cart count: " + cartCount);
        assertEquals("5", cartCount, "Cart should contain 5 items");
    }
}