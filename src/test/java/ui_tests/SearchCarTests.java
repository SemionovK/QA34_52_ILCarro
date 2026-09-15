package ui_tests;

import data_providers.CarDataProvider;
import data_providers.NotDatesDataProvider;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;

import java.time.LocalDate;

public class SearchCarTests extends AppManager {
    HomePage homePage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod(alwaysRun = true)
    public void openHomePage() {
        homePage = new HomePage(getDriver());
    }

    @Test
    public void searchCarPositiveTest() {
        String city = "Haifa";
        LocalDate startDate = LocalDate.now()
                .plusDays(2);
        LocalDate endDate = LocalDate.now()
                .plusDays(8);
        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnYallaWithJS();
        Assert.assertTrue(homePage.isUrlContainsText("results"));
    }

    @Test
    public void searchCarWithCalendarPositiveTest() {
        String city = "Haifa";
        LocalDate startDate = LocalDate.now()
                .plusDays(2);
        LocalDate endDate = LocalDate.now()
                .plusDays(8);
        homePage.typeSearchFormWithCalendar(city, startDate, endDate);
        homePage.clickBtnYallaWithJS();
        Assert.assertTrue(homePage.isUrlContainsText("results"));
    }

    @Test
    public void searchCarForLessThanADayNegativeTest(){
        String city = "Haifa";
        LocalDate startDate = LocalDate.now().plusDays(2);
        LocalDate endDate = LocalDate.now().plusDays(2);
        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnYallaWithJS();
        Assert.assertTrue(homePage.isTextInErrorBookLessThanAdayPresent("You can't book car for less than a day"));
    }

    @Test(dataProvider = "noDatesDataProvider", dataProviderClass = NotDatesDataProvider.class)
    public void searchCarNotDatesNegativeTest(String scenario ,String fieldDates){
        String city = "Haifa";
        homePage.typeSearchFormNotDates(city, fieldDates);
        homePage.clickBtnYallaWithJS();
        Assert.assertTrue(homePage.isTextInErrorDatesRequiredPresent("Dates are required"));
        }


    @Test
    public void searchCarNegativeMoreOneYearTest() {
        String city = "Haifa";
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now()
                .plusYears(1).plusDays(1);
        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnYallaWithJS();
        Assert.assertTrue(homePage.isTextInErrorPresent
                ("You can't pick date after one year"));
    }

    @Test
    public void searchCarNegativeStartDateAfterEndDateTest() {
        String city = "Haifa";
        LocalDate startDate = LocalDate.now().plusDays(10);
        LocalDate endDate = LocalDate.now()
                .plusDays(7);
        homePage.typeSearchForm(city, startDate, endDate);
        homePage.clickBtnYallaWithJS();
        softAssert.assertTrue(homePage.isTextInErrorPresent
                ("Second date must be after first date"));
        softAssert.assertTrue(homePage.isTextInErrorPresent
                ("You can't book car for less than a day"));
        softAssert.assertAll();
    }
}
