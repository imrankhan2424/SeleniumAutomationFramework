package PageObjects;

import UtilComponent.AbstractComponents;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractComponents {
    WebDriver driver;
    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="[placeholder='Select Country']")
    WebElement selectCountry;

    @FindBy(css=".ta-item:nth-child(3)")
    WebElement selectoption;

    @FindBy(css="a.action__submit")
    WebElement placeOrder;

    public void searchforCountry(String country){
        waitTillElementisDisplayedandEnter(selectCountry,5,country);
        waitTillElementisDisplayed(selectoption,5);
        selectoption.click();
    }

    public ConfirmationPage placeOrder(){
        JavascriptExecutor jse=(JavascriptExecutor) driver;
        waitTillElementisDisplayed(placeOrder,2);
        jse.executeScript("arguments[0].click()",placeOrder);
        return new ConfirmationPage(driver);
    }
}
