package ui_tests;

import dto.UserLombok;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import static utils.UserFactory.*;

public class LoginTests extends AppManager {
    @BeforeMethod
    public void goToLoginPage(){
        new HomePage(getDriver()).clickBtnLogin();
    }

    @Test
    public void loginPositiveTest(){
        UserLombok user = positiveUser();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        //Assert.assertEquals(loginPage.getLoggedInMessage(), "Logged in success");
        Assert.assertTrue(loginPage.validateTextInMessageLoginSuccess("Logged in success"));
        loginPage.clickOk();
    }

    @Test
    public void loginWithWrongPasswordTest(){
        UserLombok user = UserLombok.builder()
                .username("bruno1@gmail.com")
                .password("1")
                .build();
        LoginPage loginPage =  new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(loginPage.validateTextInMessageLoginFailed("Login or Password incorrect"));
        loginPage.clickOk();
    }

    @Test
    public void loginWithWrongEmailTest(){
        UserLombok user = UserLombok.builder()
                .username("runo1@gmail.com")
                .password("QAZ123!lnk")
                .build();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        Assert.assertTrue(loginPage.validateTextInMessageLoginFailed("Login or Password incorrect"));
        loginPage.clickOk();
    }

}
