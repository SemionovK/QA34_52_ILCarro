package ui_tests;

import dto.Car;
import dto.UserLombok;
import pages.PopUpPage;
import utils.enums.Fuel;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LetTheCarWorkPage;
import pages.LoginPage;
import utils.enums.HeaderMenu;

import static utils.CarFactory.*;
import static utils.PropertiesReader.getProperty;

public class LetTheCarWorkTest extends AppManager {
    LetTheCarWorkPage letTheCarWorkPage;
    LoginPage loginPage;

    @BeforeMethod
    public void toLogIn(){
//        HomePage homePage =  new HomePage(getDriver());
//        homePage.clickBtnLogin();
//        LoginPage loginPage = new LoginPage(getDriver());

        loginPage = new HomePage(getDriver()).clickHeaderButton(HeaderMenu.LOG_IN);
        UserLombok user = UserLombok.builder()
                .username(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        new PopUpPage(getDriver()).clickBtnOk();
        letTheCarWorkPage = new HomePage((getDriver())).clickHeaderButton(HeaderMenu.LET_THE_CAR_WORK);
    }

    @Test
    public void letTheCarWorkPositiveTest(){
        Car car = positiveCar();
        letTheCarWorkPage.typeCarForm(car);
        letTheCarWorkPage.downloadImage("cat.png");
        letTheCarWorkPage.clickBtnSubmitWithJS();
        Assert.assertTrue(letTheCarWorkPage.ValidateTextInMsgAddingCarFailed("Car adding failed"));
    }
}
