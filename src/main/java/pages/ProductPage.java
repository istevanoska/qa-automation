package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By productName = By.cssSelector("h1.product__name");
    private By mainProductImage = By.cssSelector("img.img-fluid.w-100");
    private By shortDescription = By.cssSelector(".product__description-short p");
    private By colorLegend = By.id("legend_2_1");
    private By productPrice = By.cssSelector(".product__price");
    private By sizeDropdown = By.cssSelector("select.form-select");
    private By quantityInput = By.id("quantity_wanted");
    private By addToCartIcon = By.cssSelector(".material-icons[aria-hidden='true']");
    private By reviewTitleInput = By.id("comment_title");
    private By reviewContentTextArea = By.id("comment_content");
    private By sendReviewBtn = By.cssSelector("button[type='submit']");
    private By star5Label = By.cssSelector("label[for='star-5-criterion-1']");
    private By incrementBtn = By.id("increment_button_1");
    private By decrementBtn = By.id("decrement_button_1");
    private By stockStatusValue = By.cssSelector(".details__item--quantities .details__right span");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getProductName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText().trim();
    }

    public boolean isMainImageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(mainProductImage)).isDisplayed();
    }

    public String getDescriptionText() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(shortDescription)).getText().trim();
        } catch (Exception e) {
            return "Description not found";
        }
    }

    public String getColorLabel() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(colorLegend)).getText().trim();
    }

    public String getPriceText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productPrice)).getText().trim();
    }

    public boolean isAddToCartIconVisible() {
        return driver.findElement(addToCartIcon).isDisplayed();
    }

    public String getQuantityValue() {
        return driver.findElement(quantityInput).getAttribute("value");
    }

    public boolean isSizeDropdownVisible() {
        return driver.findElement(sizeDropdown).isDisplayed();
    }

    public boolean isZoomModalDisplayed() {
        By modalLocator = By.cssSelector("div.modal-content");

        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(modalLocator)).isDisplayed();
        } catch (Exception e) {
            driver.switchTo().defaultContent();
            try {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(modalLocator)).isDisplayed();
            } catch (Exception ex) {
                return false;
            }
        }
    }

    public boolean isReviewModalDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("post-product-comment-form"))).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void fillReviewForm(String title, String content) {
        wait.until(ExpectedConditions.elementToBeClickable(star5Label)).click();
        driver.findElement(reviewTitleInput).sendKeys(title);
        driver.findElement(reviewContentTextArea).sendKeys(content);
    }

    public void submitReview() {
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(sendReviewBtn));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }

    public void clickIncrement() {
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(incrementBtn));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", btn
        );
        try {
            Thread.sleep(800);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }

    public void clickDecrement() {
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(decrementBtn));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'auto', block: 'center'});", btn);
        try {
            Thread.sleep(1000);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }

    public String getStockQuantity() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(stockStatusValue)).getText();
    }

    public void openProductDetails() {
        By accordionBtnLocator = By.cssSelector("#product_details_heading button");
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(accordionBtnLocator));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", btn
        );
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}