package PageObjects;

import ScriptUtils.CommonSteps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends CommonSteps {
    WebDriver driver;
    public ProductCatalogue(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css=".card-body")
    List<WebElement> productNames;

    @FindBy(css="button[routerlink*='cart']")
    WebElement cart;

    @FindBy(css=".card-body")
    WebElement product;

    @FindBy(css=".ngx-spinner-overlay")
    WebElement SpinningLoader;

    @FindBy(css=".ng-animating")
    WebElement ToastMessage;

    @FindBy(css="button[routerlink*='cart']")
    WebElement cartIcon;

    public List<WebElement> getProductList(){
        return productNames;
    }

    public void addProduct(String productName){
        waitTillElementisDisplayed(product,5);
        WebElement product=getProductList().stream().filter(s-> s.findElement(By.cssSelector("b")).getText().equals(productName))
                .findFirst().orElse(null);
        product.findElement(By.cssSelector("button:last-of-type")).click();
    }

    public CartPage goToCart(){
        waitTillInvisibilityofElement(SpinningLoader,5);
        waitTillInvisibilityofElement(ToastMessage,5);
        waitTillElementisDisplayed(cartIcon,5);
        waitTillElementisClickable(cartIcon,5);
        cart.click();
        return new CartPage(driver);
    }
}
