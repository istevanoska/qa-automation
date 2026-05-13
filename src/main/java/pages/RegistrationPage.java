package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtils;

import java.time.Duration;
import java.util.List;

public class RegistrationPage {

    private WebDriver driver;


    private By createAccountLink =
            By.cssSelector(
                    "a[data-link-action='display-register-form']"
            );

    private By socialTitleMrs =
            By.id("field-id_gender_2");

    private By socialTitleMr =
            By.cssSelector(
                    "input[name='id_gender'][value='1']"
            );

    private By firstNameField =
            By.id("field-firstname");

    private By lastNameField =
            By.id("field-lastname");

    private By emailField =
            By.id("field-email");

    private By passwordField =
            By.id("field-password");

    private By birthdayField =
            By.id("field-birthday");

    private By psgdprCheckbox =
            By.id("field-psgdpr");

    private By customerPrivacyCheckbox =
            By.id("field-customer_privacy");

    private By continueButton =
            By.cssSelector(
                    "button[type='submit']"
            );

    // ================= ADDRESS LOCATORS =================

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

    // ================= VALIDATION LOCATORS =================

    private By passwordPolicySection =
            By.cssSelector(
                    ".password-policy-input-container"
            );

    private By passwordStrengthText =
            By.cssSelector(
                    ".password-strength-text"
            );

    private By specificRequirements =
            By.cssSelector(
                    ".password-requirements-list li"
            );

    private By firstNameValidation =
            By.cssSelector(
                    ".form-control-comment"
            );

    public RegistrationPage(WebDriver driver) {

        this.driver = driver;
    }

    // ================= REGISTRATION METHODS =================

    public void clickCreateAccountLink() {

        WebElement link =
                WaitUtils.waitForVisible(
                        driver,
                        createAccountLink
                );

        WaitUtils.scrollIntoView(
                driver,
                link
        );

        WaitUtils.jsClick(
                driver,
                link
        );
    }

    public void selectMrTitle() {

        try {

            WebElement radio =
                    WaitUtils.waitForClickable(
                            driver,
                            socialTitleMr
                    );

            WaitUtils.jsClick(
                    driver,
                    radio
            );

        } catch (Exception ignored) {
        }
    }

    public void fillPersonalDetails(
            String fName,
            String lName,
            String email,
            String bday
    ) {

        try {

            WaitUtils.waitForClickable(
                    driver,
                    socialTitleMrs
            ).click();

        } catch (Exception ignored) {
        }

        driver.findElement(firstNameField)
                .sendKeys(fName);

        driver.findElement(lastNameField)
                .sendKeys(lName);

        driver.findElement(emailField)
                .sendKeys(email);

        driver.findElement(birthdayField)
                .sendKeys(bday);

        WaitUtils.jsClick(
                driver,
                driver.findElement(psgdprCheckbox)
        );

        WaitUtils.jsClick(
                driver,
                driver.findElement(customerPrivacyCheckbox)
        );
    }

    public void enterEmailOnly(String email) {

        driver.findElement(emailField)
                .sendKeys(email);
    }

    public void enterBirthday(String birthday) {

        driver.findElement(birthdayField)
                .sendKeys(birthday);
    }

    public void acceptPolicies() {

        if (driver.findElements(psgdprCheckbox)
                .size() > 0) {

            WaitUtils.jsClick(
                    driver,
                    driver.findElement(psgdprCheckbox)
            );
        }

        if (driver.findElements(customerPrivacyCheckbox)
                .size() > 0) {

            WaitUtils.jsClick(
                    driver,
                    driver.findElement(customerPrivacyCheckbox)
            );
        }
    }

    public void typePasswordRealTime(String password) {

        WebElement pField =
                WaitUtils.waitForVisible(
                        driver,
                        passwordField
                );

        pField.clear();

        for (char c : password.toCharArray()) {

            pField.sendKeys(
                    String.valueOf(c)
            );

            try {

                Thread.sleep(100);

            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
    }

    public void clickContinueButton() {

        WebElement button =
                WaitUtils.waitForClickable(
                        driver,
                        continueButton
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

    // ================= ADDRESS METHODS =================

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

        Select country =
                new Select(
                        driver.findElement(countryDropdown)
                );

        country.selectByVisibleText(
                "France"
        );
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

        WaitUtils.jsClick(
                driver,
                button
        );
    }

    // ================= VALIDATION METHODS =================

    public String getPasswordStrengthResult() {

        try {

            Thread.sleep(500);

            return WaitUtils.waitForVisible(
                    driver,
                    passwordStrengthText
            ).getText();

        } catch (Exception e) {

            return "Unknown";
        }
    }

    public void printFailedRequirements() {

        List<WebElement> requirements =
                driver.findElements(
                        specificRequirements
                );

        System.out.println(
                "Status of specific requirements:"
        );

        for (WebElement req : requirements) {

            String status =
                    req.getAttribute("class");

            String icon =
                    (status.contains("success")
                            ||

                            status.contains("valid"))

                            ?

                            "[✔]"

                            :

                            "[✘]";

            System.out.println(
                    "   "
                            + icon
                            + " "
                            + req.getText()
            );
        }
    }

    public boolean areRequirementsVisible() {

        try {

            WebDriverWait wait =
                    new WebDriverWait(
                            driver,
                            Duration.ofSeconds(3)
                    );

            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            passwordPolicySection
                    )
            ).isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    public String getFirstNameValidationMessage() {

        try {

            return WaitUtils.waitForVisible(
                    driver,
                    firstNameValidation
            ).getText();

        } catch (Exception e) {

            return "";
        }
    }
}