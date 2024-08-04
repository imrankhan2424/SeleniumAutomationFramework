package PageObjects;

import ScriptUtils.CommonSteps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrdersPage extends CommonSteps {
    WebDriver driver;

    public OrdersPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="tr td:nth-child(2)")
    List<WebElement> orderProducts;

    public boolean verifyProductinOrders(String productName){
        waitTillElementsareDisplayed(orderProducts,5);
        return orderProducts.stream().anyMatch(s->s.getText().equals(productName));
    }



}
