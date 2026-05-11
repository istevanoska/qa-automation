package tests.navigation.automated_ai;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class AIAutomatedNavigation_TC01 {

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
    }

    private void switchToLanguage(String language, String expectedLoginText) {

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));

        WebElement languageDropdownElement = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("select.js-language-selector")
                )
        );

        Select languageDropdown = new Select(languageDropdownElement);

        languageDropdown.selectByVisibleText(language);

        driver.switchTo().defaultContent();

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));

        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.tagName("body"),
                expectedLoginText
        ));
    }

    private boolean isTextVisible(String text) {

        return !driver.findElements(
                By.xpath("//*[contains(normalize-space(),'" + text + "')]")
        ).isEmpty();
    }

    private boolean isMenuTextVisible(String text) {

        return !driver.findElements(
                By.xpath(
                        "//a[contains(@class,'ps-mainmenu__tree-link') " +
                                "and contains(normalize-space(),'" + text + "')]"
                )
        ).isEmpty();
    }

    private void printCheck(
            String checkName,
            String language,
            String expectedText,
            boolean result
    ) {

        System.out.println(
                "[" + language + "] " +
                        checkName +
                        " | expected: " +
                        expectedText +
                        " | result: " +
                        (result ? "PASSED" : "FAILED")
        );
    }

    @ParameterizedTest
    @CsvSource({
            "Hrvatski, Prijavite se, Košarica, Pribor",
            "Français, Connexion, Panier, Accessoires"
    })
    void shouldDisplayTranslatedNavigationAndHeaderElementsAfterLanguageSwitch(
            String language,
            String loginText,
            String cartText,
            String accessoriesText
    ) {

        switchToLanguage(language, loginText);

        boolean isLoginVisible = isTextVisible(loginText);

        printCheck(
                "Login translation",
                language,
                loginText,
                isLoginVisible
        );

        assertTrue(
                isLoginVisible,
                "Login text should be translated correctly for language: "
                        + language
        );

        boolean isCartVisible = isTextVisible(cartText);

        printCheck(
                "Cart translation",
                language,
                cartText,
                isCartVisible
        );

        assertTrue(
                isCartVisible,
                "Cart text should be translated correctly for language: "
                        + language
        );

        boolean isAccessoriesVisible =
                isMenuTextVisible(accessoriesText);

        printCheck(
                "Accessories category translation",
                language,
                accessoriesText,
                isAccessoriesVisible
        );

        assertTrue(
                isAccessoriesVisible,
                "Accessories category should be translated correctly for language: "
                        + language
        );

        boolean isClothesStillEnglish =
                isMenuTextVisible("Clothes");

        printCheck(
                "English text removal check",
                language,
                "Clothes should not remain visible",
                !isClothesStillEnglish
        );

        assertFalse(
                isClothesStillEnglish,
                "Clothes category should not remain in English for language: "
                        + language
        );
    }

    @ParameterizedTest
    @CsvSource({
            "Hrvatski, Prijavite se",
            "Français, Connexion"
    })
    void shouldUpdateProductTitlesWhenLanguageIsChanged(
            String language,
            String expectedLoginText
    ) {

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));

        String englishProductTitle = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("a.product-miniature__title")
                )
        ).getText();

        System.out.println(
                "[" + language + "] Product title before language switch: "
                        + englishProductTitle
        );

        WebElement languageDropdownElement = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("select.js-language-selector")
                )
        );

        Select languageDropdown = new Select(languageDropdownElement);

        languageDropdown.selectByVisibleText(language);

        driver.switchTo().defaultContent();

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));

        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.tagName("body"),
                expectedLoginText
        ));

        String translatedProductTitle = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("a.product-miniature__title")
                )
        ).getText();

        System.out.println(
                "[" + language + "] Product title after language switch: "
                        + translatedProductTitle
        );

        boolean titleChanged =
                !englishProductTitle.equals(translatedProductTitle);

        printCheck(
                "Product title localization",
                language,
                "Translated product title",
                titleChanged
        );

        assertNotEquals(
                englishProductTitle,
                translatedProductTitle,
                "Product titles should be updated according to the selected language: "
                        + language
        );
    }

    @ParameterizedTest
    @CsvSource({
            "Hrvatski, Prijavite se",
            "Français, Connexion"
    })
    void shouldDisplayProductPricesCorrectlyAfterLanguageSwitch(
            String language,
            String expectedLoginText
    ) {

        switchToLanguage(language, expectedLoginText);

        WebElement productPrice = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("div.product-miniature__prices")
                )
        );

        boolean isPriceVisible = productPrice.isDisplayed();

        printCheck(
                "Product price visibility",
                language,
                "Visible product prices",
                isPriceVisible
        );

        assertTrue(
                isPriceVisible,
                "Product price should remain visible for language: "
                        + language
        );
    }

    @ParameterizedTest
    @CsvSource({
            "Hrvatski, Prijavite se, Kontakt",
            "Français, Connexion, Contact"
    })
    void shouldDisplayLocalizedFooterElementsAfterLanguageSwitch(
            String language,
            String expectedLoginText,
            String footerText
    ) {

        switchToLanguage(language, expectedLoginText);

        boolean isFooterVisible = isTextVisible(footerText);

        printCheck(
                "Footer localization",
                language,
                footerText,
                isFooterVisible
        );

        assertTrue(
                isFooterVisible,
                "Localized footer content should be displayed correctly for language: "
                        + language
        );
    }

    @AfterEach
    void tearDown() {

        if (driver != null) {

            driver.quit();
        }
    }
}