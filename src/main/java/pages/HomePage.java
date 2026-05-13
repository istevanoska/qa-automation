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
    private By firstProduct =
            By.cssSelector(
                    "article.product-miniature a"
            );

    private By addToCartButton =
            By.cssSelector(
                    "button.product__add-to-cart-button[data-button-action='add-to-cart']"
            );

    private By modalCheckoutButton =
            By.cssSelector(
                    "#blockcart-modal .btn-primary"
            );

    private By shoppingCartTitle =
            By.xpath(
                    "//h1[contains(text(),'Shopping Cart')]"
            );

    private By checkoutButton =
            By.cssSelector(
                    ".cart-summary .btn-primary"
            );

    private By mrRadio =
            By.cssSelector(
                    "input[name='id_gender'][value='1']"
            );

    private By firstNameField =
            By.name("firstname");

    private By lastNameField =
            By.name("lastname");

    private By emailField =
            By.name("email");

    private By passwordField =
            By.id("field-password");

    private By birthdayField =
            By.name("birthday");

    private By termsCheckbox =
            By.name("psgdpr");

    private By privacyCheckbox =
            By.name("customer_privacy");

    private By continueButton =
            By.cssSelector(
                    "button[type='submit']"
            );

    private By addressField =
            By.name("address1");

    private By cityField =
            By.name("city");

    private By postcodeField =
            By.name("postcode");

    private By countryDropdown =
            By.name("id_country");

    private By continueAddressButton =
            By.name("confirm-addresses");

    private By shippingStep =
            By.id("delivery");

    private By continueShippingButton =
            By.name("confirmDeliveryOption");

    private By cartBadge =
            By.cssSelector(
                    ".header-block__badge"
            );

    private By cartButton =
            By.cssSelector(
                    ".header-block__action-btn"
            );
    private By addToCartSimpleButton =
            By.cssSelector(
                    "button[data-button-action='add-to-cart']"
            );

    private By cartPageButton =
            By.cssSelector(
                    ".header-block__action-btn"
            );

    // ================= CHECKOUT METHODS =================
    public void clickSimpleAddToCart() {

        WebElement button =
                WaitUtils.waitForClickable(
                        driver,
                        addToCartSimpleButton
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

    public boolean isCartAccessible() {

        try {

            WebElement button =
                    WaitUtils.waitForClickable(
                            driver,
                            cartPageButton
                    );

            WaitUtils.scrollIntoView(
                    driver,
                    button
            );

            WaitUtils.jsClick(
                    driver,
                    button
            );

            return true;

        } catch (Exception e) {

            return false;
        }
    }

    public int getCartCount() {

        try {

            WebElement badge =
                    WaitUtils.waitForVisible(
                            driver,
                            cartBadge
                    );

            String text =
                    badge.getText().trim();

            if (text.isEmpty()) {

                return 0;
            }

            return Integer.parseInt(text);

        } catch (Exception e) {

            return 0;
        }
    }

    public void refreshStorePage()
            throws Exception {

        driver.navigate().refresh();

        driver.switchTo().defaultContent();

        Thread.sleep(3000);

        driver.switchTo().frame(0);

        Thread.sleep(3000);
    }

    public void openInvalidProductPage() {

        ((JavascriptExecutor) driver)
                .executeScript(
                        "window.location.hash='#/en/999999-nonexistent-product.html';"
                );
    }
    public void openFirstProduct() {

        WebElement product =
                WaitUtils.waitForClickable(
                        driver,
                        firstProduct
                );

        WaitUtils.robustClick(
                driver,
                product
        );
    }

    public void clickAddToCart() {

        WebElement button =
                WaitUtils.waitForClickable(
                        driver,
                        addToCartButton
                );

        WaitUtils.robustClick(
                driver,
                button
        );
    }

    public void proceedToCheckoutFromModal() {

        try {

            WebElement button =
                    WaitUtils.waitForClickable(
                            driver,
                            modalCheckoutButton
                    );

            WaitUtils.robustClick(
                    driver,
                    button
            );

        } catch (Exception e) {

            driver.navigate().to(
                    "https://demo.prestashop.com/#/en/cart"
            );
        }
    }

    public boolean isShoppingCartPageDisplayed() {

        try {

            return WaitUtils.waitForVisible(
                    driver,
                    shoppingCartTitle
            ).isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    public void clickCheckoutButton() {

        WebElement button =
                WaitUtils.waitForClickable(
                        driver,
                        checkoutButton
                );

        WaitUtils.scrollIntoView(
                driver,
                button
        );

        WaitUtils.robustClick(
                driver,
                button
        );
    }

    public void fillPersonalInformation(
            String firstName,
            String lastName,
            String email,
            String birthday
    ) {

        try {

            if (driver.findElements(mrRadio).size() > 0) {

                WebElement radio =
                        WaitUtils.waitForClickable(
                                driver,
                                mrRadio
                        );

                WaitUtils.jsClick(
                        driver,
                        radio
                );
            }

        } catch (Exception ignored) {
        }

        try {

            WebElement firstNameInput =
                    WaitUtils.waitForVisible(
                            driver,
                            firstNameField
                    );

            firstNameInput.clear();

            firstNameInput.sendKeys(firstName);

        } catch (Exception ignored) {
        }

        try {

            WebElement lastNameInput =
                    WaitUtils.waitForVisible(
                            driver,
                            lastNameField
                    );

            lastNameInput.clear();

            lastNameInput.sendKeys(lastName);

        } catch (Exception ignored) {
        }

        try {

            WebElement emailInput =
                    WaitUtils.waitForVisible(
                            driver,
                            emailField
                    );

            emailInput.clear();

            emailInput.sendKeys(email);

        } catch (Exception ignored) {
        }

        // IMPORTANT FIX
        try {

            if (driver.findElements(
                    By.name("password-form__check")
            ).size() > 0) {

                WebElement checkbox =
                        driver.findElement(
                                By.name("password-form__check")
                        );

                WaitUtils.jsClick(
                        driver,
                        checkbox
                );

                Thread.sleep(1000);
            }

            if (driver.findElements(passwordField).size() > 0) {

                WebElement passwordInput =
                        WaitUtils.waitForVisible(
                                driver,
                                passwordField
                        );

                WaitUtils.scrollIntoView(
                        driver,
                        passwordInput
                );

                WaitUtils.jsClick(
                        driver,
                        passwordInput
                );

                passwordInput.sendKeys(
                        "StrongPass123!"
                );
            }

        } catch (Exception ignored) {
        }

        try {

            if (driver.findElements(birthdayField).size() > 0) {

                WebElement birthdayInput =
                        WaitUtils.waitForVisible(
                                driver,
                                birthdayField
                        );

                birthdayInput.sendKeys(birthday);
            }

        } catch (Exception ignored) {
        }

        try {

            if (driver.findElements(
                    termsCheckbox
            ).size() > 0) {

                WaitUtils.jsClick(
                        driver,
                        driver.findElement(
                                termsCheckbox
                        )
                );
            }

        } catch (Exception ignored) {
        }

        try {

            if (driver.findElements(
                    privacyCheckbox
            ).size() > 0) {

                WaitUtils.jsClick(
                        driver,
                        driver.findElement(
                                privacyCheckbox
                        )
                );
            }

        } catch (Exception ignored) {
        }
    }

    public void clickContinuePersonalInformation() {

        try {

            By continueButton1 =
                    By.cssSelector(
                            "button.continue.btn.btn-primary"
                    );

            WebElement button =
                    WaitUtils.waitForClickable(
                            driver,
                            continueButton1
                    );

            WaitUtils.scrollIntoView(
                    driver,
                    button
            );

            WaitUtils.jsClick(
                    driver,
                    button
            );

            return;

        } catch (Exception ignored) {
        }

        try {

            By continueButton2 =
                    By.cssSelector(
                            "button[type='submit']"
                    );

            WebElement button =
                    WaitUtils.waitForClickable(
                            driver,
                            continueButton2
                    );

            WaitUtils.scrollIntoView(
                    driver,
                    button
            );

            WaitUtils.jsClick(
                    driver,
                    button
            );

            return;

        } catch (Exception ignored) {
        }

        try {

            By continueButton3 =
                    By.xpath(
                            "//button[contains(.,'Continue')]"
                    );

            WebElement button =
                    WaitUtils.waitForClickable(
                            driver,
                            continueButton3
                    );

            WaitUtils.scrollIntoView(
                    driver,
                    button
            );

            WaitUtils.jsClick(
                    driver,
                    button
            );

        } catch (Exception e) {

            System.out.println(
                    "Could not click continue personal information button"
            );
        }
    }

    public boolean isAddressStepVisible() {

        return driver.findElements(
                addressField
        ).size() > 0;
    }

    public void fillAddressInformation() {

        driver.findElement(addressField)
                .sendKeys("123 Main St");

        driver.findElement(cityField)
                .sendKeys("Paris");

        driver.findElement(postcodeField)
                .sendKeys("75001");

        org.openqa.selenium.support.ui.Select country =
                new org.openqa.selenium.support.ui.Select(
                        driver.findElement(countryDropdown)
                );

        country.selectByVisibleText("France");
    }

    public void continueAddressStep() {

        WebElement button =
                WaitUtils.waitForClickable(
                        driver,
                        continueAddressButton
                );

        WaitUtils.scrollIntoView(
                driver,
                button
        );

        WaitUtils.robustClick(
                driver,
                button
        );
    }

    public boolean isShippingStepVisible() {

        try {

            return WaitUtils.waitForVisible(
                    driver,
                    shippingStep
            ).isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    public void continueShippingStep() {

        try {

            WebElement button =
                    WaitUtils.waitForClickable(
                            driver,
                            continueShippingButton
                    );

            WaitUtils.scrollIntoView(
                    driver,
                    button
            );

            WaitUtils.jsClick(
                    driver,
                    button
            );

        } catch (Exception ignored) {
        }
    }

    public String getCartBadgeValue() {

        return WaitUtils.waitForVisible(
                driver,
                cartBadge
        ).getText().trim();
    }

    public void clickCartButton() {

        WebElement button =
                WaitUtils.waitForClickable(
                        driver,
                        cartButton
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

        WebDriverWait wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(20)
                );

        WebElement element =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                userProfileName
                        )
                );

        try {

            element.click();

        } catch (Exception e) {

            WaitUtils.jsClick(
                    driver,
                    element
            );
        }
    }

}