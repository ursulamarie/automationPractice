package com.automationpractice.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import javax.security.auth.kerberos.KerberosTicket;

public class CreateAccountPageObject extends HeaderPageObject {
    public CreateAccountPageObject(WebDriver driver, Logger log) {
        super(driver, log);
    }

    private By nameLocator = By.id("customer_firstname");
    private By surnameLocator = By.id("customer_lastname");
    private By emailLocator = By.id("email");
    private By passLocator = By.id("passwd");
    private By addNameLocator = By.id("firstname");
    private By addSurnameLocator = By.id("lastname");
    private By addressLocator = By.id("address1");
    private By cityLocator = By.id("city");
    private By stateLocator = By.id("id_state");
    private By postCodeLocator = By.id("postcode");
    private By mobileLocator = By.id("phone_mobile");
    private By registerButtonLocator = By.id("submitAccount");

    public void fillInRegistrationForm(String name, String surname, String pass, String address, String city, String stateOption, String postcode, String mobile) {
        log.info("Filling in registration form");
        typeText(name, nameLocator);
        typeText(surname, surnameLocator);
        typeText(pass, passLocator);
        typeText(address, addressLocator);
        typeText(city, cityLocator);
        selectOption(stateOption);
        typeText(postcode, postCodeLocator);
        typeText(mobile, mobileLocator);
    }

    public MyAccountPageObject clickRegister() {
        log.info("Clicking register button");
        click(registerButtonLocator);
        return new MyAccountPageObject(driver, log);
    }

    public void selectOption(String i) {
        log.info("Selecting option " + i + " from dropdown");
        WebElement stateDropdown = findElement(stateLocator);
        Select dropdown = new Select(stateDropdown);
        dropdown.selectByValue("" + i);
    }

    public String getEmailValue() {
        //WebElement email = findElement(By.id("email"));
        // return email.getAttribute("value");
        By email = By.id("email");
        return getAttributeValue(email, "value");
    }

    public String getNameValue() {
        By name = By.id("firstname");
        return getAttributeValue(name, "value");
    }

    public String getSurnameValue() {
        By surname = By.id("lastname");
        return getAttributeValue(surname, "value");
    }
}
