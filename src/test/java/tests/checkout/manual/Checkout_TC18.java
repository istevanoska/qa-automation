package tests.checkout.manual;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Checkout_TC18 extends BaseTest {
    @Test
    public void cartShouldNotOpenWhenEmpty() {
        HomePage homePage = new HomePage(driver);
        homePage.switchToStoreFrame();
        String badgeValue = homePage.getCartBadgeValue();
        assertEquals("0", badgeValue, "Cart should be empty");
        homePage.clickCartButton();
        boolean cartPageOpened = driver.getPageSource().contains("Shopping Cart");
        assertFalse(cartPageOpened, "Cart page should NOT open when cart is empty");
    }
}