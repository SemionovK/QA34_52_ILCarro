package pages;

import dto.UserLombok;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class RegistrationPage extends BasePage {
    public RegistrationPage(WebDriver driver){
        PageFactory.initElements(new
                AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(css = "input[id='name']")
    WebElement inputName;
    @FindBy(id = "lastName")
    WebElement inputLastName;
    @FindBy(id = "email")
    WebElement inputEmail;
    @FindBy(id = "password")
    WebElement inputPassword;
//    @FindBy(xpath = "//label[contains(., 'I agree to the')]")
//    WebElement checkboxMy;
    @FindBy(xpath = "//label[@for='terms-of-use']")
    WebElement checkbox;
    @FindBy(css = "button[type='submit']")
    WebElement btnYalla;
    @FindBy(xpath = "//*[text()='You are logged in success']")
    WebElement loggedInMessage;

    public void typeRegistrationForm(UserLombok user){
        inputName.sendKeys(user.getFirstName());
        inputLastName.sendKeys(user.getLastName());
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());

    }

    public void clickCheckboxByMyScreenResolution(){
        new Actions(driver)
                .moveToElement(checkbox, -80, 0)
                .click()
                .perform();
    }

//    public void clickCheckbox(){
//        int x = checkbox.getSize().getWidth();
//        int y = checkbox.getSize().getHeight();
//        //System.out.println(x + "X" + y);  246X38
//        Actions actions = new Actions(driver);
//        actions.moveToElement(checkbox, -x/2 + 25, -y/2 +25)
//                .click()
//                .perform();
//    }

    public void clickBtnYalla(){
        btnYalla.click();
    }

    public boolean isPopUpLoginDisplayed(){
        return isElementDisplayed(loggedInMessage);
    }


}
