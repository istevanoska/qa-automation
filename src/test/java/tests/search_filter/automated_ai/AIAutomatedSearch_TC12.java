package tests.search_filter.automated_ai;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import utils.FrameUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AIAutomatedSearch_TC12 extends BaseTest {

    @Test
    void shouldSortNewProductsByPriceLowToHigh() {

        HomePage homePage =
                openNewProductsPage();

        homePage.selectSortOption("Price, low to high");

        List<Double> actualPrices =
                homePage.getDisplayedProductPricesForSorting();

        List<Double> expectedPrices =
                new ArrayList<>(actualPrices);

        expectedPrices.sort(Comparator.naturalOrder());

        System.out.println("Actual low to high prices: " + actualPrices);
        System.out.println("Expected low to high prices: " + expectedPrices);

        assertEquals(
                expectedPrices,
                actualPrices,
                "Products should be sorted by price from low to high."
        );
    }

    @Test
    void shouldSortNewProductsByPriceHighToLow() {

        HomePage homePage =
                openNewProductsPage();

        homePage.selectSortOption("Price, high to low");

        List<Double> actualPrices =
                homePage.getDisplayedProductPricesForSorting();

        List<Double> expectedPrices =
                new ArrayList<>(actualPrices);

        expectedPrices.sort(Comparator.reverseOrder());

        System.out.println("Actual high to low prices: " + actualPrices);
        System.out.println("Expected high to low prices: " + expectedPrices);

        assertEquals(
                expectedPrices,
                actualPrices,
                "Products should be sorted by price from high to low."
        );
    }

    @Test
    void shouldSortNewProductsByNameAToZ() {

        HomePage homePage =
                openNewProductsPage();

        homePage.selectSortOption("Name, A to Z");

        List<String> actualNames =
                normalizeNames(
                        homePage.getDisplayedProductNamesForSorting()
                );

        List<String> expectedNames =
                new ArrayList<>(actualNames);

        expectedNames.sort(Comparator.naturalOrder());

        System.out.println("Actual A-Z names: " + actualNames);
        System.out.println("Expected A-Z names: " + expectedNames);

        assertEquals(
                expectedNames,
                actualNames,
                "Products should be sorted by name from A to Z."
        );
    }

    @Test
    void shouldSortNewProductsByNameZToA() {

        HomePage homePage =
                openNewProductsPage();

        homePage.selectSortOption("Name, Z to A");

        List<String> actualNames =
                normalizeNames(
                        homePage.getDisplayedProductNamesForSorting()
                );

        List<String> expectedNames =
                new ArrayList<>(actualNames);

        expectedNames.sort(Comparator.reverseOrder());

        System.out.println("Actual Z-A names: " + actualNames);
        System.out.println("Expected Z-A names: " + expectedNames);

        assertEquals(
                expectedNames,
                actualNames,
                "Products should be sorted by name from Z to A."
        );
    }

    private HomePage openNewProductsPage() {

        HomePage homePage =
                new HomePage(driver);

        FrameUtils.switchToStoreFrame(driver);

        homePage.openNewProductsPage();

        return homePage;
    }

    private List<String> normalizeNames(List<String> names) {

        List<String> normalized =
                new ArrayList<>();

        for (String name : names) {

            normalized.add(
                    name
                            .trim()
                            .toLowerCase()
                            .replaceAll("\\s+", " ")
            );
        }

        return normalized;
    }
}























