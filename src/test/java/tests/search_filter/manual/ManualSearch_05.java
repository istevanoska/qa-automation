package tests.search_filter.manual;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ManualSearch_05 {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String URL =
            "https://demo.prestashop.com/#/en/front";

    private final String[] keywords = {
            "mug",
            "t-shirt",
            "cushion",
            "notebook",
            "poster"
    };

    @BeforeEach
    void setUp() {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.manage().window().maximize();
        driver.get(URL);

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
    }

    @Test
    void shouldSearchProductWithValidKeywords() {

        for (String keyword : keywords) {

            driver.get(URL);
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));

            WebElement searchBar = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.cssSelector("input.js-search-input")
                    )
            );

            searchBar.click();
            searchBar.clear();
            searchBar.sendKeys(keyword);
            searchBar.sendKeys(Keys.ENTER);

            wait.until(
                    ExpectedConditions.textToBePresentInElementLocated(
                            By.tagName("body"),
                            "Search results"
                    )
            );

            List<WebElement> productTitles = wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(
                            By.cssSelector(".product-miniature__title")
                    )
            );

            assertFalse(
                    productTitles.isEmpty(),
                    "Search results should display products for valid keyword: " + keyword
            );

            boolean foundMatchingProduct = false;

            for (WebElement title : productTitles) {

                String productName = title.getText().toLowerCase();

                System.out.println("Keyword: " + keyword + " | Displayed product: " + productName);

                if (productName.contains(keyword)) {
                    foundMatchingProduct = true;
                }
            }

            assertTrue(
                    foundMatchingProduct,
                    "At least one displayed product should be related to searched keyword: " + keyword
            );
        }
    }

    @AfterEach
    void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}