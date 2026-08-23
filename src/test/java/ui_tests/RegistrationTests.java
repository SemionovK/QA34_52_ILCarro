package ui_tests;

import dto.UserLombok;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.RegistrationPage;
import static utils.UserFactory.positiveUser;

public class RegistrationTests extends AppManager {
    RegistrationPage registrationPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void goToRegistrationPage(){
        logger.info("start registration test");
        new HomePage(getDriver()).clickBtnSignUp();
        registrationPage = new RegistrationPage(getDriver());
    }

    @Test
    public void registrationPositiveTest() {
        UserLombok user = positiveUser();
        System.out.println(user);
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckboxByMyScreenResolution();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(registrationPage.isPopUpLoginDisplayed());
    }
}
