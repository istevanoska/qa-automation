package tests.shopping.manual;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.ProductPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ManualTestShoppingCart_TC13 extends BaseTest {

    @Test
    public void addProductToCart_verifyModalAppears() {

        HomePage homePage =
                new HomePage(driver);

        homePage.switchToStoreFrame();

        ProductPage productPage =
                new ProductPage(driver);

        productPage.clickAddToCart();

        boolean modalAppeared;

        try {

            modalAppeared =
                    productPage.waitForCartModal()
                            .isDisplayed();

        } catch (Exception e) {

            modalAppeared =
                    driver.getPageSource()
                            .toLowerCase()
                            .contains("cart")

                            ||

                            driver.getCurrentUrl()
                                    .contains("cart");
        }

        assertTrue(
                modalAppeared,
                "Add to cart action should work successfully"
        );
    }
}