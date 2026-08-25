package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {
    static WebDriver driver;
    Logger logger = LoggerFactory.getLogger(BasePage.class);

    public void setDriver(WebDriver wd){
        driver = wd;
    }

    // !!! error red msges should be looked for when Chrome is controlled by aut software !!!
    @FindBy(xpath = "//div[@class='error']")
    List<WebElement> listErrors;

    public boolean isTextInErrorPresent(String text){
        if (listErrors == null || listErrors.isEmpty())
            return false;
        for (WebElement element: listErrors){
            if (element.getText().contains(text))
                return true;
        }
        return false;
    }


//    1st option
    public boolean isTextInElementPresent(WebElement element, String text) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions
                            .textToBePresentInElement(element, text));
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("created exeption");
            logger.error("created exeption", e);
        }
        return false;
    }

  //   2nd option
    public boolean isElementDisplayed(WebElement element){
        return element.isDisplayed();
    }


    public void clickWait(WebElement element){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
