package com.automationpractice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

public class AuthenticationPageObject extends HeaderPageObject {
    public AuthenticationPageObject(WebDriver driver, Logger log) {
        super(driver, log);
    }

    private String expectedErrorMessage = "Invalid email address.";
    private By emailAddressFieldLocator = By.id("email_create");
    private By createAccountButtonLocator = By.name("SubmitCreate");
    private By errorMessage = By.xpath("//div[@id='create_account_error']/ol/li");
    private WebElement validationBox  = findElement(By.xpath("//label[@for='email_create']//parent::div"));

    public void negativeRegistration(String emailAddress) {
        log.info("Performing negative registration with email: " + emailAddress);
        typeText(emailAddress, emailAddressFieldLocator);
        click(createAccountButtonLocator);

    }

    public CreateAccountPageObject correctEmailRegistration(String emailAddress) {
        log.info("Performing positive registration with email: " + emailAddress);
        typeText(emailAddress, emailAddressFieldLocator);
        click(createAccountButtonLocator);
        return new CreateAccountPageObject(driver, log);

    }

    public void waitForErrorMessage() {
        waitForVisibility(errorMessage, 5);
    }

    public String getActualErrorMessage() {
        return findElement(errorMessage).getText();
    }

    public String getValidationBoxClassName() {
        return validationBox.getAttribute("class");
    }
}
