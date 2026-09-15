package ui_tests;

import data_providers.UserDataProvider;
import dto.UserLombok;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.RegistrationPage;
import utils.TestNGListener;

import static utils.UserFactory.positiveUser;
@Listeners(TestNGListener.class)

public class RegistrationTests extends AppManager {
    RegistrationPage registrationPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod(alwaysRun = true)
    public void goToRegistrationPage(){
        logger.info("start registration test");
        new HomePage(getDriver()).clickBtnSignUp();
        registrationPage = new RegistrationPage(getDriver());
    }

    @Test(groups = {"smoke", "regress", "user", "positive"})
    public void registrationPositiveTest() {
        UserLombok user = positiveUser();
        System.out.println(user);
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickCheckboxByMyScreenResolution();
        registrationPage.clickBtnYalla();
        Assert.assertTrue(registrationPage.isPopUpLoginDisplayed());
    }

    @Test(dataProvider = "dataProviderForRegistrationWrongPasswordOrEmail",
            dataProviderClass = UserDataProvider.class)
    public void registrationNegativeWrongPasswordTest(UserLombok user) {
        registrationPage.clickCheckboxByMyScreenResolution();
        registrationPage.typeRegistrationForm(user);
        registrationPage.clickBtnYalla();
        Assert.assertTrue(registrationPage.isTextInErrorPresent("Password must contain 1 uppercase letter, " +
                "1 lowercase letter, 1 number and one special symbol of [@$#^&*!]"));
    }
}
