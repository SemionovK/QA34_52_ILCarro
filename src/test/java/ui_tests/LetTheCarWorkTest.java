package ui_tests;

import dto.Car;
import dto.UserLombok;
import enums.Fuel;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LetTheCarWorkPage;
import pages.LoginPage;

import static utils.PropertiesReader.getProperty;

public class LetTheCarWorkTest extends AppManager {
    LetTheCarWorkPage letTheCarWorkPage;

    @BeforeMethod
    public void toLogIn(){
        HomePage homePage =  new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        UserLombok user = UserLombok.builder()
                .username(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();
        loginPage.typeLoginForm(user);
        loginPage.clickBtnYalla();
        homePage.clickBtnLetTheCarWork();
        letTheCarWorkPage = new LetTheCarWorkPage(getDriver());
    }

    @Test
    public void letTheCarWorkPositiveTest(){
        Car car = Car.builder()
                .location("Haifa")
                .manufacture("KIA")
                .model("TF100")
                .year(2024)
                .fuel(Fuel.HYBRID)
                .seats(4)
                .carClass("Business")
                .carRegistrationNumber("TD25342")
                .price(120.50)
                .about("in a good condition")
                .inputUploadPhoto("C:\\PicsForQa\\qa.jpg")
                .build();
        letTheCarWorkPage.typeCarForm(car);
        letTheCarWorkPage.waitForPhotoUpload();
        letTheCarWorkPage.clickBtnSubmitWithJS();
        Assert.assertTrue(letTheCarWorkPage.ValidateTextInMsgAddingCarFailed("Car adding failed"));
    }
}
