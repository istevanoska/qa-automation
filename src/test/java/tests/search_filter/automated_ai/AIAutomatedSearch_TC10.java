package tests.search_filter.automated_ai;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AIAutomatedSearch_TC10 {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String URL =
            "https://demo.prestashop.com/#/en/front";

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
    void shouldFilterProductsByDimensionOnArtPage() {

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@class,'ps-mainmenu__tree-link') and normalize-space()='Art']")
        )).click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.tagName("body"),
                "Art"
        ));

        WebElement dimensionFilterSection = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("section[data-name='Dimension']")
                )
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                dimensionFilterSection
        );

        WebElement dimensionButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("section[data-name='Dimension'] button.accordion-button")
                )
        );

        if (dimensionButton.getAttribute("class").contains("collapsed")) {
            dimensionButton.click();
        }

        WebElement dimensionLabel = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//section[@data-name='Dimension']//label[contains(.,'40×60cm') or contains(.,'40x60cm')]")
                )
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                dimensionLabel
        );

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector(".search-filters__item.facet-label.active")
        ));

        List<WebElement> products = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.cssSelector("article.product-miniature")
                )
        );

        assertFalse(
                products.isEmpty(),
                "Products should be displayed after applying the 40x60cm dimension filter."
        );

        WebElement firstProduct = products.get(0);

        assertTrue(
                firstProduct.isDisplayed(),
                "Filtered product should be visible."
        );
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}