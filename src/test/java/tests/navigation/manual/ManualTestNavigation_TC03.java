package tests.navigation.manual;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import pages.HomePage;
import pages.ProductPage;
import utils.FrameUtils;
import utils.WaitUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ManualTestNavigation_TC03 extends BaseTest {

    @Test
    void shouldNavigateThroughMainCategoriesAndDisplayRelevantProducts() {

        HomePage homePage = new HomePage(driver);
        ProductPage productPage = new ProductPage(driver);

        FrameUtils.switchToStoreFrame(driver);

        checkCategory(
                homePage,
                productPage,
                "Clothes",
                List.of("Men", "Women"),
                List.of("t-shirt", "sweater", "size")
        );

        checkCategory(
                homePage,
                productPage,
                "Accessories",
                List.of("Home Accessories", "Stationery"),
                List.of("mug", "notebook", "cushion")
        );

        checkCategory(
                homePage,
                productPage,
                "Art",
                List.of(),
                List.of("poster", "art")
        );
    }

    private void checkCategory(
            HomePage homePage,
            ProductPage productPage,
            String categoryName,
            List<String> expectedSubcategories,
            List<String> expectedProductKeywords
    ) {

        homePage.hoverCategory(categoryName);

        if (!expectedSubcategories.isEmpty()) {

            boolean subcategoryVisible = expectedSubcategories
                    .stream()
                    .anyMatch(homePage::isSubcategoryVisible);

            assertTrue(
                    subcategoryVisible,
                    categoryName + " subcategories should be visible after hover."
            );
        }

        homePage.clickCategory(categoryName);

        WaitUtils.waitForText(
                driver,
                By.cssSelector("h1"),
                categoryName
        );

        assertTrue(
                driver.getCurrentUrl()
                        .toLowerCase()
                        .contains(categoryName.toLowerCase()),
                categoryName + " category URL should be opened."
        );

        String pageText =
                homePage.getPageText().toLowerCase();

        assertTrue(
                pageText.contains(categoryName.toLowerCase()),
                categoryName + " category page should be opened."
        );

        assertTrue(
                productPage.getDisplayedProductCount() > 0,
                categoryName + " category should display products."
        );

        boolean relevantProductIsDisplayed =
                expectedProductKeywords
                        .stream()
                        .anyMatch(keyword ->
                                pageText.contains(keyword.toLowerCase())
                        );

        assertTrue(
                relevantProductIsDisplayed,
                categoryName + " category should display relevant products."
        );
    }
}