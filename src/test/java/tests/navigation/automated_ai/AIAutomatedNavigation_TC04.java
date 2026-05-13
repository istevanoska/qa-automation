package tests.navigation.automated_ai;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import utils.FrameUtils;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AIAutomatedNavigation_TC04 extends BaseTest {

    @Test
    void shouldNavigateToAllFooterLinks() {

        String[][] footerLinks = {
                {"#link-cms-page-2-2", "legal-notice"},
                {"#link-static-page-contact-2", "contact-us"},
                {"#link-static-page-sitemap-2", "sitemap"},
                {"#link-static-page-stores-2", "stores"}
        };

        for (String[] footerLink : footerLinks) {

            driver.get("https://demo.prestashop.com/#/en/front");

            HomePage homePage = new HomePage(driver);

            FrameUtils.switchToStoreFrame(driver);

            homePage.clickFooterLink(footerLink[0]);

            boolean urlChanged =
                    new org.openqa.selenium.support.ui.WebDriverWait(
                            driver,
                            java.time.Duration.ofSeconds(15)
                    ).until(driver ->
                            homePage.getCurrentFrameUrl().contains(footerLink[1])
                    );

            assertTrue(
                    urlChanged,
                    "Expected iframe URL to contain: "
                            + footerLink[1]
                            + ", but was: "
                            + homePage.getCurrentFrameUrl()
            );

            FrameUtils.switchToDefaultContent(driver);
        }
    }
}