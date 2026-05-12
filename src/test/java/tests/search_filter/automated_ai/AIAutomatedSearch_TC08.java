    package tests.search_filter.automated_ai;

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

    public class AIAutomatedSearch_TC08 {

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

            wait.until(
                    ExpectedConditions.frameToBeAvailableAndSwitchToIt(0)
            );
        }

        @Test
        void shouldFilterNewProductsByCeramicComposition() {

            openNewProductsPage();

            selectCompositionFilterByText("Ceramic");

            verifyProductTitlesContain("mug", "Ceramic");
        }

        @Test
        void shouldFilterNewProductsByMattPaperComposition() {

            openNewProductsPage();

            selectCompositionFilterByText("Matt paper");

            verifyProductTitlesContain("framed poster", "Matt paper");
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

            WebElement compositionSection = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.cssSelector("section[data-name='Composition']")
                    )
            );

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    compositionSection
            );

            sleep(800);

            WebElement compositionButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.cssSelector("section[data-name='Composition'] button.accordion-button")
                    )
            );

            if (compositionButton.getAttribute("class").contains("collapsed")) {
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].click();",
                        compositionButton
                );
            }

            sleep(1000);
        }

        private void selectCompositionFilterByText(String compositionName) {

            WebElement compositionLabel = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath(
                                    "//section[@data-name='Composition']//label[contains(normalize-space(.), '"
                                            + compositionName
                                            + "')]"
                            )
                    )
            );

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    compositionLabel
            );

            sleep(700);

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    compositionLabel
            );

            wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.cssSelector(".search-filters__item.facet-label.active")
                    )
            );
        }

        private void verifyProductTitlesContain(String expectedKeyword, String filterName) {

            List<WebElement> productTitles = wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(
                            By.cssSelector(".product-miniature__title")
                    )
            );

            assertFalse(
                    productTitles.isEmpty(),
                    "Products should be displayed after applying " + filterName + " composition filter."
            );

            for (WebElement title : productTitles) {

                String productName = title.getText().toLowerCase();

                System.out.println("Displayed " + filterName + " product: " + productName);

                assertTrue(
                        productName.contains(expectedKeyword),
                        "Only " + filterName + " products should be displayed. Unexpected product: "
                                + productName
                );
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