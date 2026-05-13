package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtils;

import java.time.Duration;

public class ProductPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // existing locators
    private By productName =
            By.cssSelector("h1.product__name");

    private By mainProductImage =
            By.cssSelector("img.img-fluid.w-100");

    private By shortDescription =
            By.cssSelector(".product__description-short p");

    private By colorLegend =
            By.id("legend_2_1");

    private By productPrice =
            By.cssSelector(".product__price");

    private By sizeDropdown =
            By.cssSelector("select.form-select");

    private By quantityInput =
            By.cssSelector(
                    "input.js-cart-line-product-quantity"
            );

    private By addToCartIcon =
            By.cssSelector(
                    ".material-icons[aria-hidden='true']"
            );

    private By reviewTitleInput =
            By.id("comment_title");

    private By reviewContentTextArea =
            By.id("comment_content");

    private By sendReviewBtn =
            By.cssSelector("button[type='submit']");

    private By star5Label =
            By.cssSelector(
                    "label[for='star-5-criterion-1']"
            );

    private By incrementBtn =
            By.id("increment_button_1");

    private By decrementBtn =
            By.id("decrement_button_1");

    private By stockStatusValue =
            By.cssSelector(
                    ".details__item--quantities .details__right span"
            );

    // shopping/cart locators
    private By addToCartButton =
            By.cssSelector(
                    "button[data-button-action='add-to-cart']"
            );

    private By cartModal =
            By.id("blockcart-modal");

    private By cartBadge =
            By.cssSelector(
                    ".header-block__badge"
            );

    private By proceedToCheckoutButton =
            By.cssSelector(
                    "#blockcart-modal a[href*='cart?action=show']"
            );

    private By continueShoppingButton =
            By.cssSelector(
                    "#blockcart-modal button[data-bs-dismiss='modal']"
            );

    private By plusButton =
            By.cssSelector(
                    ".js-increment-button"
            );

    private By minusButton =
            By.cssSelector(
                    ".js-decrement-button"
            );

    public ProductPage(WebDriver driver) {

        this.driver = driver;

        this.wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(10)
                );
    }

    // ================= SHOPPING METHODS =================

    public void clickIncrementQuantity(int times) {

        for (int i = 0; i < times; i++) {

            WebElement btn =
                    wait.until(
                            ExpectedConditions.elementToBeClickable(
                                    incrementBtn
                            )
                    );

            ((JavascriptExecutor) driver)
                    .executeScript(
                            "arguments[0].scrollIntoView({behavior:'smooth',block:'center'});",
                            btn
                    );

            ((JavascriptExecutor) driver)
                    .executeScript(
                            "arguments[0].click();",
                            btn
                    );
        }
    }

    public void clickAddToCart() {

        WebElement btn =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                addToCartButton
                        )
                );

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({behavior:'smooth',block:'center'});",
                        btn
                );

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].click();",
                        btn
                );
    }

    public WebElement waitForCartModal() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        cartModal
                )
        );
    }

    public String getCartBadgeCount() {

        WebElement badge =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                cartBadge
                        )
                );

        return badge.getText().trim();
    }

    public void clickProceedToCheckout() {

        WebElement button =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                proceedToCheckoutButton
                        )
                );

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        button
                );

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].click();",
                        button
                );
    }

    public void clickContinueShopping() {

        WebElement button =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                continueShoppingButton
                        )
                );

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        button
                );

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].click();",
                        button
                );
    }

    public String getQuantityValue() {

        WebElement input =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                quantityInput
                        )
                );

        return input.getAttribute("value");
    }

    public void clickPlusButton(int times) {

        for (int i = 0; i < times; i++) {

            WebElement button =
                    wait.until(
                            ExpectedConditions.elementToBeClickable(
                                    plusButton
                            )
                    );

            WaitUtils.scrollIntoView(
                    driver,
                    button
            );

            WaitUtils.jsClick(
                    driver,
                    button
            );
        }
    }

    public void clickMinusButton() {

        WebElement button =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                minusButton
                        )
                );

        WaitUtils.scrollIntoView(
                driver,
                button
        );

        WaitUtils.jsClick(
                driver,
                button
        );
    }

    // ================= EXISTING PRODUCT METHODS =================

    public String getProductName() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        productName
                )
        ).getText().trim();
    }

    public boolean isMainImageDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        mainProductImage
                )
        ).isDisplayed();
    }

    public String getDescriptionText() {

        try {

            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            shortDescription
                    )
            ).getText().trim();

        } catch (Exception e) {

            return "Description not found";
        }
    }

    public String getColorLabel() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        colorLegend
                )
        ).getText().trim();
    }

    public String getPriceText() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        productPrice
                )
        ).getText().trim();
    }

    public boolean isAddToCartIconVisible() {

        return driver.findElement(
                addToCartIcon
        ).isDisplayed();
    }

    public boolean isSizeDropdownVisible() {

        return driver.findElement(
                sizeDropdown
        ).isDisplayed();
    }

    public boolean isZoomModalDisplayed() {

        By modalLocator =
                By.cssSelector(
                        "div.modal-content"
                );

        try {

            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            modalLocator
                    )
            ).isDisplayed();

        } catch (Exception e) {

            driver.switchTo().defaultContent();

            try {

                return wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                modalLocator
                        )
                ).isDisplayed();

            } catch (Exception ex) {

                return false;
            }
        }
    }

    public boolean isReviewModalDisplayed() {

        try {

            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.id("post-product-comment-form")
                    )
            ).isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    public void fillReviewForm(
            String title,
            String content
    ) {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        star5Label
                )
        ).click();

        driver.findElement(reviewTitleInput)
                .sendKeys(title);

        driver.findElement(reviewContentTextArea)
                .sendKeys(content);
    }

    public void submitReview() {

        WebElement btn =
                wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                sendReviewBtn
                        )
                );

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].click();",
                        btn
                );
    }

    public void clickIncrement() {

        WebElement btn =
                wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                incrementBtn
                        )
                );

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({behavior:'smooth',block:'center'});",
                        btn
                );

        try {

            Thread.sleep(800);

        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].click();",
                        btn
                );
    }

    public void clickDecrement() {

        WebElement btn =
                wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                decrementBtn
                        )
                );

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({behavior:'auto',block:'center'});",
                        btn
                );

        try {

            Thread.sleep(1000);

        } catch (Exception e) {

            e.printStackTrace();
        }

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].click();",
                        btn
                );
    }

    public String getStockQuantity() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        stockStatusValue
                )
        ).getText();
    }

    public void openProductDetails() {

        By accordionBtnLocator =
                By.cssSelector(
                        "#product_details_heading button"
                );

        WebElement btn =
                wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                accordionBtnLocator
                        )
                );

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({behavior:'smooth',block:'center'});",
                        btn
                );

        try {

            Thread.sleep(1000);

        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].click();",
                        btn
                );

        try {

            Thread.sleep(1000);

        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }
}

