package tests.product_details.automated_ai;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AIAutomatedProductDetails_TC31 extends BaseTest {

    private static final String EMAIL = "pub@prestashop.com";
    private static final String PASSWORD = "123456789";

    // TC.31 - Product Details - Product Review & Rating Submission by Logged-in User
    @Test
    public void TC31_verifyLoggedUserCanWriteReview() {
        HomePage home = new HomePage(driver);
        LoginPage login = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        home.switchToStoreFrame();
        home.clickSignIn();
        login.login(EMAIL, PASSWORD);

        driver.switchTo().defaultContent();
        home.switchToStoreFrame();

        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("img[alt='Hummingbird printed t-shirt']")
        ));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", product);
        try { Thread.sleep(1500); } catch (InterruptedException e) {}
        js.executeScript("arguments[0].click();", product);

        driver.switchTo().defaultContent();
        try { Thread.sleep(4000); } catch (InterruptedException e) {}
        home.switchToStoreFrame();

        WebElement reviewBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.id("product-additional-info-review-button")
        ));
        js.executeScript("arguments[0].scrollIntoView(true);", reviewBtn);
        js.executeScript("arguments[0].click();", reviewBtn);

        boolean isModalOpen = productPage.isReviewModalDisplayed();
        if (!isModalOpen) {
            driver.switchTo().defaultContent();
            isModalOpen = productPage.isReviewModalDisplayed();
        }

        assertTrue(isModalOpen, "ERROR: Write your review modal did not appear!");

        productPage.fillReviewForm("Excellent!", "Best quality t-shirt I have ever bought.");
        productPage.submitReview();

        System.out.println("TC.31 SUCCESS: Review submitted successfully.");
    }
}