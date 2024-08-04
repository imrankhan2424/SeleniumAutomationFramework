import PageObjects.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EcommerceTest {
    @Test
    public void EndtoEndFlow() {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver=new FirefoxDriver();
        driver.manage().window().maximize();

        String productName="ZARA COAT 3";

        LandingPage landingPage=new LandingPage(driver);
        landingPage.goTo("https://rahulshettyacademy.com/client/");

        ProductCatalogue productCatalogue=landingPage.login("yobeje7646@eixdeal.com","Test@1234");
        productCatalogue.addProduct(productName);

        CartPage cartPage=productCatalogue.goToCart();
        Assert.assertTrue(cartPage.verifyProductinCart(productName));

        CheckoutPage checkoutPage=cartPage.proceedToCheckout();
        checkoutPage.searchforCountry("India");

        ConfirmationPage confirmationPage=checkoutPage.placeOrder();
        Assert.assertTrue(confirmationPage.getMessage().equalsIgnoreCase("Thankyou for the order."));

        driver.quit();
    }
}