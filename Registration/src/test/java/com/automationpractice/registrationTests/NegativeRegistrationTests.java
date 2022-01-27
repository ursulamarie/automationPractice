package com.automationpractice.registrationTests;

import com.automationpractice.base.BaseTest;
import com.automationpractice.pages.AuthenticationPageObject;
import com.automationpractice.pages.HomePageObject;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NegativeRegistrationTests extends BaseTest {

    @Parameters({"emailAddress", "expectedErrorMessage"})
    @Test
    public void invalidEmailTest(String emailAddress, String expectedErrorMessage) {

        // open Homepage
        HomePageObject homePage = new HomePageObject(driver, log);
        homePage.openHomePage();

        // open authentication page
        AuthenticationPageObject authenticationPage = homePage.clickSignInLink();

        // perform negative registration
        authenticationPage.negativeRegistration(emailAddress);


        //wait for error message and get its text
        authenticationPage.waitForErrorMessage();

        String actualErrorMessage = authenticationPage.getActualErrorMessage();

        //assertions
        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage), "actual: " + actualErrorMessage);

    }
}
