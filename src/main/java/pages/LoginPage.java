package pages;

import dto.UserLombok;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver){
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//input[@id='email']")
    WebElement inputEmail;
    @FindBy(css = "input[id='password']")
    WebElement inputPassword;
    @FindBy(css = "button[type='submit']")
    WebElement btnYalla;
    @FindBy(xpath = "//*[text()='Logged in success']")
    WebElement loggedInMessage;
    @FindBy(xpath = "//button[text()='Ok']")
    WebElement btnOk;
    @FindBy(css = "h2.message")
    WebElement errorMessage;

    public boolean validateTextInMessageLoginFailed(String text){
        return isTextInElementPresent(errorMessage, text) ;
    }

//    public String getErrorMessage() {
//        return errorMessage.getText();
//    }

    public boolean validateTextInMessageLoginSuccess(String text){
        return isTextInElementPresent(loggedInMessage, text);
    }

    public String getLoggedInMessage(){
        return loggedInMessage.getText();
    }

    public void clickOk(){
        btnOk.click();
    }

    public void typeLoginForm(UserLombok user){
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
    }

    public void clickBtnYalla(){
        btnYalla.click();
    }

}
