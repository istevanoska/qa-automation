package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.FrameUtils;
import utils.WaitUtils;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;

    private By signInButton = By.cssSelector("a[href*='login'], a[href*='my-account']");
    private By userProfileName = By.cssSelector(".header-block__title");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void switchToStoreFrame() {
        FrameUtils.switchToStoreFrame(driver);
    }

    public void clickSignIn() {
        WaitUtils.waitForClickable(driver, signInButton).click();
    }

    public void clickUserProfile() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(userProfileName));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            System.out.println("Failed to click profile via standard wait, trying direct JS...");
            ((JavascriptExecutor) driver).executeScript("document.querySelector('.header-block__title').click();");
        }
    }
}