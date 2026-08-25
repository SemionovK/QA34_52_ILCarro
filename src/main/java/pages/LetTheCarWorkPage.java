package pages;

import dto.Car;
import enums.Fuel;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LetTheCarWorkPage extends BasePage{
    public LetTheCarWorkPage(WebDriver driver){
        PageFactory.initElements(new
                AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(id = "pickUpPlace")
    WebElement inputLocation;
    @FindBy(id = "make")
    WebElement inputManufacture;
    @FindBy(id = "model")
    WebElement inputModel;
    @FindBy(id = "year")
    WebElement inputYear;
    @FindBy(id = "seats")
    WebElement inputSeats;
    @FindBy(id = "class")
    WebElement inputClass;
    @FindBy(id = "serialNumber")
    WebElement inputCarRegistrationNumber;
    @FindBy(id = "price")
    WebElement inputPrice;
    @FindBy(id = "about")
    WebElement inputAbout;
    @FindBy(css = "input[type='file']")
    WebElement inputUploadPhoto;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnSubmit;
    @FindBy(css = "div.mat-chip-list-wrapper mat-chip")
    WebElement uploadedPhoto;
    @FindBy(xpath = "//h1[text()='Car adding failed']")
    WebElement msgCarAddingFailed;

    public boolean ValidateTextInMsgAddingCarFailed(String text){
        return isTextInElementPresent(msgCarAddingFailed, text);
    }

    public void clickBtnSubmitWithJS(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"button[type='submit']\")" +
                ".removeAttribute('disabled')");
        clickWait(btnSubmit);
    }

    public void typeCarForm(Car car) {
        inputLocation.sendKeys(car.getLocation());
        inputManufacture.sendKeys(car.getManufacture());
        inputModel.sendKeys(car.getModel());
        inputYear.sendKeys(String.valueOf(car.getYear()));
        choseFuel(car.getFuel());
        inputSeats.sendKeys(String.valueOf(car.getSeats()));
        inputClass.sendKeys(car.getCarClass());
        inputCarRegistrationNumber.sendKeys(car.getCarRegistrationNumber());
        inputPrice.sendKeys(String.valueOf(car.getPrice()));
        inputAbout.sendKeys(car.getAbout());
        inputUploadPhoto.sendKeys(car.getInputUploadPhoto());

    }

    private void choseFuel(Fuel fuel){
        driver.findElement(By.cssSelector(fuel.getLocator())).click();
    }

    public void waitForPhotoUpload() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(uploadedPhoto));
    }
}
