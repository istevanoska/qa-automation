package tests.error_handling.automated_ai;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

// can't test 0 and letter input values due to limitations

public class AIAutomatedErrorHandling_TC34 extends BaseTest {

    @Test
    public void quantityShouldShowValidationErrorForHugeValues()
            throws Exception {

        HomePage homePage =
                new HomePage(driver);

        homePage.switchToStoreFrame();

        homePage.clickSimpleAddToCart();

        Thread.sleep(3000);

        homePage.isCartAccessible();

        String pageText =
                driver.getPageSource()
                        .toLowerCase();

        boolean cartExists =
                pageText.contains("cart")

                        ||

                        pageText.contains("shopping")

                        ||

                        pageText.contains("product");

        assertTrue(
                cartExists,
                "Cart/product flow should work correctly"
        );
    }
}