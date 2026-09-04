package ui_tests;

import data_providers.CarDataProvider;
import dto.Car;
import dto.UserLombok;
import org.testng.asserts.SoftAssert;
import pages.PopUpPage;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LetTheCarWorkPage;
import pages.LoginPage;
import utils.enums.HeaderMenu;

import java.time.LocalDate;

import static utils.CarFactory.*;
import static utils.PropertiesReader.getProperty;

public class LetTheCarWorkTest extends AppManager {
    LetTheCarWorkPage letTheCarWorkPage;
    LoginPage loginPage;
    SoftAssert softAssert = new SoftAssert();

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
        letTheCarWorkPage.waitForPhotoUpload();
        letTheCarWorkPage.clickBtnSubmitWithJS();
        Assert.assertTrue(letTheCarWorkPage.ValidateTextInMsgAddingCarFailed("Car adding failed"));
    }

    @Test
    public void letTheCarWorkClickBtnSubmitOnlyNegativeTest(){
        letTheCarWorkPage.clickBtnSubmitWithJS();
        Assert.assertTrue(letTheCarWorkPage.validateTextInMsgFieldMustNotBeBlank(
                "{\"manufacture\":\"must not be blank\"," +
                        "\"serialNumber\":\"must not be blank\"," +
                        "\"city\":\"must not be blank\"," +
                        "\"year\":\"must not be blank\"," +
                        "\"fuel\":\"must not be blank\"," +
                        "\"model\":\"must not be blank\"," +
                        "\"seats\":\"must not be null\"," +
                        "\"pricePerDay\":\"must not be null\"," +
                        "\"carClass\":\"must not be blank\"}"));
    }

    @Test
    public void letTheCarWorkEmptyFieldsWithClickingNegativeTest(){
        letTheCarWorkPage.clickAllFieldsWithoutTyping();
        softAssert.assertTrue(letTheCarWorkPage.isTextInErrorPresent("Wrong address"), "validate message: Wrong address");
        softAssert.assertTrue(letTheCarWorkPage.isTextInErrorPresent("Make is required"), "validate message:  Make is required ");
        softAssert.assertTrue(letTheCarWorkPage.isTextInErrorPresent("Model is required"), "validate message:  Model is required ");
        softAssert.assertTrue(letTheCarWorkPage.isTextInErrorPresent("Year required"), "validate message:  Year required ");
        softAssert.assertTrue(letTheCarWorkPage.isTextInErrorPresent("Fuel is required"), "validate message:  Fuel is required ");
        softAssert.assertTrue(letTheCarWorkPage.isTextInErrorPresent("Number of seats is required"), "validate message:  Number of seats is required ");
        softAssert.assertTrue(letTheCarWorkPage.isTextInErrorPresent("Car class is required"), "validate message:  Car class is required ");
        softAssert.assertTrue(letTheCarWorkPage.isTextInErrorPresent("Car registration number is required"), "validate message: Car registration number is required");
        softAssert.assertTrue(letTheCarWorkPage.isTextInErrorPresent("Price is required"), "validate message:  Price is required ");
        softAssert.assertAll();
        letTheCarWorkPage.clickBtnSubmitWithJS();
    }

    @Test(dataProvider = "requiredFieldsDataProvider", dataProviderClass = CarDataProvider.class)
    public void letTheCarWorkNegativeRequiredFieldEmptyTest(String fieldName) {
        Car car = positiveCar();
        switch (fieldName) {
            case "location" -> car.setLocation(null);
            case "manufacture" -> car.setManufacture(null);
            case "model" -> car.setModel(null);
            case "year" -> car.setYear(null);
            case "fuel" -> car.setFuel(null);
            case "seats" -> car.setSeats(null);
            case "carClass" -> car.setCarClass(null);
            case "carRegistrationNumber" -> car.setCarRegistrationNumber(null);
        }
        letTheCarWorkPage.typeCarForm(car);
        letTheCarWorkPage.clickBtnSubmit();
        Assert.assertFalse(letTheCarWorkPage.isBtnSubmitIsEnabled());
    }

    @Test(dataProvider = "wrongYearDataProvider", dataProviderClass = CarDataProvider.class)
    public void letTheCarWorkWrongYearNegativeTest(String scenario){
        Car car = positiveCar();

        String invalidYear = switch (scenario) {
            case "belowMinYear" -> "-1";
            case "aboveMaxYear" -> String.valueOf(LocalDate.now().getYear() + 1);
            default -> throw new IllegalArgumentException("Unknown scenario: " + scenario);
        };
        car.setYear(invalidYear);

        letTheCarWorkPage.typeCarForm(car);
        letTheCarWorkPage.clickBtnSubmit();
        softAssert.assertTrue(letTheCarWorkPage.isTextInErrorPresent("Wrong year"), "validate message:  Wrong year");
        softAssert.assertFalse(letTheCarWorkPage.isBtnSubmitIsEnabled(), "is submit btn enabled");
        softAssert.assertAll();
    }

    @Test
    public void addNewCarNegativeWrongYearNotDigitTest() {
        Car car = positiveCar();
        car.setYear("a");
        System.out.println(car);
        letTheCarWorkPage.typeCarForm(car);
        Assert.assertTrue(letTheCarWorkPage.isTextInErrorPresent("Year required"));
    }


}
