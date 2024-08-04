package ScriptUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CommonSteps {
    WebDriver driver;
    public CommonSteps(WebDriver driver){
        this.driver=driver;
    }

    public void waitTillElementisDisplayed(By elemBy,int seconds){
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(Long.valueOf(seconds)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(elemBy));

    }

    public void waitTillElementisDisplayed(WebElement elemBy,int seconds){
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(Long.valueOf(seconds)));
        wait.until(ExpectedConditions.visibilityOf(elemBy));

    }

    public void waitTillElementsareDisplayed(List<WebElement> elemBy, int seconds){
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(Long.valueOf(seconds)));
        wait.until(ExpectedConditions.visibilityOfAllElements(elemBy));

    }

    public void waitTillElementisDisplayedandEnter(WebElement elemBy, int seconds, String enterValue){
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(Long.valueOf(seconds)));
        WebElement element=wait.until(ExpectedConditions.visibilityOf(elemBy));
        element.sendKeys(enterValue);
    }



    public void waitTillElementisClickable(WebElement elemBy,int seconds){
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(Long.valueOf(seconds)));
        wait.until(ExpectedConditions.elementToBeClickable(elemBy));

    }

    public void waitTillInvisibilityofElement(WebElement elemBy,int seconds){
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(Long.valueOf(seconds)));
        wait.until(ExpectedConditions.invisibilityOf(elemBy));

    }


}
