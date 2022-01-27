package com.automationpractice.registrationTests;

import com.automationpractice.base.BaseTest;
import com.automationpractice.base.CsvDataProviders;
import com.automationpractice.pages.AuthenticationPageObject;
import com.automationpractice.pages.CreateAccountPageObject;
import com.automationpractice.pages.HomePageObject;
import com.automationpractice.pages.MyAccountPageObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class PositiveRegistrationTests extends BaseTest {
    @Test(dataProvider = "csvReader", dataProviderClass = CsvDataProviders.class)
    public void positiveRegistrationTest(Map<String, String> testData) {

        // data
        String name = testData.get("name");
        String surname = testData.get("surname");
        String email = testData.get("email");
        String pass = testData.get("pass");
        String address = testData.get("address");
        String city = testData.get("city");
        String stateOption = testData.get("stateOption");
        String postcode = testData.get("postcode");
        String mobile = testData.get("mobile");

        // open homepage
        HomePageObject homePage = new HomePageObject(driver, log);
        homePage.openHomePage();

        // open authentication page
        AuthenticationPageObject authenticationPage = homePage.clickSignInLink();

        // start registration, fill in fields and assert that the email is correct
        CreateAccountPageObject createAccountPage = authenticationPage.correctEmailRegistration(email);

        String correctEmailClassName = "form-group form-ok";
        Assert.assertEquals(authenticationPage.getValidationBoxClassName(), correctEmailClassName, "Email is in incorrect format");

        createAccountPage.fillInRegistrationForm(name, surname, pass, address, city, stateOption, postcode, mobile);

        /** add error messages **/
        //assert that email address was copied correctly
        Assert.assertEquals(createAccountPage.getEmailValue(), email, "Email is incorrect");

        //assert that first and last names were copied correctly
        Assert.assertEquals(createAccountPage.getNameValue(), name, "Name is incorrect");
        Assert.assertEquals(createAccountPage.getSurnameValue(), surname,"Surname is incorrect");

        // finish registration
        MyAccountPageObject myAccountPage = createAccountPage.clickRegister();

        //assert that username is displayed on MyAccount page
        Assert.assertEquals(myAccountPage.getUsername(), name + " " + surname, "Username is incorrect");

    }
}

