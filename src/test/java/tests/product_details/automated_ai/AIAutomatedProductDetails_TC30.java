package tests.product_details.automated_ai;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.ProductPage;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AIAutomatedProductDetails_TC30 extends BaseTest {

    // TC.30 - Product Image Gallery - Zoom and Modal
    @Test
    public void TC30_verifyImageZoomAndModal() {
        HomePage home = new HomePage(driver);
        ProductPage productPage = new ProductPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        home.switchToStoreFrame();

        WebElement productImg = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("img[alt='Hummingbird printed t-shirt']")
        ));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'auto', block: 'center'});", productImg);
        js.executeScript("arguments[0].click();", productImg);

        driver.switchTo().defaultContent();
        try { Thread.sleep(4000); } catch (InterruptedException e) {}
        home.switchToStoreFrame();

        WebElement zoomBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("button.product__zoom")
        ));
        js.executeScript("arguments[0].click();", zoomBtn);

        try { Thread.sleep(2000); } catch (InterruptedException e) {}

        boolean isDisplayed = productPage.isZoomModalDisplayed();

        assertTrue(isDisplayed, "ERROR: The zoom modal (.modal-content) was not detected by Selenium!");

        System.out.println("TC.30 SUCCESS: Image zoom modal is verified.");
    }
}