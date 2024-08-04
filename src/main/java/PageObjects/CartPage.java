package PageObjects;

import ScriptUtils.CommonSteps;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends CommonSteps {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css=".cartSection h3")
    List<WebElement> cartProducts;

    @FindBy(css=".subtotal button")
    WebElement checkout;

    public boolean verifyProductinCart(String productName){
        waitTillElementsareDisplayed(cartProducts,5);
        return cartProducts.stream().anyMatch(s->s.getText().equals(productName));

    }

    public CheckoutPage proceedToCheckout(){
        waitTillElementisDisplayed(checkout,5);
//        Actions mouse=new Actions(driver);
//        mouse.moveToElement(checkout).click().build().perform();
        JavascriptExecutor jse=(JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click()",checkout);
        return new CheckoutPage(driver);
    }
}
