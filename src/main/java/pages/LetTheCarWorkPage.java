package pages;

import dto.Car;
import utils.enums.Fuel;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
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
    @FindBy(id = "fuel")
    WebElement inputFuel;
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
    WebElement inputImage;
    @FindBy(xpath = "//h1[text()='Car adding failed']")
    WebElement msgCarAddingFailed;
    @FindBy(css = "h2.message")
    WebElement msgFieldMustNotBeBlank;

    public boolean ValidateTextInMsgAddingCarFailed(String text){
        return isTextInElementPresent(msgCarAddingFailed, text);
    }

    public boolean validateTextInMsgFieldMustNotBeBlank(String text){
        return isTextInElementPresent(msgFieldMustNotBeBlank, text);
    }

    public void clickBtnSubmitWithJS(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"button[type='submit']\")" +
                ".removeAttribute('disabled')");
        clickWait(btnSubmit);
    }

    public void typeCarForm(Car car) {
        if (car.getLocation() != null) {inputLocation.sendKeys(car.getLocation());}
        if (car.getManufacture() != null) {inputManufacture.sendKeys(car.getManufacture());}
        if (car.getModel() != null) {inputModel.sendKeys(car.getModel());}
        if (car.getYear() != null) {inputYear.sendKeys(car.getYear());}
        if (car.getFuel() != null) {choseFuel(car.getFuel());}
        if (car.getSeats() != null) {
            inputSeats.sendKeys(String.valueOf(car.getSeats()));
            //inputSeats.sendKeys(car.getSeats().toString());
            //inputSeats.sendKeys(car.getSeats() + "");
            //inputSeats.sendKeys(Integer.toString(car.getSeats()));
        }
        if (car.getCarClass() != null) {inputClass.sendKeys(car.getCarClass());}
        if (car.getCarRegistrationNumber() != null) {inputCarRegistrationNumber.sendKeys(car.getCarRegistrationNumber());}
        if (car.getPrice() != 0) {inputPrice.sendKeys(String.valueOf(car.getPrice()));}
        if (car.getAbout() != null) {inputAbout.sendKeys(car.getAbout());}
       // inputUploadPhoto.sendKeys(car.getInputUploadPhoto());  my option
    }

    private void choseFuel(Fuel fuel){
        driver.findElement(By.cssSelector(fuel.getLocator())).click();
    }

    public void downloadImage(String filename){
        inputUploadPhoto.sendKeys(new File("src/test/resources/" + filename).getAbsolutePath());
    }

    public void waitForPhotoUpload() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(inputImage));
    }

    public void clickAllFieldsWithoutTyping() {
        inputLocation.click();
        inputManufacture.click();
        inputModel.click();
        inputYear.click();
        inputFuel.click();
        inputSeats.click();
        inputClass.click();
        inputCarRegistrationNumber.click();
        inputPrice.click();
        inputAbout.click();
    }

    public boolean  isBtnSubmitIsEnabled(){
        return btnSubmit.isEnabled();
    }

    public void clickBtnSubmit() {
        btnSubmit.click();
    }
}
