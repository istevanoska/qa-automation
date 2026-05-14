package tests.search_filter.automated_ai;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import utils.FrameUtils;

import static org.junit.jupiter.api.Assertions.*;

public class AIAutomatedSearch_TC11 extends BaseTest {

    @Test
    void shouldKeepWhiteColorAndPriceFiltersActiveOnNewProductsPage() {

        HomePage homePage =
                new HomePage(driver);

        FrameUtils.switchToStoreFrame(driver);

        homePage.openNewProductsPage();

        homePage.openPriceFilter();

        String initialPriceRange =
                homePage.getSelectedPriceRange();

        homePage.moveLowerPriceSlider(40);

        String selectedPriceRange =
                homePage.getSelectedPriceRange();

        assertNotEquals(
                initialPriceRange,
                selectedPriceRange,
                "Price range should change after moving the slider."
        );

        homePage.waitUntilAllProductPricesAreWithinRange(
                selectedPriceRange
        );

        homePage.openFilterSection("Color");

        homePage.applyColorFilter("White");

        assertTrue(
                homePage.getActiveFiltersCount() >= 2,
                "Price range and White color filters should both remain active."
        );

        homePage.openPriceFilter();

        String actualPriceRange =
                homePage.getSelectedPriceRange();

        assertEquals(
                selectedPriceRange,
                actualPriceRange,
                "Price range should remain selected after applying White color filter."
        );

        homePage.waitUntilAllProductPricesAreWithinRange(
                selectedPriceRange
        );

        assertTrue(
                homePage.isProductImageDisplayed(),
                "Products should still be displayed after applying Price and White color filters."
        );
    }
}