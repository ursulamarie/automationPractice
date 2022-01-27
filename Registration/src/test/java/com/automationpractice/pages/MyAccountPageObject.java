package com.automationpractice.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPageObject extends HeaderPageObject {
    public MyAccountPageObject(WebDriver driver, Logger log) {
        super(driver, log);
    }

    private By username = By.xpath("//a[contains(@title,'View my customer')]");

    public String getUsername() {
        return getElementText(username);
    }
}
