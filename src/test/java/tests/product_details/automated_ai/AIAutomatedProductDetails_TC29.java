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
import static org.junit.jupiter.api.Assertions.*;

public class AIAutomatedProductDetails_TC29 extends BaseTest {

    // TC.29 - Product Detail Page - Complete Information Display
    @Test
    public void TC29_verifyFullProductInformation() {
        HomePage home = new HomePage(driver);
        ProductPage productPage = new ProductPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        home.switchToStoreFrame();

        WebElement featuredTitle = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("h2.section-title")
        ));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", featuredTitle);

        try { Thread.sleep(1500); } catch (InterruptedException e) {}

        WebElement productImg = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("img[alt='Hummingbird printed t-shirt']")
        ));
        js.executeScript("arguments[0].click();", productImg);

        driver.switchTo().defaultContent();
        try { Thread.sleep(4000); } catch (InterruptedException e) {}
        home.switchToStoreFrame();

        assertEquals("Hummingbird printed t-shirt", productPage.getProductName(), "ERROR: Product name does not match!");

        assertTrue(productPage.isMainImageDisplayed(), "ERROR: Product main image is not displayed!");

        String description = productPage.getDescriptionText();
        assertTrue(description.contains("pima cotton"), "ERROR: Description does not contain the expected text. Found: " + description);

        String price = productPage.getPriceText();
        assertTrue(price.contains("22.94"), "ERROR: Product price does not match! Found: " + price);

        assertEquals("Color", productPage.getColorLabel(), "ERROR: 'Color' variant label was not found!");
        assertEquals("1", productPage.getQuantityValue(), "ERROR: Initial quantity should be 1.");
        assertTrue(productPage.isSizeDropdownVisible(), "ERROR: Size selection dropdown is not visible.");
        assertTrue(productPage.isAddToCartIconVisible(), "ERROR: Add to Cart icon is not visible!");

        System.out.println("TC.29 SUCCESS: All product information is correctly displayed.");
    }
}