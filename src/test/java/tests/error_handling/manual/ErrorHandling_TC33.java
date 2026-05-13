package tests.error_handling.manual;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertFalse;

// ~15 mins generation + fixes
// Additional server-side error scenarios such as
// 403 Forbidden and 500 Internal Server Error could not be validated due to
// limitations of the public demo environment.

public class ErrorHandling_TC33 extends BaseTest {

    @Test
    public void invalidProductShouldNotOpenValidProductPage()
            throws Exception {

        HomePage homePage =
                new HomePage(driver);

        homePage.switchToStoreFrame();

        homePage.openInvalidProductPage();

        Thread.sleep(3000);

        String page =
                driver.getPageSource()
                        .toLowerCase();

        boolean validProductLoaded =
                page.contains("add to cart")

                        ||

                        page.contains("product-details")

                        ||

                        page.contains("quantity");

        assertFalse(
                validProductLoaded,
                "Invalid product should NOT load valid product page"
        );
    }
}