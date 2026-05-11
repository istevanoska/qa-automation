package tests.navigation.automated_ai;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AIAutomatedNavigation_TC04 {

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

        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.manage().window().maximize();
    }

    @Test
    void shouldNavigateToAllFooterLinks() {

        String[][] footerLinks = {
                {"#link-cms-page-2-2", "legal-notice"},
                {"#link-static-page-contact-2", "contact-us"},
                {"#link-static-page-sitemap-2", "sitemap"},
                {"#link-static-page-stores-2", "stores"}
        };

        for (String[] footerLink : footerLinks) {

            driver.get(URL);

            wait.until(
                    ExpectedConditions.frameToBeAvailableAndSwitchToIt(0)
            );

            clickFooterLinkAndVerify(
                    footerLink[0],
                    footerLink[1]
            );

            driver.switchTo().defaultContent();
        }
    }

    private void clickFooterLinkAndVerify(
            String footerLinkSelector,
            String expectedUrlPart
    ) {

        WebElement footerLink = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector(footerLinkSelector)
                )
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                footerLink
        );

        sleep(2000);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                footerLink
        );

        sleep(2500);

        wait.until(driver ->
                getIframeUrl().contains(expectedUrlPart)
        );

        String iframeUrl = getIframeUrl();

        assertTrue(
                iframeUrl.contains(expectedUrlPart),
                "Expected iframe URL to contain: "
                        + expectedUrlPart
                        + ", but was: "
                        + iframeUrl
        );

        sleep(3000);
    }

    private String getIframeUrl() {

        return (String) ((JavascriptExecutor) driver)
                .executeScript("return window.location.href;");
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