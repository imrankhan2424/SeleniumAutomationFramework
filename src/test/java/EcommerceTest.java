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
        landingPage.login("yobeje7646@eixdeal.com","Test@1234");


        ProductCatalogue productCatalogue=new ProductCatalogue(driver);
        productCatalogue.addProduct(productName);
        productCatalogue.goToCart();

        CartPage cartPage=new CartPage(driver);
        Assert.assertTrue(cartPage.verifyProductinCart(productName));
        cartPage.proceedToCheckout();

        CheckoutPage checkoutPage=new CheckoutPage(driver);
        checkoutPage.searchforCountry("India");
        checkoutPage.placeOrder();

        ConfirmationPage confirmationPage=new ConfirmationPage(driver);
        Assert.assertTrue(confirmationPage.getMessage().equalsIgnoreCase("Thankyou for the order."));

        driver.quit();
    }
}