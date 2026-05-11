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

public class AccountPage {

    private WebDriver driver;

    private By signOut = By.xpath("//a[contains(.,'Sign out')]");
    private By signOutButton = By.cssSelector("a[href*='mylogout']");
    private By userProfileName = By.cssSelector(".header-block__title");
    private By identityLink = By.cssSelector("a[href*='identity']");
    private By firstNameField = By.id("field-firstname");
    private By lastNameField = By.id("field-lastname");
    private By emailField = By.id("field-email");
    private By passwordField = By.id("field-password");
    private By saveButton = By.cssSelector("button[data-link-action='save-customer']");
    private By successAlert = By.cssSelector(".alert-success");
    private By psgdprCheckbox = By.id("field-psgdpr");
    private By customerPrivacyCheckbox = By.id("field-customer_privacy");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLoggedIn() {
        return driver.findElements(signOut).size() > 0
                || driver.getPageSource().contains("Sign out");
    }

    public void logout() {
        FrameUtils.switchToStoreFrame(driver);

        WaitUtils.scrollToBottom(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        By signOutLocator = By.xpath("//a[contains(normalize-space(),'Sign out')]");

        WebElement signOutButton = wait.until(ExpectedConditions.presenceOfElementLocated(signOutLocator));

        WaitUtils.scrollIntoView(driver, signOutButton);

        WaitUtils.jsClick(driver, signOutButton);

        driver.switchTo().defaultContent();
    }

    public void logoutViaDropdown() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement profile = wait.until(ExpectedConditions.presenceOfElementLocated(userProfileName));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", profile);

        WebElement logoutBtn = wait.until(ExpectedConditions.elementToBeClickable(signOutButton));
        logoutBtn.click();
    }

    public void openInformationSection() {
        WaitUtils.waitForClickable(driver, identityLink).click();
    }

    public void updatePersonalInfo(String fName, String lName, String email, String currentPassword) {
        WebElement fn = WaitUtils.waitForVisible(driver, firstNameField);
        fn.clear();
        fn.sendKeys(fName);

        WebElement ln = driver.findElement(lastNameField);
        ln.clear();
        ln.sendKeys(lName);

        WebElement em = driver.findElement(emailField);
        em.clear();
        em.sendKeys(email);

        driver.findElement(passwordField).sendKeys(currentPassword);

        WaitUtils.jsClick(driver, driver.findElement(psgdprCheckbox));
        WaitUtils.jsClick(driver, driver.findElement(customerPrivacyCheckbox));

        WebElement saveBtn = driver.findElement(saveButton);
        WaitUtils.scrollIntoView(driver, saveBtn);
        WaitUtils.jsClick(driver, saveBtn);
    }

    public String getSuccessMessage() {
        return WaitUtils.waitForVisible(driver, successAlert).getText();
    }
}