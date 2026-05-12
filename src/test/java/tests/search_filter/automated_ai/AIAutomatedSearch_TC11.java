package tests.search_filter.automated_ai;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AIAutomatedSearch_TC11 {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String URL =
            "https://demo.prestashop.com/#/en/front";

    private String selectedPriceRange;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        driver.manage().window().maximize();
        driver.get(URL);

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
    }

    @Test
    void shouldFailWhenWhiteColorIsAppliedAfterPriceRangeFilter() {

        openNewProductsPage();

        openPriceFilterSection();

        String initialPriceRange = getSelectedPriceRange();
        System.out.println("Initial price range: " + initialPriceRange);

        moveLowerPriceSlider(40);
        sleep(2500);

        selectedPriceRange = getSelectedPriceRange();
        System.out.println("Selected price range after slider movement: " + selectedPriceRange);

        assertNotEquals(
                initialPriceRange,
                selectedPriceRange,
                "Price range should change after moving the slider."
        );

        verifyProductPricesAreWithinRange(selectedPriceRange);

        openFilterSection("Color");
        applyColorFilter("White");

        verifyActiveFiltersAreDisplayed();

        verifyPriceRangePersisted(selectedPriceRange);

        verifyProductPricesAreWithinRange(selectedPriceRange);

        verifyWhiteColorImageVariant();
    }

    private void openNewProductsPage() {

        WebElement allNewProductsButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("a[href*='new-products']")
                )
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                allNewProductsButton
        );

        sleep(800);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                allNewProductsButton
        );

        wait.until(
                ExpectedConditions.textToBePresentInElementLocated(
                        By.tagName("body"),
                        "New products"
                )
        );
    }

    private void openPriceFilterSection() {

        WebElement priceSection = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("section[data-type='price']")
                )
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                priceSection
        );

        sleep(700);

        WebElement priceButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("section[data-type='price'] button.accordion-button")
                )
        );

        if (priceButton.getAttribute("class").contains("collapsed")) {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    priceButton
            );
        }

        sleep(800);
    }

    private void openFilterSection(String sectionName) {

        WebElement section = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("section[data-name='" + sectionName + "']")
                )
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                section
        );

        sleep(700);

        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("section[data-name='" + sectionName + "'] button.accordion-button")
                )
        );

        if (button.getAttribute("class").contains("collapsed")) {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    button
            );
        }

        sleep(800);
    }

    private void applyColorFilter(String colorName) {

        WebElement colorLabel = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath(
                                "//input[contains(@data-search-url,'Color-"
                                        + colorName
                                        + "')]/following-sibling::label"
                        )
                )
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                colorLabel
        );

        sleep(600);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                colorLabel
        );

        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector(".search-filters__item.facet-label.active")
                )
        );

        sleep(1500);
    }

    private void moveLowerPriceSlider(int offset) {

        WebElement lowerHandle = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("section[data-type='price'] .noUi-handle-lower")
                )
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                lowerHandle
        );

        sleep(700);

        new Actions(driver)
                .moveToElement(lowerHandle)
                .clickAndHold()
                .pause(Duration.ofMillis(400))
                .moveByOffset(offset, 0)
                .pause(Duration.ofMillis(400))
                .release()
                .perform();
    }

    private String getSelectedPriceRange() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("section[data-type='price'] .search-filters__slider-values")
                )
        ).getText();
    }

    private void verifyPriceRangePersisted(String expectedRange) {

        openPriceFilterSection();

        String actualRange = getSelectedPriceRange();

        System.out.println("Price range after applying White color: " + actualRange);

        assertEquals(
                expectedRange,
                actualRange,
                "Price range should remain selected after applying White color filter."
        );
    }

    private void verifyActiveFiltersAreDisplayed() {

        List<WebElement> activeFilters = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.cssSelector(".search-filters__item.facet-label.active")
                )
        );

        assertTrue(
                activeFilters.size() >= 2,
                "Price range and White color filters should both remain active."
        );
    }

    private void verifyWhiteColorImageVariant() {

        WebElement productImage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".product-miniature__image")
                )
        );

        String imageSrc = productImage.getAttribute("src");

        System.out.println("White filtered image src: " + imageSrc);

        assertTrue(
                imageSrc != null && imageSrc.contains("/2-default/"),
                "BUG: White filter is active after price filter, but displayed product image is not the white variant. Actual image src: "
                        + imageSrc
        );
    }

    private void verifyProductPricesAreWithinRange(String selectedRange) {

        double minPrice = extractMinPrice(selectedRange);
        double maxPrice = extractMaxPrice(selectedRange);

        List<WebElement> productPrices = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.cssSelector(".product-miniature__price")
                )
        );

        assertFalse(
                productPrices.isEmpty(),
                "Products should be displayed after applying filters."
        );

        for (WebElement productPrice : productPrices) {

            String priceText = productPrice.getText();

            if (priceText == null || priceText.isBlank()) {
                continue;
            }

            double actualPrice = extractSinglePrice(priceText);

            assertTrue(
                    actualPrice >= minPrice && actualPrice <= maxPrice,
                    "Product price should be within selected range. Range: "
                            + selectedRange
                            + ", actual price: "
                            + priceText
            );
        }
    }

    private double extractMinPrice(String rangeText) {
        String[] parts = rangeText.split("-");
        return extractSinglePrice(parts[0]);
    }

    private double extractMaxPrice(String rangeText) {
        String[] parts = rangeText.split("-");
        return extractSinglePrice(parts[1]);
    }

    private double extractSinglePrice(String priceText) {

        String cleanedPrice = priceText
                .replace("€", "")
                .replace(",", ".")
                .replaceAll("[^0-9.]", "")
                .trim();

        return Double.parseDouble(cleanedPrice);
    }

    private void sleep(int milliseconds) {

        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @AfterEach
    void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}