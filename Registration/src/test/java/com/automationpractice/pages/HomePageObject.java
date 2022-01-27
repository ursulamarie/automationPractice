package com.automationpractice.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePageObject extends HeaderPageObject {
    public HomePageObject(WebDriver driver, Logger log) {
        super(driver, log);
    }

    private String pageUrl = "http://automationpractice.com/index.php";
    private By signInLinkLocator = By.linkText("Sign in");

    public void openHomePage() {
        log.info("Opening page: " + pageUrl);
        openUrl(pageUrl);
        log.info("Page opened!");
    }

    public AuthenticationPageObject clickSignInLink() {
        log.info(("Clicking Sign In Link"));
        click(signInLinkLocator);
        return new AuthenticationPageObject(driver, log);
    }
}
