package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.time.LocalDate;

import static utils.PropertiesReader.*;

public class HomePage extends BasePage{
    public HomePage(WebDriver driver){
        setDriver(driver);
        //driver.get("https://ilcarro.web.app/search");
        driver.get(getProperty("base.properties", "baseUrl"));
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(css = "a[href='/login?url=%2Fsearch']")
    WebElement btnLogin;
    @FindBy(css = "a[href='/registration?url=%2Fsearch']")
    WebElement btnSignUp;
    @FindBy(css = "a[href='/let-car-work']")
    WebElement btnLetTheCarWork;
    @FindBy(id = "city")
    WebElement inputCity;
    @FindBy(id = "dates")
    WebElement inputDates;
    @FindBy(xpath = "//button[text()='Y’alla!']")
    WebElement btnYalla;
    @FindBy(xpath = "//div[contains(text(), \"You can't book car for less than a day\")]")
    WebElement errLessThanADay;
    @FindBy(xpath = "//div[contains(text(), \"Dates are required\")]")
    WebElement errDatesRequired;
    @FindBy(css = ".dismissButton")
    WebElement btnGoogleOk;


    public void clickBtnLogin(){
        btnLogin.click();
    }

    public void clickBtnSignUp(){btnSignUp.click();}

    public void clickBtnLetTheCarWork() {btnLetTheCarWork.click();}

    public void typeSearchForm(String city,
                               LocalDate startDate, LocalDate endDate) {
        inputCity.sendKeys(city);
        closeGooglePopupIfPresent(btnGoogleOk);

        System.out.println(startDate);
        System.out.println(endDate);
        // 2026-09-04  9/4/2026 - 9/10/2026
        System.out.println(startDate.getMonthValue());
        System.out.println(startDate.getDayOfMonth());
        String dates =
                startDate.getMonthValue() + "/"
                        + startDate.getDayOfMonth() + "/"
                        + startDate.getYear() + " - "
                        + endDate.getMonthValue() + "/"
                        + endDate.getDayOfMonth() + "/"
                        + endDate.getYear();
        System.out.println(dates);
        inputDates.sendKeys(dates);
    }

    public void typeSearchFormNotDates(String city, String text){
        if (city != null)
            inputCity.sendKeys(city);
        closeGooglePopupIfPresent(btnGoogleOk);
        if (text != null)
            inputDates.sendKeys(text);
    }

    public void clickBtnYallaWithJS(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"button[type='submit']\")" +
                ".removeAttribute('disabled')");
        clickWait(btnYalla);
    }

    public void clickBtnYalla(){
        clickWait(btnYalla);
    }

    public boolean isTextInErrorBookLessThanAdayPresent(String text){
        return isTextInElementPresent(errLessThanADay, text);
    }

    public boolean isTextInErrorDatesRequiredPresent(String text){
        return isTextInElementPresent(errDatesRequired, text);
    }

    public void clickBtnGoogleOk(){
        btnGoogleOk.click();
    }

}