//package tests.search_filter.automated_ai;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.junit.jupiter.api.*;
//
//import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class AIAutomatedSearch_TC12 {
//
//    private WebDriver driver;
//    private WebDriverWait wait;
//
//    private static final String URL =
//            "https://demo.prestashop.com/#/en/front";
//
//    @BeforeEach
//    void setUp() {
//
//        WebDriverManager.chromedriver().setup();
//
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--remote-allow-origins=*");
//
//        driver = new ChromeDriver(options);
//
//        wait = new WebDriverWait(driver, Duration.ofSeconds(25));
//
//        driver.manage().window().maximize();
//
//        driver.get(URL);
//
//        wait.until(
//                ExpectedConditions.frameToBeAvailableAndSwitchToIt(0)
//        );
//    }
//
//    @Test
//    void shouldSortNewProductsByPriceLowToHigh() {
//
//        openNewProductsPage();
//
//        selectSortOption("Price, low to high");
//
//        verifyPricesAreSortedAscending();
//    }
//
//    @Test
//    void shouldSortNewProductsByPriceHighToLow() {
//
//        openNewProductsPage();
//
//        selectSortOption("Price, high to low");
//
//        verifyPricesAreSortedDescending();
//    }
//
//    @Test
//    void shouldSortNewProductsByNameAToZ() {
//
//        openNewProductsPage();
//
//        selectSortOption("Name, A to Z");
//
//        verifyNamesAreSortedAscending();
//    }
//
//    @Test
//    void shouldFailWhenProductsAreIncorrectlySortedByNameZToA() {
//
//        openNewProductsPage();
//
//        selectSortOption("Name, Z to A");
//
//        verifyNamesAreSortedDescending();
//    }
//
//    private void openNewProductsPage() {
//
//        WebElement allNewProductsButton = wait.until(
//                ExpectedConditions.elementToBeClickable(
//                        By.cssSelector("a[href*='new-products']")
//                )
//        );
//
//        ((JavascriptExecutor) driver).executeScript(
//                "arguments[0].scrollIntoView({block:'center'});",
//                allNewProductsButton
//        );
//
//        sleep(800);
//
//        ((JavascriptExecutor) driver).executeScript(
//                "arguments[0].click();",
//                allNewProductsButton
//        );
//
//        wait.until(
//                ExpectedConditions.textToBePresentInElementLocated(
//                        By.tagName("body"),
//                        "New products"
//                )
//        );
//
//        wait.until(
//                ExpectedConditions.visibilityOfElementLocated(
//                        By.cssSelector(".products__sort-dropdown-button")
//                )
//        );
//
//        System.out.println("Successfully opened All New Products page.");
//    }
//
//    private void selectSortOption(String optionText) {
//
//        WebElement sortButton = wait.until(
//                ExpectedConditions.elementToBeClickable(
//                        By.cssSelector(".products__sort-dropdown-button")
//                )
//        );
//
//        ((JavascriptExecutor) driver).executeScript(
//                "arguments[0].scrollIntoView({block:'center'});",
//                sortButton
//        );
//
//        sleep(700);
//
//        ((JavascriptExecutor) driver).executeScript(
//                "arguments[0].click();",
//                sortButton
//        );
//
//        WebElement sortOption = wait.until(
//                ExpectedConditions.elementToBeClickable(
//                        By.xpath(
//                                "//a[contains(@class,'js-search-link') and normalize-space()='"
//                                        + optionText
//                                        + "']"
//                        )
//                )
//        );
//
//        ((JavascriptExecutor) driver).executeScript(
//                "arguments[0].click();",
//                sortOption
//        );
//
//        sleep(3000);
//
//        wait.until(
//                ExpectedConditions.visibilityOfElementLocated(
//                        By.cssSelector(".product-miniature")
//                )
//        );
//
//        System.out.println("Applied sorting option: " + optionText);
//    }
//
//    private void verifyPricesAreSortedAscending() {
//
//        List<Double> prices = getDisplayedProductPrices();
//
//        assertFalse(
//                prices.isEmpty(),
//                "Products should be displayed after sorting by Price: Low to High."
//        );
//
//        for (int i = 0; i < prices.size() - 1; i++) {
//
//            assertTrue(
//                    prices.get(i) <= prices.get(i + 1),
//                    "Products are not sorted correctly from low to high."
//            );
//        }
//
//        System.out.println("Ascending prices: " + prices);
//    }
//
//    private void verifyPricesAreSortedDescending() {
//
//        List<Double> prices = getDisplayedProductPrices();
//
//        assertFalse(
//                prices.isEmpty(),
//                "Products should be displayed after sorting by Price: High to Low."
//        );
//
//        for (int i = 0; i < prices.size() - 1; i++) {
//
//            assertTrue(
//                    prices.get(i) >= prices.get(i + 1),
//                    "Products are not sorted correctly from high to low."
//            );
//        }
//
//        System.out.println("Descending prices: " + prices);
//    }
//
//    private void verifyNamesAreSortedAscending() {
//
//        List<String> names = getDisplayedProductNames();
//
//        assertFalse(
//                names.isEmpty(),
//                "Products should be displayed after sorting by Name: A to Z."
//        );
//
//        System.out.println("Actual A-Z names: " + names);
//
//        for (int i = 0; i < names.size() - 1; i++) {
//
//            String current = normalizeName(names.get(i));
//            String next = normalizeName(names.get(i + 1));
//
//            assertTrue(
//                    current.compareTo(next) <= 0,
//                    "Products are not sorted correctly from A to Z. "
//                            + current
//                            + " should come before "
//                            + next
//            );
//        }
//    }
//
//    private void verifyNamesAreSortedDescending() {
//
//        List<String> names = getDisplayedProductNames();
//
//        assertFalse(
//                names.isEmpty(),
//                "Products should be displayed after sorting by Name: Z to A."
//        );
//
//        System.out.println("Actual Z-A names: " + names);
//
//        for (int i = 0; i < names.size() - 1; i++) {
//
//            String current = normalizeName(names.get(i));
//            String next = normalizeName(names.get(i + 1));
//
//            assertTrue(
//                    current.compareTo(next) >= 0,
//                    "BUG: Products are not globally sorted from Z to A. "
//                            + "Current product '" + names.get(i)
//                            + "' should come after or equal to next product '"
//                            + names.get(i + 1) + "'."
//            );
//        }
//    }
//
//    private String normalizeName(String name) {
//
//        return name
//                .trim()
//                .toLowerCase()
//                .replaceAll("\\s+", " ");
//    }
//
//    private List<Double> getDisplayedProductPrices() {
//
//        List<WebElement> priceElements = wait.until(
//                ExpectedConditions.visibilityOfAllElementsLocatedBy(
//                        By.cssSelector(".product-miniature__price")
//                )
//        );
//
//        List<Double> prices = new ArrayList<>();
//
//        for (WebElement priceElement : priceElements) {
//
//            String priceText = priceElement.getText();
//
//            if (priceText == null || priceText.isBlank()) {
//                continue;
//            }
//
//            prices.add(extractPrice(priceText));
//        }
//
//        return prices;
//    }
//
//    private List<String> getDisplayedProductNames() {
//
//        List<WebElement> titleElements = wait.until(
//                ExpectedConditions.visibilityOfAllElementsLocatedBy(
//                        By.cssSelector(".product-miniature__title")
//                )
//        );
//
//        List<String> names = new ArrayList<>();
//
//        for (WebElement titleElement : titleElements) {
//
//            String productName = titleElement.getText();
//
//            if (productName == null || productName.isBlank()) {
//                continue;
//            }
//
//            names.add(productName.trim());
//        }
//
//        return names;
//    }
//
//    private double extractPrice(String priceText) {
//
//        String cleanedPrice = priceText
//                .replace("€", "")
//                .replace(",", ".")
//                .replaceAll("[^0-9.]", "")
//                .trim();
//
//        return Double.parseDouble(cleanedPrice);
//    }
//
//    private void sleep(int milliseconds) {
//
//        try {
//            Thread.sleep(milliseconds);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//    }
//
//    @AfterEach
//    void tearDown() {
//
//        if (driver != null) {
//
//            driver.quit();
//        }
//    }
//}