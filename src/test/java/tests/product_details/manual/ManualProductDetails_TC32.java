package tests.product_details.manual;

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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ManualProductDetails_TC32 extends BaseTest {

    // TC.32 - Product Detail Page - Stock Status Display & Behavior of Quantity Selectors
    @Test
    public void TC32_verifyStockAndQtySelectors() {
        HomePage home = new HomePage(driver);
        ProductPage productPage = new ProductPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        home.switchToStoreFrame();
        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("img[alt='Hummingbird printed t-shirt']")));
        js.executeScript("arguments[0].click();", product);
        driver.switchTo().defaultContent();
        try {
            Thread.sleep(4000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        home.switchToStoreFrame();
        productPage.openProductDetails();

        String stockInfo = productPage.getStockQuantity();
        assertTrue(stockInfo.contains("Items"), "Error: Stock info is not displayed in product details!");
        String initialQty = productPage.getQuantityValue();
        productPage.clickIncrement();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String qtyAfterInc = productPage.getQuantityValue();
        assertEquals("2", qtyAfterInc, "Error: Quantity did not increase to 2!");
        productPage.clickDecrement();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String qtyFinal = productPage.getQuantityValue();
        assertEquals(initialQty, qtyFinal, "Error: Quantity did not decrease back to 1!");

        System.out.println("TC.32 PASSED: Stock visibility and +/- buttons are working as expected.");
    }
}