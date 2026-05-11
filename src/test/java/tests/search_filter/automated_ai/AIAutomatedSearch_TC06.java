package tests.search_filter.automated_ai;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AIAutomatedSearch_TC06 {

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
    }

    @Test
    void shouldShowNoResultsMessageForInvalidSearchKeywords() {

        String[] invalidKeywords = {
                "xyz123abc",
                "zzz"
        };

        for (String invalidKeyword : invalidKeywords) {

            driver.get(URL);

            wait.until(
                    ExpectedConditions.frameToBeAvailableAndSwitchToIt(0)
            );

            WebElement searchBar = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.cssSelector("input[placeholder='Search products...']")
                    )
            );

            sleep(1500);
            searchBar.click();
            sleep(1000);
            searchBar.clear();
            sleep(800);
            searchBar.sendKeys(invalidKeyword);
            sleep(800);
            searchBar.sendKeys(Keys.ENTER);
            sleep(1200);

            WebElement noResultsTitle = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector("h1.page-title-section")
                    )
            );

            assertTrue(
                    noResultsTitle.getText().contains(
                            "No search results for \"" + invalidKeyword + "\""
                    ),
                    "No-results message should be displayed for invalid keyword: "
                            + invalidKeyword
            );

            sleep(3000);

            driver.switchTo().defaultContent();
        }
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