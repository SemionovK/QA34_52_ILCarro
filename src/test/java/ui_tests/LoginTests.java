package ui_tests;

import dto.UserLombok;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;

import static utils.PropertiesReader.*;

public class LoginTests extends AppManager {
    LoginPage loginPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToLoginPage(){
        new HomePage(getDriver()).clickBtnLogin();
        loginPage = new LoginPage(getDriver());
    }

    @Test
    public void loginPositiveTest(){
        UserLombok user = UserLombok.builder()
                .username(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        //Assert.assertEquals(loginPage.getLoggedInMessage(), "Logged in success");
        //Assert.assertTrue(loginPage.validateTextInMessageLoginSuccess("Logged in success"));
        Assert.assertTrue(loginPage.isPopUpLoginDisplayed());
        loginPage.clickOk();
    }

    @Test
    public void loginWithWrongPasswordTest(){
        UserLombok user = UserLombok.builder()
                .username(getProperty("base.properties", "email"))
                .password("QQAZ123!lnk")
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        //Assert.assertTrue(loginPage.validateTextInMessageLoginFailed("Login or Password incorrect"));
        Assert.assertTrue(loginPage.isPopUpLoginFailedDisplayed());
        loginPage.clickOk();
    }

    @Test
    public void loginWithWrongEmailTest(){
        UserLombok user = UserLombok.builder()
                .username("runo1@gmail.com")
                .password(getProperty("base.properties", "password"))
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        //Assert.assertTrue(loginPage.validateTextInMessageLoginFailed("Login or Password incorrect"));
        Assert.assertTrue(loginPage.isPopUpLoginFailedDisplayed());
        loginPage.clickOk();
    }

    @Test
    public void loginNegativeEmptyAllFieldsWithoutClickInFieldsTest(){
        loginPage.clickBtnYalla();
        Assert.assertFalse(loginPage.isBtnYallaEnabled());
    }

    @Test
    public void loginNegativeEmptyAllFieldsWithClickInFieldsTest(){
        UserLombok user = UserLombok.builder()
                        .username("")
                        .password("")
                        .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        softAssert.assertFalse(loginPage.isBtnYallaEnabled(), "validate isBtnYallaEnabled");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Email is required"), "validate message: Email is required");
        softAssert.assertTrue(loginPage.isTextInErrorPresent("Password is required"), "validate message: Password is required");
        softAssert.assertAll();
    }

}
