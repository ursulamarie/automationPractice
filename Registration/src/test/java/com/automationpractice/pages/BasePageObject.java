package com.automationpractice.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageObject {
    protected WebDriver driver;
    protected Logger log;


    public BasePageObject(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;
    }

    // open given URL
    public void openUrl(String url) {
        driver.get(url);
    }

    // read actual url
    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // type in text
    protected void typeText(String text, By locator) {
        waitForVisibility(locator, 10);
        findElement(locator).sendKeys(text);
    }

    // click element
    protected void click(By locator) {
        waitForVisibility(locator, 5);
        findElement(locator).click();
    }

    //get elements value
    protected String getAttributeValue(By locator, String value) {
        waitForVisibility(locator);
        return findElement(locator).getAttribute(value);
    }

    //get elements text
    protected String getElementText(By locator){
        waitForVisibility(locator);
        return findElement(locator).getText();
    }

    // find element using locator
    protected WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    // wait for x seconds for the condition to be fulfilled
    protected void waitFor(ExpectedCondition<WebElement> condition, Integer timeoutInSecs) {
        timeoutInSecs = timeoutInSecs != null ? timeoutInSecs : 30;
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecs);
        wait.until(condition);
    }

    // wait for given element to be visible
    protected void waitForVisibility(By locator, Integer... timeoutInSecs) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                waitFor(ExpectedConditions.visibilityOfElementLocated(locator), timeoutInSecs.length > 0 ? timeoutInSecs[0] : null);
                break;
            } catch (StaleElementReferenceException e) {
                System.out.println("Waiting failed with exception " + e);
            }
        }
        attempts++;
    }


}
